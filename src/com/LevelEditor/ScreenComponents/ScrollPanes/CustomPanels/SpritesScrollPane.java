package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels;

import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.DarkSpriteLinker;
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

    private FlowLayout layout = new FlowLayout(FlowLayout.LEFT);

    public SpritesScrollPane(int width, int windowHeight) {
        setFocusable(false);
        this.windowHeight = windowHeight;
        this.width = width - (int) getVerticalScrollBar().getPreferredSize().getWidth();

        infoTip = createInfoLabel();

        setViewportView(scrollPanel);

        //TEMP, change properties to Flowlayout also
        scrollPanel.setPreferredSize(new Dimension(width, windowHeight));
        scrollPanel.setLayout(layout);
    }

    public void updateSpriteEditor() {

        //all selected shapes
        ArrayList<Shape> shapes = ManageLevelArrayLists.getSelectedShapes();

        scrollPanel.removeAll();

        //manages is info tip is there or not based off # of shapes
        if (shapes.size() > 0) {
            scrollPanel.remove(infoTip);
            layout.setVgap(0);
            layout.setHgap(0);
        } else {
            scrollPanel.add(infoTip);

            layout.setVgap(20);
            layout.setHgap(10);

            //redrawing pane
            revalidate();
            repaint();

            return;
        }

        for (Shape s : shapes){
            DarkSpriteLinker link = new DarkSpriteLinker(s);
            scrollPanel.add(link);
        }

        revalidate();
        repaint();

    }

    private JLabel createInfoLabel() {

        JLabel infoTip;

        int labelWidth = 130;
        int labelHeight = 70;

        infoTip = new JLabel("<html><div style='text-align: center;'>" +
                "[select shapes to<br>link sprites]" +
                "</div></html>", SwingConstants.CENTER);
        infoTip.setBounds((width / 2 - labelWidth / 2) + (int) getVerticalScrollBar().getPreferredSize().getWidth() / 2,
                20, labelWidth, labelHeight);
        infoTip.setFont(CONSOLAS);

        infoTip.setForeground(INFO_BOX_COLOR);
        infoTip.setBorder(BorderFactory.createLineBorder(INFO_BOX_COLOR));
        infoTip.setBackground(super.getBackground());

        return infoTip;
    }

}
