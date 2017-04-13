package com.LevelEditor.ScreenComponents.DarkComponents.DarkCheckBox;


import javax.swing.*;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.backgroundShadedColor;
import static com.LevelEditor.ApplicationWindow.lightColor;

public class DarkerCheckBox extends JCheckBox {

    public DarkerCheckBox(String text, Font font) {
        super(text);
        setFont(font);
        setFocusable(false);
        setBackground(backgroundShadedColor);
        setForeground(lightColor);
    }

}


