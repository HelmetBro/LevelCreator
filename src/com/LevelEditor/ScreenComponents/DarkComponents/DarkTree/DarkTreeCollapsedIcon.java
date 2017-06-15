package com.LevelEditor.ScreenComponents.DarkComponents.DarkTree;

import javax.swing.*;
import javax.swing.plaf.UIResource;
import java.awt.*;
import java.io.Serializable;

public class DarkTreeCollapsedIcon implements Icon, UIResource, Serializable {

    private static final int CONTROL_SIZE = 12;

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        //no icon
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