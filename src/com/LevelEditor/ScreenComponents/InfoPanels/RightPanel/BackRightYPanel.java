package com.LevelEditor.ScreenComponents.InfoPanels.RightPanel;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Resizable;
import com.LevelEditor.ScreenComponents.InfoPanels.BackPanel;
import com.LevelEditor.StartWindow.AspectSettings;

import java.awt.*;

import static com.LevelEditor.StartWindow.AspectSettings.RULER_HEIGHT;
import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class BackRightYPanel extends BackPanel {

    private RightYPanel rightYPanel;

    public BackRightYPanel(String text, Font font, int x, int y, int width, int height) {
        super(width, height, x, y);
        rightYPanel = new RightYPanel(text, font, x, y, width, height);
        add(rightYPanel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < RULER_WIDTH; i++) {

            float newAlpha = FADE_BACKGROUND_COLOR.getAlpha() - (255 * i / (float) RULER_WIDTH);

            g.setColor(new Color(
                    FADE_BACKGROUND_COLOR.getRed(),
                    FADE_BACKGROUND_COLOR.getGreen(),
                    FADE_BACKGROUND_COLOR.getBlue(),
                    (int) newAlpha)
            );

            g.drawLine(getWidth() - i, 0, getWidth() - i, getHeight());
        }

    }

    @Override
    public void moveComponent(int windowWidth, int windowHeight) {
        int moveDistanceX = (windowWidth - ApplicationWindow.settings.getLvlMakerWidth() - RULER_WIDTH - AspectSettings.TOOLS_WINDOW_SIZE_X);
        int resizeHeight = windowHeight - ApplicationWindow.settings.getLvlMakerHeight() - RULER_HEIGHT;

        //setLocation(x + moveDistanceX, y);
        setBounds(x + moveDistanceX, y, startWidth, startHeight + resizeHeight + Resizable.Y_OFFSET);

        rightYPanel.moveComponent(windowWidth, windowHeight);
    }
}
