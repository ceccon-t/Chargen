package dev.ceccon.storage;

import dev.ceccon.character.FantasyCharacter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalFileStorage {

    public void saveCharacter(FantasyCharacter character, String characterBio, byte[] characterAvatar) {
        String rootFolder = "characters";
        String characterFolder = LocalDate.now().toString() + "_" + character.getName();
        String pictureFileName = "pic_" + character.getName().replace(" ", "-") + "_" + LocalDateTime.now().toString() + ".jpg";
        String bioFileName = "bio_" + character.getName().replace(" ", "-") + "_" + LocalDateTime.now().toString();

        String bioContent = character.getTextualDescription() + "\n\n\nBio: " + characterBio;

        String finalFolderPath = rootFolder + "/" + characterFolder;

        File folder = new File(finalFolderPath);
        if (!folder.exists()) {
            boolean createdFolders = folder.mkdirs();
            if (!createdFolders) {
                System.out.println("Could not created folder: " + folder.toString());
                return;
            }
        }

        File bioFile = new File(folder, bioFileName);
        try (FileWriter writer = new FileWriter(bioFile)) {
            writer.write(bioContent);
            System.out.println("Saved character bio: " + bioFile.getPath());
        } catch (IOException e) {
            System.out.println("Could not write bio file: " + bioFile.getPath());
        }

        File pictureFile = new File(folder, pictureFileName);
        try (FileOutputStream fos = new FileOutputStream(pictureFile)) {
            fos.write(characterAvatar);
            System.out.println("Saved character picture: " + pictureFile.getPath());
        } catch (IOException e) {
            System.out.println("Could not write picture file: " + pictureFile.getPath());
        }
    }
}
