package com.LevelEditor.Shapes;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.GlobalMouseListeners.CustomMouseWheelListener;
import com.LevelEditor.MouseStates.MouseState;
import com.LevelEditor.MouseStates.RotateMouseState;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.Visibility.HideRotationOutlineListener;
import com.LevelEditor.Utilities;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;

import static com.LevelEditor.ApplicationWindow.NORMAL_STROKE;
import static com.LevelEditor.MouseStates.RotateMouseState.EXTENSION;

public class Point extends Shape {

    public static final String LOG_MESSAGE = "Level - added point";
    public static final String LOG_MESSAGE_DELETE = "Level - removed point";
    public static final int pointSize = 7;
    //drawing properties
    private static final int pointLineWidth = 3;
    protected int x;
    protected int y;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this(p.getX(), p.getY());
    }

    public float dstX(Point p) {
        return this.x - p.x;
    }

    public float dstY(Point p) {
        return this.y - p.y;
    }

//    public float dst2(Point v) {
//        final float x_d = v.x - x;
//        final float y_d = v.y - y;
//        return x_d * x_d + y_d * y_d;
//    }

    @Override
    public void drawName(Graphics2D g, Font font) {
        if (name == null)
            return;

        FontRenderContext frc = g.getFontRenderContext();
        float fontWidth = g.getFontMetrics().stringWidth(name);
        float fontHeight = font.getLineMetrics(name, frc).getHeight();

        g.drawString(name, x - fontWidth / 2f, y - fontHeight / 2f - pointSize / 2f);
    }

    @Override
    public void drawSprite(Graphics2D g) {
        Graphics2D rg2d = (Graphics2D) g.create();
        rg2d.rotate(Math.toRadians(Utilities.undoAngleMods(angle)), x, y);

        rg2d.drawImage(image, x - spriteW/2, y - spriteH/2, spriteW, spriteH, null);

        rg2d.dispose();
    }

    @Override
    public void drawRotationOutline(Graphics2D g) {
    }

    public void drawShape(Graphics2D g) {

        Graphics2D rg2d = (Graphics2D) g.create();
        rg2d.rotate(Math.toRadians(Utilities.undoAngleMods(angle)), x, y);

        if (super.isSelected)
            rg2d.setColor(ApplicationWindow.SELECTION_COLOR);
        else
            rg2d.setColor(ApplicationWindow.SHAPE_COLOR);

        rg2d.setStroke(new BasicStroke(pointLineWidth));

        rg2d.draw(new Line2D.Float(x - pointSize, y - pointSize, x + pointSize, y + pointSize));
        rg2d.draw(new Line2D.Float(x + pointSize, y - pointSize, x - pointSize, y + pointSize));

        rg2d.setStroke(NORMAL_STROKE);

        //rotation lines
        if (CustomMouseWheelListener.getState() == MouseState.EMouseStates.ROTATION && !HideRotationOutlineListener.isHidden) {

            rg2d.setColor(RotateMouseState.LINE_COLOR);

            rg2d.drawLine(x - EXTENSION, y, x + EXTENSION, y);
            rg2d.drawLine(x, y - EXTENSION, x, y + EXTENSION);
        }

        rg2d.dispose();
    }

//    private static BufferedImage realCircle() {
//
//        int diameter = pointSize + pointSize;
//
//        BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
//        Graphics g = image.getGraphics();
//
//        Point center = new Point(pointSize, pointSize);
//
//        for (int i = 0; i < diameter; i++) {
//            for (int j = 0; j < diameter; j++) {
//
//                Point drawP = new Point(i, j);
//
//                System.out.println(Math.sqrt(drawP.dst2(center)));
//
//                if (Math.sqrt(drawP.dst2(center)) <= pointSize)
//                    g.fillRect(i, j, 1, 1);
//
//            }
//        }
//
//        return image;
//
//    }

//    private static java.awt.Shape createRingShape(
//            float centerX, float centerY, float outerRadius, float thickness) {
//        Ellipse2D outer = new Ellipse2D.Double(
//                centerX - outerRadius,
//                centerY - outerRadius,
//                outerRadius + outerRadius,
//                outerRadius + outerRadius);
//        Ellipse2D inner = new Ellipse2D.Double(
//                centerX - outerRadius + thickness,
//                centerY - outerRadius + thickness,
//                outerRadius + outerRadius - thickness - thickness,
//                outerRadius + outerRadius - thickness - thickness);
//        Area area = new Area(outer);
//        area.subtract(new Area(inner));
//        return area;
//    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Point copyFlip() {
        Point copy = new Point(this);
        copy.setProperties(this.getProperties());

        copy.spritePath = this.spritePath;
        copy.spriteW = this.spriteW;
        copy.spriteH = this.spriteH;

        copy.name = this.name;
        copy.angle = this.angle;

        copy.y = ApplicationWindow.settings.getLvlMakerHeight() - copy.y;

        return copy;
    }
}