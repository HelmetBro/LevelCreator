package com.LevelEditor.Shapes;

import com.LevelEditor.ApplicationWindow;

import java.awt.*;
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

        if (super.isSelected)
            g.setColor(ApplicationWindow.selectionColor);
        else
            g.setColor(color);

        for (int i = 0; i < pathPoints.size() - 1; i++) {

            g.setStroke(dashedStroke);
            g.drawLine(pathPoints.get(i).x, pathPoints.get(i).y, pathPoints.get(i + 1).x, pathPoints.get(i + 1).y);

            g.setStroke(new BasicStroke(2));
            drawArrowHead(g, pathPoints.get(i), pathPoints.get(i + 1));
        }

        g.setStroke(normalStroke);

    }

    private void drawArrowHead(Graphics2D g, Point start, Point end){

        final int lineLength = 14;

        float newAngle = (float)Math.atan2(end.y - start.y, end.x - start.x);

        //the angle tilt of the arrow
        newAngle += (float)(Math.sqrt(2) / 2f);

        g.drawLine(end.x, end.y,
                (int)(end.x + Math.cos(newAngle) * lineLength * -1),
                (int)(end.y + Math.sin(newAngle) * lineLength * -1));

    }

    public ArrayList<Point> getPoints(){
        return pathPoints;
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