package dev.ceccon;

import dev.ceccon.config.AppConfig;
import dev.ceccon.config.LLMAPIConfig;
import dev.ceccon.config.SDAPIConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChargenTest {

    @Test
    void cliOptionLLMPortWithoutValueThrowsException() {
        String[] args = new String[]{"-lp"};

        assertThrows(IllegalArgumentException.class, () -> {
            Chargen.parseArguments(args, new AppConfig());
        });
    }

    @Test
    void cliOptionLLMPortWithNonNumericValueThrowsException() {
        String[] args = new String[]{"-lp", "port"};

        assertThrows(IllegalArgumentException.class, () -> {
            Chargen.parseArguments(args, new AppConfig());
        });
    }

    @Test
    void cliOptionLLMPortWithNumericValueSetsPortParameterOnLLMApiConfig() {
        String port = "8081";
        String[] args = new String[]{"-lp", port};

        LLMAPIConfig llmapiConfig = new LLMAPIConfig();
        AppConfig appConfig = new AppConfig();
        appConfig.setLlmApiConfig(llmapiConfig);

        Chargen.parseArguments(args, appConfig);

        String portOnLLMApiConfig = llmapiConfig.getPort();

        assertEquals(port, portOnLLMApiConfig);
    }

    @Test
    void cliOptionSDPortWithoutValueThrowsException() {
        String[] args = new String[]{"-sp"};

        assertThrows(IllegalArgumentException.class, () -> {
            Chargen.parseArguments(args, new AppConfig());
        });
    }

    @Test
    void cliOptionSDPortWithNonNumericValueThrowsException() {
        String[] args = new String[]{"-sp", "port"};

        assertThrows(IllegalArgumentException.class, () -> {
            Chargen.parseArguments(args, new AppConfig());
        });
    }

    @Test
    void cliOptionSDPortWithNumericValueSetsPortParameterOnSDApiConfig() {
        String port = "7891";
        String[] args = new String[]{"-sp", port};

        SDAPIConfig sdapiConfig = new SDAPIConfig();
        AppConfig appConfig = new AppConfig();
        appConfig.setSdApiConfig(sdapiConfig);

        Chargen.parseArguments(args, appConfig);

        String portOnSDApiConfig = sdapiConfig.getPort();

        assertEquals(port, portOnSDApiConfig);
    }

    @Test
    void cliOptionLanguageModelWithoutValueThrowsException() {
        String[] args = new String[]{"-lm"};

        assertThrows(IllegalArgumentException.class, () -> {
            Chargen.parseArguments(args, new AppConfig());
        });
    }

    @Test
    void cliOptionLanguageModelWithValueSetsModelOnLLMApiConfig() {
        String model = "llama3";
        String[] args = new String[]{"-lm", model};

        LLMAPIConfig llmapiConfig = new LLMAPIConfig();
        AppConfig appConfig = new AppConfig();
        appConfig.setLlmApiConfig(llmapiConfig);

        Chargen.parseArguments(args, appConfig);

        String modelOnLLMApiConfig = llmapiConfig.getModel();

        assertEquals(model, modelOnLLMApiConfig);
    }
}