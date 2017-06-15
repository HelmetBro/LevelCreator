package com.LevelEditor;


import com.LevelEditor.MouseStates.PathCreatorState;
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

            case PolygonCreatorState.LOG_MESSAGE_POINT:
                PolygonCreatorState.removePointCurrentPoly();
                break;

            case PathCreatorState.LOG_MESSAGE_POINT:
                PathCreatorState.removeCurrentVector();
                break;

            //circle
            case Circle.LOG_MESSAGE:
                ManageLevelArrayLists.removeRecentShape(ShapeType.CIRCLE);
                break;
            case Circle.LOG_MESSAGE_DELETE:
                ManageLevelArrayLists.reAddShape(ShapeType.CIRCLE);
                break;

            //ellipse
            case Ellipse.LOG_MESSAGE:
                ManageLevelArrayLists.removeRecentShape(ShapeType.ELLIPSE);
                break;
            case Ellipse.LOG_MESSAGE_DELETE:
                ManageLevelArrayLists.reAddShape(ShapeType.ELLIPSE);
                break;

            //point
            case Point.LOG_MESSAGE:
                ManageLevelArrayLists.removeRecentShape(ShapeType.POINT);
                break;
            case Point.LOG_MESSAGE_DELETE:
                ManageLevelArrayLists.reAddShape(ShapeType.POINT);
                break;

            //polygon
            case Polygon.LOG_MESSAGE:
                ManageLevelArrayLists.removeRecentShape(ShapeType.POLYGON);
                break;
            case Polygon.LOG_MESSAGE_DELETE:
                ManageLevelArrayLists.reAddShape(ShapeType.POLYGON);
                break;

            //rectangle
            case Rectangle.LOG_MESSAGE:
                ManageLevelArrayLists.removeRecentShape(ShapeType.RECTANGLE);
                break;
            case Rectangle.LOG_MESSAGE_DELETE:
                ManageLevelArrayLists.reAddShape(ShapeType.RECTANGLE);
                break;

            //path
            case Path.LOG_MESSAGE:
                ManageLevelArrayLists.removeRecentShape(ShapeType.PATH);
                break;
            case Path.LOG_MESSAGE_DELETE:
                ManageLevelArrayLists.reAddShape(ShapeType.PATH);
                break;

            default:
                System.out.println("ERROR [LOGGING_MANAGER] - Undo log doesn't match any case. [Undo]");
        }

        //updates objects list
        ScrollPaneHandler.objSP.updateList();
    }

    //for debugging
    public static void printHistory() {

        System.out.println("-------------");

        for (String h : history)
            System.out.println(h);

        System.out.println("-------------");

    }

}
