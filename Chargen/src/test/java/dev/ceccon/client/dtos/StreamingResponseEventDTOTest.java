package dev.ceccon.client.dtos;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StreamingResponseEventDTOTest {

    @Test
    public void getAndSetChoices() {
        List<StreamingResponseEventDTO.Choice> choices = dummySimpleChoices();
        StreamingResponseEventDTO dto = new StreamingResponseEventDTO();

        dto.setChoices(choices);

        List<StreamingResponseEventDTO.Choice> returnedChoices = dto.getChoices();

        assertEquals(choices, returnedChoices);
    }

    @Test
    public void getAndSetCreated() {
        Long created = 42L;
        StreamingResponseEventDTO dto = new StreamingResponseEventDTO();

        dto.setCreated(created);

        Long returnedCreated = dto.getCreated();

        assertEquals(created, returnedCreated);
    }

    @Test
    public void getAndSetId() {
        String id = "id";
        StreamingResponseEventDTO dto = new StreamingResponseEventDTO();

        dto.setId(id);

        String returnedId = dto.getId();

        assertEquals(id, returnedId);
    }

    @Test
    public void getAndSetModel() {
        String model = "model";
        StreamingResponseEventDTO dto = new StreamingResponseEventDTO();

        dto.setModel(model);

        String returnedModel = dto.getModel();

        assertEquals(model, returnedModel);
    }

    @Test
    public void getAndSetObject() {
        String object = "object";
        StreamingResponseEventDTO dto = new StreamingResponseEventDTO();

        dto.setObject(object);

        String returnedObject = dto.getObject();

        assertEquals(object, returnedObject);
    }

    @Test
    public void recognizesNonFinalEvent() {
        List<StreamingResponseEventDTO.Choice> choices = dummySimpleChoices();
        StreamingResponseEventDTO dto = new StreamingResponseEventDTO();

        dto.setChoices(choices);

        assertFalse(dto.isFinalEvent());
    }

    @Test
    public void recognizesFinalEvent() {
        List<StreamingResponseEventDTO.Choice> choices = List.of(
                new StreamingResponseEventDTO.Choice(new StreamingResponseEventDTO.Delta("a"), "stop", 0)
        );
        StreamingResponseEventDTO dto = new StreamingResponseEventDTO();

        dto.setChoices(choices);

        assertTrue(dto.isFinalEvent());
    }

    @Test
    public void bestChoiceReturnsOnlyChoiceWhenSingleOption() {
        String bestContent = "cool";
        List<StreamingResponseEventDTO.Choice> choices = List.of(
                new StreamingResponseEventDTO.Choice(new StreamingResponseEventDTO.Delta(bestContent), "stop", 0)
        );
        StreamingResponseEventDTO dto = new StreamingResponseEventDTO();

        dto.setChoices(choices);

        String returnedBestChoice = dto.getBestChoice();

        assertTrue(bestContent.equals(returnedBestChoice));
    }

    @Test
    public void bestChoiceReturnsFirstOptionWhenMultipleOptions() {
        String bestContentFirstOption = "coolest";
        String bestContentSecondOption = "cool";
        List<StreamingResponseEventDTO.Choice> choices = List.of(
                new StreamingResponseEventDTO.Choice(new StreamingResponseEventDTO.Delta(bestContentFirstOption), "stop", 0),
                new StreamingResponseEventDTO.Choice(new StreamingResponseEventDTO.Delta(bestContentSecondOption), "stop", 1)
        );
        StreamingResponseEventDTO dto = new StreamingResponseEventDTO();

        dto.setChoices(choices);

        String returnedBestChoice = dto.getBestChoice();

        assertTrue(bestContentFirstOption.equals(returnedBestChoice));
    }

    private List<StreamingResponseEventDTO.Choice> dummySimpleChoices() {
        return List.of(
                new StreamingResponseEventDTO.Choice(new StreamingResponseEventDTO.Delta("test1"), "", 0),
                new StreamingResponseEventDTO.Choice(new StreamingResponseEventDTO.Delta("test2"), "", 1)
        );
    }

}