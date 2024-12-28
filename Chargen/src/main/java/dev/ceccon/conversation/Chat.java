package dev.ceccon.conversation;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    private List<Message> messages = new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(String role, String content) {
        messages.add(new Message(role, content));
    }

}
