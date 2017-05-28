package com.LevelEditor.MouseStates;


import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class SelectionMouseState extends MouseState {

    @Override
    public void updateLayer(Graphics2D g) {
        super.updateLayer(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {

        if (SwingUtilities.isRightMouseButton(e))
            rightClick();
        else if (SwingUtilities.isLeftMouseButton(e))
            select();
    }

    private void rightClick() {
        //delete shapes
        if (ManageLevelArrayLists.getSelectedShapes().size() > 0) {
            new Thread(() -> {
                ManageLevelArrayLists.removeSelectedShapes();
                ScrollPaneHandler.propSP.updatePropertyEditor();
                ScrollPaneHandler.objSP.updateList();
            }).start();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
