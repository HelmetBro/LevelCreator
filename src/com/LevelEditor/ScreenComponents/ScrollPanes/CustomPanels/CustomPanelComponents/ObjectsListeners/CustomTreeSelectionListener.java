package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ObjectsListeners;


import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkTree.CustomTreeNode;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.ObjectsTreeScrollPane;
import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;
import com.LevelEditor.UpdatePaint;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class CustomTreeSelectionListener implements TreeSelectionListener {

    static TreePath[] selectedPaths;

    @Override
    public void valueChanged(TreeSelectionEvent e) {

        selectedPaths = ObjectsTreeScrollPane.listOfShapes.getSelectionPaths();

        if (selectedPaths == null)
            return;

        ManageLevelArrayLists.removeAllSelections();

        for (TreePath tp : selectedPaths) {

            Object node = tp.getLastPathComponent();

            if (!(node instanceof CustomTreeNode))
                continue;

            //get node that current selected path points too, then get the custom index of that
            CustomTreeNode selectedNode = (CustomTreeNode) tp.getLastPathComponent();

            selectedNode.getShape().isSelected = true;
        }

        //starting new thread to manage property label stuff
        new Thread(() -> ScrollPaneHandler.propSP.updatePropertyEditor()).start();

        UpdatePaint.remakeWindow();

    }
}