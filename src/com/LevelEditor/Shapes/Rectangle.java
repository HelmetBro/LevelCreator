package com.LevelEditor.Shapes;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.GlobalMouseListeners.CustomMouseWheelListener;
import com.LevelEditor.MouseStates.MouseState;
import com.LevelEditor.MouseStates.RotateMouseState;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.ShapeFillListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.Visibility.HideRotationOutlineListener;
import com.LevelEditor.Utilities;

import java.awt.*;
import java.awt.font.FontRenderContext;

import static com.LevelEditor.ApplicationWindow.DASHED_STROKE;
import static com.LevelEditor.ApplicationWindow.NORMAL_STROKE;
import static com.LevelEditor.MouseStates.RotateMouseState.EXTENSION;

public class Rectangle extends Shape {

    public static final String LOG_MESSAGE = "Level - added rectangle";
    public static final String LOG_MESSAGE_DELETE = "Level - removed rectangle";
    public int width;
    public int height;
    private Point topLeft;
    private Point center;

    public Rectangle() {
        this(new Point(0, 0), 0, 0);
    }

    public Rectangle(Point p, int width, int height) {
        this.topLeft = p;
        this.center = new Point(topLeft.x + width / 2, topLeft.y + height / 2);
        this.width = width;
        this.height = height;
    }

    @Override
    public void drawName(Graphics2D g, Font font) {
        if (!hasUniqueName)
            return;

        FontRenderContext frc = g.getFontRenderContext();
        float fontWidth = g.getFontMetrics().stringWidth(name);
        float fontHeight = font.getLineMetrics(name, frc).getHeight();

        g.drawString(name, center.x - fontWidth / 2f, center.y - fontHeight / 2f);
    }

    @Override
    public void drawRotationOutline(Graphics2D g) {
        if (HideRotationOutlineListener.isHidden || angle <= 0 || angle >= 360)
            return;

        if (super.isSelected)
            g.setColor(ApplicationWindow.SELECTION_COLOR);
        else
            g.setColor(ApplicationWindow.SHAPE_COLOR);

        g.setStroke(DASHED_STROKE);
        g.drawRect(topLeft.x, topLeft.y, width, height);
        g.setStroke(NORMAL_STROKE);

    }

    public void drawShape(Graphics2D g) {

        drawRotationOutline(g);

        Graphics2D rg2d = (Graphics2D) g.create();
        rg2d.rotate(Math.toRadians(Utilities.undoAngleMods(angle)), center.x, center.y);

        //color
        if (super.isSelected)
            rg2d.setColor(ApplicationWindow.SELECTION_COLOR);
        else
            rg2d.setColor(ApplicationWindow.SHAPE_COLOR);

        //drawing
        if (ShapeFillListener.isFilled)
            rg2d.fillRect(topLeft.x, topLeft.y, width, height);
        else
            rg2d.drawRect(topLeft.x, topLeft.y, width, height);

        //rotation lines
        if (CustomMouseWheelListener.getState() == MouseState.EMouseStates.ROTATION && !HideRotationOutlineListener.isHidden) {

            rg2d.setColor(RotateMouseState.LINE_COLOR);

            rg2d.drawLine(topLeft.x - EXTENSION, topLeft.y + height / 2,
                    width + topLeft.x + EXTENSION, topLeft.y + height / 2);
            rg2d.drawLine(topLeft.x + width / 2, topLeft.y - EXTENSION,
                    topLeft.x + width / 2, topLeft.y + height + EXTENSION);
        }

        rg2d.dispose();
    }

//    public Polygon toPolygon(){
//
//        Polygon newPoly = new Polygon();
//        newPoly.addPoint(x, y);
//        newPoly.addPoint(x + startWidth, y);
//        newPoly.addPoint(x + startWidth, y + startHeight);
//        newPoly.addPoint(x, y + startHeight);
//
//        return newPoly;
//    }

    public Point getCenter() {
        return this.center;
    }

    public void setCenter(Point p) {
        this.center = p;
        this.topLeft = new Point(center.x - width / 2, center.y - height / 2);
    }

    public Point getTopLeft() {
        return this.topLeft;
    }

    public void setTopLeft(Point p) {
        this.topLeft = p;
        this.center = new Point(topLeft.x + width / 2, topLeft.y + height / 2);
    }

    public boolean hasSize() {
        return width != 0 && height != 0;
    }

    @Override
    public Rectangle copyFlip() {
        Rectangle copy = new Rectangle();
        copy.setProperties(this.getProperties());
        copy.name = this.name;
        copy.angle = this.angle;

        copy.width = this.width;
        copy.height = this.height;

        copy.center = this.getCenter().copyFlip();
        copy.topLeft = this.getTopLeft().copyFlip();
        copy.topLeft.y = copy.topLeft.y - copy.height;

        return copy;
    }
}
