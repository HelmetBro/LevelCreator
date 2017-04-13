package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FlipYListener implements ActionListener {

    public static boolean flipY;

    @Override
    public void actionPerformed(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();

        flipY = abstractButton.getModel().isSelected();

        //flip mouse thing

        //flip coordinates of all shapes


    }

}
