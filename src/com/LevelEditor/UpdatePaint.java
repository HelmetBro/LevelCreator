package com.LevelEditor;

public class UpdatePaint {

    public static void remakeAll() {
        remakeRightY();
        remakeTopX();
        remakeWindow();
        delay();
    }

    public static void remakeWindow() {
        ApplicationWindow.canvas.repaint();
        ApplicationWindow.scrollHolder.repaint();
        delay();
    }

    private static void remakeRightY() {
        ApplicationWindow.backRightYPanel.repaint();
    }

    private static void remakeTopX() {
        ApplicationWindow.backTopXPanel.repaint();
    }

    //artificial method to reduce delay
    private static void delay() {

        try {
            Thread.sleep(2);
        } catch (InterruptedException ignore) {
        }

    }

}
