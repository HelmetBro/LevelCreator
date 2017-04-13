package com.LevelEditor.ScreenComponents.DarkComponents.DarkTree;

import javax.swing.*;
import javax.swing.plaf.UIResource;
import java.awt.*;
import java.io.Serializable;

import static com.LevelEditor.ApplicationWindow.lightColor;

public class DarkTreeOpenIcon implements Icon, UIResource, Serializable {

    private final int controlSize = 12;

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(lightColor);
        g.drawRect(1, 1, controlSize, controlSize);
        g.drawRect(4, 4, controlSize - 6, controlSize - 6);
    }

    @Override
    public int getIconWidth() {
        return controlSize;
    }

    @Override
    public int getIconHeight() {
        return controlSize;
    }
}