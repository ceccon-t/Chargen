package dev.ceccon.config;

public class SDAPIConfig {

    private String protocol = "http";
    private String host = "localhost";
    private String port = "7860";
    private String endpoint = "sdapi/v1/txt2img";

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
