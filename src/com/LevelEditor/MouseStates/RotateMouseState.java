package com.LevelEditor.MouseStates;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.Shapes.Shape;
import com.LevelEditor.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RotateMouseState extends MouseState {

    public static final int EXTENSION = 10;
    public static final Color LINE_COLOR = new Color(158, 88, 213);
    //a^2 + b^2 = c^2 is what this is
    public static final int GRID_RADIUS = (int) Math.sqrt(
            (ApplicationWindow.settings.getLvlMakerHeight() * ApplicationWindow.settings.getLvlMakerHeight()) +
                    ApplicationWindow.settings.getLvlMakerWidth() * ApplicationWindow.settings.getLvlMakerWidth());
    //reference
    private ArrayList<Shape> selectedShapes;
    //degrees
    public static float currentAngle = 0;

    @Override
    public void updateLayer(Graphics2D g) {
        super.updateLayer(g);

        selectedShapes = ManageLevelArrayLists.getSelectedShapes();
        if (selectedShapes == null || selectedShapes.isEmpty())
            return;

        currentAngle = updatedAngle();

        //current show degrees in ratio button
        ApplicationWindow.ratioButton.setText((Math.round(currentAngle * 10) / 10f) + "°");

        for (Shape s : selectedShapes)
            s.angle = currentAngle;

    }

    private float updatedAngle() {
        return Utilities.normalize((float) Math.toDegrees(
                Math.atan2(ApplicationWindow.settings.getLvlMakerHeight() / 2 - MouseState.currentClickY,
                        MouseState.currentClickX - ApplicationWindow.settings.getLvlMakerWidth() / 2)));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (SwingUtilities.isRightMouseButton(e) || SwingUtilities.isLeftMouseButton(e))
            select();

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
