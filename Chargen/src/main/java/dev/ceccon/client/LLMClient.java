package dev.ceccon.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ceccon.client.dtos.StreamingResponseEventDTO;
import dev.ceccon.config.LLMAPIConfig;
import dev.ceccon.conversation.Chat;
import dev.ceccon.conversation.Message;
import dev.ceccon.client.dtos.LLMPromptDTO;
import dev.ceccon.client.dtos.LLMResponseDTO;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;
import java.util.function.Function;

public class LLMClient {

    private LLMAPIConfig apiConfig;

    public LLMClient(LLMAPIConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public Message sendPrompt(Chat chat) throws IOException {
        String urlString = apiConfig.getFullUrl();
        LLMPromptDTO promptDTO = LLMPromptDTO.build(chat, apiConfig);

        ObjectMapper mapper = new ObjectMapper();

        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        String body = mapper.writeValueAsString(promptDTO);

        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.writeBytes(body);
            wr.flush();
        }

        String rawResponse = "";

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            rawResponse = response.toString();
        }

        connection.disconnect();

        LLMResponseDTO responseDTO = mapper.readValue(rawResponse, LLMResponseDTO.class);

        return new Message(responseDTO.getChoices().get(0).message().role(),
                responseDTO.getChoices().get(0).message().content());

    }

    public String getBioStreaming(Chat chat, Consumer<String> tokenConsumer, Function<Void, Void> onFinish) throws IOException {
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
        onFinish.apply(null);

        return rawResponse;
    }
}
