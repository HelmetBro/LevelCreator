package com.LevelEditor.Shapes;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.ShapeFillListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.Visibility.HideHitBoxesListener;

import java.awt.*;
import java.awt.font.FontRenderContext;


public class Ellipse extends Shape {

    public static final String LOG_MESSAGE = "Level - added ellipse";
    public static final String LOG_MESSAGE_DELETE = "Level - removed ellipse";
    public int width;
    public int height;
    private Point topLeft;
    private Point center;

    public Ellipse() {
        this(new Point(0, 0), 0, 0);
    }

    public Ellipse(Point p, int width, int height) {
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
    public void drawHitBox(Graphics2D g) {
        if (HideHitBoxesListener.isHidden)
            return;


    }

    public void drawShape(Graphics2D g) {

        if (super.isSelected)
            g.setColor(ApplicationWindow.SELECTION_COLOR);
        else
            g.setColor(ApplicationWindow.SHAPE_COLOR);

        if (ShapeFillListener.isFilled)
            g.fillOval(topLeft.x, topLeft.y, width, height);
        else
            g.drawOval(topLeft.x, topLeft.y, width, height);
    }

    public Point getTopLeft() {
        return this.topLeft;
    }

    public void setTopLeft(Point p) {
        this.topLeft = p;
        this.center = new Point(topLeft.x + width / 2, topLeft.y + height / 2);
    }

    public Point getCenter() {
        return this.center;
    }

    public void setCenter(Point p) {
        this.center = p;
        this.topLeft = new Point(center.x - width / 2, center.y - height / 2);
    }

    @Override
    public Ellipse copyFlip() {
        Ellipse copy = new Ellipse();

        copy.width = this.width;
        copy.height = this.height;

        copy.center = this.center.copyFlip();
        copy.topLeft = this.topLeft.copyFlip();
        copy.topLeft.y = copy.topLeft.y - copy.height;

        return copy;
    }

}
