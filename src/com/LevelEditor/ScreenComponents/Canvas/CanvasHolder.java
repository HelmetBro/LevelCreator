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
        int newWidth = windowWidth - ApplicationWindow.settings.TOOLS_WINDOW_SIZE_X - RULER_WIDTH;
        int newHeight = windowHeight - RULER_HEIGHT;

        setSize(new Dimension(newWidth, newHeight));
        revalidate();
    }
}