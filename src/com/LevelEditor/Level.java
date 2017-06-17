package com.LevelEditor;

import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.Visibility.HideNamesListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.Visibility.HidePathsListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.Visibility.HidePointsListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.Visibility.HideShapesListener;
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
    @XmlElement(name = "Paths")
    public ArrayList<Path> paths;

    private transient Font font = new Font("Consolas", Font.PLAIN, 16);
    private transient Color fontColor = new Color(216, 216, 213, 170);

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
        paths = new ArrayList<>();
    }

    public void updateLevel(Graphics2D g) {

        //creating new thread safe arrays
        CopyOnWriteArrayList<Circle> safeCircles = new CopyOnWriteArrayList<>(circles);
        CopyOnWriteArrayList<Ellipse> safeEllipses = new CopyOnWriteArrayList<>(ellipses);
        CopyOnWriteArrayList<Point> safePoints = new CopyOnWriteArrayList<>(points);
        CopyOnWriteArrayList<Polygon> safePolygons = new CopyOnWriteArrayList<>(polygons);
        CopyOnWriteArrayList<Rectangle> safeRectangles = new CopyOnWriteArrayList<>(rectangles);
        CopyOnWriteArrayList<Path> safePaths = new CopyOnWriteArrayList<>(paths);

        drawShapes(g, safeCircles, safeEllipses, safePoints, safePolygons, safeRectangles, safePaths);
        drawNames(g, safeCircles, safeEllipses, safePoints, safePolygons, safeRectangles, safePaths);
    }

    private void drawNames(Graphics2D g,
                           CopyOnWriteArrayList<Circle> safeCircles,
                           CopyOnWriteArrayList<Ellipse> safeEllipses,
                           CopyOnWriteArrayList<Point> safePoints,
                           CopyOnWriteArrayList<Polygon> safePolygons,
                           CopyOnWriteArrayList<Rectangle> safeRectangles,
                           CopyOnWriteArrayList<Path> safePaths) {

        g.setFont(font);
        g.setColor(fontColor);

        if (!HideShapesListener.isHidden && !HideNamesListener.isHidden) {
            for (Circle c : safeCircles)
                c.drawName(g, font);
            for (Ellipse e : safeEllipses)
                e.drawName(g, font);
            for (Polygon p : safePolygons)
                p.drawName(g, font);
            for (Rectangle r : safeRectangles)
                r.drawName(g, font);
        }

        if (!HidePointsListener.isHidden && !HideNamesListener.isHidden)
            for (Point p : safePoints)
                p.drawName(g, font);

        if (!HidePathsListener.isHidden && !HideNamesListener.isHidden)
            for (Path p : safePaths)
                p.drawName(g, font);
    }

    private void drawShapes(Graphics2D g,
                            CopyOnWriteArrayList<Circle> safeCircles,
                            CopyOnWriteArrayList<Ellipse> safeEllipses,
                            CopyOnWriteArrayList<Point> safePoints,
                            CopyOnWriteArrayList<Polygon> safePolygons,
                            CopyOnWriteArrayList<Rectangle> safeRectangles,
                            CopyOnWriteArrayList<Path> safePaths) {

        if (!HideShapesListener.isHidden) {
            for (Circle c : safeCircles)
                c.drawShape(g);
            for (Ellipse e : safeEllipses)
                e.drawShape(g);
            for (Polygon p : safePolygons)
                p.drawShape(g);
            for (Rectangle r : safeRectangles)
                r.drawShape(g);
        }

        if (!HidePointsListener.isHidden)
            for (Point p : safePoints)
                p.drawShape(g);

        if (!HidePathsListener.isHidden)
            for (Path p : safePaths)
                p.drawShape(g);
    }

    public Level flipYCopy() {

        Level flipLevel = new Level(this.width, this.height);

        flipLevel.flipY = this.flipY;

        CopyOnWriteArrayList<Circle> safeCircles = new CopyOnWriteArrayList<>(circles);
        CopyOnWriteArrayList<Ellipse> safeEllipses = new CopyOnWriteArrayList<>(ellipses);
        CopyOnWriteArrayList<Point> safePoints = new CopyOnWriteArrayList<>(points);
        CopyOnWriteArrayList<Polygon> safePolygons = new CopyOnWriteArrayList<>(polygons);
        CopyOnWriteArrayList<Rectangle> safeRectangles = new CopyOnWriteArrayList<>(rectangles);
        CopyOnWriteArrayList<Path> safePaths = new CopyOnWriteArrayList<>(paths);

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

        for (Path p : safePaths)
            flipLevel.paths.add(p.copyFlip());

        return flipLevel;
    }

}
