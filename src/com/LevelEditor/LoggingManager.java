package com.LevelEditor;


import com.LevelEditor.MouseStates.PolygonCreatorState;
import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;
import com.LevelEditor.Shapes.*;

import java.util.Stack;

public final class LoggingManager {

    public static Stack<String> history = new Stack<>();

    public static void Undo() {

        if (history.isEmpty())
            return;

        UpdatePaint.remakeWindow();

        //undo most recent action
        switch (history.pop()) {

            case PolygonCreatorState.logMessagePoint:
                PolygonCreatorState.removePointCurrentPoly();
                break;

            //circle
            case Circle.logMessage:
                ManageLevelArrayLists.removeRecentShape(ShapeType.CIRCLE);
                break;
            case Circle.logMessageDelete:
                ManageLevelArrayLists.reAddShape(ShapeType.CIRCLE);
                break;

            //ellipse
            case Ellipse.logMessage:
                ManageLevelArrayLists.removeRecentShape(ShapeType.ELLIPSE);
                break;
            case Ellipse.logMessageDelete:
                ManageLevelArrayLists.reAddShape(ShapeType.ELLIPSE);
                break;

            //point
            case Point.logMessage:
                ManageLevelArrayLists.removeRecentShape(ShapeType.POINT);
                break;
            case Point.logMessageDelete:
                ManageLevelArrayLists.reAddShape(ShapeType.POINT);
                break;

            //polygon
            case Polygon.logMessage:
                ManageLevelArrayLists.removeRecentShape(ShapeType.POLYGON);
                break;
            case Polygon.logMessageDelete:
                ManageLevelArrayLists.reAddShape(ShapeType.POLYGON);
                break;

            //rectangle
            case Rectangle.logMessage:
                ManageLevelArrayLists.removeRecentShape(ShapeType.RECTANGLE);
                break;
            case Rectangle.logMessageDelete:
                ManageLevelArrayLists.reAddShape(ShapeType.RECTANGLE);
                break;

            default:
                System.out.println("ERROR - Undo log doesn't match any case");
        }

        //updates objects list
        ScrollPaneHandler.objSP.updateList();
    }

}
