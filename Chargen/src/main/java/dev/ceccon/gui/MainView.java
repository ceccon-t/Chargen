package dev.ceccon.gui;

import dev.ceccon.character.FantasyCharacter;
import dev.ceccon.config.AppConfig;
import dev.ceccon.storage.LocalFileStorage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class MainView extends JFrame {

    private static final String TEXT_BUTTON_GENERATION_BIO_ENABLED = "Generate bio";
    private static final String TEXT_BUTTON_GENERATION_BIO_DISABLED = "Please wait...";
    public static final String TEXT_BUTTON_GENERATION_AVATAR_ENABLED = "Generate avatar";
    public static final String TEXT_BUTTON_GENERATION_AVATAR_DISABLED = "Please wait...";

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
    private JLabel avatarLabel;
    private JButton generateAvatarButton;
    private JButton generateBioButton;

    private JTextArea taBio;

    FantasyCharacter currentCharacter = new FantasyCharacter();
    byte[] characterAvatar = {};
    String characterBio = "";

    private GUISession session;
    private LocalFileStorage storage;

    public MainView(AppConfig appConfig, LocalFileStorage storage) {
        super("CHARGEN");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 960);
        setLayout(new BorderLayout());

        this.session = new GUISession(appConfig);
        this.storage = storage;

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

        JButton clearButton = new JButton("Clear all");
        clearButton.addActionListener(e -> {
            clearAll();
        });

        generateBioButton = new JButton(TEXT_BUTTON_GENERATION_BIO_ENABLED);
        generateBioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

        // START INFOS PANEL
        JPanel infosPanel = new JPanel(new GridBagLayout());
        infosPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        infosPanel.add(lName, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        infosPanel.add(tfName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        infosPanel.add(lRace, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        infosPanel.add(tfRace, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        infosPanel.add(lSex, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        infosPanel.add(tfSex, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        infosPanel.add(lClass, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        infosPanel.add(tfClass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        infosPanel.add(lAlignment, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        infosPanel.add(tfAlignment, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_START;
        infosPanel.add(lStrength, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        infosPanel.add(tfStrength, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_START;
        infosPanel.add(lDexterity, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        infosPanel.add(tfDexterity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LINE_START;
        infosPanel.add(lConstitution, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        infosPanel.add(tfConstitution, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_START;
        infosPanel.add(lIntelligence, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        infosPanel.add(tfIntelligence, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.LINE_START;
        infosPanel.add(lWisdom, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        infosPanel.add(tfWisdom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.LINE_START;
        infosPanel.add(lCharisma, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        infosPanel.add(tfCharisma, gbc);

        JPanel wrapperInfosPanel = new JPanel();
        wrapperInfosPanel.setBorder(BorderFactory.createTitledBorder("Character infos"));
        wrapperInfosPanel.add(infosPanel);
        // END INFOS PANEL

        ImageIcon icon = new ImageIcon("image.jpg");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);

        avatarLabel = new JLabel(scaledIcon, JLabel.CENTER);

        generateAvatarButton = new JButton(TEXT_BUTTON_GENERATION_AVATAR_ENABLED);
        generateAvatarButton.addActionListener(e -> {
            getCharacterAvatar();
        });

        JPanel picturePanel = new JPanel();
        picturePanel.setLayout(new BorderLayout());
        picturePanel.add(avatarLabel, BorderLayout.CENTER);
        picturePanel.add(generateAvatarButton, BorderLayout.SOUTH);


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        topPanel.add(wrapperInfosPanel);
        topPanel.add(picturePanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(spBio);
        mainPanel.add(generateBioButton);

        JButton saveCharacterButton = new JButton("Save character");
        saveCharacterButton.addActionListener(e -> {
            System.out.println("Saving character...");
            saveCharacter();
        });

        add(mainPanel, BorderLayout.NORTH);
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 15));
        controlsPanel.add(saveCharacterButton);
        controlsPanel.add(clearButton);
        add(controlsPanel, BorderLayout.SOUTH);
    }

    private FantasyCharacter buildCharacter() {
        String characterName = tfName.getText();

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

    private void showCharacterIncompleteErrorPopup() {
        JOptionPane.showMessageDialog(null,
                "Please fill all character infos.",
                "INCOMPLETE CHARACTER",
                JOptionPane.ERROR_MESSAGE);
    }

    public void getCharBio() throws IOException {
        FantasyCharacter character;
        try {
            character = buildCharacter();
        } catch (Exception e) {
            showCharacterIncompleteErrorPopup();
            return;
        }

        if (!character.isValid()) {
            showCharacterIncompleteErrorPopup();
            return;
        }
        currentCharacter = character;

        characterBio = "";
        generateBioButton.setEnabled(false);
        generateBioButton.setText(TEXT_BUTTON_GENERATION_BIO_DISABLED);

        session.createBio(character,
                this::appendTokenToBio,
                (end) -> wrapUpBioGeneration()
        );
    }

    private void appendTokenToBio(String token) {
        characterBio += token;
        taBio.setText(characterBio);
    }

    private void wrapUpBioGeneration() {
        generateBioButton.setEnabled(true);
        generateBioButton.setText(TEXT_BUTTON_GENERATION_BIO_ENABLED);
    }

    private void getCharacterAvatar() {
        FantasyCharacter character;
        try {
            character = buildCharacter();
        } catch (Exception e) {
            showCharacterIncompleteErrorPopup();
            return;
        }
        if (!character.isValid()) {
            showCharacterIncompleteErrorPopup();
            return;
        }
        currentCharacter = character;

        generateAvatarButton.setText(TEXT_BUTTON_GENERATION_AVATAR_DISABLED);
        generateAvatarButton.setEnabled(false);

        new Thread(() -> {
            try {
                byte[] imageData = session.createAvatar(character);
                characterAvatar = imageData;

                avatarLabel.setIcon(new ImageIcon(imageData));

            } catch (IOException e) {
                System.out.println("Error when GENERATING avatar");
            }

            generateAvatarButton.setText(TEXT_BUTTON_GENERATION_AVATAR_ENABLED);
            generateAvatarButton.setEnabled(true);
        }).start();
    }

    private void saveCharacter() {
        if (!currentCharacter.isValid()) {
            showCharacterIncompleteErrorPopup();
            return;
        }
        storage.saveCharacter(currentCharacter, characterBio, characterAvatar);
    }

    private void clearAll() {
        currentCharacter = new FantasyCharacter();
        characterAvatar = new byte[]{};
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

        avatarLabel.setIcon(scaledIcon);
        revalidate();
        repaint();
    }
}
