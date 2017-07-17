package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents;

import com.LevelEditor.ScreenComponents.DarkComponents.DarkJButton;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.SpriteLinkerListeners.OpenSpriteListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.SpriteLinkerListeners.PathTextListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.SpriteLinkerListeners.SizeTextListener;
import com.LevelEditor.Shapes.Shape;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.BACKGROUND_SHADED_COLOR;
import static com.LevelEditor.ApplicationWindow.LIGHT_COLOR;
import static com.LevelEditor.StartWindow.AspectSettings.TOOLS_WINDOW_SIZE_X;
import static com.LevelEditor.StartWindow.InitializeWindow.NORMAL_FONT;
import static com.LevelEditor.StartWindow.InitializeWindow.SMALL_FONT;

public class SpriteLinkerBox extends Container {

    //reference
    private Shape shape;

    //properties
    private Color textColor = new Color(106, 106, 103);
    private final static int V_GAP = 5;
    private final static int NUM_BOXES = 4;
    private final static int BOX_HEIGHT = 20;
    private final static int PANEL_HEIGHT = NUM_BOXES * BOX_HEIGHT + NUM_BOXES * V_GAP;

    //path
    private JTextField pathField;

    //dimensions
    private JTextField dataFieldW;
    private JTextField dataFieldH;

    public SpriteLinkerBox(Shape shape){
        this.shape = shape;
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, V_GAP));
        this.setPreferredSize(new Dimension(TOOLS_WINDOW_SIZE_X - 1, PANEL_HEIGHT));

        JLabel shapeName = shapeName(shape.name);
        Container widthBox = dimensionBox(true);
        Container heightBox = dimensionBox(false);
        Container linker = linker();

        this.add(shapeName);
        this.add(linker);
        this.add(widthBox);
        this.add(heightBox);
    }

    private JLabel shapeName(String name){

        JLabel label = new JLabel(name);
        label.setPreferredSize(new Dimension(TOOLS_WINDOW_SIZE_X - 1, BOX_HEIGHT));
        label.setFont(NORMAL_FONT);
        label.setToolTipText("Name of shape with sprite.");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        return label;
    }

    private Container linker(){
        Container c = new Container();
        c.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        //text field
        pathField = new JTextField();
        pathField.setFocusable(true);
        pathField.setHorizontalAlignment(JTextField.CENTER);
        pathField.setFont(NORMAL_FONT.deriveFont(13f));
        pathField.setForeground(textColor);
        pathField.setBackground(BACKGROUND_SHADED_COLOR.darker());
        pathField.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, LIGHT_COLOR.darker()));
        pathField.setPreferredSize(new Dimension(TOOLS_WINDOW_SIZE_X - BOX_HEIGHT - 1, BOX_HEIGHT));
        pathField.getDocument().addDocumentListener(new PathTextListener(shape));

        if (shape.spritePath == null)
            pathField.setText("[sprite path]");
        else
            pathField.setText(shape.spritePath);

        //link button
        DarkJButton linker = new DarkJButton("::", BOX_HEIGHT, BOX_HEIGHT, NORMAL_FONT,
                BACKGROUND_SHADED_COLOR.darker().darker(), LIGHT_COLOR);
        linker.setToolTipText("Click to link sprite.");
        linker.setBorderPainted(false);
        linker.setBorder(new LineBorder(Color.BLACK, 0));
        linker.addActionListener(new OpenSpriteListener(shape, pathField, dataFieldW, dataFieldH));

        //filling container
        c.add(linker);
        c.add(pathField);

        return c;

    }

    private Container dimensionBox(boolean isWidth){

        Container c = new Container();
        c.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        //label
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(BOX_HEIGHT, BOX_HEIGHT));
        label.setFont(NORMAL_FONT);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        if(isWidth){
            label.setText("W");
            label.setToolTipText("Width of sprite on editor.");
        }else{
            label.setText("H");
            label.setToolTipText("Height of sprite on editor.");
        }

        //text editor
        JTextField dataField = new JTextField();
        dataField.setFocusable(true);
        dataField.setHorizontalAlignment(JTextField.CENTER);
        dataField.setFont(SMALL_FONT);
        dataField.setPreferredSize(new Dimension(
                TOOLS_WINDOW_SIZE_X - BOX_HEIGHT - 1, BOX_HEIGHT));
        dataField.setForeground(textColor.brighter());
        dataField.setBackground(BACKGROUND_SHADED_COLOR);
        dataField.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, textColor.darker().darker()));
        dataField.getDocument().addDocumentListener(new SizeTextListener(shape, isWidth));

        c.add(label);

        if(isWidth){
            dataField.setText("" + shape.spriteW);
            dataFieldW = dataField;
            c.add(dataFieldW);
        }else{
            dataField.setText("" + shape.spriteH);
            dataFieldH = dataField;
            c.add(dataFieldH);
        }

        return c;

    }

}
