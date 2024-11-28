package dev.ceccon.conversation;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    private static final String DEFAULT_SYSTEM_PROMPT =
            "";

    public static Chat initialize(String systemPrompt) {
        Chat newChat = new Chat();
        newChat.addMessage(
                "system",
                systemPrompt
        );

        return newChat;
    }

    public static Chat initialize() {
        return initialize(DEFAULT_SYSTEM_PROMPT);
    }

    private List<Message> messages = new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(String role, String content) {
        messages.add(new Message(role, content));
    }

}
