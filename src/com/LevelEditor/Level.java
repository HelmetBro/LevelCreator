package com.LevelEditor;

import com.LevelEditor.Shapes.*;
import com.LevelEditor.Shapes.Point;
import com.LevelEditor.Shapes.Polygon;
import com.LevelEditor.Shapes.Rectangle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.LevelEditor.ApplicationWindow.basicFont;

@XmlRootElement(name = "Level")
public class Level {

    @XmlElement(name = "FlipY")
    public boolean flipY;

    @XmlElement(name = "Width")
    public int width;
    @XmlElement(name = "Height")
    public int height;

    @XmlElement(name = "Circles")
    public ArrayList<Circle> circles;
    @XmlElement(name = "Ellipses")
    public ArrayList<Ellipse> ellipses;
    @XmlElement(name = "Points")
    public ArrayList<Point> points;
    @XmlElement(name = "Polygons")
    public ArrayList<Polygon> polygons;
    @XmlElement(name = "Rectangles")
    public ArrayList<Rectangle> rectangles;

    private transient Font font = new Font("Consolas", Font.PLAIN, 16);
    private transient Color fontColor = new Color(216, 216, 213, 170);

    public Level() {}

    public Level(int width, int height) {

        this.width = width;
        this.height = height;

        circles = new ArrayList<>();
        ellipses = new ArrayList<>();
        points = new ArrayList<>();
        polygons = new ArrayList<>();
        rectangles = new ArrayList<>();
    }

    public void updateLevel(Graphics2D g) {

        //creating new thread safe arrays
        CopyOnWriteArrayList<Circle> safeCircles = new CopyOnWriteArrayList<>(circles);
        CopyOnWriteArrayList<Ellipse> safeEllipses = new CopyOnWriteArrayList<>(ellipses);
        CopyOnWriteArrayList<Point> safePoints = new CopyOnWriteArrayList<>(points);
        CopyOnWriteArrayList<Polygon> safePolygons = new CopyOnWriteArrayList<>(polygons);
        CopyOnWriteArrayList<Rectangle> safeRectangles = new CopyOnWriteArrayList<>(rectangles);

        drawShapes(g, safeCircles, safeEllipses, safePoints, safePolygons, safeRectangles);
        drawNames(g, safeCircles, safeEllipses, safePoints, safePolygons, safeRectangles);

    }

    private void drawNames(Graphics2D g,
                           CopyOnWriteArrayList<Circle> safeCircles,
                           CopyOnWriteArrayList<Ellipse> safeEllipses,
                           CopyOnWriteArrayList<Point> safePoints,
                           CopyOnWriteArrayList<Polygon> safePolygons,
                           CopyOnWriteArrayList<Rectangle> safeRectangles){

        g.setFont(font);
        g.setColor(fontColor);

        for (Circle c : safeCircles)
            c.drawName(g, font);
        for (Ellipse e : safeEllipses)
            e.drawName(g, font);
        for (Point p : safePoints)
            p.drawName(g, font);
        for (Polygon p : safePolygons)
            p.drawName(g, font);
        for (Rectangle r : safeRectangles)
            r.drawName(g, font);

    }

    private void drawShapes(Graphics2D g,
                           CopyOnWriteArrayList<Circle> safeCircles,
                           CopyOnWriteArrayList<Ellipse> safeEllipses,
                           CopyOnWriteArrayList<Point> safePoints,
                           CopyOnWriteArrayList<Polygon> safePolygons,
                           CopyOnWriteArrayList<Rectangle> safeRectangles){

        for (Circle c : safeCircles)
            c.drawShape(g);
        for (Ellipse e : safeEllipses)
            e.drawShape(g);
        for (Point p : safePoints)
            p.drawShape(g);
        for (Polygon p : safePolygons)
            p.drawShape(g);
        for (Rectangle r : safeRectangles)
            r.drawShape(g);

    }

    public Level flipYCopy() {

        Level flipLevel = new Level(this.width, this.height);

        flipLevel.flipY = this.flipY;

        CopyOnWriteArrayList<Circle> safeCircles = new CopyOnWriteArrayList<>(circles);
        CopyOnWriteArrayList<Ellipse> safeEllipses = new CopyOnWriteArrayList<>(ellipses);
        CopyOnWriteArrayList<Point> safePoints = new CopyOnWriteArrayList<>(points);
        CopyOnWriteArrayList<Polygon> safePolygons = new CopyOnWriteArrayList<>(polygons);
        CopyOnWriteArrayList<Rectangle> safeRectangles = new CopyOnWriteArrayList<>(rectangles);

        for (Circle c : safeCircles)
            flipLevel.circles.add(c.copyFlip());

        for (Ellipse e : safeEllipses)
            flipLevel.ellipses.add(e.copyFlip());

        for (Point p : safePoints)
            flipLevel.points.add(p.copyFlip());

        for (Polygon p : safePolygons)
            flipLevel.polygons.add(p.copyFlip());

        for (Rectangle r : safeRectangles)
            flipLevel.rectangles.add(r.copyFlip());

        return flipLevel;
    }

}
