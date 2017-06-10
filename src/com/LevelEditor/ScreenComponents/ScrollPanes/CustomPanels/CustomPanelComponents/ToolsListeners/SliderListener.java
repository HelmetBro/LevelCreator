package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;

import com.LevelEditor.UpdatePaint;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class SliderListener implements ChangeListener {

    //start value is gridSizeX
    public static int sliderValue = GridListener.gridSizeX;
    private JLabel valueLabel;

    public SliderListener(JLabel valueLabel) {
        this.valueLabel = valueLabel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        JSlider slider = (JSlider) e.getSource();

        sliderValue = slider.getValue();

        if (!AspectGridListener.isSelected) {
            GridListener.gridSizeX = sliderValue;
            GridListener.gridSizeY = sliderValue;
        }

        valueLabel.setText("" + sliderValue);

        UpdatePaint.remakeAll();
    }

}
