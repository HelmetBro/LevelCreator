package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels;

import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.Shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.LevelEditor.ApplicationWindow.CONSOLAS;
import static com.LevelEditor.ApplicationWindow.LIGHT_COLOR;

public class SpritesScrollPane extends CustomScrollPane {

    private static final Color INFO_BOX_COLOR = new Color(
            LIGHT_COLOR.getRed(),
            LIGHT_COLOR.getGreen(),
            LIGHT_COLOR.getBlue(),
            Math.abs(LIGHT_COLOR.getAlpha() - 200));

    private int windowHeight;
    private int width;

    private JLabel infoTip;

    public SpritesScrollPane(int width, int windowHeight) {
        setFocusable(false);
        this.windowHeight = windowHeight;
        this.width = width - (int) getVerticalScrollBar().getPreferredSize().getWidth();

        infoTip = createInfoLabel();

        scrollPanel.add(infoTip);

        setViewportView(scrollPanel);
    }

    public void updateSpriteEditor() {

        //all selected shapes
        ArrayList<Shape> shapes = ManageLevelArrayLists.getSelectedShapes();

        scrollPanel.removeAll();

        //manages is info tip is there or not based off # of shapes
        if (shapes.size() > 0) {
            scrollPanel.remove(infoTip);
        } else {
            scrollPanel.add(infoTip);

            //resetting the window startHeight without spacing
            scrollPanel.setPreferredSize(new Dimension(width, windowHeight));

            //redrawing pane
            revalidate();
            repaint();

            return;
        }


    }

    private JLabel createInfoLabel() {

        JLabel infoTip;

        int labelWidth = 150;

        infoTip = new JLabel("<html><div style='text-align: center;'>" +
                "[select shapes to<br>link sprites]" +
                "</div></html>", SwingConstants.CENTER);
        infoTip.setBounds((width / 2 - labelWidth / 2) + (int) getVerticalScrollBar().getPreferredSize().getWidth() / 2,
                20, labelWidth, 80);
        infoTip.setFont(CONSOLAS);

        infoTip.setForeground(INFO_BOX_COLOR);
        infoTip.setBorder(BorderFactory.createLineBorder(INFO_BOX_COLOR));
        infoTip.setBackground(super.getBackground());

        return infoTip;
    }

}
