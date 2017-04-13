package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;

import com.LevelEditor.Shapes.*;
import com.LevelEditor.UpdatePaint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HideShapeListener implements ActionListener {

    private final ShapeType type;

    public HideShapeListener(ShapeType type) {
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();

        switch (type) {
            case CIRCLE:
                Circle.isHidden = abstractButton.getModel().isSelected();
                break;
            case ELLIPSE:
                Ellipse.isHidden = abstractButton.getModel().isSelected();
                break;
            case POINT:
                Point.isHidden = abstractButton.getModel().isSelected();
                break;
            case POLYGON:
                Polygon.isHidden = abstractButton.getModel().isSelected();
                break;
            case RECTANGLE:
                Rectangle.isHidden = abstractButton.getModel().isSelected();
                break;
            default:
                System.out.println("ERROR - actionPerformed data invalid [HideShapeListener]");
                System.exit(-1);
        }

        UpdatePaint.remakeAll();

    }

}
