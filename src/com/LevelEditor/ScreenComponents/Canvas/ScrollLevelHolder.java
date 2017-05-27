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

public class ScrollLevelHolder extends JScrollPane implements Resizable {

    private CanvasHolder holder;

    private int startWidth;
    private int startHeight;

    public ScrollLevelHolder(CanvasHolder component, int x, int y, int startWindowWidth, int startWindowHeight) {
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

        setCorner(JScrollPane.LOWER_RIGHT_CORNER, new SnapCorner("+", 0, 0, basicFont, backgroundShadedColor, lightColor));

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
            setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, lightColor));
            addActionListener(new Resizer());
        }

        class Resizer implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.applicationWindow.forceWindowResize(startWidth, startHeight);
            }

        }//resizer

    }//snapcorner

}