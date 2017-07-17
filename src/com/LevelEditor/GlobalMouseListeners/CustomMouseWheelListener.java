package com.LevelEditor.GlobalMouseListeners;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.MouseStates.*;
import com.LevelEditor.MouseStates.MouseState.EMouseStates;
import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.ScreenComponents.CustomKeyboardListener;
import com.LevelEditor.ScreenComponents.InfoLabelButton;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.SliderListener;
import com.LevelEditor.UpdatePaint;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class CustomMouseWheelListener implements MouseWheelListener {

    public static String[] stateStrings = {"Polygon", "Rotation", "Rectangle", "Ellipse", "Circle", "Point", "Path"};
    private static final int NUM_OF_MOUSE_STATES = stateStrings.length;
    private static EMouseStates currentMouseEnumState = EMouseStates.RECTANGLE;
    private static MouseState polyState;
    private static MouseState rotateState;
    private static MouseState rectState;
    private static MouseState ellipseState;
    private static MouseState circleState;
    private static MouseState pointState;
    private static MouseState pathState;

    private Canvas canvas;

    public CustomMouseWheelListener(Canvas canvas) {

        this.canvas = canvas;

        polyState = new PolygonCreatorState();
        rotateState = new RotateMouseState();
        rectState = new RectangleCreatorState();
        ellipseState = new EllipseCreatorState();
        circleState = new CircleCreatorState();
        pointState = new PointCreatorState();
        pathState = new PathCreatorState();

        CustomMouseListener.currentState = enumToMouseState(currentMouseEnumState);
    }

    public static void switchState(EMouseStates state) {
        ApplicationWindow.ratioButton.resetText();
        currentMouseEnumState = state;
        CustomMouseListener.currentState = enumToMouseState(state);
        SliderListener.changeTextNum();
        InfoLabelButton.updateStateLabelText(state);
        UpdatePaint.remakeWindow();
    }

    public static EMouseStates getState() {
        return currentMouseEnumState;
    }

    private static MouseState enumToMouseState(EMouseStates eState) {

        switch (eState) {

            case POLYGON:
                return polyState;
            case ROTATION:
                return rotateState;
            case RECTANGLE:
                return rectState;
            case ELLIPSE:
                return ellipseState;
            case CIRCLE:
                return circleState;
            case POINT:
                return pointState;
            case PATH:
                return pathState;
            default:
                System.out.println("ERROR - enumToMouseState invalid eState");
                return null;

        }

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        //up/down mouse scroll
        if (e.getWheelRotation() < 0) {

            if (CustomKeyboardListener.isPressingCtrl())
                canvas.zoomInRequest();
            else
                switchState(decrementState());

        } else {

            if (CustomKeyboardListener.isPressingCtrl())
                canvas.zoomOutRequest();
            else
                switchState(incrementState());

        }
    }

    public void manualWheelMove(int rotationTicks) {
        //up/down mouse scroll
        if (rotationTicks < 0)
            switchState(decrementState());
        else
            switchState(incrementState());
    }

    private EMouseStates incrementState() {
        return EMouseStates.values()[(currentMouseEnumState.getIndex() + 1) % NUM_OF_MOUSE_STATES];
    }

    private EMouseStates decrementState() {

        int desiredIndex = currentMouseEnumState.getIndex() - 1;

        if (desiredIndex < 0)
            desiredIndex = NUM_OF_MOUSE_STATES - 1;

        return EMouseStates.values()[desiredIndex];
    }

}
