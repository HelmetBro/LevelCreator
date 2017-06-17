package com.LevelEditor.ScreenComponents;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Resizable;
import com.LevelEditor.ScreenComponents.Canvas.Canvas;
import com.LevelEditor.StartWindow.AspectSettings;

import javax.swing.*;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.*;
import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class RatioButton extends JButton implements Resizable {

    //zoom properties
    private static float TIMER = 2f;
    private final String text;
    private int startX;
    private float duration = 0f;
    private boolean zooming = false;
    private String zoomText;

    public RatioButton(String text, Font font, int x, int y, int width, int height) {
        super(text);
        this.startX = x;
        this.text = text;
        setBounds(x, y, width, height);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setFocusable(false);
        setContentAreaFilled(false);
        setFont(font);
        setBackground(BACKGROUND_COLOR);
        setForeground(LIGHT_COLOR);
        setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, BORDER_COLOR));
        setToolTipText("Multiplier: " + ApplicationWindow.settings.getMultiplier());
        addActionListener(e -> incrementBackground());
    }

    public void resetText() {
        setText(text);
    }

    public void displayZoom(float level) {

        //rounding to 1 decimal
        float zoom = Math.round(level * 100) / 100f;

        //the reset
        duration = 0;
        zoomText = "x" + zoom;

        //if its already zooming, don't start another thread
        if (zooming)
            return;

        new Thread(() -> {

            zooming = true;

            //wait
            while (duration < TIMER) {
                setText(zoomText);

                //wait 5 mills
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ignore) {
                }

                duration += 0.005;
            }

            //the reset
            duration = 0f;
            resetText();
            zooming = false;

        }).start();

    }

    private void incrementBackground() {

        int newIndex = ++Canvas.getColorState().index % Canvas.NUM_OF_BACK_STATES;

        ApplicationWindow.canvas.setColor(Canvas.BackgroundColorState.values()[newIndex]);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed())
            g.setColor(BACKGROUND_COLOR.darker().darker());
        else
            g.setColor(getBackground());

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void moveComponent(int windowWidth, int windowHeight) {
        int moveDistance = (windowWidth - ApplicationWindow.settings.getLvlMakerWidth() - RULER_WIDTH - AspectSettings.TOOLS_WINDOW_SIZE_X);
        setLocation(startX + moveDistance, 0);
    }
}
