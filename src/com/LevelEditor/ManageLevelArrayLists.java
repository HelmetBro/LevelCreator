package com.LevelEditor;


import com.LevelEditor.MouseStates.PolygonCreatorState;
import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;
import com.LevelEditor.Shapes.*;

import java.util.ArrayList;
import java.util.Stack;

public final class ManageLevelArrayLists {

    private static Stack<Shape> deletedShapes = new Stack<>();

    private static ArrayList<? extends Shape> typeToShapeArray(ShapeType type) {

        switch (type) {
            case CIRCLE:
                return Main.currentLevel.circles;
            case ELLIPSE:
                return Main.currentLevel.ellipses;
            case POINT:
                return Main.currentLevel.points;
            case POLYGON:
                return Main.currentLevel.polygons;
            case RECTANGLE:
                return Main.currentLevel.rectangles;
            default:
                System.out.println("ERROR - typeToShapeArray data invalid [ManageLevelArrayLists]");
                System.exit(-1);
        }

        return null;
    }


    /**
     * If shape is selected, removes from array, adds to deleted array.
     */
    public static void removeSelectedShapes() {
        Main.currentLevel.circles.removeIf(e -> shapeSelectedAndDeleted(e, ShapeType.CIRCLE));
        Main.currentLevel.points.removeIf(e -> shapeSelectedAndDeleted(e, ShapeType.POINT));
        Main.currentLevel.ellipses.removeIf(e -> shapeSelectedAndDeleted(e, ShapeType.ELLIPSE));
        Main.currentLevel.polygons.removeIf(e -> shapeSelectedAndDeleted(e, ShapeType.POLYGON));
        Main.currentLevel.rectangles.removeIf(e -> shapeSelectedAndDeleted(e, ShapeType.RECTANGLE));

        ScrollPaneHandler.objSP.updateList();
    }

    private static boolean shapeSelectedAndDeleted(Shape shape, ShapeType type) {

        if (!shape.isSelected)
            return false;

        shape.isSelected = false;
        deletedShapes.add(shape);

        switch (type) {

            case POINT:
                LoggingManager.history.push(Point.logMessageDelete);
                break;

            case CIRCLE:
                LoggingManager.history.push(Circle.logMessageDelete);
                break;

            case ELLIPSE:
                LoggingManager.history.push(Ellipse.logMessageDelete);
                break;

            case POLYGON:
                LoggingManager.history.push(Polygon.logMessageDelete);
                break;

            case RECTANGLE:
                LoggingManager.history.push(Rectangle.logMessageDelete);
                break;

        }

        return true;
    }

    public static void reAddShape(ShapeType type) {

        switch (type) {
            case CIRCLE:
                addCircle((Circle) deletedShapes.pop(), false);
                break;
            case ELLIPSE:
                addEllipse((Ellipse) deletedShapes.pop(), false);
                break;
            case POINT:
                addPoint((Point) deletedShapes.pop(), false);
                break;
            case POLYGON:
                reAddPolygon((Polygon) deletedShapes.pop(), false);
                break;
            case RECTANGLE:
                addRectangle((Rectangle) deletedShapes.pop(), false);
                break;
            default:
                System.out.println("ERROR - reAddShape data invalid [ManageLevelArrayLists]");
                System.exit(-1);
        }

    }

    /**
     * Sets all selection of shapes to false.
     */
    public static void removeAllSelections() {

        for (Circle c : Main.currentLevel.circles)
            c.isSelected = false;

        for (Ellipse e : Main.currentLevel.ellipses)
            e.isSelected = false;

        for (Point p : Main.currentLevel.points)
            p.isSelected = false;

        for (Polygon p : Main.currentLevel.polygons)
            p.isSelected = false;

        for (Rectangle r : Main.currentLevel.rectangles)
            r.isSelected = false;
    }

    public static void removeRecentShape(ShapeType type) {

        ArrayList<? extends Shape> currentType = typeToShapeArray(type);

        if (currentType == null)
            return;

        switch (type) {
            case CIRCLE:
                currentType.remove(Main.currentLevel.circles.size() - 1);
                break;
            case ELLIPSE:
                currentType.remove(Main.currentLevel.ellipses.size() - 1);
                break;
            case POINT:
                currentType.remove(Main.currentLevel.points.size() - 1);
                break;
            case POLYGON:
                currentType.remove(Main.currentLevel.polygons.size() - 1);
                break;
            case RECTANGLE:
                currentType.remove(Main.currentLevel.rectangles.size() - 1);
                break;
            default:
                System.out.println("ERROR - removeRecentShape data invalid [ManageLevelArrayLists]");
                System.exit(-1);
        }

        //updates objects list
        ScrollPaneHandler.objSP.updateList();

    }

    public static void addCircle(Circle circle, boolean log) {
        Main.currentLevel.circles.add(circle);

        //updates objects list
        ScrollPaneHandler.objSP.updateList();

        if (log)
            LoggingManager.history.push(Circle.logMessage);
    }

    public static void addEllipse(Ellipse ellipse, boolean log) {
        Main.currentLevel.ellipses.add(ellipse);

        //updates objects list
        ScrollPaneHandler.objSP.updateList();

        if (log)
            LoggingManager.history.push(Ellipse.logMessage);
    }

    public static void addPoint(Point point, boolean log) {
        Main.currentLevel.points.add(point);

        //updates objects list
        ScrollPaneHandler.objSP.updateList();

        if (log)
            LoggingManager.history.push(Point.logMessage);
    }

    public static void addPolygon(Polygon polygon, boolean log) {
        Main.currentLevel.polygons.add(polygon);

        //iterate over array and remove latest logMessagePoint messages
        for (int i = 0; i < polygon.getNumPoints(); i++) {
            for (String logMessage : LoggingManager.history) {
                if (logMessage.equals(PolygonCreatorState.logMessagePoint)) {
                    LoggingManager.history.remove(logMessage);
                    break;
                }
            }
        }

        //updates objects list
        ScrollPaneHandler.objSP.updateList();

        if (log)
            LoggingManager.history.push(Polygon.logMessage);
    }

    public static void addRectangle(Rectangle rectangle, boolean log) {
        Main.currentLevel.rectangles.add(rectangle);

        //updates objects list
        ScrollPaneHandler.objSP.updateList();

        if (log)
            LoggingManager.history.push(Rectangle.logMessage);
    }

    public static void reAddPolygon(Polygon polygon, boolean log) {
        Main.currentLevel.polygons.add(polygon);

        //updates objects list
        ScrollPaneHandler.objSP.updateList();

        if (log)
            LoggingManager.history.push(Polygon.logMessage);
    }

    public static ArrayList<Shape> getSelectedShapes() {

        ArrayList<Shape> shapes = new ArrayList<>();

        for (Circle c : Main.currentLevel.circles)
            if (c.isSelected)
                shapes.add(c);

        for (Point p : Main.currentLevel.points)
            if (p.isSelected)
                shapes.add(p);

        for (Ellipse e1 : Main.currentLevel.ellipses)
            if (e1.isSelected)
                shapes.add(e1);

        for (Polygon p : Main.currentLevel.polygons)
            if (p.isSelected)
                shapes.add(p);

        for (Rectangle r : Main.currentLevel.rectangles)
            if (r.isSelected)
                shapes.add(r);

        return shapes;


    }

}