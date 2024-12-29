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

    private LLMClient llmClient;
    private SDClient sdClient;

    public GUISession(AppConfig appConfig) {
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

    public byte[] createAvatar(FantasyCharacter character) throws IOException {
        String basePrompt = "character portrait, dark fantasy, CHAR_DESCRIPTION_PROMPT natural lighting, high detail, 8K";
        String negativePrompt = """
            NSFW, (worst quality,low quality:1.4), glassy eyes, logo, text, monochrome, Deformity, Twisted limbs, 
            Incorrect proportions, Ugliness, Ugly limbs, Deformed arm, Deformed fingers, Three hands, Deformed hand, 
            4 fingers, 6 fingers, Deformed thigh, Twisted thigh, Three legs, Short neck, Curved spine, Muscle atrophy, 
            Bony, Facial asymmetry, Incoordinated body, Double chin, Long chin, Elongated physique, Emaciated
        """;

        // SEX RACE CLASS, STRENGTH EFFECT, CONSTITUTION EFFECT, CHARISMA EFFECT, ALIGNMENT,
        StringBuilder characterDescription = new StringBuilder();
        // SEX RACE CLASS,
        characterDescription.append(character.getSex().toLowerCase()).append(" ");
        characterDescription.append(character.getRace().toLowerCase()).append(" ");
        characterDescription.append(character.getCharacterClass().toLowerCase()).append(", ");
        // STRENGTH EFFECT,
        if (character.getStrength() >= 17) {
            characterDescription.append("muscular body, ");
        } else if (character.getStrength() <= 12) {
            characterDescription.append("slim body, ");
        }
        // CONSTITUTION EFFECT,
        if (character.getConstitution() >= 13) {
            characterDescription.append("healthy, ");
        } else {
            characterDescription.append("sickly, ");
        }
        // CHARISMA EFFECT,
        if (character.getCharisma() >= 17) {
            characterDescription.append("smiling confidently, ");
        } else if (character.getCharisma() >= 15) {
            characterDescription.append("smiling, ");
        } else if (character.getCharisma() <= 12) {
            characterDescription.append("annoyed face, ");
        }
        // ALIGNMENT,
        characterDescription.append(character.getAlignment().toLowerCase()).append(",");
        String finalPrompt = basePrompt.replace("CHAR_DESCRIPTION_PROMPT", characterDescription.toString());
        finalPrompt = LLMSanitizer.sanitizeForChat(finalPrompt);

        SDPromptDTO prompt = new SDPromptDTO();
        prompt.setPrompt(finalPrompt);
        prompt.setNegative_prompt(negativePrompt);
        prompt.setSeed(-1L);
        prompt.setSteps(20);
        prompt.setWidth(512);
        prompt.setHeight(512);
        prompt.setCfg_scale(7);
        prompt.setSampler_name("Euler a");
        prompt.setN_iter(1);
        prompt.setBatch_size(1);

        SDResponseDTO response = sdClient.getAvatar(prompt);
        byte[] imageData = Base64.getDecoder().decode(response.getImages().get(0));
        return imageData;
    }

}
