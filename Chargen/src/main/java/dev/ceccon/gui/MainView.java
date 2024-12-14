package dev.ceccon.gui;

import dev.ceccon.character.FantasyCharacter;
import dev.ceccon.client.SDClient;
import dev.ceccon.config.AppConfig;
import dev.ceccon.config.SDAPIConfig;
import dev.ceccon.client.dtos.SDPromptDTO;
import dev.ceccon.client.dtos.SDResponseDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

public class MainView extends JFrame {

    private AppConfig appConfig;

    private JLabel lName;
    private JTextField tfName;
    private JLabel lRace;
    private JTextField tfRace;
    private JLabel lSex;
    private JTextField tfSex;
    private JLabel lClass;
    private JTextField tfClass;
    private JLabel lAlignment;
    private JTextField tfAlignment;

    private JLabel lStrength;
    private JTextField tfStrength;
    private JLabel lDexterity;
    private JTextField tfDexterity;
    private JLabel lConstitution;
    private JTextField tfConstitution;
    private JLabel lIntelligence;
    private JTextField tfIntelligence;
    private JLabel lWisdom;
    private JTextField tfWisdom;
    private JLabel lCharisma;
    private JTextField tfCharisma;

    private ImageIcon scaledIcon;
    private JLabel imageLabel;
    private JButton generateImageButton;

    private JTextArea taBio;

    private JLabel stateInfo;

    byte[] characterPicture = {};
    String characterBio = "";

    private GUISession session;

    public MainView(AppConfig appConfig) {
        super("CHARGEN");

        this.appConfig = appConfig;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 960);
        setLayout(new BorderLayout());

        session = new GUISession(appConfig);

        buildView();

        setVisible(true);
    }

    private void buildView() {
        lName = new JLabel("Name:");
        tfName = new JTextField(20);
        lRace = new JLabel("Race:");
        tfRace = new JTextField(20);
        lSex = new JLabel("Sex:");
        tfSex = new JTextField(20);
        lClass = new JLabel("Class:");
        tfClass = new JTextField(20);
        lAlignment = new JLabel("Alignment:");
        tfAlignment = new JTextField(20);
        lStrength = new JLabel("Strength:");
        tfStrength = new JTextField(20);
        lDexterity = new JLabel("Dexterity:");
        tfDexterity = new JTextField(20);
        lConstitution = new JLabel("Constitution:");
        tfConstitution = new JTextField(20);
        lIntelligence = new JLabel("Intelligence:");
        tfIntelligence = new JTextField(20);
        lWisdom = new JLabel("Wisdom:");
        tfWisdom = new JTextField(20);
        lCharisma = new JLabel("Charisma:");
        tfCharisma = new JTextField(20);
        taBio = new JTextArea(15, 30);

        taBio.setLineWrap(true);
        taBio.setWrapStyleWord(true);
        JScrollPane spBio = new JScrollPane(taBio);

        stateInfo = new JLabel("Enter your character info and press Submit.");

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            clearAll();
        });

        JButton generateBioButton = new JButton("Generate bio");
        generateBioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String characterName = tfName.getText();
                stateInfo.setText("Creating bio for character " + characterName + ", please wait...");
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    try {
                        getCharBio();
                    } catch (IOException ex) {
                        System.out.println("Error when generation bio...");
                        throw new RuntimeException(ex);
                    }
                });
            }
        });

        JPanel pnName = new JPanel(new FlowLayout());
        pnName.add(lName);
        pnName.add(tfName);

        JPanel pnRace = new JPanel(new FlowLayout());
        pnRace.add(lRace);
        pnRace.add(tfRace);

        JPanel pnSex = new JPanel(new FlowLayout());
        pnSex.add(lSex);
        pnSex.add(tfSex);

        JPanel pnClass = new JPanel(new FlowLayout());
        pnClass.add(lClass);
        pnClass.add(tfClass);

        JPanel pnAlignment = new JPanel(new FlowLayout());
        pnAlignment.add(lAlignment);
        pnAlignment.add(tfAlignment);

        JPanel pnStrength = new JPanel(new FlowLayout());
        pnStrength.add(lStrength);
        pnStrength.add(tfStrength);

        JPanel pnDexterity = new JPanel(new FlowLayout());
        pnDexterity.add(lDexterity);
        pnDexterity.add(tfDexterity);

        JPanel pnConstitution = new JPanel(new FlowLayout());
        pnConstitution.add(lConstitution);
        pnConstitution.add(tfConstitution);

        JPanel pnIntelligence = new JPanel(new FlowLayout());
        pnIntelligence.add(lIntelligence);
        pnIntelligence.add(tfIntelligence);

        JPanel pnWisdom = new JPanel(new FlowLayout());
        pnWisdom.add(lWisdom);
        pnWisdom.add(tfWisdom);

        JPanel pnCharisma = new JPanel(new FlowLayout());
        pnCharisma.add(lCharisma);
        pnCharisma.add(tfCharisma);


        JPanel infosPanel = new JPanel();
        infosPanel.setLayout(new BoxLayout(infosPanel, BoxLayout.Y_AXIS));
        infosPanel.add(pnName);
        infosPanel.add(pnRace);
        infosPanel.add(pnSex);
        infosPanel.add(pnClass);
        infosPanel.add(pnAlignment);
        infosPanel.add(pnStrength);
        infosPanel.add(pnDexterity);
        infosPanel.add(pnConstitution);
        infosPanel.add(pnIntelligence);
        infosPanel.add(pnWisdom);
        infosPanel.add(pnCharisma);
//        infosPanel.add(spBio);
//        infosPanel.add(stateInfo);
        infosPanel.add(clearButton);

        ImageIcon icon = new ImageIcon("image.jpg");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);

        imageLabel = new JLabel(scaledIcon, JLabel.CENTER);

        generateImageButton = new JButton("Generate image");
        generateImageButton.addActionListener(e -> {
            System.out.println("Generating image...");
            getCharacterPicture();
        });

        JPanel picturePanel = new JPanel();
        picturePanel.setLayout(new BorderLayout());
        picturePanel.add(imageLabel, BorderLayout.CENTER);
        picturePanel.add(generateImageButton, BorderLayout.SOUTH);


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(infosPanel);
        topPanel.add(picturePanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(spBio);
        mainPanel.add(generateBioButton);
        //mainPanel.add(stateInfo);

        JButton saveCharacterButton = new JButton("Save character");
        saveCharacterButton.addActionListener(e -> {
            System.out.println("Saving character...");
            saveCharacter();
        });

        add(mainPanel, BorderLayout.NORTH);
        add(saveCharacterButton, BorderLayout.SOUTH);
    }

    private FantasyCharacter buildCharacter() {
        String characterName = tfName.getText();
//        stateInfo.setText("Creating bio for character " + characterName + ", please wait...");

        String race = tfRace.getText();
        String sex = tfSex.getText();
        String characterClass = tfClass.getText();
        String alignment = tfAlignment.getText();

        Integer strength = Integer.valueOf(tfStrength.getText());
        Integer dexterity = Integer.valueOf(tfDexterity.getText());
        Integer constitution = Integer.valueOf(tfConstitution.getText());
        Integer intelligence = Integer.valueOf(tfIntelligence.getText());
        Integer wisdom = Integer.valueOf(tfWisdom.getText());
        Integer charisma = Integer.valueOf(tfCharisma.getText());

        FantasyCharacter theCharacter = new FantasyCharacter();
        theCharacter.setName(characterName);
        theCharacter.setRace(race);
        theCharacter.setSex(sex);
        theCharacter.setCharacterClass(characterClass);
        theCharacter.setAlignment(alignment);
        theCharacter.setStrength(strength);
        theCharacter.setDexterity(dexterity);
        theCharacter.setConstitution(constitution);
        theCharacter.setIntelligence(intelligence);
        theCharacter.setWisdom(wisdom);
        theCharacter.setCharisma(charisma);

        return theCharacter;
    }

    public void getCharBio() throws IOException {
        characterBio = "";

        session.createBio(buildCharacter(),
                this::appendTokenToBio,
                (end) -> wrapUpBioGeneration()
        );
    }

    private void appendTokenToBio(String token) {
        characterBio += token;
        taBio.setText(characterBio);
    }

    private void wrapUpBioGeneration() {
        stateInfo.setText("Done!");
    }

    private void getCharacterPicture() {
        SDAPIConfig sdapiConfig = appConfig.getSdApiConfig();
        String url = sdapiConfig.getFullUrl();

        String basePrompt = "character portrait, dark fantasy, CHAR_DESCRIPTION_PROMPT natural lighting, high detail, 8K";

        FantasyCharacter theCharacter = buildCharacter();

        // SEX RACE CLASS, STRENGTH EFFECT, CONSTITUTION EFFECT, CHARISMA EFFECT, ALIGNMENT,
        StringBuilder characterDescription = new StringBuilder();
        // SEX RACE CLASS,
        characterDescription.append(theCharacter.getSex().toLowerCase()).append(" ");
        characterDescription.append(theCharacter.getRace().toLowerCase()).append(" ");
        characterDescription.append(theCharacter.getCharacterClass().toLowerCase()).append(", ");
        // STRENGTH EFFECT,
        if (theCharacter.getStrength() >= 17) {
            characterDescription.append("muscular body, ");
        } else if (theCharacter.getStrength() <= 12) {
            characterDescription.append("slim body, ");
        }
        // CONSTITUTION EFFECT,
        if (theCharacter.getConstitution() >= 13) {
            characterDescription.append("healthy, ");
        } else {
            characterDescription.append("sickly, ");
        }
        // CHARISMA EFFECT,
        if (theCharacter.getCharisma() >= 17) {
            characterDescription.append("smiling confidently, ");
        } else if (theCharacter.getCharisma() >= 15) {
            characterDescription.append("smiling, ");
        } else if (theCharacter.getCharisma() <= 12) {
            characterDescription.append("annoyed face, ");
        }
        // ALIGNMENT,
        characterDescription.append(theCharacter.getAlignment().toLowerCase()).append(",");

        SDPromptDTO prompt = new SDPromptDTO();
        prompt.setPrompt(basePrompt.replace("CHAR_DESCRIPTION_PROMPT", characterDescription.toString()));
        prompt.setNegative_prompt("NSFW, (worst quality,low quality:1.4), glassy eyes, logo, text, monochrome, Deformity, Twisted limbs, Incorrect proportions, Ugliness, Ugly limbs, Deformed arm, Deformed fingers, Three hands, Deformed hand, 4 fingers, 6 fingers, Deformed thigh, Twisted thigh, Three legs, Short neck, Curved spine, Muscle atrophy, Bony, Facial asymmetry, Incoordinated body, Double chin, Long chin, Elongated physique, Emaciated");
        prompt.setSeed(-1L);
        prompt.setSteps(20);
        prompt.setWidth(512);
        prompt.setHeight(512);
        prompt.setCfg_scale(7);
        prompt.setSampler_name("Euler a");
        prompt.setN_iter(1);
        prompt.setBatch_size(1);

        System.out.println("Prompt: " + prompt.getPrompt());

        try {
            generateImageButton.setText("Generating...");
            revalidate();
            repaint();

            SDResponseDTO response = SDClient.sendPrompt(url, prompt);

            byte[] imageData = Base64.getDecoder().decode(response.getImages().get(0));
            characterPicture = imageData;

            imageLabel.setIcon(new ImageIcon(imageData));

            generateImageButton.setText("Generate image");
            revalidate();
            repaint();

        } catch (IOException e) {
            System.out.println("Error when GENERATING image");
            throw new RuntimeException(e);
        }
    }

    private void saveGeneratedImage(String outputFileName, byte[] imageData) {
        try(FileOutputStream fos = new FileOutputStream("pictures/" + outputFileName)) {
            fos.write(imageData);
            System.out.println("Image saved.");
        } catch (IOException e) {
            System.out.println("Error when SAVING image");
            throw new RuntimeException(e);
        }
    }

    private void saveCharacter() {
        String rootFolder = "characters";
        FantasyCharacter theCharacter = buildCharacter();
        String characterFolder = LocalDate.now().toString() + "_" + theCharacter.getName();
        String pictureFileName = "pic_" + theCharacter.getName().replace(" ", "-") + "_" + LocalDateTime.now().toString() + ".jpg";
        String bioFileName = "bio_" + theCharacter.getName().replace(" ", "-") + "_" + LocalDateTime.now().toString();

        String bioContent = theCharacter.getTextualDescription() + "\n\n\nBio: " + characterBio;

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
            fos.write(characterPicture);
            System.out.println("Saved character picture: " + pictureFile.getPath());
        } catch (IOException e) {
            System.out.println("Could not write picture file: " + pictureFile.getPath());
        }
    }

    private void clearAll() {
        characterPicture = new byte[]{};
        characterBio = "";

        tfName.setText("");
        tfRace.setText("");
        tfSex.setText("");
        tfClass.setText("");
        tfAlignment.setText("");
        tfStrength.setText("");
        tfDexterity.setText("");
        tfConstitution.setText("");
        tfIntelligence.setText("");
        tfWisdom.setText("");
        tfCharisma.setText("");

        taBio.setText("");

        imageLabel.setIcon(scaledIcon);
        revalidate();
        repaint();
    }
}
