package com.LevelEditor.MouseStates;


import com.LevelEditor.Main;
import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.CustomKeyboardListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;
import com.LevelEditor.Shapes.*;
import com.LevelEditor.Shapes.Point;
import com.LevelEditor.Shapes.Polygon;
import com.LevelEditor.Shapes.Rectangle;
import com.LevelEditor.UpdatePaint;
import com.LevelEditor.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class SelectionMouseState extends MouseState {

    @Override
    public void updateLayer(Graphics2D g) {
        super.updateLayer(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (SwingUtilities.isRightMouseButton(e))
            rightClick();
        else if (SwingUtilities.isLeftMouseButton(e))
            leftClick();
    }

    private void rightClick() {
        //delete shapes
        if (ManageLevelArrayLists.getSelectedShapes().size() > 0) {
            new Thread(() -> {
                ManageLevelArrayLists.removeSelectedShapes();
                ScrollPaneHandler.propSP.updatePropertyEditor();
                ScrollPaneHandler.objSP.updateList();
            }).start();
        }
    }

    private void leftClick() {

        //if not pressing shift or ctrl, then remove all selections
        if (!CustomKeyboardListener.isPressingCtrl() && !CustomKeyboardListener.isPressingShift())
            ManageLevelArrayLists.removeAllSelections();


        for (Circle c : Main.currentLevel.circles)
            if (Utilities.circleCollide(c.getCenter(), c.radius, currentClickX, currentClickY) && !c.isSelected)
                c.isSelected = true;

        for (Ellipse e1 : Main.currentLevel.ellipses)
            if (Utilities.ellipseCollide(e1.getCenter(), e1.width, e1.height, currentClickX, currentClickY) && !e1.isSelected)
                e1.isSelected = true;

        for (Point p : Main.currentLevel.points)
            if (Utilities.rectangleCollide(
                    new Rectangle(new Point(p.getX() - Point.pointSize, p.getY() - Point.pointSize),
                            Point.pointSize * 2, Point.pointSize * 2), currentClickX, currentClickY)
                    && !p.isSelected)
                p.isSelected = true;

        for (Polygon p : Main.currentLevel.polygons)
            if (Utilities.polyCollide(p.getNumPoints(), p.arrayOfXPoints(), p.arrayOfYPoints(), currentClickX, currentClickY) && !p.isSelected)
                p.isSelected = true;

        for (Rectangle r : Main.currentLevel.rectangles)
            if (Utilities.rectangleCollide(r, currentClickX, currentClickY) && !r.isSelected)
                r.isSelected = true;

        UpdatePaint.remakeWindow();
        ScrollPaneHandler.propSP.updatePropertyEditor();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
