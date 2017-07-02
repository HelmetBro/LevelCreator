package com.LevelEditor.Shapes;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.GlobalMouseListeners.CustomMouseWheelListener;
import com.LevelEditor.MouseStates.MouseState;
import com.LevelEditor.MouseStates.RotateMouseState;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.Visibility.HideRotationOutlineListener;
import com.LevelEditor.Utilities;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;

import static com.LevelEditor.ApplicationWindow.DASHED_STROKE;
import static com.LevelEditor.MouseStates.RotateMouseState.EXTENSION;

public class Path extends Shape {

    public static final String LOG_MESSAGE = "Level - added path";
    public static final String LOG_MESSAGE_DELETE = "Level - removed path";
    public static final int START_POINT_RADIUS = 8; //8
    private static final Color lineColor = new Color(29, 187, 56, 200);
    private static final Color dotColor = new Color(187, 45, 39, 90);
    private static final byte NAME_HOVER = 8;

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

        if (isEmpty())
            return;

        drawRotationOutline(g);

        /* ++ drawing line ++ */

        if (super.isSelected)
            g.setColor(ApplicationWindow.SELECTION_COLOR);
        else
            g.setColor(lineColor);

        for (int i = 0; i < pathPoints.size() - 1; i++) {

            g.setStroke(DASHED_STROKE);
            g.drawLine(pathPoints.get(i).x, pathPoints.get(i).y, pathPoints.get(i + 1).x, pathPoints.get(i + 1).y);

            g.setStroke(new BasicStroke(2));
            drawArrowHead(g, pathPoints.get(i), pathPoints.get(i + 1));
        }

        /* ++ drawing start point ++ */

        if (super.isSelected)
            g.setColor(dotColor.darker());
        else
            g.setColor(dotColor);

        Circle circle = new Circle(startPoint(), START_POINT_RADIUS);
        g.fillOval(circle.getTopLeft().x - START_POINT_RADIUS, circle.getTopLeft().y - START_POINT_RADIUS,
                START_POINT_RADIUS * 2, START_POINT_RADIUS * 2);
    }

    @Override
    public void drawRotationOutline(Graphics2D g) {

        if (CustomMouseWheelListener.getState() != MouseState.EMouseStates.ROTATION || HideRotationOutlineListener.isHidden)
            return;

        Graphics2D rg2d = (Graphics2D) g.create();
        Point start = startPoint();
        Point end = new Point(start.x + EXTENSION * 4, start.y);
        rg2d.rotate(Math.toRadians(Utilities.undoAngleMods(angle)), start.x, start.y);

        rg2d.setColor(RotateMouseState.LINE_COLOR);

        rg2d.drawLine(start.x, start.y, end.x, end.y);
        drawArrowHead(rg2d, start, end);
    }

    private void drawArrowHead(Graphics2D g, Point start, Point end) {

        final int lineLength = 14;

        float newAngle = (float) Math.atan2(end.y - start.y, end.x - start.x);

        //the angle tilt of the arrow
        newAngle += (float) (Math.sqrt(2) / 2f);

        g.drawLine(end.x, end.y,
                (int) (end.x + Math.cos(newAngle) * lineLength * -1),
                (int) (end.y + Math.sin(newAngle) * lineLength * -1));

    }

    public ArrayList<Point> getPoints() {
        return pathPoints;
    }

    public Point startPoint() {
        return pathPoints.get(0);
    }

    @Override
    public void drawName(Graphics2D g, Font font) {
        if (!hasUniqueName)
            return;

        FontRenderContext frc = g.getFontRenderContext();
        float fontWidth = g.getFontMetrics().stringWidth(name);
        float fontHeight = font.getLineMetrics(name, frc).getHeight();

        g.drawString(name, startPoint().x - fontWidth / 2f, startPoint().y - fontHeight / 2f - NAME_HOVER / 2f);

    }

    @Override
    public Path copyFlip() {
        Path copy = new Path();

        copy.name = this.name;
        copy.angle = this.angle;

        for (Point p : pathPoints)
            copy.addVector(p.copyFlip());

        return copy;
    }

}