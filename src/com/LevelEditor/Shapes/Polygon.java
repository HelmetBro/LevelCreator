package com.LevelEditor.Shapes;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.GlobalMouseListeners.CustomMouseWheelListener;
import com.LevelEditor.MouseStates.MouseState;
import com.LevelEditor.MouseStates.RotateMouseState;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.ShapeFillListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.Visibility.HideRotationOutlineListener;
import com.LevelEditor.Utilities;

import javax.xml.bind.annotation.XmlElement;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;
import java.util.Stack;

import static com.LevelEditor.ApplicationWindow.DASHED_STROKE;
import static com.LevelEditor.ApplicationWindow.NORMAL_STROKE;
import static com.LevelEditor.MouseStates.RotateMouseState.EXTENSION;

public class Polygon extends Shape {

    public static final String LOG_MESSAGE = "Level - added polygon";
    public static final String LOG_MESSAGE_DELETE = "Level - removed polygon";
    private static Color concaveColor = new Color(175, 95, 0, 180);
    private boolean isConvex;
    private int numPoints;

    @XmlElement
    private Stack<Point> points;

    public Polygon() {
        this.points = new Stack<>();
        this.numPoints = 0;
    }

    @Override
    public void drawName(Graphics2D g, Font font) {
        if (!hasUniqueName)
            return;

        Point centroid = Utilities.compute2DPolygonCentroid(points);

        FontRenderContext frc = g.getFontRenderContext();
        float fontWidth = g.getFontMetrics().stringWidth(name);
        float fontHeight = font.getLineMetrics(name, frc).getHeight();

        g.drawString(name, centroid.x - fontWidth / 2f, centroid.y - fontHeight / 2f);
    }

    @Override
    public void drawRotationOutline(Graphics2D g) {
        if (HideRotationOutlineListener.isHidden || angle <= 0 || angle >= 360)
            return;

        if (super.isSelected)
            g.setColor(ApplicationWindow.SELECTION_COLOR);
        else if (isConvex)
            g.setColor(ApplicationWindow.SHAPE_COLOR);
        else
            g.setColor(concaveColor);

        g.setStroke(DASHED_STROKE);

        java.awt.Polygon javaPoly = new java.awt.Polygon();

        for (Point p : points)
            javaPoly.addPoint(p.getX(), p.getY());

        if (ShapeFillListener.isFilled)
            g.drawPolygon(javaPoly);

        g.setStroke(NORMAL_STROKE);

    }

    public void drawShape(Graphics2D g) {

        drawRotationOutline(g);

        Point centroid = Utilities.compute2DPolygonCentroid(points);
        Graphics2D rg2d = (Graphics2D) g.create();
        rg2d.rotate(Math.toRadians(Utilities.undoAngleMods(angle)), centroid.x, centroid.y);

        //color
        if (super.isSelected)
            rg2d.setColor(ApplicationWindow.SELECTION_COLOR);
        else if (isConvex)
            rg2d.setColor(ApplicationWindow.SHAPE_COLOR);
        else
            rg2d.setColor(concaveColor);

        java.awt.Polygon javaPoly = new java.awt.Polygon();

        for (Point p : points)
            javaPoly.addPoint(p.getX(), p.getY());

        if (ShapeFillListener.isFilled)
            rg2d.fillPolygon(javaPoly);
        else
            rg2d.drawPolygon(javaPoly);

        //rotation lines
        if (CustomMouseWheelListener.getState() == MouseState.EMouseStates.ROTATION && !HideRotationOutlineListener.isHidden) {

            rg2d.setColor(RotateMouseState.LINE_COLOR);

            int longDist = (int) calculateLongestDistance();
            rg2d.drawLine(centroid.x - longDist / 2 - EXTENSION, centroid.y,
                    centroid.x + longDist / 2 + EXTENSION, centroid.y);
            rg2d.drawLine(centroid.x, centroid.y - longDist / 2 - EXTENSION,
                    centroid.x, centroid.y + longDist / 2 - EXTENSION);
        }

        rg2d.dispose();

    }

    private float calculateLongestDistance() {

        if (numPoints < 4)
            return 0;

        float current, longest = 0f;

        for (int i = 0; i < numPoints - 2; i++)
            for (int j = i + 1; j <= numPoints - 1 - i; j++) {
                current = Utilities.distance2(points.get(i), (points.get(j)));
                if (current > longest)
                    longest = current;
            }

        return (float) Math.sqrt(longest);
    }

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
        copy.name = this.name;
        copy.angle = this.angle;

        //adding points sets numPoints accordingly
        for (Point p : points)
            copy.addPoint(p.copyFlip());

        return copy;
    }
}