package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;

import com.LevelEditor.GlobalMouseListeners.CustomMouseWheelListener;
import com.LevelEditor.MouseStates.MouseState;
import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.UpdatePaint;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener {

    //start value is gridSizeX
    public static int sliderValue = GridListener.gridSizeX;
    private static JLabel valueLabel;

    public SliderListener(JLabel valueLabel) {
        SliderListener.valueLabel = valueLabel;
    }

    public static void changeTextNum() {
        if (CustomMouseWheelListener.getState() == MouseState.EMouseStates.ROTATION)
            valueLabel.setText("" + Canvas.chosen);
        else
            valueLabel.setText("" + sliderValue);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        JSlider slider = (JSlider) e.getSource();

        sliderValue = slider.getValue();

        if (!AspectGridListener.isSelected) {
            GridListener.gridSizeX = sliderValue;
            GridListener.gridSizeY = sliderValue;
        }

        changeTextNum();

        UpdatePaint.remakeAll();
    }

}
