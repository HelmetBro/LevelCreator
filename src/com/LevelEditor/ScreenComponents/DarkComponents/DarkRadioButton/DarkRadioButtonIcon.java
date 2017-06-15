package com.LevelEditor.ScreenComponents.DarkComponents.DarkRadioButton;


import javax.swing.*;
import javax.swing.plaf.UIResource;
import java.awt.*;
import java.io.Serializable;

public class DarkRadioButtonIcon implements Icon, UIResource, Serializable {

    private static final int CONTROL_SIZE = 14;

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        JRadioButton cb = (JRadioButton) c;
        ButtonModel model = cb.getModel();

        if (model.isEnabled()) {
            if (model.isPressed() && model.isArmed()) {
                g.setColor(UIManager.getColor("DarkRadioButton.select"));
                g.fillOval(x, y, CONTROL_SIZE - 1, CONTROL_SIZE - 1);
            } else {
                g.setColor(UIManager.getColor("DarkRadioButton.background"));
                g.fillOval(x, y, CONTROL_SIZE - 1, CONTROL_SIZE - 1);
            }
            g.setColor(UIManager.getColor("DarkRadioButton.foreground"));
        }

        if (model.isSelected())
            drawCheck(g, x, y);
    }

    private void drawCheck(Graphics g, int x, int y) {
        g.fillOval(x + 2, y + 2, CONTROL_SIZE - 5, CONTROL_SIZE - 5);
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
