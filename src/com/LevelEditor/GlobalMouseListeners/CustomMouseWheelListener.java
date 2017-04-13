package com.LevelEditor.GlobalMouseListeners;

import com.LevelEditor.MouseStates.*;
import com.LevelEditor.MouseStates.MouseState.EMouseStates;
import com.LevelEditor.ScreenComponents.InfoLabelButton;
import com.LevelEditor.UpdatePaint;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class CustomMouseWheelListener implements MouseWheelListener {

    private static EMouseStates currentMouseEnumState = EMouseStates.SELECTION;

    public static String[] stateStrings = {"Polygon", "Selection", "Rectangle", "Ellipse", "Circle", "Point"};

    private static final int numOfMouseStates = stateStrings.length;
    private static MouseState polyState;
    private static MouseState normalState;
    private static MouseState rectState;
    private static MouseState ellipseState;
    private static MouseState circleState;
    private static MouseState pointState;

    public CustomMouseWheelListener() {
        polyState = new PolygonCreatorState();
        normalState = new SelectionMouseState();
        rectState = new RectangleCreatorState();
        ellipseState = new EllipseCreatorState();
        circleState = new CircleCreatorState();
        pointState = new PointCreatorState();

        CustomMouseListener.currentState = enumToMouseState(currentMouseEnumState);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        //up/down mouse scroll
        if (e.getWheelRotation() < 0)
            decrementState();
        else
            incrementEnumState();

        CustomMouseListener.currentState = enumToMouseState(currentMouseEnumState);

        InfoLabelButton.updateStateLabelText(currentMouseEnumState);

        UpdatePaint.remakeWindow();
    }

    public void manualWheelMove(int rotationTicks) {
        //up/down mouse scroll
        if (rotationTicks < 0)
            decrementState();
        else
            incrementEnumState();

        CustomMouseListener.currentState = enumToMouseState(currentMouseEnumState);

        InfoLabelButton.updateStateLabelText(currentMouseEnumState);

        UpdatePaint.remakeWindow();
    }

    private void incrementEnumState() {
        currentMouseEnumState = EMouseStates.values()[(currentMouseEnumState.getIndex() + 1) % numOfMouseStates];
    }

    private void decrementState() {

        int desiredIndex = currentMouseEnumState.getIndex() - 1;

        if (desiredIndex < 0)
            desiredIndex = numOfMouseStates - 1;

        currentMouseEnumState = EMouseStates.values()[desiredIndex];

    }

    private MouseState enumToMouseState(EMouseStates eState) {

        switch (eState) {

            case POLYGON:
                return polyState;
            case SELECTION:
                return normalState;
            case RECTANGLE:
                return rectState;
            case ELLIPSE:
                return ellipseState;
            case CIRCLE:
                return circleState;
            case POINT:
                return pointState;
            default:
                System.out.println("ERROR - enumToMouseState invalid eState");
                return null;

        }

    }

}