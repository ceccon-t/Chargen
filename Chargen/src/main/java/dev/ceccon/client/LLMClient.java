package dev.ceccon.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ceccon.dtos.LLMPromptDTO;
import dev.ceccon.dtos.LLMResponseDTO;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LLMClient {

    public static LLMResponseDTO sendPrompt(String urlString, LLMPromptDTO promptDTO) throws IOException {
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

        return mapper.readValue(rawResponse, LLMResponseDTO.class);

    }
}
