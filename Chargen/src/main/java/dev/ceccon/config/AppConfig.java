package dev.ceccon.config;

public class AppConfig {

    private LLMAPIConfig llmApiConfig;
    private SDAPIConfig sdApiConfig;

    public AppConfig() {
        llmApiConfig = new LLMAPIConfig();
        sdApiConfig = new SDAPIConfig();
    }

    public LLMAPIConfig getLlmApiConfig() {
        return llmApiConfig;
    }

    public void setLlmApiConfig(LLMAPIConfig llmApiConfig) {
        this.llmApiConfig = llmApiConfig;
    }

    public SDAPIConfig getSdApiConfig() {
        return sdApiConfig;
    }

    public void setSdApiConfig(SDAPIConfig sdApiConfig) {
        this.sdApiConfig = sdApiConfig;
    }
}
