package com.LevelEditor.Shapes;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.ShapeFillListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.Visibility.HideHitBoxesListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.Visibility.HideShapesListener;
import com.LevelEditor.Utilities;

import javax.xml.bind.annotation.XmlElement;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;
import java.util.Stack;

public class Polygon extends Shape {

    public static final String logMessage = "Level - added polygon";
    public static final String logMessageDelete = "Level - removed polygon";
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

        Point centroid = compute2DPolygonCentroid();

        FontRenderContext frc = g.getFontRenderContext();
        float fontWidth = g.getFontMetrics().stringWidth(name);
        float fontHeight = font.getLineMetrics(name, frc).getHeight();

        g.drawString(name, centroid.x - fontWidth/2f, centroid.y - fontHeight/2f);
    }

    @Override
    public void drawHitBox(Graphics2D g) {
        if (HideHitBoxesListener.isHidden)
            return;


    }

    public void drawShape(Graphics2D g) {

        java.awt.Polygon javaPoly = new java.awt.Polygon();

        for (Point p : points)
            javaPoly.addPoint(p.getX(), p.getY());

        if (super.isSelected)
            g.setColor(ApplicationWindow.selectionColor);
        else if (isConvex)
            g.setColor(ApplicationWindow.shapeColor);
        else
            g.setColor(concaveColor);


        if (ShapeFillListener.isFilled)
            g.fillPolygon(javaPoly);
        else
            g.drawPolygon(javaPoly);

    }

    public Point compute2DPolygonCentroid()
    {
        Point centroid = new Point();
        double signedArea = 0.0;
        double x0; // Current vertex X
        double y0; // Current vertex Y
        double x1; // Next vertex X
        double y1; // Next vertex Y
        double a;  // Partial signed area

        // For all vertices except last
        int i;
        for (i = 0; i< numPoints - 1; ++i)
        {
            x0 = points.get(i).x;
            y0 = points.get(i).y;
            x1 = points.get(i + 1).x;
            y1 = points.get(i + 1).y;
            a = x0*y1 - x1*y0;
            signedArea += a;
            centroid.x += (x0 + x1)*a;
            centroid.y += (y0 + y1)*a;
        }

        // Do last vertex separately to avoid performing an expensive
        // modulus operation in each iteration.
        x0 = points.get(i).x;
        y0 = points.get(i).y;
        x1 = points.get(0).x;
        y1 = points.get(0).y;
        a = x0*y1 - x1*y0;
        signedArea += a;
        centroid.x += (x0 + x1)*a;
        centroid.y += (y0 + y1)*a;

        signedArea *= 0.5;
        centroid.x /= (6.0*signedArea);
        centroid.y /= (6.0*signedArea);

        return centroid;
    }

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