package com.LevelEditor.Shapes;


import com.LevelEditor.ApplicationWindow;

import java.awt.*;

public class Circle extends Shape {

    public transient static boolean isHidden;

    public static final String logMessage = "Level - added circle";
    public static final String logMessageDelete = "Level - removed circle";

    private Point topLeft;
    private Point center;
    public int radius;

    public Circle() {
        this(new Point(0, 0), 0);
    }

    public Circle(Point p, int radius) {
        this.topLeft = p;
        this.center = new Point(topLeft.x + radius, topLeft.y + radius);
        this.radius = radius;
    }

    public void drawCircle(Graphics2D g) {

        if (isHidden)
            return;

        if (super.isSelected)
            g.setColor(ApplicationWindow.selectionColor);
        else
            g.setColor(ApplicationWindow.shapeColor);


        if (isFilled)
            g.fillOval(topLeft.x, topLeft.y, radius * 2, radius * 2);
        else
            g.drawOval(topLeft.x, topLeft.y, radius * 2, radius * 2);

    }

    public void setCenter(Point p) {
        this.center = p;
        this.topLeft = new Point(center.x - radius, center.y - radius);
    }

    public void setTopLeft(Point p) {
        this.topLeft = p;
        this.center = new Point(topLeft.x + radius, topLeft.y + radius);
    }

    public Point getTopLeft() {
        return this.topLeft;
    }

    public Point getCenter() {
        return this.center;
    }

    @Override
    public Circle copyFlip() {
        Circle copy = new Circle();
        copy.radius = this.radius;

        copy.center = this.center.copyFlip();
        copy.topLeft = this.topLeft.copyFlip();
        copy.topLeft.y = copy.topLeft.y - copy.radius * 2;

        return copy;
    }
}
