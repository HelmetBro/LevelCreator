package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels;


import com.LevelEditor.ScreenComponents.DarkComponents.DarkCheckBox.DarkerCheckBox;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkRadioButton.DarkerRadioButton;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.DarkSlider;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.*;
import com.LevelEditor.Shapes.ShapeType;

import javax.swing.*;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.*;

public class ToolsScrollPane extends CustomScrollPane {

    private static final int spacing = 5;

    private Container editorCheckBoxes;
    private Container visibilityBoxes;
    private Container cursorRadioButtons;
    private Container exportBoxes;
    private Container gridSlider;

    public ToolsScrollPane(int width) {

        //creating main components
        editorCheckBoxes = createEditorCheckBoxes(width);
        visibilityBoxes = createVisibilityBoxes(width);
        cursorRadioButtons = createCursorRadioButtons(width);
        exportBoxes = createExportBoxes(width);
        gridSlider = createSlider(width);

        scrollPanel.add(editorCheckBoxes);
        scrollPanel.add(visibilityBoxes);
        scrollPanel.add(cursorRadioButtons);
        scrollPanel.add(exportBoxes);
        scrollPanel.add(gridSlider);

        //using gridSlider because that's the last container in tools
        scrollPanel.setPreferredSize(new Dimension(
                width - getVerticalScrollBar().getPreferredSize().width,
                gridSlider.getY() + gridSlider.getHeight()));

        //setting it
        setViewportView(scrollPanel);
    }

    private Container createVisibilityBoxes(int width) {
        int numItems = 5;

        Container p = new Container();
        p.setLayout(new GridLayout(numItems + 1, 1));
        p.setBounds(0, editorCheckBoxes.getY() + editorCheckBoxes.getHeight() + spacing, width, (numItems + 1) * 25);
        p.setBackground(backgroundShadedColor);

        //label
        JLabel editorLabel = new JLabel("-Visibility-");
        editorLabel.setFocusable(false);
        editorLabel.setFont(basicFont);
        editorLabel.setBackground(backgroundShadedColor);
        editorLabel.setForeground(lightColor);

        //circles
        DarkerCheckBox circles = new DarkerCheckBox("Hide Circles", basicFont);
        circles.setToolTipText("Enable to hide all circles");
        circles.addActionListener(new HideShapeListener(ShapeType.CIRCLE));

        //ellipses
        DarkerCheckBox ellipses = new DarkerCheckBox("Hide Ellipses", basicFont);
        ellipses.setToolTipText("Enable to hide all ellipses");
        ellipses.addActionListener(new HideShapeListener(ShapeType.ELLIPSE));

        //points
        DarkerCheckBox points = new DarkerCheckBox("Hide Points", basicFont);
        points.setToolTipText("Enable to hide all points");
        points.addActionListener(new HideShapeListener(ShapeType.POINT));

        //polygons
        DarkerCheckBox polygons = new DarkerCheckBox("Hide Polygons", basicFont);
        polygons.setToolTipText("Enable to hide all polygons");
        polygons.addActionListener(new HideShapeListener(ShapeType.POLYGON));

        //rectangles
        DarkerCheckBox rectangles = new DarkerCheckBox("Hide Rectangles", basicFont);
        rectangles.setToolTipText("Enable to hide all rectangles");
        rectangles.addActionListener(new HideShapeListener(ShapeType.RECTANGLE));

        p.add(editorLabel);
        p.add(circles);
        p.add(ellipses);
        p.add(points);
        p.add(polygons);
        p.add(rectangles);

        return p;
    }

    private Container createSlider(int width) {

        int numItems = 3;

        Container c = new Container();
        c.setLayout(new GridLayout(numItems + 1, 1));
        c.setBounds(0, exportBoxes.getY() + exportBoxes.getHeight() + spacing, width, (numItems + 1) * 25);
        c.setBackground(backgroundShadedColor);

        //label
        JLabel sliderLabel = new JLabel("-Grid Size-");
        sliderLabel.setFocusable(false);
        sliderLabel.setFont(basicFont);
        sliderLabel.setBackground(backgroundShadedColor);
        sliderLabel.setForeground(lightColor);

        //value label
        JLabel valueLabel = new JLabel("" + GridListener.gridSizeX);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setVerticalAlignment(SwingConstants.TOP);
        valueLabel.setFocusable(false);
        valueLabel.setFont(basicFont);
        valueLabel.setBackground(backgroundShadedColor);
        valueLabel.setForeground(lightColor);

        //slider
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 5, 100, GridListener.gridSizeX);
        slider.setPaintTicks(false);
        slider.setForeground(lightColor);
        slider.setBackground(backgroundShadedColor);
        slider.setFocusable(false);
        slider.addChangeListener(new SliderListener(valueLabel));
        slider.setPreferredSize(new Dimension(width - 60, 5));
        slider.setUI(new DarkSlider(slider));

        //check box aspect value
        DarkerCheckBox aspectGrid = new DarkerCheckBox("Aspect Ratio Grid", basicFont);
        aspectGrid.setToolTipText("Creates cells with same size as aspect ratio");
        aspectGrid.addActionListener(new AspectGridListener());

        c.add(sliderLabel);
        c.add(slider);
        c.add(valueLabel);
        c.add(aspectGrid);

        return c;
    }

    private Container createExportBoxes(int width) {

        int numItems = 1;

        Container p = new Container();
        p.setLayout(new GridLayout(numItems + 1, 1));
        p.setBounds(0, cursorRadioButtons.getY() + cursorRadioButtons.getHeight() + spacing, width, (numItems + 1) * 25);
        p.setBackground(backgroundShadedColor);

        //label
        JLabel editorLabel = new JLabel("-Export Options-");
        editorLabel.setFocusable(false);
        editorLabel.setFont(basicFont);
        editorLabel.setBackground(backgroundShadedColor);
        editorLabel.setForeground(lightColor);

        //pretty print
        DarkerCheckBox prettyPrint = new DarkerCheckBox("Pretty Print", basicFont);
        prettyPrint.setToolTipText("Writes contents of JSON and XML file with organized layout");
        prettyPrint.addActionListener(new ExportListener());

        p.add(editorLabel);
        p.add(prettyPrint);

        return p;
    }

    private Container createEditorCheckBoxes(int width) {

        int numItems = 7;

        Container p = new Container();
        p.setLayout(new GridLayout(numItems + 1, 1));
        //startWidth is 0 to wrap options in same alignment
        p.setBounds(0, spacing, width, (numItems + 1) * 25);
        p.setBackground(backgroundShadedColor);

        //label
        JLabel editorLabel = new JLabel("-Editor Tools-");
        editorLabel.setFocusable(false);
        editorLabel.setFont(basicFont);
        editorLabel.setBackground(backgroundShadedColor);
        editorLabel.setForeground(lightColor);

        //precision box
        DarkerCheckBox precision = new DarkerCheckBox("Precision Lines", basicFont);
        precision.setToolTipText("Adds lines indicating your mouse position");
        precision.addActionListener(new PrecisionLinesListener());

        //mouse markers
        DarkerCheckBox mouseMarker = new DarkerCheckBox("Mouse Marker", basicFont);
        mouseMarker.setToolTipText("Adds markers on the measuring labels");
        mouseMarker.addActionListener(new MarkerListener());

        //grid
        DarkerCheckBox grid = new DarkerCheckBox("Grid", basicFont);
        grid.setToolTipText("Draws a grid on screen");
        grid.addActionListener(new GridListener());

        //snap to grid
        DarkerCheckBox snap = new DarkerCheckBox("Snap To Grid", basicFont);
        snap.setToolTipText("Restricts point placement to grid size");
        snap.addActionListener(new SnapToGridListener());

        //antiAlias
        DarkerCheckBox antiA = new DarkerCheckBox("Anti-Aliasing", basicFont);
        antiA.setToolTipText("If enabled, all edges will be smooth");
        antiA.setSelected(true);
        antiA.addActionListener(new AntiAliasListener());

        //shapeFill
        DarkerCheckBox shapeFill = new DarkerCheckBox("Shape Fill", basicFont);
        shapeFill.setToolTipText("Fills in all shapes in editor");
        shapeFill.setSelected(true);
        shapeFill.addActionListener(new ShapeFillListener());

        //flipY
        DarkerCheckBox flipY = new DarkerCheckBox("Flip Y-axis", basicFont);
        flipY.setToolTipText("Flips y-axis. Changes vertices of all shapes");
        flipY.addActionListener(new FlipYListener());

        //adding components
        p.add(editorLabel);
        p.add(precision);
        p.add(mouseMarker);
        p.add(grid);
        p.add(snap);
        p.add(antiA);
        p.add(shapeFill);
        p.add(flipY);

        return p;
    }

    private Container createCursorRadioButtons(int width) {

        int numItems = 5;

        Container p = new Container();
        p.setLayout(new GridLayout(numItems + 1, 1));
        p.setBackground(backgroundShadedColor);
        p.setBounds(0, visibilityBoxes.getY() + visibilityBoxes.getHeight() + spacing, width, (numItems + 1) * 25);

        //label
        JLabel cursorLabel = new JLabel("-Cursors-");
        cursorLabel.setFocusable(false);
        cursorLabel.setFont(basicFont);
        cursorLabel.setBackground(backgroundShadedColor);
        cursorLabel.setForeground(lightColor);

        //cursor radio buttons

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        ClassLoader classLoader = getClass().getClassLoader();

        Image imgBlack = toolkit.createImage(classLoader.getResource("BlackCursor.png"));
        Image imgWhite = toolkit.createImage(classLoader.getResource("WhiteCursor.png"));
        Image imgPrecision = toolkit.createImage(classLoader.getResource("PrecisionCursor.png"));
        Image blank = toolkit.createImage("");

        DarkerRadioButton defaultCur = new DarkerRadioButton("Default", basicFont);
        defaultCur.addActionListener(new ChangeCursorListener(Cursor.getDefaultCursor()));
        defaultCur.setSelected(true);
        defaultCur.setToolTipText("OS default cursor");

        DarkerRadioButton precisionCur = new DarkerRadioButton("Hollow", basicFont);
        precisionCur.addActionListener(new ChangeCursorListener(toolkit.createCustomCursor(imgPrecision, new Point(16, 16), "precision cursor")));
        precisionCur.setToolTipText("Hollow-point cursor. Best with smaller levels");

        DarkerRadioButton invisibleCur = new DarkerRadioButton("Invisible", basicFont);
        invisibleCur.addActionListener(new ChangeCursorListener(toolkit.createCustomCursor(blank, new Point(16, 16), "invisible cursor")));
        invisibleCur.setToolTipText("Gets rid of cursor. Best with precision lines and snap to grid");

        DarkerRadioButton whiteCur = new DarkerRadioButton("White", basicFont);
        whiteCur.addActionListener(new ChangeCursorListener(toolkit.createCustomCursor(imgWhite, new Point(16, 16), "white cursor")));
        whiteCur.setToolTipText("Small precise white cursor. Best with darker backgrounds");

        DarkerRadioButton blackCur = new DarkerRadioButton("Black", basicFont);
        blackCur.addActionListener(new ChangeCursorListener(toolkit.createCustomCursor(imgBlack, new Point(16, 16), "black cursor")));
        blackCur.setToolTipText("Small precise black cursor. Best with lighter backgrounds");

        //creating group to hold all radio buttons
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(defaultCur);
        radioGroup.add(precisionCur);
        radioGroup.add(invisibleCur);
        radioGroup.add(blackCur);
        radioGroup.add(whiteCur);

        p.add(cursorLabel);
        p.add(defaultCur);
        p.add(precisionCur);
        p.add(invisibleCur);
        p.add(blackCur);
        p.add(whiteCur);

        return p;
    }

}//JScrollPane
