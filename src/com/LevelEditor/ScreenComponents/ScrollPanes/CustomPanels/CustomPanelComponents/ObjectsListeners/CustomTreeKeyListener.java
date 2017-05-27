package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ObjectsListeners;

import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.UpdatePaint;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomTreeKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        //if i press delete on some nodes
        if (e.getKeyCode() == KeyEvent.VK_DELETE && CustomTreeSelectionListener.selectedPaths != null) {
            ManageLevelArrayLists.removeSelectedShapes();
            UpdatePaint.remakeWindow();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
