package com.LevelEditor.ScreenComponents.ScrollPanes;


import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.backgroundShadedColor;
import static com.LevelEditor.ApplicationWindow.lightColor;

public class CustomScrollBarUI extends BasicScrollBarUI {

    private static final int pixelDecreaseOfBar = 7;

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(backgroundShadedColor);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

        if (trackHighlight == DECREASE_HIGHLIGHT) {
            paintDecreaseHighlight(g);
        } else if (trackHighlight == INCREASE_HIGHLIGHT) {
            paintIncreaseHighlight(g);
        }
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {

        if (thumbBounds.isEmpty() || !scrollbar.isEnabled())
            return;

        int w = thumbBounds.width;
        int h = thumbBounds.height;

        g.translate(thumbBounds.x, thumbBounds.y);

        g.setColor(lightColor);

        if (h > w)
            g.fillRect(pixelDecreaseOfBar / 2 + 1, 0, w - 1 - pixelDecreaseOfBar, h - 1);
        else
            g.fillRect(0, pixelDecreaseOfBar / 2 + 1, w - 1, h - 1 - pixelDecreaseOfBar);

        g.translate(-thumbBounds.x, -thumbBounds.y);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return makeButtonProperties(orientation);
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return makeButtonProperties(orientation);
    }

    private JButton makeButtonProperties(int direction) {

        JButton button = new JButton() {

            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isPressed())
                    g.setColor(backgroundShadedColor.darker());
                else
                    g.setColor(getBackground());

                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };

        //1 is vertical up direction, 7 is left horizontal direction.
        if (direction == 1)
            button.setBorder(BorderFactory.createMatteBorder(
                    0, 1, 1, 0, lightColor));
        else if (direction == 7)
            button.setBorder(BorderFactory.createMatteBorder(
                    1, 0, 0, 1, lightColor));
        else
            button.setBorder(BorderFactory.createMatteBorder(
                    1, 1, 0, 0, lightColor));

        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(super.scrollBarWidth, super.scrollBarWidth));
        button.setFocusable(false);
        button.setBackground(backgroundShadedColor.darker().darker().darker().darker());
        button.setForeground(lightColor);

        return button;

    }

}
