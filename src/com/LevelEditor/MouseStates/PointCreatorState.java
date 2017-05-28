package com.LevelEditor.MouseStates;

import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.Shapes.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class PointCreatorState extends MouseState {

    private Point point;

    public PointCreatorState() {
        point = new Point(currentClickX, currentClickY);
    }

    @Override
    public void updateLayer(Graphics2D g) {
        super.updateLayer(g);

        point.setX(currentClickX);
        point.setY(currentClickY);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e)) {
            ManageLevelArrayLists.addPoint(point, true);
            point = new Point(currentClickX, currentClickY);
        } else if (SwingUtilities.isRightMouseButton(e)){
            select();
        }


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
