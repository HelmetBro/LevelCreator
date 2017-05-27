package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;


import com.LevelEditor.ScreenComponents.Canvas.LevelWindow;
import com.LevelEditor.UpdatePaint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnapToGridListener implements ActionListener {

    public static final int dotSize = 10;

    @Override
    public void actionPerformed(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();

        LevelWindow.snapToGrid = abstractButton.getModel().isSelected();

        UpdatePaint.remakeAll();
    }

}
