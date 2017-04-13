package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;

import com.LevelEditor.ScreenComponents.LevelWindow;
import com.LevelEditor.UpdatePaint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AntiAliasListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();

        LevelWindow.antiAlias = abstractButton.getModel().isSelected();

        UpdatePaint.remakeAll();
    }

}
