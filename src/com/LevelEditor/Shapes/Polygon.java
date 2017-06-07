package com.LevelEditor.Shapes;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Utilities;

import javax.xml.bind.annotation.XmlElement;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Polygon extends Shape {

    public transient static boolean isHidden;

    public static final String logMessage = "Level - added polygon";
    public static final String logMessageDelete = "Level - removed polygon";

    private boolean isConvex;
    private int numPoints;

    private static Color concaveColor = new Color(175, 95, 0, 180);

    @XmlElement
    private Stack<Point> points;

    public Polygon() {
        this.points = new Stack<>();
        this.numPoints = 0;
    }

    public void drawPolygon(Graphics2D g) {

        if (isHidden)
            return;

        java.awt.Polygon javaPoly = new java.awt.Polygon();

        for (Point p : points)
            javaPoly.addPoint(p.getX(), p.getY());

        if (super.isSelected)
            g.setColor(ApplicationWindow.selectionColor);
        else if (isConvex)
            g.setColor(ApplicationWindow.shapeColor);
        else
            g.setColor(concaveColor);


        if (isFilled)
            g.fillPolygon(javaPoly);
        else
            g.drawPolygon(javaPoly);

    }

    //also maybe calculate centroid. FOR LATER USE?
//    private float calculateLongestDistance() {
//
//        if (numPoints < 4)
//            return 0;
//
//        float current, longest = 0f;
//
//        for (int i = 0; i < numPoints - 2; i++)
//            for (int j = i + 1; j <= numPoints - 1 - i; j++) {
//                current = points.get(i).dst2(points.get(j));
//                if (current > longest)
//                    longest = current;
//            }
//
//        return (float) Math.sqrt(longest);
//    }

    public int[] arrayOfXPoints() {
        ArrayList<Integer> xPoints = new ArrayList<>();

        for (Point p : points)
            xPoints.add(p.getX());

        return Utilities.intListToStaticArray(xPoints);
    }

    public int[] arrayOfYPoints() {
        ArrayList<Integer> yPoints = new ArrayList<>();

        for (Point p : points)
            yPoints.add(p.getY());

        return Utilities.intListToStaticArray(yPoints);
    }

    private boolean isConvex() {
        if (points.size() < 4)
            return true;

        boolean sign = false;
        int n = points.size();
        for (int i = 0; i < n; i++) {
            double dx1 = points.get((i + 2) % n).getX() - points.get((i + 1) % n).getX();
            double dy1 = points.get((i + 2) % n).getY() - points.get((i + 1) % n).getY();
            double dx2 = points.get(i).getX() - points.get((i + 1) % n).getX();
            double dy2 = points.get(i).getY() - points.get((i + 1) % n).getY();
            double zCrossProduct = dx1 * dy2 - dy1 * dx2;
            if (i == 0)
                sign = zCrossProduct > 0;
            else if (sign != (zCrossProduct > 0))
                return false;
        }
        return true;
    }

    public boolean isEmpty() {
        return numPoints == 0;
    }

    public Stack<Point> getPoints() {
        return points;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void addPoint(Point p) {
        numPoints++;
        points.push(p);
        isConvex = isConvex();
    }

    public void addPoint(int x, int y) {
        addPoint(new Point(x, y));
    }

    public void removePoint(int index) {
        numPoints--;
        points.remove(index);
        isConvex = isConvex();
    }

    @Override
    public Polygon copyFlip() {
        Polygon copy = new Polygon();
        copy.isConvex = this.isConvex;

        //adding points sets numPoints accordingly
        for (Point p : points)
            copy.addPoint(p.copyFlip());

        return copy;
    }
}