package com.LevelEditor.StartWindow.StartListeners;

import com.LevelEditor.StartWindow.AspectSettings;
import com.LevelEditor.StartWindow.InitializeWindow;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class DimensionListener extends ValueDocumentListener {

    public enum DimensionFields {
        X,
        Y,
        WIDTH,
        HEIGHT,
        MULTIPLIER,
    }

    private final DimensionFields field;

    public DimensionListener(InitializeWindow window, AspectSettings settings, DimensionFields field) {
        super(window, settings);
        this.field = field;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateFields(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateFields(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateFields(e);
    }

    @Override
    protected void updateFields(DocumentEvent e) {

        int value;

        if (inWrongState()){
            window.updateRatioText();
            return;
        }

        try {

            value = textToValue(e);

            if (value == -1)
                return;

        } catch (Exception e1) {
            window.updateRatioText();
            return;
        }

        switch (field) {

            case X:
                settings.changeAspectX(value);
                break;

            case Y:
                settings.changeAspectY(value);
                break;

            case MULTIPLIER:
                settings.changeMultiplier(value);
                break;

        }


        window.updateRatioText();

    }

    @Override
    protected boolean inWrongState(){
        return InitializeWindow.selectionChoice == RadioListener.StartOptions.AUTO ||
                InitializeWindow.selectionChoice == RadioListener.StartOptions.LOAD ||
                InitializeWindow.selectionChoice == RadioListener.StartOptions.CUSTOM_AB;
    }

}
