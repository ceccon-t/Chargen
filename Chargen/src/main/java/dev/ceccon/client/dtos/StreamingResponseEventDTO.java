package dev.ceccon.client.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingResponseEventDTO {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Delta(String content) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Choice(Delta delta, String finish_reason, Integer index) {}

    private List<Choice> choices;
    private Long created;
    private String id;
    private String model;
    private String object;

    public String getBestChoice() {
        return this.choices.get(0).delta.content;
    }

    public boolean isFinalEvent() {
        return "stop".equals(this.choices.get(0).finish_reason);
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
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
}