package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;

import com.LevelEditor.ScreenComponents.LevelWindow;
import com.LevelEditor.UpdatePaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridListener implements ActionListener {

    public static int gridSizeX = 20;
    public static int gridSizeY = 20;

    public static final Color gridColor = Color.gray;

    @Override
    public void actionPerformed(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();

        LevelWindow.drawGrid = abstractButton.getModel().isSelected();

        UpdatePaint.remakeAll();
    }

}
