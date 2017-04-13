package com.LevelEditor.MouseStates;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.GlobalMouseListeners.CustomMouseMoveListener;
import com.LevelEditor.Main;
import com.LevelEditor.ScreenComponents.LevelWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class MouseState {

    public static final Color drawColor = ApplicationWindow.lightShadedColor;
    public static final Color drawClearColor = new Color(
            drawColor.getRed(), drawColor.getGreen(), drawColor.getBlue(), 100);

    protected static final int lineWidth = 7;

    //to prevent double clicks
    protected static int lastClickX = 0;
    protected static int lastClickY = 0;

    protected static int currentClickX = 0;
    protected static int currentClickY = 0;

    public enum EMouseStates {

        POLYGON(0),
        SELECTION(1),
        RECTANGLE(2),
        ELLIPSE(3),
        CIRCLE(4),
        POINT(5);

        private int index;

        EMouseStates(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

    }

    public void updateLayer(Graphics2D g) {

        if (LevelWindow.snapToGrid) {
            currentClickX = LevelWindow.snappedX;
            currentClickY = LevelWindow.snappedY;
        } else {
            currentClickX = CustomMouseMoveListener.getX();
            currentClickY = CustomMouseMoveListener.getY();
        }

        Main.applicationWindow.setTitle("Mouse Location: (" + currentClickX + ", " + currentClickY + ")");

    }

    public abstract void mouseClicked(MouseEvent e);

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseEntered(MouseEvent e);

    public abstract void mouseExited(MouseEvent e);

}
