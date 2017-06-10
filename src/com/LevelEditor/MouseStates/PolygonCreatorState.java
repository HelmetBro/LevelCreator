package com.LevelEditor.MouseStates;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.LoggingManager;
import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.Shapes.Point;
import com.LevelEditor.Shapes.Polygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class PolygonCreatorState extends MouseState {

    public static final String logMessagePoint = "PolygonCreatorState - added point";

    private static Polygon currentPoly;

    private int uncreatedPointLineWidth = 1;
    private int uncreatedPointSize = 10;

    public PolygonCreatorState() {
        currentPoly = new Polygon();
    }

    public static void removePointCurrentPoly() {
        if (!currentPoly.isEmpty())
            currentPoly.removePoint(currentPoly.getNumPoints() - 1);
        else
            System.out.println("ERROR - Tried to remove point that doesn't exist.");
    }

    @Override
    public void updateLayer(Graphics2D g) {
        super.updateLayer(g);

        //sz short for size
        int sz = uncreatedPointSize / 2;
        for (Point p : currentPoly.getPoints()) {

            g.setColor(MouseState.drawColor);
            g.setStroke(new BasicStroke(uncreatedPointLineWidth));

            g.draw(new Line2D.Float(p.getX() - sz, p.getY() - sz, p.getX() + sz, p.getY() + sz));
            g.draw(new Line2D.Float(p.getX() + sz, p.getY() - sz, p.getX() - sz, p.getY() + sz));

            if (Canvas.drawGrid) {
                g.setStroke(new BasicStroke(Math.abs(lineWidth - 3)));
                g.setColor(ApplicationWindow.selectionColor);
                g.draw(new Line2D.Float(p.getX() - sz, p.getY() - sz, p.getX() + sz, p.getY() + sz));
                g.draw(new Line2D.Float(p.getX() + sz, p.getY() - sz, p.getX() - sz, p.getY() + sz));

                //resetting stroke
                g.setStroke(new BasicStroke(1));
            }//if

        }//for

    }

    private void addPointCurrentPoly() {
        if (lastClickX == currentClickX && lastClickY == currentClickY)
            return;

        currentPoly.addPoint(currentClickX, currentClickY);

        lastClickX = currentClickX;
        lastClickY = currentClickY;

        //logging action
        LoggingManager.history.push(logMessagePoint);
    }

    private void finalizePoly() {

        if (!currentPoly.isEmpty() && currentPoly.getNumPoints() > 2)
            ManageLevelArrayLists.addPolygon(currentPoly, true);
        else
            for (int i = 0; i < currentPoly.getNumPoints(); i++)
                LoggingManager.history.pop();

        currentPoly = new Polygon();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e))
            addPointCurrentPoly();
        else if (SwingUtilities.isRightMouseButton(e)) {

            if (currentPoly.isEmpty())
                select();

            finalizePoly();

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
