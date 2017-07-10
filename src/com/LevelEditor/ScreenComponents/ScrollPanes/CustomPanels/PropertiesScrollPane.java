package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels;


import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesBox;
import com.LevelEditor.Shapes.Properties.Property;
import com.LevelEditor.Shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.LevelEditor.ApplicationWindow.CONSOLAS;
import static com.LevelEditor.ApplicationWindow.LIGHT_COLOR;

public class PropertiesScrollPane extends CustomScrollPane {

    private static final Color INFO_BOX_COLOR = new Color(
            LIGHT_COLOR.getRed(),
            LIGHT_COLOR.getGreen(),
            LIGHT_COLOR.getBlue(),
            Math.abs(LIGHT_COLOR.getAlpha() - 200));

    private JLabel infoTip;
    private FlowLayout layout = new FlowLayout(FlowLayout.LEFT,0 , 0);

    public PropertiesScrollPane(int width, int windowHeight) {
        setFocusable(false);

        infoTip = createInfoLabel();

        setViewportView(scrollPanel);

        scrollPanel.setPreferredSize(new Dimension(width, windowHeight));
        scrollPanel.setLayout(layout);
    }

    public void updatePropertyEditor() {

        //all selected shapes
        ArrayList<Shape> shapes = ManageLevelArrayLists.getSelectedShapes();

        scrollPanel.removeAll();

        if (shapes.size() > 0) {
            scrollPanel.remove(infoTip);
        } else {
            scrollPanel.add(infoTip);

            //redrawing pane
            revalidate();
            repaint();

            return;
        }

        for (Shape shape : shapes) {

            PropertiesBox pb = new PropertiesBox(shape);

            //PROPERTIES
            ArrayList<Property> properties = shape.getProperties();

            for (Property property : properties)
                pb.addPropertyBox(property);

            //add button
            pb.showAddButton();

            scrollPanel.add(pb);

        }//big for

        revalidate();
        repaint();
    }

    private JLabel createInfoLabel() {

        JLabel infoTip;

        int labelWidth = 130;
        int labelHeight = 70;

        infoTip = new JLabel("<html><div style='text-align: center;'>" +
                "[select shapes<br>to add<br>properties]" +
                "</div></html>", SwingConstants.CENTER);
        infoTip.setPreferredSize(new Dimension(labelWidth, labelHeight));
        infoTip.setFont(CONSOLAS);

        infoTip.setForeground(INFO_BOX_COLOR);
        infoTip.setBorder(BorderFactory.createLineBorder(INFO_BOX_COLOR));
        infoTip.setBackground(super.getBackground());

        return infoTip;
    }

}