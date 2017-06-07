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

    public Level() {
    }

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

        for (Circle c : safeCircles)
            c.drawCircle(g);

        for (Ellipse e : safeEllipses)
            e.drawEllipse(g);

        for (Point p : safePoints)
            p.drawPoint(g);

        for (Polygon p : safePolygons)
            p.drawPolygon(g);

        for (Rectangle r : safeRectangles)
            r.drawRectangle(g);

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
