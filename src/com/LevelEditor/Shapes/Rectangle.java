package com.LevelEditor.Shapes;


import com.LevelEditor.ApplicationWindow;

import java.awt.*;

public class Rectangle extends Shape {

    public transient static boolean isHidden;

    public static final String logMessage = "Level - added rectangle";
    public static final String logMessageDelete = "Level - removed rectangle";

    private Point topLeft;
    private Point center;

    public int width;
    public int height;

    public Rectangle() {
        this(new Point(0, 0), 0, 0);
    }

    public Rectangle(Point p, int width, int height) {
        this.topLeft = p;
        this.center = new Point(topLeft.x + width / 2, topLeft.y + height / 2);
        this.width = width;
        this.height = height;
    }

    public void drawRectangle(Graphics2D g) {

        if (isHidden)
            return;

        if (super.isSelected)
            g.setColor(ApplicationWindow.selectionColor);
        else
            g.setColor(ApplicationWindow.shapeColor);

        if (isFilled)
            g.fillRect(topLeft.x, topLeft.y, width, height);
        else
            g.drawRect(topLeft.x, topLeft.y, width, height);
    }

//    public Polygon toPolygon(){
//
//        Polygon newPoly = new Polygon();
//        newPoly.addPoint(x, y);
//        newPoly.addPoint(x + startWidth, y);
//        newPoly.addPoint(x + startWidth, y + startHeight);
//        newPoly.addPoint(x, y + startHeight);
//
//        return newPoly;
//    }

    public void setCenter(Point p) {
        this.center = p;
        this.topLeft = new Point(center.x - width / 2, center.y - height / 2);
    }

    public void setTopLeft(Point p) {
        this.topLeft = p;
        this.center = new Point(topLeft.x + width / 2, topLeft.y + height / 2);
    }

    public Point getCenter() {
        return this.center;
    }

    public Point getTopLeft() {
        return this.topLeft;
    }

    public boolean hasSize() {
        return width != 0 && height != 0;
    }

    @Override
    public Rectangle copyFlip() {
        Rectangle copy = new Rectangle();

        copy.width = this.width;
        copy.height = this.height;

        copy.center = this.getCenter().copyFlip();
        copy.topLeft = this.getTopLeft().copyFlip();
        copy.topLeft.y = copy.topLeft.y - copy.height;

        return copy;
    }
}
