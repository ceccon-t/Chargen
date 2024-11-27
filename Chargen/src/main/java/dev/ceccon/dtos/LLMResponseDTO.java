package dev.ceccon.dtos;

import java.util.List;

public class LLMResponseDTO {
    // Auxiliary data structures
    public record MessageDTO (String role, String content) {}
    public record ChoiceDTO (String finish_reason, Integer index, MessageDTO message) {}
    public record UsageDTO (Integer completion_tokens, Integer prompt_tokens, Integer total_tokens) {}

    // Fields from response
    private List<ChoiceDTO> choices;
    private Integer created;
    private String id;
    private String model;
    private String object;
    private UsageDTO usage;

    public String getRole() {
        return choices.get(0).message().role();
    }

    public String getContent() {
        return choices.get(0).message().content();
    }

    public List<ChoiceDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceDTO> choices) {
        this.choices = choices;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public UsageDTO getUsage() {
        return usage;
    }

    public void setUsage(UsageDTO usage) {
        this.usage = usage;
    }
}
