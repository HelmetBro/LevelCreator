package com.LevelEditor.Shapes;

import com.LevelEditor.ApplicationWindow;

import java.awt.*;
import java.awt.Point;
import java.util.ArrayList;

import static com.LevelEditor.ApplicationWindow.dashedStroke;
import static com.LevelEditor.ApplicationWindow.normalStroke;

public class Path extends Shape {

    public static final String logMessage = "Level - added path";
    public static final String logMessageDelete = "Level - removed path";

    private static final Color color = new Color(29, 187, 56, 200);

    private ArrayList<Point> pathPoints;

    public Path() {
        pathPoints = new ArrayList<>();
    }

    public void addVector(Point p) {
        pathPoints.add(p);
    }

    public Point pop(int index) {
        pathPoints.remove(index);
        return pathPoints.get(index - 1);
    }

    public int getSize() {
        return pathPoints.size();
    }

    public boolean isEmpty() {
        return pathPoints.size() < 1;
    }

    @Override
    public void drawShape(Graphics2D g) {

        g.setStroke(dashedStroke);

        if (super.isSelected)
            g.setColor(ApplicationWindow.selectionColor);
        else
            g.setColor(color);

        for (int i = 0; i < pathPoints.size() - 1; i++) {
            g.drawLine(pathPoints.get(i).x, pathPoints.get(i).y, pathPoints.get(i + 1).x, pathPoints.get(i + 1).y);
            //TODO: later draw the vector head
        }

        g.setStroke(normalStroke);

    }

    @Override
    public void drawName(Graphics2D g, Font font) {

    }

    @Override
    public void drawHitBox(Graphics2D g) {

    }

    @Override
    public Path copyFlip() {
        return null;
    }

}