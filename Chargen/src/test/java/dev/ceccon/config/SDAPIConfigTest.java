package dev.ceccon.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SDAPIConfigTest {

    @Test
    void getAndSetPort() {
        String port = "7891";

        SDAPIConfig config = new SDAPIConfig();
        config.setPort(port);

        String portOnConfig = config.getPort();

        assertEquals(port, portOnConfig);
    }

}