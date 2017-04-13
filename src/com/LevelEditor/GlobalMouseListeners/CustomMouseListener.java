package com.LevelEditor.GlobalMouseListeners;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.MouseStates.MouseState;
import com.LevelEditor.UpdatePaint;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomMouseListener implements MouseListener {

    static MouseState currentState;

    public static void updateLayer(Graphics2D g) {
        currentState.updateLayer(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        UpdatePaint.remakeWindow();
        currentState.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        UpdatePaint.remakeWindow();
        currentState.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        UpdatePaint.remakeWindow();
        currentState.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ApplicationWindow.lvlWindow.requestFocus();
        currentState.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        currentState.mouseExited(e);
    }
}