package com.LevelEditor.Shapes;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.ShapeFillListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.Visibility.HideHitBoxesListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.Visibility.HideShapesListener;

import java.awt.*;
import java.awt.font.FontRenderContext;

public class Circle extends Shape {

    public static final String logMessage = "Level - added circle";
    public static final String logMessageDelete = "Level - removed circle";
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

        if (super.isSelected)
            g.setColor(ApplicationWindow.selectionColor);
        else
            g.setColor(ApplicationWindow.shapeColor);


        if (ShapeFillListener.isFilled)
            g.fillOval(topLeft.x, topLeft.y, radius * 2, radius * 2);
        else
            g.drawOval(topLeft.x, topLeft.y, radius * 2, radius * 2);

    }

    @Override
    public void drawName(Graphics2D g, Font font) {
        if (!hasUniqueName)
            return;

        FontRenderContext frc = g.getFontRenderContext();
        float fontWidth = g.getFontMetrics().stringWidth(name);
        float fontHeight = font.getLineMetrics(name, frc).getHeight();

        g.drawString(name, center.x - fontWidth/2f, center.y - fontHeight/2f);
    }

    @Override
    public void drawHitBox(Graphics2D g) {
        if (HideHitBoxesListener.isHidden)
            return;


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
        copy.radius = this.radius;

        copy.center = this.center.copyFlip();
        copy.topLeft = this.topLeft.copyFlip();
        copy.topLeft.y = copy.topLeft.y - copy.radius * 2;

        return copy;
    }
}
