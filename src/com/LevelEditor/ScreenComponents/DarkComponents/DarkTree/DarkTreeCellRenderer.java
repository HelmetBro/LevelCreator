package com.LevelEditor.ScreenComponents.DarkComponents.DarkTree;


import com.LevelEditor.ApplicationWindow;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.basicFont;
import static com.LevelEditor.ApplicationWindow.lightColor;

public class DarkTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel, boolean exp, boolean leaf,
                                                  int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row, hasFocus);
        setForeground(lightColor);
        setFont(basicFont);

        return this;
    }

    @Override
    public Color getBackgroundNonSelectionColor() {
        return (null);
    }

    @Override
    public Color getBackgroundSelectionColor() {
        return ApplicationWindow.selectionColor;
    }

    @Override
    public Color getBackground() {
        return (null);
    }


}
