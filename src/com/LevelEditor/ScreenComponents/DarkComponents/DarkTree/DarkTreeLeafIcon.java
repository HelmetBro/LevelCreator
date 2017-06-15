package com.LevelEditor.ScreenComponents.DarkComponents.DarkTree;

import javax.swing.*;
import javax.swing.plaf.UIResource;
import java.awt.*;
import java.io.Serializable;

import static com.LevelEditor.ApplicationWindow.LIGHT_COLOR;

public class DarkTreeLeafIcon implements Icon, UIResource, Serializable {

    private static final int CONTROL_SIZE = 12;

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(LIGHT_COLOR);
        g.drawRect(4, 4, CONTROL_SIZE - 5, CONTROL_SIZE - 5);
    }

    @Override
    public int getIconWidth() {
        return CONTROL_SIZE;
    }

    @Override
    public int getIconHeight() {
        return CONTROL_SIZE;
    }
}