package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;

import com.LevelEditor.ScreenComponents.InfoPanels.SidePanel;
import com.LevelEditor.UpdatePaint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarkerListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();

        SidePanel.drawMarker = abstractButton.getModel().isSelected();

        UpdatePaint.remakeAll();
    }
}
