package dev.ceccon;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import dev.ceccon.config.AppConfig;
import dev.ceccon.config.LLMAPIConfig;
import dev.ceccon.config.SDAPIConfig;
import dev.ceccon.gui.MainView;
import dev.ceccon.storage.LocalFileStorage;

import javax.swing.*;

public class Chargen {
    public static void main( String[] args ) {
        AppConfig appConfig = new AppConfig();
        parseArguments(args, appConfig);

        LocalFileStorage storage = new LocalFileStorage();

        printBanner();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainView(appConfig, storage);
            }
        });
    }

    public static void parseArguments(String[] args, AppConfig appConfig) {
        LLMAPIConfig llmapiConfig = appConfig.getLlmApiConfig();
        SDAPIConfig sdapiConfig = appConfig.getSdApiConfig();

        ChargenArgs chargenArgs = new ChargenArgs();
        JCommander cmd = JCommander.newBuilder()
                .addObject(chargenArgs)
                .build();

        try {
            cmd.parse(args);
        } catch (ParameterException e) {
            throw new IllegalArgumentException("Could not parse parameters.");
        }

        if (chargenArgs.hasLlmPort()) {
            Integer llmPort = chargenArgs.getLlmPort();
            llmapiConfig.setPort(llmPort.toString());
        }

        if (chargenArgs.hasSdPort()) {
            Integer sdPort = chargenArgs.getSdPort();
            sdapiConfig.setPort(sdPort.toString());
        }

        if (chargenArgs.hasLlmModel()) {
            String model = chargenArgs.getLlmModel();
            llmapiConfig.setModel(model);
        }

        if (chargenArgs.hasLlmTemperature()) {
            double temperature = chargenArgs.getLlmTemperature();
            llmapiConfig.setTemperature(temperature);
        }

    }

    private static void printBanner() {
        System.out.println("==================================");
        System.out.println("======  Welcome to Chargen  ======");
        System.out.println("==================================");
    }

    static class ChargenArgs {
        @Parameter(
                names = {"-lp", "--llmport"},
                description = "LLM server port",
                required = false,
                arity = 1
        )
        private Integer llmPort;

        @Parameter(
                names = {"-sp", "--sdport"},
                description = "Stable Diffusion server port",
                required = false,
                arity = 1
        )
        private Integer sdPort;

        @Parameter(
                names = {"-lm", "--llmmodel"},
                description = "LLM model to be used (when available)",
                required = false,
                arity = 1
        )
        private String llmModel;

        @Parameter(
                names = {"-lt", "--llmtemperature"},
                description = "Temperature to be used when generating text",
                required = false,
                arity = 1
        )
        private Double llmTemperature;

        public Integer getLlmPort() {
            return llmPort;
        }

        public boolean hasLlmPort() {
            return llmPort != null;
        }

        public Integer getSdPort() {
            return sdPort;
        }

        public boolean hasSdPort() {
            return sdPort != null;
        }

        public String getLlmModel() {
            return llmModel;
        }

        public boolean hasLlmModel() {
            return llmModel != null;
        }

        public Double getLlmTemperature() {
            return llmTemperature;
        }

        public boolean hasLlmTemperature() {
            return llmTemperature != null;
        }
    }

}
