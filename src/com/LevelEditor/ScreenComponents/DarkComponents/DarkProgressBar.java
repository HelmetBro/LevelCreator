package com.LevelEditor.ScreenComponents.DarkComponents;

import com.LevelEditor.ApplicationWindow;

import javax.swing.*;

public class DarkProgressBar extends JProgressBar {

    public DarkProgressBar(int min, int max, int x, int y, int width, int height) {
        super(min, max);
        setBounds(x, y, width, height);
        setFocusable(false);
        setBorderPainted(false);
        setToolTipText("Loading bar");
        setBorderPainted(false);
        setForeground(ApplicationWindow.selectionColor);
        setStringPainted(true);
        setString("");
    }

}