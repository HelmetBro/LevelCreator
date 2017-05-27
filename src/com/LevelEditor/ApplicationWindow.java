package com.LevelEditor;

import com.LevelEditor.ScreenComponents.Canvas.CanvasHolder;
import com.LevelEditor.ScreenComponents.Canvas.ScrollLevelHolder;
import com.LevelEditor.TabActions.FileTabActions.ExportJSONActionListener;
import com.LevelEditor.TabActions.FileTabActions.ExportXMLActionListener;
import com.LevelEditor.TabActions.FileTabActions.SaveActionListener;
import com.LevelEditor.TabActions.HelpTabActions.DonateActionListener;
import com.LevelEditor.TabActions.HelpTabActions.HowToUseActionListener;
import com.LevelEditor.TabActions.HelpTabActions.ReportBugActionListener;
import com.LevelEditor.ScreenComponents.InfoLabelButton;
import com.LevelEditor.ScreenComponents.InfoPanels.RightPanel.BackRightYPanel;
import com.LevelEditor.ScreenComponents.InfoPanels.TopPanel.BackTopXPanel;
import com.LevelEditor.ScreenComponents.Canvas.LevelWindow;
import com.LevelEditor.ScreenComponents.RatioButton;
import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;
import com.LevelEditor.ScreenComponents.ToolBarButton;
import com.LevelEditor.StartWindow.AspectSettings;
import com.LevelEditor.StartWindow.InitializeWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.LevelEditor.StartWindow.AspectSettings.RULER_HEIGHT;
import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class ApplicationWindow extends JFrame {

    public static AspectSettings settings;

    public static InfoLabelButton infoLabelButton;
    public static ScrollPaneHandler scrollPaneHandler;
    public static LevelWindow lvlWindow;
    public static ScrollLevelHolder scrollHolder;
    public static CanvasHolder panelHolder;

    static BackRightYPanel backRightYPanel;
    static BackTopXPanel backTopXPanel;

    static RatioButton ratioButton;
    static ToolBarButton toolBarButton;

    public static final Color selectionColor = new Color(111, 59, 255, 100);
    public static final Color shapeColor = new Color(0, 137, 153, 180);

    public static final Color backgroundColor = new Color(34, 34, 31);
    public static final Color backgroundShadedColor = new Color(24, 24, 21);

    public static final Color lightColor = new Color(175, 175, 172);
    public static final Color lightShadedColor = new Color(165, 165, 162);

    public static final Color borderColor = lightShadedColor.darker().darker();

    private static final Font fontOfLabels = new Font("monospaced", Font.BOLD, 14);
    public static final Font basicFont = new Font("Consolas", Font.PLAIN, 14);

    public ApplicationWindow(AspectSettings settings) {
        ApplicationWindow.settings = settings;
        InitializeWindow.updateProgressBar(75);

        windowSettings();

        addComponentListener(new ResizeListener(this));
    }

    public void forceWindowResize(int width, int height){
        setSize(width, height);
        pack();
    }

    private void windowSettings() {

        ClassLoader classLoader = getClass().getClassLoader();

        try {
            ImageIcon img = new ImageIcon(classLoader.getResource("LevelEditorIcon.png"));
            setIconImage(img.getImage());
        } catch (NullPointerException ignore) {}

        //mandatory settings
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(3, 3, 0));
        getContentPane().setPreferredSize(new Dimension(settings.getWindowWidth() + settings.toolsWindowSizeX, settings.getWindowHeight()));

        createMenuBar();
        standardizeLayout();

        //very last
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void standardizeLayout() {
        setLayout(null);

        //top label
        backTopXPanel = new BackTopXPanel("" + settings.getLvlMakerWidth(), fontOfLabels, 0, 0, settings.getLvlMakerWidth(), RULER_HEIGHT);

        //right label
        backRightYPanel = new BackRightYPanel("" + settings.getLvlMakerHeight(), fontOfLabels,
                settings.getLvlMakerWidth(), RULER_HEIGHT, RULER_WIDTH, settings.getLvlMakerHeight());

        //ratio label
        ratioButton = new RatioButton(settings.getAspectRatioX() + ":" + settings.getAspectRatioY(), fontOfLabels,
                settings.getLvlMakerWidth(), 0, RULER_WIDTH, RULER_HEIGHT);

        //info thing
        infoLabelButton = new InfoLabelButton(settings.getLvlMakerWidth() + RULER_WIDTH, RULER_HEIGHT, settings.toolsWindowSizeX);

        //scroll pane
        scrollPaneHandler = new ScrollPaneHandler(settings.getLvlMakerWidth() + RULER_WIDTH, RULER_HEIGHT + InfoLabelButton.heightOfLabel,
                settings.toolsWindowSizeX, settings.getLvlMakerHeight() - InfoLabelButton.heightOfLabel);

        //tools button
        toolBarButton = new ToolBarButton(fontOfLabels, settings.getLvlMakerWidth() + RULER_WIDTH, 0, settings.toolsWindowSizeX, RULER_HEIGHT);

        //main window
        lvlWindow = new LevelWindow(0, 0, settings.getLvlMakerWidth(), settings.getLvlMakerHeight());
        //pane holder for scroller
        panelHolder = new CanvasHolder(lvlWindow);
        //scroller that holds it
        scrollHolder = new ScrollLevelHolder(panelHolder, 0, RULER_HEIGHT,
                settings.getWindowWidth() + settings.toolsWindowSizeX,
                settings.getWindowHeight());

        //order matters
        add(backTopXPanel);
        add(ratioButton);
        add(toolBarButton);
        add(scrollHolder);
        add(backRightYPanel);
        add(infoLabelButton);
        add(scrollPaneHandler);
    }

    private void createMenuBar() {

        //creating menu
        JMenuBar menuBar = new JMenuBar();

        //file tab------
        JMenu fileTab = new JMenu("File");
        fileTab.setMnemonic(KeyEvent.VK_F);
        fileTab.setForeground(lightShadedColor);

        JMenuItem save = new JMenuItem("Save");
        save.setMnemonic(KeyEvent.VK_S);
        save.setToolTipText("If file is loaded, saves current edits to that file");
        save.addActionListener(new SaveActionListener());

        JMenuItem exportToJson = new JMenuItem("Export To JSON");
        exportToJson.setMnemonic(KeyEvent.VK_J);
        exportToJson.setToolTipText("Exports current configuration to a JSON file");
        exportToJson.addActionListener(new ExportJSONActionListener());

        JMenuItem exportToXML = new JMenuItem("Export To XML");
        exportToXML.setMnemonic(KeyEvent.VK_X);
        exportToXML.setToolTipText("Exports current configuration to an XML file");
        exportToXML.addActionListener(new ExportXMLActionListener());

        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setToolTipText("Exit application");
        exit.addActionListener((ActionEvent event) -> System.exit(0));

        //adding selections to file tab bar
        fileTab.add(save);
        fileTab.add(exportToJson);
        fileTab.add(exportToXML);
        fileTab.add(exit);

        //help tab------
        JMenu helpTab = new JMenu("Help");
        helpTab.setMnemonic(KeyEvent.VK_H);
        helpTab.setForeground(lightShadedColor);

        JMenuItem donateTab = new JMenuItem("Donate");
        donateTab.setMnemonic(KeyEvent.VK_D);
        donateTab.addActionListener(new DonateActionListener());

        JMenuItem howTab = new JMenuItem("How to use");
        howTab.setMnemonic(KeyEvent.VK_U);
        howTab.addActionListener(new HowToUseActionListener());

        JMenuItem bugTab = new JMenuItem("Report a bug");
        bugTab.setMnemonic(KeyEvent.VK_R);
        bugTab.addActionListener(new ReportBugActionListener());

        helpTab.add(howTab);
        helpTab.add(bugTab);
        helpTab.add(donateTab);

        //adding file tab bar to menu bar
        menuBar.add(fileTab);
        menuBar.add(helpTab);

        //adding menu bar to JFrame
        setJMenuBar(menuBar);

    }//createMenuBar

}
