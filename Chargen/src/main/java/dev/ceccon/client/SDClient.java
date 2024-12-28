package dev.ceccon.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ceccon.client.dtos.SDPromptDTO;
import dev.ceccon.client.dtos.SDResponseDTO;
import dev.ceccon.config.SDAPIConfig;

import java.io.BufferedReader;
import java.io.IOException;

public class SDClient {

    private SDAPIConfig apiConfig;

    public SDClient(SDAPIConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public SDResponseDTO getAvatar(SDPromptDTO promptDTO) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String body = mapper.writeValueAsString(promptDTO);

        LLMConnection apiConnection = LLMConnection.forUrl(apiConfig.getFullUrl());
        apiConnection.send(body);

        String rawResponse = "";

        try (BufferedReader in = apiConnection.getBufferedReader()) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            rawResponse = response.toString();
        }

        apiConnection.close();

        return mapper.readValue(rawResponse, SDResponseDTO.class);
    }

}
