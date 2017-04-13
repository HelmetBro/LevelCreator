package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;

import com.LevelEditor.Shapes.Shape;
import com.LevelEditor.UpdatePaint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShapeFillListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();

        Shape.isFilled = abstractButton.getModel().isSelected();

        UpdatePaint.remakeAll();
    }

}
