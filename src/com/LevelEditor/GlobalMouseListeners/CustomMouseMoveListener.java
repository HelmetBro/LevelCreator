package com.LevelEditor.GlobalMouseListeners;

import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.UpdatePaint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CustomMouseMoveListener implements MouseMotionListener {

    private static int x = 0;
    private static int y = 0;

    private Canvas canvas;

    public CustomMouseMoveListener(Canvas canvas) {
        this.canvas = canvas;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
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
            x = canvas.getMousePosition().x;
            y = canvas.getMousePosition().y;
        } catch (Exception ignore) {
        }
    }

}
