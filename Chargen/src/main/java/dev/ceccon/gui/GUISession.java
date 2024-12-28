package dev.ceccon.gui;

import dev.ceccon.character.FantasyCharacter;
import dev.ceccon.client.LLMClient;
import dev.ceccon.client.LLMSanitizer;
import dev.ceccon.client.SDClient;
import dev.ceccon.client.dtos.SDPromptDTO;
import dev.ceccon.client.dtos.SDResponseDTO;
import dev.ceccon.config.AppConfig;
import dev.ceccon.conversation.Chat;
import dev.ceccon.conversation.Message;

import java.io.IOException;
import java.util.Base64;
import java.util.function.Consumer;

public class GUISession {

    private AppConfig appConfig;
    private LLMClient llmClient;
    private SDClient sdClient;

    public GUISession(AppConfig appConfig) {
        this.appConfig = appConfig;
        this.llmClient = new LLMClient(appConfig.getLlmApiConfig());
        this.sdClient = new SDClient(appConfig.getSdApiConfig());
    }

    public String createBio(FantasyCharacter userCharacter, Consumer<String> tokenConsumer, Consumer<Void> onFinish) throws IOException {
        Chat chat = new Chat();
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

        Message response = llmClient.getBioStreaming(chat, tokenConsumer, onFinish);

        return LLMSanitizer.sanitizeLLMSpecialTokens(response.content());
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

    public byte[] createAvatar(String url, SDPromptDTO promptDTO) throws IOException {
        SDResponseDTO response = sdClient.getAvatar(promptDTO);
        byte[] imageData = Base64.getDecoder().decode(response.getImages().get(0));
        return imageData;
    }

}
