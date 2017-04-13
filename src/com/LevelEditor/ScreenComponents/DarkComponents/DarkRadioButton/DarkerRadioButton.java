package com.LevelEditor.ScreenComponents.DarkComponents.DarkRadioButton;

import javax.swing.*;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.backgroundShadedColor;
import static com.LevelEditor.ApplicationWindow.lightColor;

public class DarkerRadioButton extends JRadioButton {

    public DarkerRadioButton(String text, Font font) {
        super(text);
        setFocusable(false);
        setFont(font);
        setBackground(backgroundShadedColor);
        setForeground(lightColor);
    }

}
