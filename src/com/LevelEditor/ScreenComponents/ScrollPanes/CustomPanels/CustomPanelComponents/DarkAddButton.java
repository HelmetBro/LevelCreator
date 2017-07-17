package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents;

import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.AddPropertyListener;
import com.LevelEditor.Shapes.Shape;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.*;


public class DarkAddButton extends JButton {

    public static final int WIDTH = 30;
    public static final int HEIGHT = WIDTH;

    public DarkAddButton(Shape shape) {
        super("+");

        Border outline = new LineBorder(Color.BLACK, 1);
        Border margin = new LineBorder(BACKGROUND_SHADED_COLOR, 5);
        Border border = new CompoundBorder(margin, outline);

        setBorder(border);
        setFocusable(false);
        setBackground(BACKGROUND_COLOR);
        setForeground(LIGHT_COLOR);
        setFont(CONSOLAS.deriveFont(19f));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setToolTipText("Click to add property to shape.");
        setFocusPainted(false);
        setContentAreaFilled(false);

        addActionListener(new AddPropertyListener(shape));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed())
            g.setColor(BACKGROUND_COLOR.darker().darker());
        else
            g.setColor(BACKGROUND_COLOR);

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

}