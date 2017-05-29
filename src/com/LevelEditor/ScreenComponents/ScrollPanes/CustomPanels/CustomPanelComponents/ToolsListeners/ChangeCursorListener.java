package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners;

import com.LevelEditor.ApplicationWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeCursorListener implements ActionListener {

    private Cursor cursor;

    public ChangeCursorListener(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ApplicationWindow.canvas.setCursor(cursor);
    }
}


