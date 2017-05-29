package com.LevelEditor.ScreenComponents;

import com.LevelEditor.LoggingManager;
import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;
import com.LevelEditor.UpdatePaint;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class CustomKeyboardListener implements KeyListener {

    private static boolean pressingCtrl;
    private static boolean pressingShift;

    private Canvas canvas;

    public CustomKeyboardListener(Canvas canvas){
        this.canvas = canvas;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        //checking for arrow keys
        if (e.getKeyCode() == KeyEvent.VK_UP)asdfgasdfg

                //ADD ARROW KEYS FOR MOVING CANVAS


        pressingCtrl = e.isControlDown();
        pressingShift = e.isShiftDown();

        //if im pressing ctrl + z
        if (pressingCtrl && e.getKeyCode() == KeyEvent.VK_Z)
            LoggingManager.Undo();

        //if i press delete and there are shapes selected
        if (e.getKeyCode() == KeyEvent.VK_DELETE && ManageLevelArrayLists.getSelectedShapes().size() > 0) {
            new Thread(() -> {
                ManageLevelArrayLists.removeSelectedShapes();
                ScrollPaneHandler.propSP.updatePropertyEditor();
                UpdatePaint.remakeAll();
            }).start();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressingCtrl = e.isControlDown();
        pressingShift = e.isShiftDown();
    }

    public static boolean isPressingCtrl() {
        return pressingCtrl;
    }

    public static boolean isPressingShift() {
        return pressingShift;
    }

}
