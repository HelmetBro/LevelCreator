package com.LevelEditor.MouseStates;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.Shapes.Point;
import com.LevelEditor.Shapes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class RectangleCreatorState extends MouseState {

    private Rectangle currentRect;

    private Point initialP;
    private Point endP;

    public RectangleCreatorState() {
        currentRect = new Rectangle();
        endP = new Point(currentClickX, currentClickY);
    }

    @Override
    public void updateLayer(Graphics2D g) {
        super.updateLayer(g);

        endP.setX(currentClickX);
        endP.setY(currentClickY);

        if (initialP != null) {
            currentRect.width = (int) endP.dstX(initialP);
            currentRect.height = (int) endP.dstY(initialP);
        }

        drawRectangle(g);
    }

    private Rectangle normalizeRectangle(Rectangle rect) {
        int absoluteX = rect.getTopLeft().getX();
        int absoluteY = rect.getTopLeft().getY();

        int absoluteWidth = rect.width;
        int absoluteHeight = rect.height;

        if (absoluteWidth < 0) {
            absoluteX += absoluteWidth;
            absoluteWidth *= -1;
        }

        if (absoluteHeight < 0) {
            absoluteY += absoluteHeight;
            absoluteHeight *= -1;
        }

        return new Rectangle(new Point(absoluteX, absoluteY), absoluteWidth, absoluteHeight);
    }

    private void drawRectangle(Graphics2D g) {

        Rectangle drawtangle = normalizeRectangle(currentRect);

        if (drawtangle.height == 0 || drawtangle.width == 0)
            return;

        g.setColor(MouseState.drawClearColor);
        g.drawRect(drawtangle.getTopLeft().getX(), drawtangle.getTopLeft().getY(),
                drawtangle.width, drawtangle.height);

        if (Canvas.drawGrid) {

            g.setStroke(new BasicStroke(lineWidth));
            g.setColor(ApplicationWindow.selectionColor);
            g.drawRect(drawtangle.getTopLeft().getX(), drawtangle.getTopLeft().getY(),
                    drawtangle.width, drawtangle.height);

            //resetting stroke
            g.setStroke(new BasicStroke(1));
        }

    }

    private void finalizeRect() {
        if (currentRect.height == 0 || currentRect.width == 0)
            return;

        ManageLevelArrayLists.addRectangle(normalizeRectangle(currentRect), true);
        currentRect = new Rectangle();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        currentRect = new Rectangle();
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e)) {

            if (initialP == null)
                initialP = new Point(currentClickX, currentClickY);

            currentRect.setTopLeft(initialP);
        } else if (SwingUtilities.isRightMouseButton(e)){
            select();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (SwingUtilities.isRightMouseButton(e))
            finalizeRect();

        initialP = null;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
