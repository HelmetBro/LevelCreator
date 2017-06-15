package com.LevelEditor.ScreenComponents.InfoPanels;


import com.LevelEditor.ApplicationWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MarkerImage extends BufferedImage {

    private static final int width = 12;
    private static final int height = 12;

    MarkerImage() {
        super(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics g = this.createGraphics();

        g.setColor(ApplicationWindow.LIGHT_COLOR);

        //creating triangle polygon
        Polygon triangle = new Polygon();
        triangle.addPoint(0, 0);
        triangle.addPoint(width, 0);
        triangle.addPoint(width / 2, height);

        g.fillPolygon(triangle);

        //keeping this planet green
        g.dispose();

    }
}
