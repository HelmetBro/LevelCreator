package com.LevelEditor.MouseStates;

import com.LevelEditor.*;
import com.LevelEditor.GlobalMouseListeners.CustomMouseMoveListener;
import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.ScreenComponents.CustomKeyboardListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;
import com.LevelEditor.Shapes.*;
import com.LevelEditor.Shapes.Point;
import com.LevelEditor.Shapes.Polygon;
import com.LevelEditor.Shapes.Rectangle;

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

    public void updateLayer(Graphics2D g) {

        if (Canvas.snapToGrid) {
            currentClickX = Canvas.snappedX;
            currentClickY = Canvas.snappedY;
        } else {
            currentClickX = CustomMouseMoveListener.getX();
            currentClickY = CustomMouseMoveListener.getY();
        }

        int showY = currentClickY;
        if (Main.currentLevel.flipY)
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

        for (Path p : Main.currentLevel.paths)
            if (Utilities.pathCollide(p, currentClickX, currentClickY) && !p.isSelected)
                p.isSelected = true;

        UpdatePaint.remakeWindow();
        ScrollPaneHandler.propSP.updatePropertyEditor();
    }

    public abstract void mouseClicked(MouseEvent e);

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseEntered(MouseEvent e);

    public abstract void mouseExited(MouseEvent e);

    public enum EMouseStates {

        POLYGON(0),
        SELECTION(1),
        RECTANGLE(2),
        ELLIPSE(3),
        CIRCLE(4),
        POINT(5),
        PATH(6);

        private int index;

        EMouseStates(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

    }

}
