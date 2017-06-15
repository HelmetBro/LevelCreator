package com.LevelEditor.ScreenComponents.ScrollPanes;


import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.BACKGROUND_SHADED_COLOR;
import static com.LevelEditor.ApplicationWindow.LIGHT_COLOR;

public class CustomScrollBarUI extends BasicScrollBarUI {

    private static final int PIXEL_DECREASE_OF_BAR = 7;

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(BACKGROUND_SHADED_COLOR);
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

        g.setColor(LIGHT_COLOR);

        if (h > w)
            g.fillRect(PIXEL_DECREASE_OF_BAR / 2 + 1, 0, w - 1 - PIXEL_DECREASE_OF_BAR, h - 1);
        else
            g.fillRect(0, PIXEL_DECREASE_OF_BAR / 2 + 1, w - 1, h - 1 - PIXEL_DECREASE_OF_BAR);

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
                    g.setColor(BACKGROUND_SHADED_COLOR.darker());
                else
                    g.setColor(getBackground());

                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };

        //1 is vertical up direction, 7 is left horizontal direction.
        if (direction == 1)
            button.setBorder(BorderFactory.createMatteBorder(
                    0, 1, 1, 0, LIGHT_COLOR));
        else if (direction == 7)
            button.setBorder(BorderFactory.createMatteBorder(
                    1, 0, 0, 1, LIGHT_COLOR));
        else
            button.setBorder(BorderFactory.createMatteBorder(
                    1, 1, 0, 0, LIGHT_COLOR));

        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(super.scrollBarWidth, super.scrollBarWidth));
        button.setFocusable(false);
        button.setBackground(BACKGROUND_SHADED_COLOR.darker().darker().darker().darker());
        button.setForeground(LIGHT_COLOR);

        return button;

    }

}
