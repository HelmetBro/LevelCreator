package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents;


import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.geom.GeneralPath;

import static com.LevelEditor.ApplicationWindow.BACKGROUND_SHADED_COLOR;
import static com.LevelEditor.ApplicationWindow.LIGHT_COLOR;

public class DarkSlider extends BasicSliderUI {

    private static final int WIDTH = 16;
    private static final int HEIGHT = 25;

    public DarkSlider(JSlider b) {
        super(b);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g, c);
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke old = g2d.getStroke();
        g2d.setPaint(LIGHT_COLOR);
        if (slider.getOrientation() == SwingConstants.HORIZONTAL) {
            g2d.drawLine(trackRect.x, trackRect.y + trackRect.height / 2,
                    trackRect.x + trackRect.width, trackRect.y + trackRect.height / 2);
        } else {
            g2d.drawLine(trackRect.x + trackRect.width / 2, trackRect.y,
                    trackRect.x + trackRect.width / 2, trackRect.y + trackRect.height);
        }
        g2d.setStroke(old);
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int x1 = thumbRect.x + 2;
        int x2 = thumbRect.x + thumbRect.width - 2;
        int width = thumbRect.width - 4;
        int topY = thumbRect.y + thumbRect.height / 2 - thumbRect.width / 3;

        GeneralPath shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        shape.moveTo(x1, topY);
        shape.lineTo(x2, topY);
        shape.lineTo((x1 + x2) / 2, topY + width);
        shape.closePath();

        g2d.setPaint(BACKGROUND_SHADED_COLOR.brighter().brighter());
        g2d.fill(shape);
        Stroke old = g2d.getStroke();

        g2d.setStroke(new BasicStroke(2f));
        g2d.setPaint(BACKGROUND_SHADED_COLOR.brighter().brighter().brighter().brighter().brighter());
        g2d.draw(shape);
        g2d.setStroke(old);
    }

}
