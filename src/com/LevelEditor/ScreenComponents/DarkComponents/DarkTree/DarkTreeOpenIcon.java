package com.LevelEditor.ScreenComponents.DarkComponents.DarkTree;

import javax.swing.*;
import javax.swing.plaf.UIResource;
import java.awt.*;
import java.io.Serializable;

import static com.LevelEditor.ApplicationWindow.LIGHT_COLOR;

public class DarkTreeOpenIcon implements Icon, UIResource, Serializable {

    private static final int CONTROL_SIZE = 12;

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(LIGHT_COLOR);
        g.drawRect(1, 1, CONTROL_SIZE, CONTROL_SIZE);
        g.drawRect(4, 4, CONTROL_SIZE - 6, CONTROL_SIZE - 6);
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