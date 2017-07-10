package com.LevelEditor.ScreenComponents.DarkComponents;

import javax.swing.*;
import java.awt.*;


public class DarkJButton extends JButton {

    private Color baseBackgroundColor;

    public DarkJButton(String text, int width, int height, Font font, Color background, Color foreground) {
        super(text);

        this.baseBackgroundColor = background;

        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setFont(font);
        setBackground(background);
        setForeground(foreground);
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed())
            g.setColor(baseBackgroundColor.darker().darker());
        else
            g.setColor(baseBackgroundColor);

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

}
