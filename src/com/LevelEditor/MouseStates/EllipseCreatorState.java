package com.LevelEditor.MouseStates;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.Shapes.Ellipse;
import com.LevelEditor.Shapes.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class EllipseCreatorState extends MouseState {

    private Ellipse currentEllipse;

    private Point initialP;
    private Point endP;

    public EllipseCreatorState() {
        currentEllipse = new Ellipse();
        endP = new Point(currentClickX, currentClickY);
    }

    @Override
    public void updateLayer(Graphics2D g) {
        super.updateLayer(g);

        endP.setX(currentClickX);
        endP.setY(currentClickY);

        if (initialP != null) {
            currentEllipse.width = (int) endP.dstX(initialP);
            currentEllipse.height = (int) endP.dstY(initialP);
        }

        drawEllipse(g);
    }

    private Ellipse normalizeEllipse(Ellipse ellipse) {
        int absoluteX = ellipse.getTopLeft().getX();
        int absoluteY = ellipse.getTopLeft().getY();

        int absoluteWidth = ellipse.width;
        int absoluteHeight = ellipse.height;

        if (absoluteWidth < 0) {
            absoluteX += absoluteWidth;
            absoluteWidth *= -1;
        }

        if (absoluteHeight < 0) {
            absoluteY += absoluteHeight;
            absoluteHeight *= -1;
        }

        return new Ellipse(new Point(absoluteX, absoluteY), absoluteWidth, absoluteHeight);
    }

    private void drawEllipse(Graphics2D g) {

        Ellipse drawlipse = normalizeEllipse(currentEllipse);

        if (drawlipse.height == 0 || drawlipse.width == 0)
            return;

        g.setColor(MouseState.DRAW_CLEAR_COLOR);
        g.drawOval(drawlipse.getTopLeft().getX(), drawlipse.getTopLeft().getY(),
                drawlipse.width, drawlipse.height);

        if (Canvas.drawGrid) {

            g.setStroke(new BasicStroke(LINE_WIDTH));
            g.setColor(ApplicationWindow.SELECTION_COLOR);
            g.drawOval(drawlipse.getTopLeft().getX(), drawlipse.getTopLeft().getY(),
                    drawlipse.width, drawlipse.height);

            //resetting stroke
            g.setStroke(new BasicStroke(1));
        }

    }

    private void finalizeEllipse() {
        if (currentEllipse.height == 0 || currentEllipse.width == 0)
            return;

        ManageLevelArrayLists.addEllipse(normalizeEllipse(currentEllipse), true);
        currentEllipse = new Ellipse();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        currentEllipse = new Ellipse();
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e)) {

            if (initialP == null)
                initialP = new Point(currentClickX, currentClickY);

            currentEllipse.setTopLeft(initialP);
        } else if (SwingUtilities.isRightMouseButton(e) && initialP == null) {
            select();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (SwingUtilities.isRightMouseButton(e))
            finalizeEllipse();

        initialP = null;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
