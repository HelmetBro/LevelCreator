package com.LevelEditor.ScreenComponents;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Resizable;

import javax.swing.*;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.backgroundColor;
import static com.LevelEditor.ApplicationWindow.lightShadedColor;
import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class ToolBarButton extends JButton implements Resizable {

    private static final State defaultState = State.TOOLS;
    private static State currentState = defaultState;

    private int startX;

    //Properties
    private static final String[] labels = new String[]{"Tools", "Properties", "Objects"};

    public ToolBarButton(Font font, int x, int y, int width, int height) {
        super(labels[defaultState.numVal]);
        this.startX = x;
        setFocusable(false);
        setBounds(x, y, width, height);
        setFont(font);
        setBackground(backgroundColor);
        setForeground(lightShadedColor);
        setToolTipText("Click to change editor options");

        setFocusPainted(false);
        setContentAreaFilled(false);

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));

        addActionListener(e -> incrementState());

        loadNewState();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed())
            g.setColor(backgroundColor.darker().darker());
        else
            g.setColor(getBackground());

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    private void incrementState() {
        //change state
        currentState = State.values()[(currentState.numVal + 1) % labels.length];

        //changing to new state
        loadNewState();
    }

    private void loadNewState() {
        //changing label
        setText(labels[currentState.numVal]);
        //changing panes to match new state
        ApplicationWindow.scrollPaneHandler.switchTab(currentState.numVal);
    }

    @Override
    public void moveComponent(int windowWidth, int windowHeight) {
        int moveDistance = (windowWidth - ApplicationWindow.settings.getLvlMakerWidth() - RULER_WIDTH - ApplicationWindow.settings.toolsWindowSizeX);
        setLocation(startX + moveDistance, 0);
    }

    enum State {
        TOOLS(0),
        PROPERTIES(1),
        OBJECTS(2);

        int numVal;

        State(int numVal) {
            this.numVal = numVal;
        }

    }//State enum

}//ToolBarButton
