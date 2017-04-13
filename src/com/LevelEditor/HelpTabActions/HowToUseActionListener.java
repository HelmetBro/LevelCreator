package com.LevelEditor.HelpTabActions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HowToUseActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        JOptionPane.showMessageDialog(null, "This program was designed for developers and " +
                "game designers to create simple levels. Once you're finished,\nyou're able to create a JSON or XML file " +
                "[File > Export to JSON/XML] that contains multiple array lists of circles,\nellipses, points, polygons, " +
                "and rectangles. Tool tips are enabled all around the settings, so hover over them for\na description " +
                "of the tool. Also, don't be afraid to play around! Start smashing buttons and " +
                "clicking everywhere.\nYou might just find something cool!\n\nAlso, it's important to mention the color of the polygons. " +
                "A simple concave polygon shows up in orange,\nand a convex polygon shows up in blue (google that). This is important " +
                "for most developers, as\nsimple concave polygons screw with 2D collision and other mathematical calculations. You " +
                "can always\nmake a concave polygon with multiple convex ones!");

    }
}
