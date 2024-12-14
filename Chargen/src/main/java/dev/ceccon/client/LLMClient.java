package dev.ceccon.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ceccon.client.dtos.StreamingResponseEventDTO;
import dev.ceccon.config.LLMAPIConfig;
import dev.ceccon.conversation.Chat;
import dev.ceccon.conversation.Message;
import dev.ceccon.client.dtos.LLMPromptDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Consumer;

public class LLMClient {

    private LLMAPIConfig apiConfig;

    public LLMClient(LLMAPIConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public Message getBioStreaming(Chat chat, Consumer<String> tokenConsumer, Consumer<Void> onFinish) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        LLMPromptDTO promptDTO = LLMPromptDTO.build(chat, apiConfig);
        promptDTO.setStream(true);
        String body = mapper.writeValueAsString(promptDTO);

        LLMConnection llmConnection = LLMConnection.forUrl(apiConfig.getFullUrl());
        llmConnection.send(body);

        String rawResponse = "";

        try (BufferedReader in = llmConnection.getBufferedReader()) {
            String inputLine;
            StringBuilder responseBuilder = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                inputLine = LLMSanitizer.sanitizeLLMSpecialTokens(inputLine);
                if (inputLine.isEmpty()) continue;
                StreamingResponseEventDTO eventDTO = mapper.readValue(inputLine.substring(6), StreamingResponseEventDTO.class);
                if (eventDTO.isFinalEvent()) break;
                String eventData = eventDTO.getBestChoice();

                tokenConsumer.accept(eventData);
                responseBuilder.append(eventData);
            }
            rawResponse = responseBuilder.toString();
        }

        llmConnection.close();
        onFinish.accept(null);

        return new Message("assistant", rawResponse);
    }
}
