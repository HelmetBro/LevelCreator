package com.LevelEditor.GlobalMouseListeners;

import com.LevelEditor.Main;
import com.LevelEditor.ScreenComponents.Canvas.LevelWindow;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.FlipYListener;
import com.LevelEditor.UpdatePaint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CustomMouseMoveListener implements MouseMotionListener {

    private static int x = 0;
    private static int y = 0;

    private LevelWindow levelWindow;

    public CustomMouseMoveListener(LevelWindow levelWindow) {
        this.levelWindow = levelWindow;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        UpdatePaint.remakeAll();
        updateMousePosition();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        UpdatePaint.remakeAll();
        updateMousePosition();
    }

    private void updateMousePosition() {

        try {

            x = levelWindow.getMousePosition().x;

            if (FlipYListener.flipY)
                y = Main.applicationWindow.getHeight() - levelWindow.getMousePosition().y;
            else
                y = levelWindow.getMousePosition().y;

        } catch (Exception ignore) {
        }

    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

}
