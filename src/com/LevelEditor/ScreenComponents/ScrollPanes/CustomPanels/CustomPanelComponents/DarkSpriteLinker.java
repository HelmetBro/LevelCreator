package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents;

import com.LevelEditor.ScreenComponents.DarkComponents.DarkJButton;
import com.LevelEditor.Shapes.Shape;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.BACKGROUND_SHADED_COLOR;
import static com.LevelEditor.ApplicationWindow.LIGHT_COLOR;
import static com.LevelEditor.ApplicationWindow.scrollHolder;
import static com.LevelEditor.StartWindow.AspectSettings.TOOLS_WINDOW_SIZE_X;
import static com.LevelEditor.StartWindow.InitializeWindow.NORMAL_FONT;
import static com.LevelEditor.StartWindow.InitializeWindow.SMALL_FONT;

public class DarkSpriteLinker extends JPanel {

    //reference
    private Shape shape;

    //components
    private JLabel shapeName;
    private Container linker;
    private Container widthBox;
    private Container heightBox;

    private int width = 0;
    private int height = 0;

    //properties
    private Color textColor = new Color(106, 106, 103);
    private final static int V_GAP = 5;
    private final static int NUM_BOXES = 4;
    private final static int BOX_HEIGHT = 20;
    private final static int PANEL_HEIGHT = NUM_BOXES * BOX_HEIGHT + NUM_BOXES * V_GAP;


    public DarkSpriteLinker(Shape shape){
        this.shape = shape;
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, V_GAP));


        System.out.println(PANEL_HEIGHT);
        System.out.println();
        this.setPreferredSize(new Dimension(TOOLS_WINDOW_SIZE_X, PANEL_HEIGHT));

        shapeName = shapeName(shape.name);
        linker = linker();
        widthBox = dimensionBox("W", width, "Width of sprite on editor.");
        heightBox = dimensionBox("H", height, "Height of sprite on editor.");

        this.add(shapeName);
        this.add(linker);
        this.add(widthBox);
        this.add(heightBox);

    }

    private JLabel shapeName(String name){

        JLabel label = new JLabel(name);
        label.setPreferredSize(new Dimension(TOOLS_WINDOW_SIZE_X, BOX_HEIGHT));
        label.setFont(NORMAL_FONT);
        label.setToolTipText("Name of shape with sprite.");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        return label;
    }

    private Container linker(){
        Container c = new Container();
        c.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        //link button
        DarkJButton linker = new DarkJButton("::", BOX_HEIGHT, BOX_HEIGHT, NORMAL_FONT,
                BACKGROUND_SHADED_COLOR.darker().darker(), LIGHT_COLOR);
        linker.setToolTipText("Click to link sprite.");
        linker.setBorderPainted(false);
        linker.setBorder(new LineBorder(Color.BLACK, 0));

        //text field
        JTextField pathField = new JTextField();
        pathField.setFocusable(true);
        pathField.setHorizontalAlignment(JTextField.CENTER);
        pathField.setFont(NORMAL_FONT.deriveFont(13f));
        pathField.setForeground(textColor);
        pathField.setBackground(BACKGROUND_SHADED_COLOR.darker());
        pathField.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, LIGHT_COLOR.darker()));
        pathField.setText("[sprite path]");
        pathField.setPreferredSize(new Dimension(
                TOOLS_WINDOW_SIZE_X - BOX_HEIGHT - (int)scrollHolder.getVerticalScrollBar().getPreferredSize().getWidth() - 3,
                BOX_HEIGHT));

        //filling container
        c.add(linker);
        c.add(pathField);

        return c;

    }

    private Container dimensionBox(String labelText, int dataText, String toolTip){

        Container c = new Container();
        c.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        //label
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(BOX_HEIGHT, BOX_HEIGHT));
        label.setFont(NORMAL_FONT);
        label.setToolTipText(toolTip);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        //text editor
        JTextField dataField = new JTextField();
        dataField.setFocusable(true);
        dataField.setHorizontalAlignment(JTextField.CENTER);
        dataField.setFont(SMALL_FONT);
        dataField.setPreferredSize(new Dimension(
                TOOLS_WINDOW_SIZE_X - BOX_HEIGHT - (int)scrollHolder.getVerticalScrollBar().getPreferredSize().getWidth() - 3,
                BOX_HEIGHT));
        dataField.setForeground(textColor.brighter());
        dataField.setBackground(BACKGROUND_SHADED_COLOR);
        dataField.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
        dataField.setText("" + dataText);

        c.add(label);
        c.add(dataField);

        return c;

    }

}
