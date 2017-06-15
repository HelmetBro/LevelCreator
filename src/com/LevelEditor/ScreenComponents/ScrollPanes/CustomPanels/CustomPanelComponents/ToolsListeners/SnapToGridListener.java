package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;


import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.UpdatePaint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnapToGridListener implements ActionListener {

    public static final int DOT_SIZE = 10;

    @Override
    public void actionPerformed(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();

        Canvas.snapToGrid = abstractButton.getModel().isSelected();

        UpdatePaint.remakeAll();
    }

}
