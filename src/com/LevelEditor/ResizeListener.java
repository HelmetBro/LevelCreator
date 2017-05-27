package com.LevelEditor;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created by eric on 4/7/2017.
 */
public class ResizeListener extends ComponentAdapter {

    //reference
    private static JFrame window;

    public ResizeListener(JFrame window){
        ResizeListener.window = window;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        super.componentResized(e);

        int width = window.getContentPane().getSize().width;
        int height = window.getContentPane().getSize().height;

        //setting the values
        ApplicationWindow.settings.resizeWindowValues(window.getWidth(), window.getHeight());

        ApplicationWindow.backTopXPanel.moveComponent(width, height);
        ApplicationWindow.backRightYPanel.moveComponent(width, height);
        ApplicationWindow.ratioButton.moveComponent(width, height);
        ApplicationWindow.infoLabelButton.moveComponent(width, height);
        ApplicationWindow.scrollPaneHandler.moveComponent(width, height);
        ApplicationWindow.toolBarButton.moveComponent(width, height);
        ApplicationWindow.panelHolder.moveComponent(width, height);
        ApplicationWindow.scrollHolder.moveComponent(width, height); // ??
        ApplicationWindow.lvlWindow.moveComponent(width, height);
    }

}
