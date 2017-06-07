package com.LevelEditor.ScreenComponents.InfoPanels;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Resizable;

import javax.swing.*;
import java.awt.*;

public abstract class BackPanel extends JPanel implements Resizable {

    protected static final Color FADE_BACKGROUND_COLOR = ApplicationWindow.backgroundShadedColor.brighter().brighter().brighter();
    private static final Color BASE_BACKGROUND_COLOR = Color.BLACK;

    protected int startWidth;
    protected int startHeight;

    protected int x;
    protected int y;

    public BackPanel(int width, int height, int x, int y) {
        this.startWidth = width;
        this.startHeight = height;
        this.x = x;
        this.y = y;

        setBounds(x, y, width, height);
        setLayout(null);

        setBackground(BASE_BACKGROUND_COLOR);
        setFocusable(false);
        setVisible(true); //COULD OVERRIDE PAINT AND BACK IT FADE OOOOOOOOOOOOO
    }

}
