package com.LevelEditor.ScreenComponents.Canvas;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.GlobalMouseListeners.CustomMouseListener;
import com.LevelEditor.GlobalMouseListeners.CustomMouseMoveListener;
import com.LevelEditor.GlobalMouseListeners.CustomMouseWheelListener;
import com.LevelEditor.Main;
import com.LevelEditor.Resizable;
import com.LevelEditor.ScreenComponents.CustomKeyboardListener;
import com.LevelEditor.ScreenComponents.RatioButton;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.GridListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.PrecisionLinesListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.SnapToGridListener;
import com.LevelEditor.Utilities;

import javax.swing.*;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.lightColor;
import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class Canvas extends JPanel implements Resizable {

    //default values
    public static boolean drawPrecision;
    public static boolean drawGrid;
    public static boolean snapToGrid;
    public static boolean antiAlias = true;

    private static BackgroundColorState colorState = BackgroundColorState.LIGHT_BLACK;
    public static final byte numOfBackStates = 5;

    private final int width;
    private final int height;

    public static int snappedX = 0;
    public static int snappedY = 0;

    //zooming properties
    private float currentZoom = 1f;
    private static final float ZOOM_INTERVAL = 0.05f;
    private static final float ZOOM_MAX = 8f;
    private static final float ZOOM_MIN = ZOOM_INTERVAL;
    private boolean translate = false;

    private float translateCoorX = 0;
    private float translateCoorY = 0;

    private RatioButton button;

    public Canvas(int x, int y, int width, int height, RatioButton button) {
        this.width = width;
        this.height = height;
        this.button = button;
        setPreferredSize(new Dimension(width, height));
        setBounds(x, y, width, height);
        setColor(colorState);
        setVisible(true);
        setFocusable(true);

        addMouseListener(new CustomMouseListener());
        addMouseWheelListener(new CustomMouseWheelListener(this));
        addKeyListener(new CustomKeyboardListener(this));
        addMouseMotionListener(new CustomMouseMoveListener(this));
    }

    public void zoomInRequest() {
        if (currentZoom < ZOOM_MAX) {
            currentZoom += ZOOM_INTERVAL;
            translate = true;
        }

        repaint();
    }

    public void zoomOutRequest() {
        if (currentZoom > ZOOM_MIN) {
            currentZoom += -ZOOM_INTERVAL;
            translate = true;
        }

        repaint();
    }

    private void zoom(Graphics2D g2d) {

        if (translate) {

            float xOff = (width - width * currentZoom);
            float yOff = (height - height * currentZoom);
            translateCoorX = xOff * CustomMouseMoveListener.getX() / (float) width / currentZoom;
            translateCoorY = yOff * CustomMouseMoveListener.getY() / (float) height / currentZoom;

            button.displayZoom(currentZoom);

            //resetting flag
            translate = false;
        }

        //scale/translate it
        g2d.scale(currentZoom, currentZoom);
        g2d.translate(translateCoorX, translateCoorY);

        //draw outline so user can see canvas
        g2d.setColor(lightColor);
        g2d.drawRect(-1, -1, width + 1, height + 1);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (antiAlias)
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

        zoom(g2d);

        //added shapes
        Main.currentLevel.updateLevel(g2d);

        //shape in the making
        CustomMouseListener.updateLayer(g2d);

        //editor grid
        updateGrid(g2d);

        //editor precision lines
        updatePrecision(g2d);

        //update mouse snapping to grid
        updateSnapGrid(g2d);

    }

    private void updateSnapGrid(Graphics2D g) {

        if (snapToGrid) {

            snappedX = Utilities.round(CustomMouseMoveListener.getX(), GridListener.gridSizeX);
            snappedY = Utilities.round(CustomMouseMoveListener.getY(), GridListener.gridSizeY);

            g.setColor(GridListener.gridColor.brighter());

            g.fillOval(snappedX - SnapToGridListener.dotSize / 2, snappedY - SnapToGridListener.dotSize / 2,
                    SnapToGridListener.dotSize, SnapToGridListener.dotSize);
        }
    }

    private void updateGrid(Graphics2D g) {
        if (drawGrid) {
            g.setColor(GridListener.gridColor);

            //horizontal lines
            for (int i = 0; i < height; i += GridListener.gridSizeY)
                g.drawLine(0, i, width, i);

            //vertical lines
            for (int i = 0; i < width; i += GridListener.gridSizeX)
                g.drawLine(i, 0, i, height);

        }
    }

    private void updatePrecision(Graphics2D g) {

        if (drawPrecision) {

            int mouseX = CustomMouseMoveListener.getX();
            int mouseY = CustomMouseMoveListener.getY();

            g.setColor(PrecisionLinesListener.GridColor);

            //vertical line
            g.drawLine(mouseX, 0, mouseX, height);

            //horizontal line
            g.drawLine(0, mouseY, width, mouseY);

        }
    }

    public void setColor(BackgroundColorState state) {
        setBackground(new Color(state.red, state.green, state.blue));
    }

    public static BackgroundColorState getColorState() {
        return colorState;
    }

    @Override
    public void moveComponent(int windowWidth, int windowHeight) {
        int moveDistanceX = (windowWidth - ApplicationWindow.settings.getLvlMakerWidth() - RULER_WIDTH - ApplicationWindow.settings.toolsWindowSizeX) / 2;
        int moveDistanceY = (windowHeight - ApplicationWindow.settings.getLvlMakerHeight()) / 2;
        setLocation(moveDistanceX, moveDistanceY - Resizable.YOffset);
    }

    public enum BackgroundColorState {

        BLACK(3, 3, 0, 0),
        LIGHT_BLACK(46, 46, 43, 1),
        GRAY(110, 110, 107, 2),
        DARK_WHITE(195, 195, 192, 3),
        WHITE(255, 255, 252, 4);

        public int red;
        public int green;
        public int blue;
        public int index;

        BackgroundColorState(int red, int green, int blue, int index) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.index = index;
        }

    }//BackgroundColorState

}
