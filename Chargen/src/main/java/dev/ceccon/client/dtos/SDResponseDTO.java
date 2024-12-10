package dev.ceccon.client.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SDResponseDTO {

    private List<String> images;

    public SDResponseDTO() { }

    public SDResponseDTO(List<String> images) {
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
