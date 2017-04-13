package com.LevelEditor.ScreenComponents.DarkComponents.DarkTree;

import com.LevelEditor.Shapes.Shape;

import javax.swing.tree.DefaultMutableTreeNode;


public class CustomTreeNode extends DefaultMutableTreeNode {

    //reference
    private Shape shape;

    public CustomTreeNode(Shape shape) {
        super(shape.name);
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }

}