package com.LevelEditor.StartWindow;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Level;
import com.LevelEditor.Main;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkJButton;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkProgressBar;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkRadioButton.DarkerRadioButton;
import com.LevelEditor.StartWindow.StartListeners.AbsolutePixelListener;
import com.LevelEditor.StartWindow.StartListeners.DimensionListener;
import com.LevelEditor.StartWindow.StartListeners.OpenDirectoryListener;
import com.LevelEditor.StartWindow.StartListeners.RadioListener;
import com.LevelEditor.TabActions.FileTabActions.ExportAction;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import javax.swing.border.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

import static com.LevelEditor.ApplicationWindow.backgroundShadedColor;
import static com.LevelEditor.ApplicationWindow.lightColor;


public class InitializeWindow extends JFrame {

    private static final String TITLE = "Level Creator Prototype v1.5";

    public static String filePath;
    public static boolean isFileLoaded;
    public static boolean isJSONNotXML;

    public static RadioListener.StartOptions selectionChoice = RadioListener.StartOptions.AUTO;

    private static AspectSettings settings;

    private static final int progressMaximum = 100;
    private static final int progressMinimum = 0;

    private static DarkProgressBar progressBar;

    private static final Font bigFont = new Font("Consolas", Font.PLAIN, 24);
    private static final Font normalFont = new Font("Consolas", Font.PLAIN, 14);
    private static final Font smallFont = new Font("Consolas", Font.PLAIN, 12);
    private static final Font startFont = new Font("Consolas", Font.PLAIN, 17);
    private static final Color infoBoxColor = new Color(
            lightColor.getRed(),
            lightColor.getGreen(),
            lightColor.getBlue(),
            Math.abs(lightColor.getAlpha() - 200));
    private static final Color startButtonColor = new Color(34, 34, 31);

    public static JTextField pathField;

    private boolean changingDocuments;
    private JTextField multiplierField;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField xField;
    private JTextField yField;
    private JTextField absoluteWidth;
    private JTextField absoluteHeight;

    private static final int initializeWindowWidth = 410;
    private static final int initializeWindowHeight = 490;

    public InitializeWindow() {
        setPreferredSize(new Dimension(initializeWindowWidth, initializeWindowHeight));
        setResizable(false);
        setLayout(new BorderLayout());
        setTitle(TITLE);

        settings = new AspectSettings();

        Border outline = new LineBorder(lightColor, 1, false);
        Border margin = new EmptyBorder(2, 5, 0, 5);
        Border border = new CompoundBorder(outline, margin);

        title();
        options(border);
        begin();

        updateRatioText();

        //very last, setting icon
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon img = new ImageIcon(classLoader.getResource("LevelEditorIcon.png"));
        setIconImage(img.getImage());

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void title() {
        JLabel title = new JLabel(TITLE, SwingConstants.CENTER);
        title.setFocusable(false);

        //title compound borders
        Border outline = new LineBorder(infoBoxColor, 5);
        Border margin = new EmptyBorder(4, 0, 0, 0);
        Border border = new CompoundBorder(outline, margin);

        title.setBorder(border);

        title.setFont(bigFont);
        title.setForeground(lightColor);
        title.setBackground(backgroundShadedColor);
        title.setOpaque(true);
        add(title, BorderLayout.NORTH);
    }

    private void options(Border border) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(backgroundShadedColor);
        GridBagConstraints c = new GridBagConstraints();

        //creating radio buttons and container
        ButtonGroup radioGroup = new ButtonGroup();
        DarkerRadioButton load = new DarkerRadioButton("Load File", normalFont);
        load.setToolTipText("Loads up a JSON or XML file to resume editing");
        load.addActionListener(new RadioListener(RadioListener.StartOptions.LOAD, this));

        DarkerRadioButton auto = new DarkerRadioButton("Auto Scale", normalFont);
        auto.setToolTipText("Creates new level with dimensions that auto scale according to your monitor");
        auto.addActionListener(new RadioListener(RadioListener.StartOptions.AUTO, this));
        auto.setSelected(true);

        DarkerRadioButton customAR = new DarkerRadioButton("Custom by Aspect Ratio", normalFont);
        customAR.setToolTipText("Creates new level with custom aspect ratio properties below");
        customAR.addActionListener(new RadioListener(RadioListener.StartOptions.CUSTOM_AR, this));

        DarkerRadioButton customAbsolute = new DarkerRadioButton("Custom by Absolute", normalFont);
        customAbsolute.setToolTipText("Creates new level with inputted pixel size below");
        customAbsolute.addActionListener(new RadioListener(RadioListener.StartOptions.CUSTOM_AB, this));

        radioGroup.add(load);
        radioGroup.add(auto);
        radioGroup.add(customAR);
        radioGroup.add(customAbsolute);

        //load
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        p.add(load, c);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 10, 0);
        p.add(loadContainer(border), c);

        //auto
        c.gridx = 0;
        c.gridy = 2;
        p.add(auto, c);

        //resetting insets
        c.insets = new Insets(0, 0, 0, 0);

        //custom aspect ratio
        c.gridx = 0;
        c.gridy = 3;
        p.add(customAR, c);
        c.gridx = 0;
        c.gridy = 4;
        p.add(customARContainer(border), c);

        //custom absolute
        c.gridx = 0;
        c.gridy = 5;
        p.add(customAbsolute, c);
        c.gridx = 0;
        c.gridy = 6;
        p.add(customAbsoluteContainer(border), c);

        add(p, SwingConstants.CENTER);
    }

    private Container customAbsoluteContainer(Border border) {
        Container c = new Container();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 15, 10);
        c.setLayout(flowLayout);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 85));
        BorderLayout layout = new BorderLayout();
        layout.setVgap(15);
        panel.setLayout(layout);
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.setBackground(backgroundShadedColor);

        //header
        JLabel choiceLabel = new JLabel("Absolute Size", SwingConstants.CENTER);
        choiceLabel.setFocusable(false);
        choiceLabel.setFont(normalFont);
        choiceLabel.setForeground(lightColor);
        choiceLabel.setBackground(backgroundShadedColor);
        choiceLabel.setOpaque(true);

        //choice 1 label
        JLabel ch1 = new JLabel("Width in Pixels: ", SwingConstants.CENTER);
        ch1.setFocusable(false);
        ch1.setFont(normalFont);
        ch1.setForeground(lightColor);
        ch1.setBackground(backgroundShadedColor);
        ch1.setOpaque(true);
        //ch1 text field
        JTextField ch1TextField = new JTextField();
        ch1TextField.setFocusable(true);
        ch1TextField.setHorizontalAlignment(JTextField.CENTER);
        ch1TextField.setFont(normalFont);
        ch1TextField.setPreferredSize(new Dimension(60, 20));
        ch1TextField.setForeground(lightColor);
        ch1TextField.setBackground(backgroundShadedColor);
        ch1TextField.setBorder(border);
        ch1TextField.setText("" + settings.getLvlMakerWidth());

        absoluteWidth = ch1TextField;
        absoluteWidth.getDocument().addDocumentListener(new AbsolutePixelListener(this, settings, AbsolutePixelListener.Dimension.WIDTH));

        //choice 2 label
        JLabel ch2 = new JLabel("Height in Pixels: ", SwingConstants.CENTER);
        ch2.setFocusable(false);
        ch2.setFont(normalFont);
        ch2.setForeground(lightColor);
        ch2.setBackground(backgroundShadedColor);
        ch2.setOpaque(true);
        //ch2 text field
        JTextField ch2TextField = new JTextField();
        ch2TextField.setFocusable(true);
        ch2TextField.setHorizontalAlignment(JTextField.CENTER);
        ch2TextField.setFont(normalFont);
        ch2TextField.setPreferredSize(new Dimension(60, 20));
        ch2TextField.setForeground(lightColor);
        ch2TextField.setBackground(backgroundShadedColor);
        ch2TextField.setBorder(border);
        ch2TextField.setText("" + settings.getLvlMakerHeight());

        absoluteHeight = ch2TextField;
        absoluteHeight.getDocument().addDocumentListener(new AbsolutePixelListener(this, settings, AbsolutePixelListener.Dimension.HEIGHT));

        //container for holding options
        Container optionContainer = new Container();
        GridLayout allG = new GridLayout(2, 1);
        allG.setVgap(10);
        optionContainer.setLayout(allG);

        //containers for individual choices
        GridLayout indivG = new GridLayout(1, 2);
        indivG.setHgap(0);
        Container option1 = new Container();
        option1.setLayout(indivG);
        option1.add(ch1);
        option1.add(ch1TextField);
        Container option2 = new Container();
        option2.setLayout(indivG);
        option2.add(ch2);
        option2.add(ch2TextField);

        optionContainer.add(option1);
        optionContainer.add(option2);

        panel.add(choiceLabel, BorderLayout.NORTH);
        panel.add(optionContainer, BorderLayout.CENTER);

        c.add(panel);

        return c;
    }

    private Container customARContainer(Border border) {
        Container c = new Container();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 15, 10);
        c.setLayout(flowLayout);

        //multiplier label
        JLabel mLabel = new JLabel("Multiplier", SwingConstants.CENTER);
        mLabel.setFocusable(false);
        mLabel.setFont(normalFont);
        mLabel.setForeground(lightColor);
        mLabel.setBackground(backgroundShadedColor);
        mLabel.setOpaque(true);
        mLabel.setToolTipText("Should be set proportionally to Aspect Ratio");

        //multiplier
        multiplierField = new JTextField();
        multiplierField.setFocusable(true);
        multiplierField.setHorizontalAlignment(JTextField.CENTER);
        multiplierField.setFont(normalFont);
        multiplierField.setPreferredSize(new Dimension(60, 20));
        multiplierField.setForeground(lightColor);
        multiplierField.setBackground(backgroundShadedColor);
        multiplierField.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        multiplierField.setToolTipText("Aspect Ratio * Multiplier = Pixels");
        multiplierField.getDocument().addDocumentListener(new DimensionListener(this, settings, DimensionListener.DimensionFields.MULTIPLIER));

        Container multiplierContainer = new Container();
        multiplierContainer.setLayout(new GridLayout(2, 1));
        multiplierContainer.add(mLabel);
        multiplierContainer.add(multiplierField);

        c.add(choicePanel("Aspect Ratio", "X: ", "Y: ", 0, true, border));
        c.add(multiplierContainer);
        c.add(choicePanel("Pixels", "Width:", "Height:", 7, false, border));

        return c;
    }

    private JPanel choicePanel(String title, String choice1, String choice2, int hGap, boolean isAspectNotPixels, Border border) {
        JPanel panel = new JPanel();
        BorderLayout layout = new BorderLayout();
        layout.setVgap(15);
        panel.setLayout(layout);
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panel.setBackground(backgroundShadedColor);

        //header
        JLabel choiceLabel = new JLabel(title, SwingConstants.CENTER);
        choiceLabel.setFocusable(false);
        choiceLabel.setFont(normalFont);
        choiceLabel.setForeground(lightColor);
        choiceLabel.setBackground(backgroundShadedColor);
        choiceLabel.setOpaque(true);

        //choice 1 label
        JLabel ch1 = new JLabel(choice1, SwingConstants.CENTER);
        ch1.setFocusable(false);
        ch1.setFont(normalFont);
        ch1.setForeground(lightColor);
        ch1.setBackground(backgroundShadedColor);
        ch1.setOpaque(true);
        //ch1 text field
        JTextField ch1TextField = new JTextField();
        ch1TextField.setFocusable(true);
        ch1TextField.setHorizontalAlignment(JTextField.CENTER);
        ch1TextField.setFont(normalFont);
        ch1TextField.setPreferredSize(new Dimension(60, 20));
        ch1TextField.setForeground(lightColor);
        ch1TextField.setBackground(backgroundShadedColor);
        ch1TextField.setBorder(border);
        if (isAspectNotPixels) {
            xField = ch1TextField;
            xField.getDocument().addDocumentListener(new DimensionListener(this, settings, DimensionListener.DimensionFields.X));
        } else {
            ch1TextField.setEditable(false);
            widthField = ch1TextField;
            widthField.getDocument().addDocumentListener(new DimensionListener(this, settings, DimensionListener.DimensionFields.WIDTH));
        }

        //choice 2 label
        JLabel ch2 = new JLabel(choice2, SwingConstants.CENTER);
        ch2.setFocusable(false);
        ch2.setFont(normalFont);
        ch2.setForeground(lightColor);
        ch2.setBackground(backgroundShadedColor);
        ch2.setOpaque(true);
        //ch2 text field
        JTextField ch2TextField = new JTextField();
        ch2TextField.setFocusable(true);
        ch2TextField.setHorizontalAlignment(JTextField.CENTER);
        ch2TextField.setFont(normalFont);
        ch2TextField.setPreferredSize(new Dimension(60, 20));
        ch2TextField.setForeground(lightColor);
        ch2TextField.setBackground(backgroundShadedColor);
        ch2TextField.setBorder(border);
        if (isAspectNotPixels) {
            yField = ch2TextField;
            yField.getDocument().addDocumentListener(new DimensionListener(this, settings, DimensionListener.DimensionFields.Y));
        } else {
            ch2TextField.setEditable(false);
            heightField = ch2TextField;
            heightField.getDocument().addDocumentListener(new DimensionListener(this, settings, DimensionListener.DimensionFields.HEIGHT));
        }

        //container for holding options
        Container optionContainer = new Container();
        GridLayout allG = new GridLayout(2, 1);
        allG.setVgap(10);
        optionContainer.setLayout(allG);

        //containers for individual choices
        GridLayout indivG = new GridLayout(1, 2);
        indivG.setHgap(hGap);
        Container option1 = new Container();
        option1.setLayout(indivG);
        option1.add(ch1);
        option1.add(ch1TextField);
        Container option2 = new Container();
        option2.setLayout(indivG);
        option2.add(ch2);
        option2.add(ch2TextField);

        optionContainer.add(option1);
        optionContainer.add(option2);

        panel.add(choiceLabel, BorderLayout.NORTH);
        panel.add(optionContainer, BorderLayout.CENTER);

        return panel;
    }

    private Container loadContainer(Border border) {
        Container c = new Container();
        FlowLayout f = new FlowLayout();
        f.setHgap(7);
        c.setLayout(f);

        //path label
        JLabel pathLabel = new JLabel("Path: ", SwingConstants.CENTER);
        pathLabel.setFocusable(false);
        pathLabel.setFont(normalFont);
        pathLabel.setForeground(lightColor);
        pathLabel.setBackground(backgroundShadedColor);
        pathLabel.setOpaque(true);

        //JTextField
        pathField = new JTextField("C:\\");
        pathField.setFocusable(true);
        pathField.setHorizontalAlignment(JTextField.LEFT);
        pathField.setFont(normalFont);
        pathField.setPreferredSize(new Dimension(300, 20));
        pathField.setForeground(lightColor);
        pathField.setBackground(backgroundShadedColor);
        pathField.setBorder(border);

        pathField.setToolTipText("Enter the complete path of the file to load");

        //directory label/button
        DarkJButton directory = new DarkJButton("...", 23, 20, smallFont, backgroundShadedColor, lightColor);
        directory.setBorder(new LineBorder(lightColor.darker()));
        directory.setToolTipText("Click to open directory");
        directory.addActionListener(new OpenDirectoryListener());

        c.add(pathLabel);
        c.add(pathField);
        c.add(directory);

        return c;
    }

    private void begin() {
        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 15, 5);
        panel.setLayout(flowLayout);
        panel.setBackground(backgroundShadedColor);
        panel.setBorder(new EmptyBorder(0, 0, 5, 0));

        //start button
        DarkJButton start = new DarkJButton("Begin", 80, 30, startFont, startButtonColor, lightColor);
        start.addActionListener(new BeginListener());
        start.setBorder(new LineBorder(Color.BLACK, 1));

        //progress bar
        JPanel progressPanel = new JPanel(null);
        progressPanel.setFocusable(false);
        progressPanel.setPreferredSize(new Dimension(277, 17));
        progressPanel.setBackground(backgroundShadedColor);
        progressPanel.setToolTipText("It's just a loading bar");
        progressPanel.setBorder(BorderFactory.createLineBorder(lightColor, 1));
        progressBar = new DarkProgressBar(progressMinimum, progressMaximum, -4, -1,
                (int) progressPanel.getPreferredSize().getWidth() + 9,
                (int) progressPanel.getPreferredSize().getHeight() + 4);
        progressBar.setVisible(false);
        progressPanel.add(progressBar);

        panel.add(start);
        panel.add(progressPanel);

        add(panel, BorderLayout.SOUTH);
    }

    public void updateRatioText() {

        if (changingDocuments)
            return;

        changingDocuments = true;

        SwingUtilities.invokeLater(() -> {

            multiplierField.setText("" + settings.getMultiplier());
            widthField.setText("" + settings.getLvlMakerWidth());
            heightField.setText("" + settings.getLvlMakerHeight());
            xField.setText("" + settings.getAspectRatioX());
            yField.setText("" + settings.getAspectRatioY());

            changingDocuments = false;

        });

    }

    public void updateAbsoluteText(long width, long height) {

        if (changingDocuments)
            return;

        changingDocuments = true;

        SwingUtilities.invokeLater(() -> {

            absoluteWidth.setText("" + width);
            absoluteHeight.setText("" + height);

            changingDocuments = false;

        });

    }

    public static void updateProgressBar(int value) {
        SwingUtilities.invokeLater(() -> progressBar.setValue(value));
    }

    class BeginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            progressBar.setVisible(true);
            settings.windowSettings();

            if (selectionChoice == RadioListener.StartOptions.LOAD) {

                //read contents of that file, see if it can be loaded
                if (!loadFile()) {
                    progressBar.setVisible(false);

                    return;
                }

                //if we get here, then we successfully loaded a file
                isFileLoaded = true;

            } else {
                //creating a new level
                Main.currentLevel = new Level(settings.getLvlMakerWidth(), settings.getLvlMakerHeight());
            }

            InitializeWindow.updateProgressBar(50);

            //creating application
            Main.applicationWindow = new ApplicationWindow(settings);

            if (Main.currentLevel.flipY)
                Main.currentLevel = Main.currentLevel.flipYCopy();

            InitializeWindow.updateProgressBar(100);

            dispose();
        }

    }

    private boolean loadFile() {

        filePath = InitializeWindow.pathField.getText();

        File f = new File(filePath);

        //if JSON file
        if (Objects.equals(OpenDirectoryListener.getExtension(f), ExportAction.JSONExtension)) {

            //opening GSON and popping in data
            Gson gson = new Gson();
            try {
                JsonReader reader = new JsonReader(new FileReader(InitializeWindow.pathField.getText()));
                Main.currentLevel = gson.fromJson(reader, Level.class);
                reader.close();
            } catch (Exception e) {
                InitializeWindow.pathField.setText("[JSON file was corrupted]");
                e.printStackTrace();
                return false;
            }

            isJSONNotXML = true;
            return true;

        }
        //if XML file
        else if (Objects.equals(OpenDirectoryListener.getExtension(f), ExportAction.XMLExtension)) {

            //creating XML parsers and giving data to level
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Main.currentLevel = (Level) jaxbUnmarshaller.unmarshal(f);
            } catch (JAXBException e) {
                e.printStackTrace();
                InitializeWindow.pathField.setText("[XML file was corrupted]");
                return false;
            }

            return true;
        }

        //if neither XML or JSON
        InitializeWindow.pathField.setText("[Cannot load file]");
        return false;

    }

    public static AspectSettings getSettings() {
        return settings;
    }

}