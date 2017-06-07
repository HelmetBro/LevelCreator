package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.UpdatePaint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AspectGridListener implements ActionListener {

    public static boolean isSelected;

    /* What to multiply aspect ratio by, if y or x is less than 5 */
    private static final int MINI_MULTIPLIER = 5;

    @Override
    public void actionPerformed(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();

        isSelected = abstractButton.getModel().isSelected();

        if (!isSelected) {
            GridListener.gridSizeX = SliderListener.sliderValue;
            GridListener.gridSizeY = SliderListener.sliderValue;
        } else {

            int x = ApplicationWindow.settings.getAspectRatioX();
            int y = ApplicationWindow.settings.getAspectRatioY();

            if (x < 5 || y < 5) {
                x *= MINI_MULTIPLIER;
                y *= MINI_MULTIPLIER;
            }

            GridListener.gridSizeX = x;
            GridListener.gridSizeY = y;
        }


        UpdatePaint.remakeAll();
    }

}
