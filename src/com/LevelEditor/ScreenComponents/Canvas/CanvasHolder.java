package com.LevelEditor.ScreenComponents.Canvas;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Resizable;

import javax.swing.*;
import java.awt.*;

import static com.LevelEditor.StartWindow.AspectSettings.RULER_HEIGHT;
import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class CanvasHolder extends JPanel implements Resizable {

    public CanvasHolder(JPanel canvas) {
        super(null);
        setLocation(0, 0);

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(
                ApplicationWindow.settings.getLvlMakerWidth(),
                ApplicationWindow.settings.getLvlMakerHeight()));
        add(canvas);
    }

    @Override
    public void moveComponent(int windowWidth, int windowHeight) {
        int newWidth = ApplicationWindow.settings.getWindowWidth() - ApplicationWindow.settings.toolsWindowSizeX - RULER_WIDTH;
        int newHeight = ApplicationWindow.settings.getWindowHeight();

        setSize(new Dimension(newWidth - 16, newHeight - RULER_HEIGHT - 37 - Resizable.YOffset));
        revalidate();
    }
}