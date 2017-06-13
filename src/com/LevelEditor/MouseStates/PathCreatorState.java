package com.LevelEditor.MouseStates;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.LoggingManager;
import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.Shapes.Path;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PathCreatorState extends MouseState {

    public static final String logMessagePoint = "PathCreatorState - added vector";

    private static Path currentPath;
    private static Point startPoint;
    private final byte CLICK_RESET = 1;
    //mouse action flags
    private boolean drawPathMaker;
    //used to erase start point after CLICK_RESET clicks
    private byte clickCount = 0;

    public PathCreatorState() {
        currentPath = new Path();
    }

    public static void removeCurrentVector() {

        /*
        * currentPath.getSize() > 1 because I need to take into account the
        * starting point.
        * */

        if (currentPath.getSize() > 1)
            startPoint = currentPath.pop(currentPath.getSize() - 1);
        else if (currentPath.getSize() != 1)
            System.out.println("ERROR - [PathCreatorState] Tried to remove point that doesn't exist. [removeCurrentVector]");
    }

    @Override
    public void updateLayer(Graphics2D g) {
        super.updateLayer(g);

        //draw path
        g.setColor(MouseState.drawColor);
        if (drawPathMaker)
            g.drawLine(startPoint.x, startPoint.y, currentClickX, currentClickY);

        //if grid is on, give it an outline
        if (Canvas.drawGrid && startPoint != null) {
            g.setColor(ApplicationWindow.selectionColor);
            g.setStroke(new BasicStroke(lineWidth));
            g.drawLine(startPoint.x, startPoint.y, currentClickX, currentClickY);

            //resetting stroke
            g.setStroke(new BasicStroke(1));
        }

        //draw the current path that's being made
        currentPath.drawShape(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e)) {
            drawPathMaker = true;
            clickCount++;

            if (clickCount > CLICK_RESET) {
                finalizePath();
                drawPathMaker = false;
                clickCount = 0;
            }

            startPoint = new Point(currentClickX, currentClickY);
        }

        if (SwingUtilities.isRightMouseButton(e) && clickCount == 1)
            finalizePoint();
    }

    private void finalizePath() {

        if (!currentPath.isEmpty())
            ManageLevelArrayLists.addPath(currentPath, true);

        currentPath = new Path();

    }

    private void addVector(Point p) {
        currentPath.addVector(p);
        LoggingManager.history.push(logMessagePoint);
    }

    private void finalizePoint() {
        if (currentPath.isEmpty())
            addVector(startPoint);

        addVector(new Point(currentClickX, currentClickY));
        startPoint = new Point(currentClickX, currentClickY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK)
            drawPathMaker = false;

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
