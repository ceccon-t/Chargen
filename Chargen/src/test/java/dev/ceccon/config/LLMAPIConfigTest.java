package dev.ceccon.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LLMAPIConfigTest {

    @Test
    void getAndSetPort() {
        String port = "8081";

        LLMAPIConfig config = new LLMAPIConfig();
        config.setPort(port);

        String portOnConfig = config.getPort();

        assertEquals(port, portOnConfig);
    }

}