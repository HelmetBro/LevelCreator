package com.LevelEditor;

import com.LevelEditor.MouseStates.MouseState;
import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.ScreenComponents.Canvas.CanvasHolder;
import com.LevelEditor.ScreenComponents.Canvas.ScrollHolder;
import com.LevelEditor.ScreenComponents.InfoLabelButton;
import com.LevelEditor.ScreenComponents.InfoPanels.RightPanel.BackRightYPanel;
import com.LevelEditor.ScreenComponents.InfoPanels.TopPanel.BackTopXPanel;
import com.LevelEditor.ScreenComponents.RatioButton;
import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;
import com.LevelEditor.ScreenComponents.ToolBarButton;
import com.LevelEditor.StartWindow.AspectSettings;
import com.LevelEditor.StartWindow.InitializeWindow;
import com.LevelEditor.TabActions.FileTabActions.ExportJSONActionListener;
import com.LevelEditor.TabActions.FileTabActions.ExportXMLActionListener;
import com.LevelEditor.TabActions.FileTabActions.SaveActionListener;
import com.LevelEditor.TabActions.HelpTabActions.DonateActionListener;
import com.LevelEditor.TabActions.HelpTabActions.HowToUseActionListener;
import com.LevelEditor.TabActions.HelpTabActions.ReportBugActionListener;
import com.LevelEditor.TabActions.HelpTabActions.SuggestionActionListener;
import com.LevelEditor.TabActions.ToolsTabActions.SwitchToolActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static com.LevelEditor.StartWindow.AspectSettings.RULER_HEIGHT;
import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class ApplicationWindow extends JFrame {

    public static final Color SELECTION_COLOR = new Color(111, 59, 255, 100);
    public static final Color SHAPE_COLOR = new Color(0, 137, 153, 180);
    public static final Color BACKGROUND_COLOR = new Color(34, 34, 31);
    public static final Color BACKGROUND_SHADED_COLOR = new Color(24, 24, 21);
    public static final Color LIGHT_COLOR = new Color(175, 175, 172);
    public static final Color LIGHT_SHADED_COLOR = new Color(165, 165, 162);
    public static final Color BORDER_COLOR = LIGHT_SHADED_COLOR.darker().darker();
    public static final Font CONSOLAS = new Font("Consolas", Font.PLAIN, 14);
    public static final BasicStroke NORMAL_STROKE = new BasicStroke();
    public static final BasicStroke DASHED_STROKE = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{7}, 0);
    private static final Font MONOSPACED = new Font("monospaced", Font.BOLD, 14);
    public static AspectSettings settings;
    public static InfoLabelButton infoLabelButton;
    public static ScrollPaneHandler scrollPaneHandler;
    public static Canvas canvas;
    public static ScrollHolder scrollHolder;
    public static CanvasHolder panelHolder;
    public static RatioButton ratioButton;
    static BackRightYPanel backRightYPanel;
    static BackTopXPanel backTopXPanel;
    static ToolBarButton toolBarButton;

    public ApplicationWindow(AspectSettings settings) {
        ApplicationWindow.settings = settings;
        InitializeWindow.updateProgressBar(75);

        windowSettings();

        addComponentListener(new ResizeListener(this));
    }

    public void forceWindowResize(int width, int height) {
        setSize(width, height);
        pack();
        setLocationRelativeTo(null);
    }

    private void windowSettings() {

        ClassLoader classLoader = getClass().getClassLoader();

        ImageIcon img = new ImageIcon(classLoader.getResource("LevelEditorIcon.png"));
        setIconImage(img.getImage());

        //mandatory settings
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(3, 3, 0));
        getContentPane().setPreferredSize(new Dimension(settings.getWindowWidth() + AspectSettings.TOOLS_WINDOW_SIZE_X, settings.getWindowHeight()));

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
        backTopXPanel = new BackTopXPanel("" + settings.getLvlMakerWidth(), MONOSPACED, 0, 0, settings.getLvlMakerWidth(), RULER_HEIGHT);

        //right label
        backRightYPanel = new BackRightYPanel("" + settings.getLvlMakerHeight(), MONOSPACED,
                settings.getLvlMakerWidth(), RULER_HEIGHT, RULER_WIDTH, settings.getLvlMakerHeight());

        //ratio label
        ratioButton = new RatioButton(settings.getAspectRatioX() + ":" + settings.getAspectRatioY(), MONOSPACED,
                settings.getLvlMakerWidth(), 0, RULER_WIDTH, RULER_HEIGHT);

        //info thing
        infoLabelButton = new InfoLabelButton(settings.getLvlMakerWidth() + RULER_WIDTH, RULER_HEIGHT, AspectSettings.TOOLS_WINDOW_SIZE_X);

        //scroll pane
        scrollPaneHandler = new ScrollPaneHandler(settings.getLvlMakerWidth() + RULER_WIDTH, RULER_HEIGHT + InfoLabelButton.HEIGHT_OF_LABEL,
                AspectSettings.TOOLS_WINDOW_SIZE_X, settings.getLvlMakerHeight() - InfoLabelButton.HEIGHT_OF_LABEL);

        //tools button
        toolBarButton = new ToolBarButton(MONOSPACED, settings.getLvlMakerWidth() + RULER_WIDTH, 0, AspectSettings.TOOLS_WINDOW_SIZE_X, RULER_HEIGHT);

        //main window (passing in ratio button for zoom animation)
        canvas = new Canvas(0, 0, settings.getLvlMakerWidth(), settings.getLvlMakerHeight());
        //pane holder for scroller
        panelHolder = new CanvasHolder(canvas);
        //scroller that holds it
        scrollHolder = new ScrollHolder(panelHolder, 0, RULER_HEIGHT,
                settings.getWindowWidth() + AspectSettings.TOOLS_WINDOW_SIZE_X,
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
        fileTab.setForeground(LIGHT_SHADED_COLOR);

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
        exit.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        //adding selections to file tab bar
        fileTab.add(save);
        fileTab.add(exportToJson);
        fileTab.add(exportToXML);
        fileTab.add(exit);

        //tools tab------
        JMenu toolsTab = new JMenu("Tools");
        toolsTab.setMnemonic(KeyEvent.VK_T);
        toolsTab.setForeground(LIGHT_SHADED_COLOR);

        JMenuItem circle = new JMenuItem("Circle");
        circle.setMnemonic(KeyEvent.VK_C);
        circle.setToolTipText("Switches current tool to Circle creator");
        circle.addActionListener(new SwitchToolActionListener(MouseState.EMouseStates.CIRCLE));

        JMenuItem ellipse = new JMenuItem("Ellipse");
        ellipse.setMnemonic(KeyEvent.VK_E);
        ellipse.setToolTipText("Switches current tool to ellipse creator");
        ellipse.addActionListener(new SwitchToolActionListener(MouseState.EMouseStates.ELLIPSE));

        JMenuItem point = new JMenuItem("Point");
        point.setMnemonic(KeyEvent.VK_P);
        point.setToolTipText("Switches current tool to point creator");
        point.addActionListener(new SwitchToolActionListener(MouseState.EMouseStates.POINT));

        JMenuItem path = new JMenuItem("Path");
        path.setMnemonic(KeyEvent.VK_A);
        path.setToolTipText("Switches current tool to path creator");
        path.addActionListener(new SwitchToolActionListener(MouseState.EMouseStates.PATH));

        JMenuItem polygon = new JMenuItem("Polygon");
        polygon.setMnemonic(KeyEvent.VK_L);
        polygon.setToolTipText("Switches current tool to polygon creator");
        polygon.addActionListener(new SwitchToolActionListener(MouseState.EMouseStates.POLYGON));

        JMenuItem rectangle = new JMenuItem("Rectangle");
        rectangle.setMnemonic(KeyEvent.VK_R);
        rectangle.setToolTipText("Switches current tool to rectangle creator");
        rectangle.addActionListener(new SwitchToolActionListener(MouseState.EMouseStates.RECTANGLE));

        JMenuItem rotation = new JMenuItem("Rotation");
        rotation.setMnemonic(KeyEvent.VK_O);
        rotation.setToolTipText("Switches current tool to object rotation tool");
        rotation.addActionListener(new SwitchToolActionListener(MouseState.EMouseStates.ROTATION));

        toolsTab.add(circle);
        toolsTab.add(ellipse);
        toolsTab.add(point);
        toolsTab.add(polygon);
        toolsTab.add(path);
        toolsTab.add(rectangle);
        toolsTab.add(rotation);

        //help tab------
        JMenu helpTab = new JMenu("Help");
        helpTab.setMnemonic(KeyEvent.VK_H);
        helpTab.setForeground(LIGHT_SHADED_COLOR);

        JMenuItem donateTab = new JMenuItem("Donate");
        donateTab.setMnemonic(KeyEvent.VK_D);
        donateTab.addActionListener(new DonateActionListener());

        JMenuItem howTab = new JMenuItem("How to use");
        howTab.setMnemonic(KeyEvent.VK_U);
        howTab.addActionListener(new HowToUseActionListener());

        JMenuItem bugTab = new JMenuItem("Report a bug");
        bugTab.setMnemonic(KeyEvent.VK_R);
        bugTab.addActionListener(new ReportBugActionListener());

        JMenuItem suggestTab = new JMenuItem("Make a request");
        suggestTab.setMnemonic(KeyEvent.VK_M);
        suggestTab.addActionListener(new SuggestionActionListener());

        helpTab.add(howTab);
        helpTab.add(bugTab);
        helpTab.add(suggestTab);
        helpTab.add(donateTab);

        //adding file tab bar to menu bar
        menuBar.add(fileTab);
        menuBar.add(toolsTab);
        menuBar.add(helpTab);

        //adding menu bar to JFrame
        setJMenuBar(menuBar);

    }

}
