package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;

import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.UpdatePaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PrecisionLinesListener implements ActionListener {

    public static final Color GRID_COLOR = Color.RED;
    public static final BasicStroke DASHED_STROKE = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);

    @Override
    public void actionPerformed(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();

        Canvas.drawPrecision = abstractButton.getModel().isSelected();

        UpdatePaint.remakeAll();
    }

}
