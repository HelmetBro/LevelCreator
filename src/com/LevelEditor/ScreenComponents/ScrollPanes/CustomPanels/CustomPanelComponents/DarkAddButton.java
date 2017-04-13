package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents;

import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.AddPropertyListener;
import com.LevelEditor.Shapes.Shape;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.*;


public class DarkAddButton extends JButton {

    private static final int width = 20;
    private static final int height = width;

    private static final int yOffset = 5;

    public DarkAddButton(int x, int y, Shape shape, String text) {
        super(text);
        setBorder(new LineBorder(Color.BLACK, 1));
        setFocusable(false);
        setBackground(backgroundColor);
        setForeground(lightColor);
        setFont(basicFont.deriveFont(19f));
        setBounds(x, y + yOffset, width, height);
        setToolTipText("Click to add property to shape");
        setFocusPainted(false);
        setContentAreaFilled(false);

        addActionListener(new AddPropertyListener(shape));
    }

}