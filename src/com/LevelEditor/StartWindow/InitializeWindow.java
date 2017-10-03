package com.LevelEditor.StartWindow;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Level;
import com.LevelEditor.Main;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkJButton;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkProgressBar;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkRadioButton.DarkerRadioButton;
import com.LevelEditor.Shapes.*;
import com.LevelEditor.Shapes.Point;
import com.LevelEditor.Shapes.Polygon;
import com.LevelEditor.Shapes.Rectangle;
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

import static com.LevelEditor.ApplicationWindow.BACKGROUND_SHADED_COLOR;
import static com.LevelEditor.ApplicationWindow.LIGHT_COLOR;


public class InitializeWindow extends JFrame {

    private static final String TITLE = "Editor Prototype v2";
    private static final int PROGRESS_MAXIMUM = 100;
    private static final int PROGRESS_MINIMUM = 0;

    public static final Font BIG_FONT = new Font("Consolas", Font.PLAIN, 24);
    public static final Font NORMAL_FONT = new Font("Consolas", Font.PLAIN, 14);
    public static final Font SMALL_FONT = new Font("Consolas", Font.PLAIN, 12);
    public static final Font START_FONT = new Font("Consolas", Font.PLAIN, 17);

    private static final Color INFO_BOX_COLOR = new Color(
            LIGHT_COLOR.getRed(),
            LIGHT_COLOR.getGreen(),
            LIGHT_COLOR.getBlue(),
            Math.abs(LIGHT_COLOR.getAlpha() - 200));
    private static final Color START_BUTTON_COLOR = new Color(34, 34, 31);
    private static final int INITIALIZE_WINDOW_WIDTH = 410;
    private static final int INITIALIZE_WINDOW_HEIGHT = 500;
    public static String filePath;
    public static boolean isFileLoaded;
    public static boolean isJSONNotXML;
    public static RadioListener.StartOptions selectionChoice = RadioListener.StartOptions.LOAD;
    public static JTextField pathField;
    private static AspectSettings settings;
    private static DarkProgressBar progressBar;
    private boolean changingDocuments;
    private JTextField multiplierField;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField xField;
    private JTextField yField;
    private JTextField absoluteWidth;
    private JTextField absoluteHeight;

    public InitializeWindow() {
        setPreferredSize(new Dimension(INITIALIZE_WINDOW_WIDTH, INITIALIZE_WINDOW_HEIGHT));
        setResizable(false);
        setLayout(new BorderLayout());
        setTitle(TITLE);

        settings = new AspectSettings();

        Border outline = new LineBorder(LIGHT_COLOR, 1, false);
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

    public static void updateProgressBar(int value) {
        SwingUtilities.invokeLater(() -> progressBar.setValue(value));
    }

    public static AspectSettings getSettings() {
        return settings;
    }

    private void title() {
        JLabel title = new JLabel(TITLE, SwingConstants.CENTER);
        title.setFocusable(false);

        //title compound borders
        Border outline = new LineBorder(INFO_BOX_COLOR, 5);
        Border margin = new EmptyBorder(4, 0, 0, 0);
        Border border = new CompoundBorder(outline, margin);

        title.setBorder(border);

        title.setFont(BIG_FONT);
        title.setForeground(LIGHT_COLOR);
        title.setBackground(BACKGROUND_SHADED_COLOR);
        title.setOpaque(true);
        add(title, BorderLayout.NORTH);
    }

    private void options(Border border) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(BACKGROUND_SHADED_COLOR);
        GridBagConstraints c = new GridBagConstraints();

        //creating radio buttons and container
        ButtonGroup radioGroup = new ButtonGroup();
        DarkerRadioButton load = new DarkerRadioButton("Load File", NORMAL_FONT);
        load.setToolTipText("Loads up a JSON or XML file to resume editing");
        load.addActionListener(new RadioListener(RadioListener.StartOptions.LOAD, this));
        load.setSelected(true);

        DarkerRadioButton auto = new DarkerRadioButton("Auto Scale", NORMAL_FONT);
        auto.setToolTipText("Creates new level with dimensions that auto scale according to your monitor");
        auto.addActionListener(new RadioListener(RadioListener.StartOptions.AUTO, this));

        DarkerRadioButton customAR = new DarkerRadioButton("Custom by Aspect Ratio", NORMAL_FONT);
        customAR.setToolTipText("Creates new level with custom aspect ratio properties below");
        customAR.addActionListener(new RadioListener(RadioListener.StartOptions.CUSTOM_AR, this));

        DarkerRadioButton customAbsolute = new DarkerRadioButton("Custom by Absolute", NORMAL_FONT);
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
        panel.setBackground(BACKGROUND_SHADED_COLOR);

        //header
        JLabel choiceLabel = new JLabel("Absolute Size", SwingConstants.CENTER);
        choiceLabel.setFocusable(false);
        choiceLabel.setFont(NORMAL_FONT);
        choiceLabel.setForeground(LIGHT_COLOR);
        choiceLabel.setBackground(BACKGROUND_SHADED_COLOR);
        choiceLabel.setOpaque(true);

        //choice 1 label
        JLabel ch1 = new JLabel("Width in Pixels: ", SwingConstants.CENTER);
        ch1.setFocusable(false);
        ch1.setFont(NORMAL_FONT);
        ch1.setForeground(LIGHT_COLOR);
        ch1.setBackground(BACKGROUND_SHADED_COLOR);
        ch1.setOpaque(true);
        //ch1 text field
        JTextField ch1TextField = new JTextField();
        ch1TextField.setFocusable(true);
        ch1TextField.setHorizontalAlignment(JTextField.CENTER);
        ch1TextField.setFont(NORMAL_FONT);
        ch1TextField.setPreferredSize(new Dimension(60, 20));
        ch1TextField.setForeground(LIGHT_COLOR);
        ch1TextField.setBackground(BACKGROUND_SHADED_COLOR);
        ch1TextField.setBorder(border);
        ch1TextField.setText("" + settings.getLvlMakerWidth());

        absoluteWidth = ch1TextField;
        absoluteWidth.getDocument().addDocumentListener(new AbsolutePixelListener(this, settings, AbsolutePixelListener.Dimension.WIDTH));

        //choice 2 label
        JLabel ch2 = new JLabel("Height in Pixels: ", SwingConstants.CENTER);
        ch2.setFocusable(false);
        ch2.setFont(NORMAL_FONT);
        ch2.setForeground(LIGHT_COLOR);
        ch2.setBackground(BACKGROUND_SHADED_COLOR);
        ch2.setOpaque(true);
        //ch2 text field
        JTextField ch2TextField = new JTextField();
        ch2TextField.setFocusable(true);
        ch2TextField.setHorizontalAlignment(JTextField.CENTER);
        ch2TextField.setFont(NORMAL_FONT);
        ch2TextField.setPreferredSize(new Dimension(60, 20));
        ch2TextField.setForeground(LIGHT_COLOR);
        ch2TextField.setBackground(BACKGROUND_SHADED_COLOR);
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
        mLabel.setFont(NORMAL_FONT);
        mLabel.setForeground(LIGHT_COLOR);
        mLabel.setBackground(BACKGROUND_SHADED_COLOR);
        mLabel.setOpaque(true);
        mLabel.setToolTipText("Should be set proportionally to Aspect Ratio");

        //multiplier
        multiplierField = new JTextField();
        multiplierField.setFocusable(true);
        multiplierField.setHorizontalAlignment(JTextField.CENTER);
        multiplierField.setFont(NORMAL_FONT);
        multiplierField.setPreferredSize(new Dimension(60, 20));
        multiplierField.setForeground(LIGHT_COLOR);
        multiplierField.setBackground(BACKGROUND_SHADED_COLOR);
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

    //work on this later
    private JPanel choicePanel(String title, String choice1, String choice2, int hGap, boolean isAspectNotPixels, Border border) {
        JPanel panel = new JPanel();
        BorderLayout layout = new BorderLayout();
        layout.setVgap(15);
        panel.setLayout(layout);
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panel.setBackground(BACKGROUND_SHADED_COLOR);

        //header
        JLabel choiceLabel = new JLabel(title, SwingConstants.CENTER);
        choiceLabel.setFocusable(false);
        choiceLabel.setFont(NORMAL_FONT);
        choiceLabel.setForeground(LIGHT_COLOR);
        choiceLabel.setBackground(BACKGROUND_SHADED_COLOR);
        choiceLabel.setOpaque(true);

        //choice 1 label
        JLabel ch1 = new JLabel(choice1, SwingConstants.CENTER);
        ch1.setFocusable(false);
        ch1.setFont(NORMAL_FONT);
        ch1.setForeground(LIGHT_COLOR);
        ch1.setBackground(BACKGROUND_SHADED_COLOR);
        ch1.setOpaque(true);
        //ch1 text field
        JTextField ch1TextField = new JTextField();
        ch1TextField.setFocusable(true);
        ch1TextField.setHorizontalAlignment(JTextField.CENTER);
        ch1TextField.setFont(NORMAL_FONT);
        ch1TextField.setPreferredSize(new Dimension(60, 20));
        ch1TextField.setForeground(LIGHT_COLOR);
        ch1TextField.setBackground(BACKGROUND_SHADED_COLOR);
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
        ch2.setFont(NORMAL_FONT);
        ch2.setForeground(LIGHT_COLOR);
        ch2.setBackground(BACKGROUND_SHADED_COLOR);
        ch2.setOpaque(true);
        //ch2 text field
        JTextField ch2TextField = new JTextField();
        ch2TextField.setFocusable(true);
        ch2TextField.setHorizontalAlignment(JTextField.CENTER);
        ch2TextField.setFont(NORMAL_FONT);
        ch2TextField.setPreferredSize(new Dimension(60, 20));
        ch2TextField.setForeground(LIGHT_COLOR);
        ch2TextField.setBackground(BACKGROUND_SHADED_COLOR);
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
        pathLabel.setFont(NORMAL_FONT);
        pathLabel.setForeground(LIGHT_COLOR);
        pathLabel.setBackground(BACKGROUND_SHADED_COLOR);
        pathLabel.setOpaque(true);

        //JTextField
        pathField = new JTextField("C:\\");
        pathField.setFocusable(true);
        pathField.setHorizontalAlignment(JTextField.LEFT);
        pathField.setFont(NORMAL_FONT);
        pathField.setPreferredSize(new Dimension(300, 20));
        pathField.setForeground(LIGHT_COLOR);
        pathField.setBackground(BACKGROUND_SHADED_COLOR);
        pathField.setBorder(border);

        pathField.setToolTipText("Enter the complete path of the file to load");

        //directory label/button
        DarkJButton directory = new DarkJButton("...", 23, 20, SMALL_FONT, BACKGROUND_SHADED_COLOR, LIGHT_COLOR);
        directory.setBorder(new LineBorder(LIGHT_COLOR.darker()));
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
        panel.setBackground(BACKGROUND_SHADED_COLOR);
        panel.setBorder(new EmptyBorder(0, 0, 5, 0));

        //start button
        DarkJButton start = new DarkJButton("Begin", 80, 30, START_FONT, START_BUTTON_COLOR, LIGHT_COLOR);
        start.addActionListener(new BeginListener());
        start.setBorder(new LineBorder(Color.BLACK, 1));

        //progress bar
        JPanel progressPanel = new JPanel(null);
        progressPanel.setFocusable(false);
        progressPanel.setPreferredSize(new Dimension(277, 17));
        progressPanel.setBackground(BACKGROUND_SHADED_COLOR);
        progressPanel.setToolTipText("It's just a loading bar");
        progressPanel.setBorder(BorderFactory.createLineBorder(LIGHT_COLOR, 1));
        progressBar = new DarkProgressBar(PROGRESS_MINIMUM, PROGRESS_MAXIMUM, -4, -1,
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

    private boolean loadFile() {

        filePath = InitializeWindow.pathField.getText();

        File f = new File(filePath);

        //if JSON file
        if (Objects.equals(OpenDirectoryListener.getExtension(f), ExportAction.JSON_EXTENSION)) {

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
        else if (Objects.equals(OpenDirectoryListener.getExtension(f), ExportAction.XML_EXTENSION)) {

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

            loadAssets();

            InitializeWindow.updateProgressBar(100);
            dispose();
        }

    }

    private void loadAssets(){

        for (Circle c : Main.currentLevel.circles)
            c.image = Toolkit.getDefaultToolkit().getImage(c.spritePath);
        for (Ellipse e : Main.currentLevel.ellipses)
            e.image = Toolkit.getDefaultToolkit().getImage(e.spritePath);
        for (Rectangle r : Main.currentLevel.rectangles)
            r.image = Toolkit.getDefaultToolkit().getImage(r.spritePath);
        for (Point p : Main.currentLevel.points)
            p.image = Toolkit.getDefaultToolkit().getImage(p.spritePath);
        for (Path p : Main.currentLevel.paths)
            p.image = Toolkit.getDefaultToolkit().getImage(p.spritePath);
        for (Polygon p : Main.currentLevel.polygons)
            p.image = Toolkit.getDefaultToolkit().getImage(p.spritePath);

    }

}