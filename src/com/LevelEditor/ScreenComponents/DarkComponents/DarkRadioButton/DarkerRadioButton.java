package com.LevelEditor.ScreenComponents.DarkComponents.DarkRadioButton;

import javax.swing.*;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.BACKGROUND_SHADED_COLOR;
import static com.LevelEditor.ApplicationWindow.LIGHT_COLOR;

public class DarkerRadioButton extends JRadioButton {

    public DarkerRadioButton(String text, Font font) {
        super(text);
        setFocusable(false);
        setFont(font);
        setBackground(BACKGROUND_SHADED_COLOR);
        setForeground(LIGHT_COLOR);
    }

}
