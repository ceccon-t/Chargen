package dev.ceccon.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest {

    @Test
    void getAndSetLLMAPIConfig() {
        LLMAPIConfig llmapiConfig = new LLMAPIConfig();

        AppConfig config = new AppConfig();
        config.setLlmApiConfig(llmapiConfig);

        LLMAPIConfig llmapiConfigOnConfig = config.getLlmApiConfig();

        assertEquals(llmapiConfig, llmapiConfigOnConfig);
    }

    @Test
    void getAndSetSDAPIConfig() {
        SDAPIConfig sdapiConfig = new SDAPIConfig();

        AppConfig config = new AppConfig();
        config.setSdApiConfig(sdapiConfig);

        SDAPIConfig sdapiConfigOnConfig = config.getSdApiConfig();

        assertEquals(sdapiConfig, sdapiConfigOnConfig);
    }

}