package com.LevelEditor.Shapes;

import com.LevelEditor.ApplicationWindow;

import java.awt.*;


public class Ellipse extends Shape {

    public transient static boolean isHidden;

    public static final String logMessage = "Level - added ellipse";
    public static final String logMessageDelete = "Level - removed ellipse";

    private Point topLeft;
    private Point center;

    public int width;
    public int height;

    public Ellipse() {
        this(new Point(0, 0), 0, 0);
    }

    public Ellipse(Point p, int width, int height) {
        this.topLeft = p;
        this.center = new Point(topLeft.x + width / 2, topLeft.y + height / 2);
        this.width = width;
        this.height = height;
    }

    public void drawEllipse(Graphics2D g) {

        if (isHidden)
            return;

        if (super.isSelected)
            g.setColor(ApplicationWindow.selectionColor);
        else
            g.setColor(ApplicationWindow.shapeColor);

        if (isFilled)
            g.fillOval(topLeft.x, topLeft.y, width, height);
        else
            g.drawOval(topLeft.x, topLeft.y, width, height);
    }

    public void setCenter(Point p) {
        this.center = p;
        this.topLeft = new Point(center.x - width / 2, center.y - height / 2);
    }

    public void setTopLeft(Point p) {
        this.topLeft = p;
        this.center = new Point(topLeft.x + width / 2, topLeft.y + height / 2);
    }

    public Point getTopLeft() {
        return this.topLeft;
    }

    public Point getCenter() {
        return this.center;
    }

    @Override
    public Ellipse copyFlip() {
        Ellipse copy = new Ellipse();

        copy.width = this.width;
        copy.height = this.height;

        copy.center = this.center.copyFlip();
        copy.topLeft = this.topLeft.copyFlip();
        copy.topLeft.y = copy.topLeft.y - copy.height;

        return copy;
    }

}
