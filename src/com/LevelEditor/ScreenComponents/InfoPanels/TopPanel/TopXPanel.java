package com.LevelEditor.ScreenComponents.InfoPanels.TopPanel;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.GlobalMouseListeners.CustomMouseMoveListener;
import com.LevelEditor.Resizable;
import com.LevelEditor.ScreenComponents.InfoPanels.SidePanel;

import javax.swing.*;
import java.awt.*;

import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class TopXPanel extends SidePanel implements Resizable {

    public TopXPanel(String text, Font font, int x, int y, int width, int height) {
        super(text, font, x, y, width, height);

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    protected void drawIncrements(Graphics2D g) {

        //draw increments
        for (int i = 0; i < width; i += smallLineIncrementX) {

            if (i % bigLineIncrementX == 0) {
                g.setColor(bigLineColor);
                g.drawLine(i, height, i, height - bigLineSize);
            } else {
                g.setColor(smallLineColor);
                g.drawLine(i, height, i, height - smallLineSize);
            }

        }//for loop
    }

    protected void drawMarker(Graphics2D g) {
        if (drawMarker)
            g.drawImage(marker,
                    CustomMouseMoveListener.getX() - marker.getWidth() / 2,
                    height - marker.getHeight(), null);
    }

    @Override
    public void moveComponent(int windowWidth, int windowHeight) {
        int moveDistanceX = (windowWidth - ApplicationWindow.settings.getLvlMakerWidth() - RULER_WIDTH - ApplicationWindow.settings.toolsWindowSizeX) / 2;
        setLocation(startX + moveDistanceX, 0);
    }
}
