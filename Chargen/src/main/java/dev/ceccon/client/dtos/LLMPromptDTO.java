package dev.ceccon.client.dtos;

import dev.ceccon.config.LLMAPIConfig;
import dev.ceccon.conversation.Chat;
import dev.ceccon.conversation.Message;

import java.util.ArrayList;
import java.util.List;

public class LLMPromptDTO {

    private record MessageDTO(String role, String content) {}

    private String model = "";
    private List<MessageDTO> messages = new ArrayList<>();
    private double temperature = 0.0;
    private boolean stream = false;

    private LLMPromptDTO() {
    }

    public static LLMPromptDTO build(Chat chat, LLMAPIConfig apiConfig) {
        LLMPromptDTO prompt = new LLMPromptDTO();

        for (Message message : chat.getMessages()) {
            prompt.messages.add(new MessageDTO(message.role(), message.content()));
        }

        prompt.model = apiConfig.getModel();
        prompt.temperature = apiConfig.getTemperature();

        return prompt;
    }

    public String getModel() {
        return model;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public double getTemperature() {
        return temperature;
    }

    public boolean isStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }
}
