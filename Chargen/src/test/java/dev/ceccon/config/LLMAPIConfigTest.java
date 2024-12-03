package dev.ceccon.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LLMAPIConfigTest {

    @Test
    void fullUrlDefaultsToLlamafileDefaults() {
        String expectedFullUrl = "http://localhost:8080/v1/chat/completions";

        LLMAPIConfig config = new LLMAPIConfig();

        String fullUrl = config.getFullUrl();

        assertEquals(expectedFullUrl, fullUrl);
    }

    @Test
    void parameterizedPortIsUsedOnFullUrl() {
        String port = "8081";
        String expectedFullUrl = "http://localhost:" + port + "/v1/chat/completions";

        LLMAPIConfig config = new LLMAPIConfig();
        config.setPort(port);

        String fullUrl = config.getFullUrl();

        assertEquals(expectedFullUrl, fullUrl);
    }

    @Test
    void getAndSetPort() {
        String port = "8081";

        LLMAPIConfig config = new LLMAPIConfig();
        config.setPort(port);

        String portOnConfig = config.getPort();

        assertEquals(port, portOnConfig);
    }

}