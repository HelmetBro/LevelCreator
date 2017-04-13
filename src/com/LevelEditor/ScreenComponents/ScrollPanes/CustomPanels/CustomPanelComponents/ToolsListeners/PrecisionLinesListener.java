package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;

import com.LevelEditor.ScreenComponents.LevelWindow;
import com.LevelEditor.UpdatePaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PrecisionLinesListener implements ActionListener {

    public static final Color GridColor = Color.RED;

    @Override
    public void actionPerformed(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();

        LevelWindow.drawPrecision = abstractButton.getModel().isSelected();

        UpdatePaint.remakeAll();
    }

}
