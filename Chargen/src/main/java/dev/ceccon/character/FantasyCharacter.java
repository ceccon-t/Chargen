package dev.ceccon.character;

public class FantasyCharacter {

    private String name;
    private String race;
    private String sex;
    private String characterClass;
    private String alignment;

    private Integer strength;
    private Integer dexterity;
    private Integer constitution;
    private Integer intelligence;
    private Integer wisdom;
    private Integer charisma;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getConstitution() {
        return constitution;
    }

    public void setConstitution(Integer constitution) {
        this.constitution = constitution;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getWisdom() {
        return wisdom;
    }

    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }

    public Integer getCharisma() {
        return charisma;
    }

    public void setCharisma(Integer charisma) {
        this.charisma = charisma;
    }

    public String getTextualDescription() {
        StringBuilder sb = new StringBuilder();

        sb.append("Name: "); sb.append(getName()); sb.append("\n");
        sb.append("Race: "); sb.append(getRace()); sb.append("\n");
        sb.append("Sex: "); sb.append(getSex()); sb.append("\n");
        sb.append("Class: "); sb.append(getCharacterClass()); sb.append("\n");
        sb.append("Alignment: "); sb.append(getAlignment()); sb.append("\n");
        sb.append("\n");

        sb.append("Strength: "); sb.append(getStrength()); sb.append("\n");
        sb.append("Dexterity: "); sb.append(getDexterity()); sb.append("\n");
        sb.append("Constitution: "); sb.append(getConstitution()); sb.append("\n");
        sb.append("Intelligence: "); sb.append(getIntelligence()); sb.append("\n");
        sb.append("Wisdom: "); sb.append(getWisdom()); sb.append("\n");
        sb.append("Charisma: "); sb.append(getCharisma()); sb.append("\n");

        return sb.toString();
    }

    public boolean isValid() {
        if (name == null || name.trim().isEmpty()) return false;

        if (race == null || race.trim().isEmpty()) return false;

        if (sex == null || sex.trim().isEmpty()) return false;

        if (characterClass == null || characterClass.trim().isEmpty()) return false;

        if (alignment == null || alignment.trim().isEmpty()) return false;

        if (strength == null) return false;

        if (dexterity == null) return false;

        if (constitution == null) return false;

        if (intelligence == null) return false;

        if (wisdom == null) return false;

        if (charisma == null) return false;

        return true;
    }
}
