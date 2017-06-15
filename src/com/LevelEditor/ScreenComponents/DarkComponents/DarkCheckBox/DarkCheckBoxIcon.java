package com.LevelEditor.ScreenComponents.DarkComponents.DarkCheckBox;

import javax.swing.*;
import javax.swing.plaf.UIResource;
import java.awt.*;
import java.io.Serializable;

public class DarkCheckBoxIcon implements Icon, UIResource, Serializable {

    private static final int CONTROL_SIZE = 13;

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        JCheckBox cb = (JCheckBox) c;
        ButtonModel model = cb.getModel();

        if (model.isEnabled()) {
            if (model.isPressed() && model.isArmed()) {
                g.setColor(UIManager.getColor("DarkerCheckBox.checkClickBackground"));
                g.fillRect(x, y, CONTROL_SIZE - 1, CONTROL_SIZE - 1);
            } else {
                g.setColor(UIManager.getColor("DarkerCheckBox.checkBackground"));
                g.fillRect(x, y, CONTROL_SIZE - 1, CONTROL_SIZE - 1);
            }

            g.setColor(UIManager.getColor("DarkerCheckBox.checkForeground"));

        } else {
            g.setColor(UIManager.getColor("DarkerCheckBox.checkDisabled"));
            g.drawRect(x, y, CONTROL_SIZE - 1, CONTROL_SIZE - 1);
        }

        if (model.isSelected())
            drawCheck(g, x, y);
    }

    private void drawCheck(Graphics g, int x, int y) {
        g.fillRect(x + 2, y + 2, CONTROL_SIZE - 5, CONTROL_SIZE - 5);
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