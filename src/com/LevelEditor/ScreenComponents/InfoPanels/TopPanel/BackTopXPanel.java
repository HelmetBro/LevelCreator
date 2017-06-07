package com.LevelEditor.ScreenComponents.InfoPanels.TopPanel;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.ScreenComponents.InfoPanels.BackPanel;

import java.awt.*;

import static com.LevelEditor.StartWindow.AspectSettings.RULER_HEIGHT;
import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class BackTopXPanel extends BackPanel {

    private TopXPanel topXPanel;

    public BackTopXPanel(String text, Font font, int x, int y, int width, int height) {
        super(width, height, x, y);
        topXPanel = new TopXPanel(text, font, x, y, width, height);
        add(topXPanel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < RULER_HEIGHT; i++) {

            float newAlpha = FADE_BACKGROUND_COLOR.getAlpha() - (255 * i / (float) RULER_HEIGHT);

            g.setColor(new Color(
                    FADE_BACKGROUND_COLOR.getRed(),
                    FADE_BACKGROUND_COLOR.getGreen(),
                    FADE_BACKGROUND_COLOR.getBlue(),
                    (int) newAlpha)
            );

            g.drawLine(0, i, getWidth(), i);
        }

    }

    @Override
    public void moveComponent(int windowWidth, int windowHeight) {
        int resizeWidth = windowWidth - ApplicationWindow.settings.getLvlMakerWidth() - RULER_WIDTH - ApplicationWindow.settings.toolsWindowSizeX;
        setBounds(x, y, startWidth + resizeWidth, startHeight);

        topXPanel.moveComponent(windowWidth, windowHeight);
    }
}
