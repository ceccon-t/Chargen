package dev.ceccon.config;

public class LLMAPIConfig {

    private String protocol = "http";
    private String host = "localhost";
    private String port = "8080";
    private String endpoint = "v1/chat/completions";

    public String getFullUrl() {
        return protocol + "://" + host + ":" + port + "/" + endpoint;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
