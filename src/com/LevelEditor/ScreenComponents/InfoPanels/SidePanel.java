package com.LevelEditor.ScreenComponents.InfoPanels;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Resizable;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.GridListener;

import javax.swing.*;
import java.awt.*;

public abstract class SidePanel extends JPanel implements Resizable {

    protected static final Color bigLineColor = new Color(145, 145, 142);
    protected static final Color smallLineColor = new Color(104, 104, 101);

    protected static int bigLineIncrementX = GridListener.gridSizeX;
    protected static int bigLineIncrementY = GridListener.gridSizeY;
    protected static final int bigLineSize = 10;

    protected static int smallLineIncrementX;
    protected static int smallLineIncrementY;
    protected static final int smallLineSize = 5;

    protected final int width;
    protected final int height;

    protected final int startX;

    public static boolean drawMarker = true;
    protected static MarkerImage marker;

    public SidePanel(String text, Font font, int x, int y, int width, int height) {
        super();

        this.width = width;
        this.height = height;

        this.startX = x;

        marker = new MarkerImage();

        setToolTipText("Level with " + text + " pixels in startHeight");
        setFocusable(false);
        setVisible(true);

        setLayout(new BorderLayout());
        setBounds(x, y, width, height);

        //creating JLabel
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(font);
        label.setForeground(ApplicationWindow.lightShadedColor);

        add(label, BorderLayout.CENTER);

        setBackground(ApplicationWindow.backgroundShadedColor);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        bigLineIncrementX = GridListener.gridSizeX;
        bigLineIncrementY = GridListener.gridSizeY;

        smallLineIncrementY = GridListener.gridSizeY / 4;
        smallLineIncrementX = GridListener.gridSizeX / 4;

        drawIncrements(g2d);
        drawMarker(g2d);
    }

    protected abstract void drawIncrements(Graphics2D g);

    protected abstract void drawMarker(Graphics2D g2d);

}
