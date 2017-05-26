package com.LevelEditor.ScreenComponents;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Resizable;

import javax.swing.*;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.backgroundColor;
import static com.LevelEditor.ApplicationWindow.borderColor;
import static com.LevelEditor.ApplicationWindow.lightColor;
import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class RatioButton extends JButton implements Resizable {

    private int startX;

    public RatioButton(String text, Font font, int x, int y, int width, int height) {
        super(text);
        this.startX = x;
        setBounds(x, y, width, height);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setFocusable(false);
        setContentAreaFilled(false);
        setFont(font);
        setBackground(backgroundColor);
        setForeground(lightColor);
        setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, borderColor));
        setToolTipText("Multiplier: " + ApplicationWindow.settings.getMultiplier());
        addActionListener(e -> incrementBackground());
    }

    private void incrementBackground() {

        int newIndex = ++LevelWindow.getColorState().index % LevelWindow.numOfBackStates;

        ApplicationWindow.lvlWindow.setColor(LevelWindow.BackgroundColorState.values()[newIndex]);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed())
            g.setColor(backgroundColor.darker().darker());
        else
            g.setColor(getBackground());

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void moveComponent(int windowWidth, int windowHeight) {
        int moveDistance = (windowWidth - ApplicationWindow.settings.getLvlMakerWidth() - RULER_WIDTH - ApplicationWindow.settings.toolsWindowSizeX);
        setLocation(startX + moveDistance, 0);
    }
}
