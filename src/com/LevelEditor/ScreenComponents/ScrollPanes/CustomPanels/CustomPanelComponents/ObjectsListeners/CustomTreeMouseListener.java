package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ObjectsListeners;

import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.ObjectsTreeScrollPane;
import com.LevelEditor.UpdatePaint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class CustomTreeMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        //When user clicks on the "empty surface"
        if (ObjectsTreeScrollPane.listOfShapes.getRowForLocation(e.getX(), e.getY()) == -1) {
            ObjectsTreeScrollPane.listOfShapes.clearSelection();
            ManageLevelArrayLists.removeAllSelections();
        }

        UpdatePaint.remakeWindow();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
