package com.LevelEditor.StartWindow.StartListeners;

import com.LevelEditor.StartWindow.AspectSettings;
import com.LevelEditor.StartWindow.InitializeWindow;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class AbsolutePixelListener extends ValueDocumentListener {

    public enum Dimension{
        WIDTH,
        HEIGHT
    }

    private final Dimension d;

    private long width;
    private long height;

    public AbsolutePixelListener(InitializeWindow window, AspectSettings settings, Dimension dimension){
        super(window, settings);
        this.d = dimension;
        this.width = settings.getLvlMakerWidth();
        this.height = settings.getLvlMakerHeight();
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
    protected void updateFields(DocumentEvent e){

        int value;

        if (inWrongState()){
            window.updateAbsoluteText(width, height);
            return;
        }

        try {

            value = textToValue(e);

            if (value == -1)
                return;

        } catch (Exception e1) {
            //change back to defaults
            window.updateAbsoluteText(width, height);
            return;
        }

        //changing values from text input
        if (d == Dimension.WIDTH){
            settings.changeWidth(value);
            width = value;
        } else if (d == Dimension.HEIGHT){
            settings.changeHeight(value);
            height = value;
        } else {
            System.out.println("ERROR - [AbsolutePixelListener] Invalid dimension [updateFields]");
        }
    }

    @Override
    protected boolean inWrongState(){
        return InitializeWindow.selectionChoice == RadioListener.StartOptions.AUTO ||
                InitializeWindow.selectionChoice == RadioListener.StartOptions.LOAD ||
                InitializeWindow.selectionChoice == RadioListener.StartOptions.CUSTOM_AR;
    }

}
