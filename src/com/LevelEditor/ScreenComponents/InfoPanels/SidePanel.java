package com.LevelEditor.ScreenComponents.InfoPanels;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Resizable;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.GridListener;

import javax.swing.*;
import java.awt.*;

public abstract class SidePanel extends JPanel implements Resizable {

    protected static final Color BIG_LINE_COLOR = new Color(145, 145, 142);
    protected static final Color SMALL_LINE_COLOR = new Color(104, 104, 101);
    protected static final int BIG_LINE_SIZE = 10;
    protected static final int SMALL_LINE_SIZE = 5;
    public static boolean drawMarker = true;
    protected static int bigLineIncrementX = GridListener.gridSizeX;
    protected static int bigLineIncrementY = GridListener.gridSizeY;
    protected static int smallLineIncrementX;
    protected static int smallLineIncrementY;
    protected static MarkerImage marker;
    protected final int width;
    protected final int height;
    protected final int startX;

    public SidePanel(String text, Font font, int x, int y, int width, int height) {
        super();

        this.width = width;
        this.height = height;

        this.startX = x;

        marker = new MarkerImage();

        setFocusable(false);
        setVisible(true);

        setLayout(new BorderLayout());
        setBounds(x, y, width, height);

        //creating JLabel
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(font);
        label.setForeground(ApplicationWindow.LIGHT_SHADED_COLOR);

        add(label, BorderLayout.CENTER);

        setBackground(ApplicationWindow.BACKGROUND_SHADED_COLOR);
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
