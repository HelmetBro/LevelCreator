package com.LevelEditor.ScreenComponents.InfoPanels.RightPanel;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.GlobalMouseListeners.CustomMouseMoveListener;
import com.LevelEditor.Resizable;
import com.LevelEditor.ScreenComponents.InfoPanels.SidePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

import static com.LevelEditor.StartWindow.AspectSettings.RULER_HEIGHT;

public class RightYPanel extends SidePanel implements Resizable {

    public RightYPanel(String text, Font font, int x, int y, int width, int height) {
        super(text, font, x, y, width, height);
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }//paint comp

    protected void drawIncrements(Graphics2D g) {

        //drawShape increments
        for (int i = 0; i < height; i += smallLineIncrementY) {

            if (i % bigLineIncrementY == 0) {
                g.setColor(bigLineColor);
                g.drawLine(0, i, bigLineSize - 1, i);
            } else {
                g.setColor(smallLineColor);
                g.drawLine(0, i, smallLineSize - 1, i);
            }

        }//for loop
    }

    protected void drawMarker(Graphics2D g2d) {

        if (drawMarker) {
            AffineTransform backup = g2d.getTransform();

            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(90), 0, 0);
            g2d.transform(transform);

            //transform makes this a little wonky, don't worry about it ;)
            g2d.drawImage(marker, CustomMouseMoveListener.getY() - marker.getHeight() / 2, -marker.getWidth(), null);

            g2d.setTransform(backup);

        }
    }

    @Override
    public void moveComponent(int windowWidth, int windowHeight) {
        int moveDistanceY = (windowHeight - ApplicationWindow.settings.getLvlMakerHeight() - RULER_HEIGHT) / 2;
        setLocation(0, moveDistanceY);
    }
}//RightYPanel
