package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels;


import com.LevelEditor.ScreenComponents.ScrollPanes.CustomScrollBarUI;

import javax.swing.*;

import static com.LevelEditor.ApplicationWindow.backgroundShadedColor;
import static com.LevelEditor.ApplicationWindow.lightColor;

public abstract class CustomScrollPane extends JScrollPane  {

    JPanel scrollPanel;

    CustomScrollPane() {
        super(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //creating the JPanel viewport
        scrollPanel = new JPanel(null);
        scrollPanel.setBackground(backgroundShadedColor);
        scrollPanel.setForeground(lightColor);

        setBorder(BorderFactory.createEmptyBorder());

        setScrollBottomRight();

        changeScrollToolLook();
    }

    private void setScrollBottomRight() {

        //bottom right corner thing between both scroll bars
        JLabel bottomRightCorner = new JLabel();
        bottomRightCorner.setOpaque(true);
        bottomRightCorner.setFocusable(false);
        bottomRightCorner.setVisible(true);
        bottomRightCorner.setBackground(backgroundShadedColor);
        bottomRightCorner.setBorder(BorderFactory.createMatteBorder(
                1, 1, 0, 0, lightColor));
        setCorner(JScrollPane.LOWER_RIGHT_CORNER, bottomRightCorner);

        getVerticalScrollBar().setUnitIncrement(10);
        getHorizontalScrollBar().setUnitIncrement(10);

    }

    private void changeScrollToolLook() {
        //vertical bar
        getVerticalScrollBar().setUI(new CustomScrollBarUI());

        //horizontal bar
        getHorizontalScrollBar().setUI(new CustomScrollBarUI());
    }

}
