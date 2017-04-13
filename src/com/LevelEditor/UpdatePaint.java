package com.LevelEditor;

public class UpdatePaint {

    public static void remakeAll() {
        remakeRightY();
        remakeTopX();
        remakeWindow();
    }

    public static void remakeWindow() {
        ApplicationWindow.lvlWindow.repaint();
    }

    private static void remakeRightY() {
        ApplicationWindow.backRightYPanel.repaint();
    }

    private static void remakeTopX() {
        ApplicationWindow.backTopXPanel.repaint();
    }

}
