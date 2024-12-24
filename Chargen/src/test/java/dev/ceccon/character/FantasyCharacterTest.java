package dev.ceccon.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FantasyCharacterTest {

    @Test
    void characterWithNoNameIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setName(null);

        assertFalse(character.isValid());
    }

    @Test
    void characterWithEmptyNameIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setName("");

        assertFalse(character.isValid());
    }

    @Test
    void characterWithFilledNameIsValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setName("Name");

        assertTrue(character.isValid());
    }

    @Test
    void characterWithNoRaceIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setRace(null);

        assertFalse(character.isValid());
    }

    @Test
    void characterWithEmptyRaceIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setRace("");

        assertFalse(character.isValid());
    }

    @Test
    void characterWithFilledRaceIsValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setRace("Race");

        assertTrue(character.isValid());
    }

    @Test
    void characterWithNoSexIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setSex(null);

        assertFalse(character.isValid());
    }

    @Test
    void characterWithEmptySexIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setSex("");

        assertFalse(character.isValid());
    }

    @Test
    void characterWithFilledSexIsValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setSex("Male");

        assertTrue(character.isValid());
    }

    @Test
    void characterWithNoCharacterClassIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setCharacterClass(null);

        assertFalse(character.isValid());
    }

    @Test
    void characterWithEmptyCharacterClassIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setCharacterClass("");

        assertFalse(character.isValid());
    }

    @Test
    void characterWithFilledCharacterClassIsValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setCharacterClass("Fighter");

        assertTrue(character.isValid());
    }

    @Test
    void characterWithNoAlignmentIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setAlignment(null);

        assertFalse(character.isValid());
    }

    @Test
    void characterWithEmptyAlignmentIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setAlignment("");

        assertFalse(character.isValid());
    }

    @Test
    void characterWithFilledAlignmentIsValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setAlignment("Alignment");

        assertTrue(character.isValid());
    }

    @Test
    void characterWithNoStrengthIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setStrength(null);

        assertFalse(character.isValid());
    }

    @Test
    void characterWithFilledStrengthIsValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setStrength(12);

        assertTrue(character.isValid());
    }

    @Test
    void characterWithNoDexterityIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setDexterity(null);

        assertFalse(character.isValid());
    }

    @Test
    void characterWithFilledDexterityIsValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setDexterity(12);

        assertTrue(character.isValid());
    }

    @Test
    void characterWithNoConstitutionIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setConstitution(null);

        assertFalse(character.isValid());
    }

    @Test
    void characterWithFilledConstitutionIsValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setConstitution(12);

        assertTrue(character.isValid());
    }

    @Test
    void characterWithNoIntelligenceIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setIntelligence(null);

        assertFalse(character.isValid());
    }

    @Test
    void characterWithFilledIntelligenceIsValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setIntelligence(12);

        assertTrue(character.isValid());
    }

    @Test
    void characterWithNoWisdomIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setWisdom(null);

        assertFalse(character.isValid());
    }

    @Test
    void characterWithFilledWisdomIsValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setWisdom(12);

        assertTrue(character.isValid());
    }

    @Test
    void characterWithNoCharismaIsNotValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setCharisma(null);

        assertFalse(character.isValid());
    }

    @Test
    void characterWithFilledCharismaIsValid() {
        FantasyCharacter character = getDummyCharacter();
        character.setCharisma(12);

        assertTrue(character.isValid());
    }

    private FantasyCharacter getDummyCharacter() {
        FantasyCharacter character = new FantasyCharacter();
        character.setName("Name");
        character.setRace("Race");
        character.setSex("Sex");
        character.setCharacterClass("Class");
        character.setAlignment("Alignment");
        character.setStrength(12);
        character.setDexterity(12);
        character.setConstitution(12);
        character.setIntelligence(12);
        character.setWisdom(12);
        character.setCharisma(12);

        return character;
    }

}