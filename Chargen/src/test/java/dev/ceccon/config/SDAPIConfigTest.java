package dev.ceccon.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SDAPIConfigTest {

    @Test
    void fullUrlDefaultsToStableDiffusionDefaults() {
        String expectedFullUrl = "http://localhost:7860/sdapi/v1/txt2img";

        SDAPIConfig config = new SDAPIConfig();

        String fullUrl = config.getFullUrl();

        assertEquals(expectedFullUrl, fullUrl);
    }

    @Test
    void parameterizedPortIsUsedOnFullUrl() {
        String port = "7861";
        String expectedFullUrl = "http://localhost:" + port + "/sdapi/v1/txt2img";

        SDAPIConfig config = new SDAPIConfig();
        config.setPort(port);

        String fullUrl = config.getFullUrl();

        assertEquals(expectedFullUrl, fullUrl);
    }

    @Test
    void getAndSetPort() {
        String port = "7861";

        SDAPIConfig config = new SDAPIConfig();
        config.setPort(port);

        String portOnConfig = config.getPort();

        assertEquals(port, portOnConfig);
    }

}