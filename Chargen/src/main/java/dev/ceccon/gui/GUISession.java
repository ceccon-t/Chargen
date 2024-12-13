package dev.ceccon.gui;

import dev.ceccon.character.FantasyCharacter;
import dev.ceccon.client.LLMClient;
import dev.ceccon.client.LLMSanitizer;
import dev.ceccon.config.AppConfig;
import dev.ceccon.config.LLMAPIConfig;
import dev.ceccon.conversation.Chat;
import dev.ceccon.conversation.Message;
import dev.ceccon.dtos.LLMPromptDTO;
import dev.ceccon.dtos.LLMResponseDTO;

import java.io.IOException;

public class GUISession {

    private AppConfig appConfig;
    private Chat chat;

    public GUISession(AppConfig appConfig) {
        chat = new Chat();
        this.appConfig = appConfig;
    }

    public String createBio(FantasyCharacter userCharacter) throws IOException {
        chat = new Chat();
        chat.addMessage(
                "system",
                """
                        You are part of a character generator system for fantasy universes. 
                        In each message you will receive the attributes for a character, such as name, race, sex, class and skills, and you should answer with a suitable, creative and interesting short biography for this character, that would fit with its characteristics. 
                        Do not output any physical description, as the appearance of the character will be defined elsewhere and it might generate inconsistencies if you mention anything about it here.
                        Do not output any disclaimer or explanation, just the biography for the character.
                        """
        );
        String messageContent = textDescription(userCharacter) + "\n\nWrite a suitable short biography for this character.";
        chat.addMessage(
                "user",
                LLMSanitizer.sanitizeForChat(messageContent)
        );

        LLMAPIConfig llmapiConfig = appConfig.getLlmApiConfig();

        LLMResponseDTO response = LLMClient.sendPrompt(
                llmapiConfig.getFullUrl(),
                LLMPromptDTO.forChat(chat, llmapiConfig)
        );
        chat.addMessage(
                response.getChoices().get(0).message().role(),
                response.getChoices().get(0).message().content()
        );

        return LLMSanitizer.sanitizeLLMSpecialTokens(response.getChoices().get(0).message().content());
    }


    private String textDescription(FantasyCharacter c) {
        StringBuilder sb = new StringBuilder();

        sb.append("Name: "); sb.append(c.getName()); sb.append("\n");
        sb.append("Race: "); sb.append(c.getRace()); sb.append("\n");
        sb.append("Sex: "); sb.append(c.getSex()); sb.append("\n");
        sb.append("Class: "); sb.append(c.getCharacterClass()); sb.append("\n");
        sb.append("Alignment: "); sb.append(c.getAlignment()); sb.append("\n");
        sb.append("\n");

        sb.append("Strength: "); sb.append(c.getStrength()); sb.append("\n");
        sb.append("Dexterity: "); sb.append(c.getDexterity()); sb.append("\n");
        sb.append("Constitution: "); sb.append(c.getConstitution()); sb.append("\n");
        sb.append("Intelligence: "); sb.append(c.getIntelligence()); sb.append("\n");
        sb.append("Wisdom: "); sb.append(c.getWisdom()); sb.append("\n");
        sb.append("Charisma: "); sb.append(c.getCharisma()); sb.append("\n");

        return sb.toString();
    }

    private void printLastChatMessageToCLI() {
        printMessageToCLI(chat.getMessages().getLast());
    }

    private void printMessageToCLI(Message message) {
        System.out.println(message.role() + ": " + message.content());
    }

    private void printUsageMetrics(LLMResponseDTO responseDTO) {
        StringBuilder builder = new StringBuilder();
        LLMResponseDTO.UsageDTO usageDTO = responseDTO.getUsage();
        System.out.println(builder.append("(Token usage metrics: [")
                .append("Completion tokens: ").append(usageDTO.completion_tokens())
                .append(", ")
                .append("Prompt tokens: ").append(usageDTO.prompt_tokens())
                .append(", ")
                .append("Total tokens: ").append(usageDTO.total_tokens())
                .append("])")
        );
    }
}
