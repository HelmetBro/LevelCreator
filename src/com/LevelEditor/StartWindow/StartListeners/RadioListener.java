package com.LevelEditor.StartWindow.StartListeners;

import com.LevelEditor.StartWindow.InitializeWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RadioListener implements ActionListener {

    public enum StartOptions {
        LOAD,
        AUTO,
        CUSTOM_AR,
        CUSTOM_AB
    }

    private final StartOptions selection;
    private final InitializeWindow window;

    public RadioListener(StartOptions selection, InitializeWindow window) {
        this.selection = selection;
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InitializeWindow.selectionChoice = selection;

        if (selection == StartOptions.AUTO) {
            InitializeWindow.getSettings().autoScale();
            window.updateRatioText();
        }
    }

}
