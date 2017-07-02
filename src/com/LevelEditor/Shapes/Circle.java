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

import static com.LevelEditor.MouseStates.RotateMouseState.EXTENSION;

public class Circle extends Shape {

    public static final String LOG_MESSAGE = "Level - added circle";
    public static final String LOG_MESSAGE_DELETE = "Level - removed circle";
    public int radius;
    private Point topLeft;
    private Point center;

    public Circle() {
        this(new Point(0, 0), 0);
    }

    public Circle(Point p, int radius) {
        this.topLeft = p;
        this.center = new Point(topLeft.x + radius, topLeft.y + radius);
        this.radius = radius;
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
            rg2d.fillOval(topLeft.x, topLeft.y, radius * 2, radius * 2);
        else
            rg2d.drawOval(topLeft.x, topLeft.y, radius * 2, radius * 2);

        //rotation lines
        if (CustomMouseWheelListener.getState() == MouseState.EMouseStates.ROTATION && !HideRotationOutlineListener.isHidden) {

            rg2d.setColor(RotateMouseState.LINE_COLOR);

            rg2d.drawLine(topLeft.x - EXTENSION, topLeft.y + radius,
                    radius * 2 + topLeft.x + EXTENSION, topLeft.y + radius);
            rg2d.drawLine(topLeft.x + radius, topLeft.y - EXTENSION,
                    topLeft.x + radius, topLeft.y + radius * 2 + EXTENSION);
        }

        rg2d.dispose();

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
    }

    public Point getTopLeft() {
        return this.topLeft;
    }

    public void setTopLeft(Point p) {
        this.topLeft = p;
        this.center = new Point(topLeft.x + radius, topLeft.y + radius);
    }

    public Point getCenter() {
        return this.center;
    }

    public void setCenter(Point p) {
        this.center = p;
        this.topLeft = new Point(center.x - radius, center.y - radius);
    }

    @Override
    public Circle copyFlip() {
        Circle copy = new Circle();
        copy.setProperties(this.getProperties());
        copy.radius = this.radius;
        copy.name = this.name;
        copy.angle = this.angle;

        copy.center = this.center.copyFlip();
        copy.topLeft = this.topLeft.copyFlip();
        copy.topLeft.y = copy.topLeft.y - copy.radius * 2;

        return copy;
    }
}
