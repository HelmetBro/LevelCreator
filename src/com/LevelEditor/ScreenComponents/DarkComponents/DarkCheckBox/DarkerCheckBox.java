package com.LevelEditor.ScreenComponents.DarkComponents.DarkCheckBox;


import javax.swing.*;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.BACKGROUND_SHADED_COLOR;
import static com.LevelEditor.ApplicationWindow.LIGHT_COLOR;

public class DarkerCheckBox extends JCheckBox {

    public DarkerCheckBox(String text, Font font) {
        super(text);
        setFont(font);
        setFocusable(false);
        setBackground(BACKGROUND_SHADED_COLOR);
        setForeground(LIGHT_COLOR);
    }

}


