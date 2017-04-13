package com.LevelEditor.MouseStates;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.LevelWindow;
import com.LevelEditor.Shapes.Circle;
import com.LevelEditor.Shapes.Point;
import com.LevelEditor.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class CircleCreatorState extends MouseState {

    private Circle currentCircle;

    private Point initialP;
    private Point endP;

    public CircleCreatorState() {
        currentCircle = new Circle();
        endP = new Point(currentClickX, currentClickY);
    }

    @Override
    public void updateLayer(Graphics2D g) {
        super.updateLayer(g);

        endP.setX(currentClickX);
        endP.setY(currentClickY);

        if (initialP != null)
            currentCircle.radius = (int) Utilities.distance(endP, initialP);

        drawCircle(g);
    }

    private Circle centerCircle(Circle circle) {
        return new Circle(new Point(
                circle.getTopLeft().getX() - circle.radius,
                circle.getTopLeft().getY() - circle.radius),
                circle.radius);
    }

    private void drawCircle(Graphics2D g) {

        Circle drawcle = centerCircle(currentCircle);

        if (drawcle.radius == 0)
            return;

        g.setColor(MouseState.drawClearColor);
        g.drawOval(drawcle.getTopLeft().getX(), drawcle.getTopLeft().getY(),
                drawcle.radius * 2, drawcle.radius * 2);

        if (LevelWindow.drawGrid) {

            g.setStroke(new BasicStroke(lineWidth));
            g.setColor(ApplicationWindow.selectionColor);
            g.drawOval(drawcle.getTopLeft().getX(), drawcle.getTopLeft().getY(),
                    drawcle.radius * 2, drawcle.radius * 2);

            //resetting stroke
            g.setStroke(new BasicStroke(1));
        }

    }

    private void finalizeCircle() {
        if (currentCircle.radius == 0)
            return;

        ManageLevelArrayLists.addCircle(centerCircle(currentCircle), true);
        currentCircle = new Circle();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        currentCircle = new Circle();
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e)) {

            if (initialP == null)
                initialP = new Point(currentClickX, currentClickY);

            currentCircle.setTopLeft(initialP);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (SwingUtilities.isRightMouseButton(e))
            finalizeCircle();

        initialP = null;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
