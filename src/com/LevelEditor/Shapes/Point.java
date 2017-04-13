package com.LevelEditor.Shapes;

import com.LevelEditor.ApplicationWindow;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class Point extends Shape {

    public transient static boolean isHidden;

    public static final String logMessage = "Level - added point";
    public static final String logMessageDelete = "Level - removed point";

    protected int x;
    protected int y;

    //drawing properties
    private static final int pointLineWidth = 3;
    public static final int pointSize = 7;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this(p.getX(), p.getY());
    }

    public float dstX(Point p) {
        return this.x - p.x;
    }

    public float dstY(Point p) {
        return this.y - p.y;
    }

    public float dst2(Point v) {
        final float x_d = v.x - x;
        final float y_d = v.y - y;
        return x_d * x_d + y_d * y_d;
    }

    public void drawPoint(Graphics2D g) {

        if (isHidden)
            return;

        if (super.isSelected)
            g.setColor(ApplicationWindow.selectionColor);
        else
            g.setColor(ApplicationWindow.shapeColor);

        g.setStroke(new BasicStroke(pointLineWidth));

        g.draw(new Line2D.Float(x - pointSize, y - pointSize, x + pointSize, y + pointSize));
        g.draw(new Line2D.Float(x + pointSize, y - pointSize, x - pointSize, y + pointSize));

        g.setStroke(new BasicStroke(1));

    }

    private static BufferedImage realCircle() {

        int diameter = pointSize + pointSize;

        BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        Point center = new Point(pointSize, pointSize);

        for (int i = 0; i < diameter; i++) {
            for (int j = 0; j < diameter; j++) {

                Point drawP = new Point(i, j);

                System.out.println(Math.sqrt(drawP.dst2(center)));

                if (Math.sqrt(drawP.dst2(center)) <= pointSize)
                    g.fillRect(i, j, 1, 1);

            }
        }

        return image;

    }

    private static java.awt.Shape createRingShape(
            float centerX, float centerY, float outerRadius, float thickness) {
        Ellipse2D outer = new Ellipse2D.Double(
                centerX - outerRadius,
                centerY - outerRadius,
                outerRadius + outerRadius,
                outerRadius + outerRadius);
        Ellipse2D inner = new Ellipse2D.Double(
                centerX - outerRadius + thickness,
                centerY - outerRadius + thickness,
                outerRadius + outerRadius - thickness - thickness,
                outerRadius + outerRadius - thickness - thickness);
        Area area = new Area(outer);
        area.subtract(new Area(inner));
        return area;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}