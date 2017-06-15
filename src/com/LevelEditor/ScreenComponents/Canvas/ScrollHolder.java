package com.LevelEditor.ScreenComponents.Canvas;

import com.LevelEditor.Main;
import com.LevelEditor.Resizable;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkJButton;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomScrollBarUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.LevelEditor.ApplicationWindow.*;

public class ScrollHolder extends JScrollPane implements Resizable {

    private CanvasHolder holder;

    private int startWidth;
    private int startHeight;

    public ScrollHolder(CanvasHolder component, int x, int y, int startWindowWidth, int startWindowHeight) {
        super(component);
        this.holder = component;
        this.startWidth = startWindowWidth;
        this.startHeight = startWindowHeight;
        setBorder(BorderFactory.createEmptyBorder());
        setLocation(x, y);
        getVerticalScrollBar().setUI(new CustomScrollBarUI());
        getHorizontalScrollBar().setUI(new CustomScrollBarUI());

        getVerticalScrollBar().setUnitIncrement(5);
        getHorizontalScrollBar().setUnitIncrement(5);

        setCorner(JScrollPane.LOWER_RIGHT_CORNER, new SnapCorner("+", 0, 0, CONSOLAS, BACKGROUND_SHADED_COLOR, LIGHT_COLOR));

        setBackground(Color.BLACK);
    }

    @Override
    public void moveComponent(int windowWidth, int windowHeight) {
        setSize(new Dimension(holder.getWidth(), holder.getHeight()));
    }

    //corner snap
    class SnapCorner extends DarkJButton {

        SnapCorner(String text, int width, int height, Font font, Color background, Color foreground) {
            super(text, width, height, font, background, foreground);
            setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, LIGHT_COLOR));
            addActionListener(new Resizer());
        }

        class Resizer implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.applicationWindow.forceWindowResize(startWidth, startHeight);

                //resetting zoom level
                Canvas.currentZoom = 1f;
                Canvas.translateCoorX = 0;
                Canvas.translateCoorY = 0;
            }

        }//resizer

    }//snapcorner

}