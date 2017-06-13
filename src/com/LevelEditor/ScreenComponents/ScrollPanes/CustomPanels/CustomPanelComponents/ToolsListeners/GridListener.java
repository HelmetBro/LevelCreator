package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;

import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.UpdatePaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridListener implements ActionListener {

    public static final Color gridColor = new Color(128, 128, 125, 150);
    public static int gridSizeX = 20;
    public static int gridSizeY = 20;

    @Override
    public void actionPerformed(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();

        Canvas.drawGrid = abstractButton.getModel().isSelected();

        UpdatePaint.remakeAll();
    }

}
