package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels;


import com.LevelEditor.Main;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkTree.CustomTreeNode;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkTree.DarkTreeCellRenderer;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ObjectsListeners.CustomTreeKeyListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ObjectsListeners.CustomTreeMouseListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ObjectsListeners.CustomTreeSelectionListener;
import com.LevelEditor.Shapes.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.util.Stack;

import static com.LevelEditor.ApplicationWindow.backgroundShadedColor;

public class ObjectsTreeScrollPane extends CustomScrollPane {

    private DefaultMutableTreeNode allShapeNodes;

    private DefaultMutableTreeNode circleNodes;
    private DefaultMutableTreeNode ellipseNodes;
    private DefaultMutableTreeNode pointNodes;
    private DefaultMutableTreeNode polygonNodes;
    private DefaultMutableTreeNode rectangleNodes;

    public static JTree listOfShapes;

    private DefaultTreeModel model;

    public ObjectsTreeScrollPane() {
        setFocusable(false);

        //root node
        allShapeNodes = new DefaultMutableTreeNode("Objects");

        //individual shape nodes
        circleNodes = new DefaultMutableTreeNode("Circles");
        ellipseNodes = new DefaultMutableTreeNode("Ellipses");
        pointNodes = new DefaultMutableTreeNode("Points");
        polygonNodes = new DefaultMutableTreeNode("Polygons");
        rectangleNodes = new DefaultMutableTreeNode("Rectangles");

        allShapeNodes.add(circleNodes);
        allShapeNodes.add(ellipseNodes);
        allShapeNodes.add(pointNodes);
        allShapeNodes.add(polygonNodes);
        allShapeNodes.add(rectangleNodes);

        createTree();
        updateList();

        //setting it
        setViewportView(listOfShapes);
    }

    private void createTree() {
        listOfShapes = new JTree(allShapeNodes);
        listOfShapes.setToggleClickCount(2);
        listOfShapes.setBackground(backgroundShadedColor);
        listOfShapes.setEditable(false);
        listOfShapes.setDragEnabled(false);
        listOfShapes.setCellRenderer(new DarkTreeCellRenderer());
        listOfShapes.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        listOfShapes.addTreeSelectionListener(new CustomTreeSelectionListener());
        listOfShapes.addKeyListener(new CustomTreeKeyListener());
        listOfShapes.addMouseListener(new CustomTreeMouseListener());
        model = (DefaultTreeModel) listOfShapes.getModel();
    }

    public void updateList() {

        //removes all children from folder nodes
        circleNodes.removeAllChildren();
        ellipseNodes.removeAllChildren();
        pointNodes.removeAllChildren();
        polygonNodes.removeAllChildren();
        rectangleNodes.removeAllChildren();

        updateCircles();
        updateEllipses();
        updatePoints();
        updatePolygons();
        updateRectangles();

        model.reload();
    }

    private void updateCircles() {

        for (int i = 0; i < Main.currentLevel.circles.size(); i++) {

            Circle circle = Main.currentLevel.circles.get(i);

            if (circle.name == null)
                circle.name = "Circle " + (i + 1);

            CustomTreeNode circleNode = new CustomTreeNode(circle);

            //root node
            DefaultMutableTreeNode radiusNode = new DefaultMutableTreeNode("Radius: " + circle.radius);
            radiusNode.setAllowsChildren(false);

            //root node
            DefaultMutableTreeNode locationXNode = new DefaultMutableTreeNode("X: " + circle.getCenter().getX());
            locationXNode.setAllowsChildren(false);

            //root node
            DefaultMutableTreeNode locationYNode = new DefaultMutableTreeNode("Y: " + circle.getCenter().getY());
            locationYNode.setAllowsChildren(false);

            circleNode.add(locationXNode);
            circleNode.add(locationYNode);
            circleNode.add(radiusNode);

            circleNodes.add(circleNode);

        }//big for loop

    }

    private void updateEllipses() {

        for (int i = 0; i < Main.currentLevel.ellipses.size(); i++) {

            Ellipse ellipse = Main.currentLevel.ellipses.get(i);

            if (ellipse.name == null)
                ellipse.name = "Ellipse " + (i + 1);

            CustomTreeNode ellipseNode = new CustomTreeNode(ellipse);

            //root node
            DefaultMutableTreeNode widthNode = new DefaultMutableTreeNode("Width: " + ellipse.width);
            widthNode.setAllowsChildren(false);

            //root node
            DefaultMutableTreeNode heightNode = new DefaultMutableTreeNode("Height: " + ellipse.height);
            heightNode.setAllowsChildren(false);

            //root node
            DefaultMutableTreeNode locationXNode = new DefaultMutableTreeNode("X: " + ellipse.getCenter().getX());
            locationXNode.setAllowsChildren(false);

            //root node
            DefaultMutableTreeNode locationYNode = new DefaultMutableTreeNode("Y: " + ellipse.getCenter().getY());
            locationYNode.setAllowsChildren(false);

            ellipseNode.add(locationXNode);
            ellipseNode.add(locationYNode);
            ellipseNode.add(widthNode);
            ellipseNode.add(heightNode);

            ellipseNodes.add(ellipseNode);

        }//big for loop
    }

    private void updatePoints() {

        for (int i = 0; i < Main.currentLevel.points.size(); i++) {

            Point point = Main.currentLevel.points.get(i);

            if (point.name == null)
                point.name = "Point " + (i + 1);

            CustomTreeNode pointNode = new CustomTreeNode(point);

            //root node
            DefaultMutableTreeNode locationXNode = new DefaultMutableTreeNode("X: " + point.getX());
            locationXNode.setAllowsChildren(false);

            //root node
            DefaultMutableTreeNode locationYNode = new DefaultMutableTreeNode("Y: " + point.getY());
            locationYNode.setAllowsChildren(false);

            pointNode.add(locationXNode);
            pointNode.add(locationYNode);

            pointNodes.add(pointNode);

        }//big for loop
    }

    private void updatePolygons() {
        for (int i = 0; i < Main.currentLevel.polygons.size(); i++) {

            Polygon poly = Main.currentLevel.polygons.get(i);

            if (poly.name == null)
                poly.name = "Polygon " + (i + 1);

            CustomTreeNode polygonNode = new CustomTreeNode(poly);

            Stack<Point> polyPoints = poly.getPoints();

            for (Point polyPoint : polyPoints) {
                //root node
                DefaultMutableTreeNode point = new DefaultMutableTreeNode("(" + polyPoint.getX() + ", " + polyPoint.getY() + ")");
                point.setAllowsChildren(false);

                polygonNode.add(point);
            }

            polygonNodes.add(polygonNode);

        }//big for loop
    }

    private void updateRectangles() {

        for (int i = 0; i < Main.currentLevel.rectangles.size(); i++) {

            Rectangle rectangle = Main.currentLevel.rectangles.get(i);

            if (rectangle.name == null)
                rectangle.name = "Rectangle " + (i + 1);

            CustomTreeNode rectangleNode = new CustomTreeNode(rectangle);

            //root node X
            DefaultMutableTreeNode locationXNode = new DefaultMutableTreeNode("X: " + rectangle.getCenter().getX());
            locationXNode.setAllowsChildren(false);

            //root node Y
            DefaultMutableTreeNode locationYNode = new DefaultMutableTreeNode("Y: " + rectangle.getCenter().getY());
            locationYNode.setAllowsChildren(false);

            //root node startWidth
            DefaultMutableTreeNode width = new DefaultMutableTreeNode("W: " + rectangle.width);
            width.setAllowsChildren(false);

            //root node startHeight
            DefaultMutableTreeNode height = new DefaultMutableTreeNode("H: " + rectangle.height);
            height.setAllowsChildren(false);

            rectangleNode.add(locationXNode);
            rectangleNode.add(locationYNode);
            rectangleNode.add(width);
            rectangleNode.add(height);

            rectangleNodes.add(rectangleNode);

        }//big for loop

    }

}
