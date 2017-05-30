package com.LevelEditor.MouseStates;


import com.LevelEditor.*;
import com.LevelEditor.GlobalMouseListeners.CustomMouseMoveListener;
import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.ScreenComponents.CustomKeyboardListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.FlipYListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;
import com.LevelEditor.Shapes.Circle;
import com.LevelEditor.Shapes.Ellipse;

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

        if (Canvas.snapToGrid) {
            currentClickX = Canvas.snappedX;
            currentClickY = Canvas.snappedY;
        } else {
            currentClickX = CustomMouseMoveListener.getX();
            currentClickY = CustomMouseMoveListener.getY();
        }

        int showY = currentClickY;
        if (FlipYListener.flipY)
            showY = ApplicationWindow.settings.getLvlMakerHeight() - currentClickY;

        Main.applicationWindow.setTitle("Mouse Location: (" + currentClickX + ", " + showY + ")");
    }

    public void select() {

        //if not pressing shift or ctrl, then remove all selections
        if (!CustomKeyboardListener.isPressingCtrl() && !CustomKeyboardListener.isPressingShift())
            ManageLevelArrayLists.removeAllSelections();

        for (Circle c : Main.currentLevel.circles)
            if (Utilities.circleCollide(c.getCenter(), c.radius, currentClickX, currentClickY) && !c.isSelected)
                c.isSelected = true;

        for (Ellipse e1 : Main.currentLevel.ellipses)
            if (Utilities.ellipseCollide(e1.getCenter(), e1.width, e1.height, currentClickX, currentClickY) && !e1.isSelected)
                e1.isSelected = true;

        for (com.LevelEditor.Shapes.Point p : Main.currentLevel.points)
            if (Utilities.rectangleCollide(
                    new com.LevelEditor.Shapes.Rectangle(new com.LevelEditor.Shapes.Point(p.getX() - com.LevelEditor.Shapes.Point.pointSize, p.getY() - com.LevelEditor.Shapes.Point.pointSize),
                            com.LevelEditor.Shapes.Point.pointSize * 2, com.LevelEditor.Shapes.Point.pointSize * 2), currentClickX, currentClickY)
                    && !p.isSelected)
                p.isSelected = true;

        for (com.LevelEditor.Shapes.Polygon p : Main.currentLevel.polygons)
            if (Utilities.polyCollide(p.getNumPoints(), p.arrayOfXPoints(), p.arrayOfYPoints(), currentClickX, currentClickY) && !p.isSelected)
                p.isSelected = true;

        for (com.LevelEditor.Shapes.Rectangle r : Main.currentLevel.rectangles)
            if (Utilities.rectangleCollide(r, currentClickX, currentClickY) && !r.isSelected)
                r.isSelected = true;

        UpdatePaint.remakeWindow();
        ScrollPaneHandler.propSP.updatePropertyEditor();
    }

    public abstract void mouseClicked(MouseEvent e);

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseEntered(MouseEvent e);

    public abstract void mouseExited(MouseEvent e);

}
