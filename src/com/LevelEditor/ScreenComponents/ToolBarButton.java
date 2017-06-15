package com.LevelEditor.ScreenComponents;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Resizable;

import javax.swing.*;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.*;
import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class ToolBarButton extends JButton implements Resizable {

    private static final State DEFAULT_STATE = State.TOOLS;
    //Properties
    private static final String[] LABELS = new String[]{"Tools", "Properties", "Objects", "Sprites"};
    private static State currentState = DEFAULT_STATE;
    private int startX;

    public ToolBarButton(Font font, int x, int y, int width, int height) {
        super(LABELS[DEFAULT_STATE.numVal]);
        this.startX = x;
        setFocusable(false);
        setBounds(x, y, width, height);
        setFont(font);
        setBackground(BACKGROUND_COLOR);
        setForeground(LIGHT_SHADED_COLOR);
        setToolTipText("Click to change editor options");

        setFocusPainted(false);
        setContentAreaFilled(false);

        setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, BORDER_COLOR));

        addActionListener(e -> incrementState());

        loadNewState();
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

    private void incrementState() {
        //change state
        currentState = State.values()[(currentState.numVal + 1) % LABELS.length];

        //changing to new state
        loadNewState();
    }

    private void loadNewState() {
        //changing label
        setText(LABELS[currentState.numVal]);
        //changing panes to match new state
        ApplicationWindow.scrollPaneHandler.switchTab(currentState.numVal);
    }

    @Override
    public void moveComponent(int windowWidth, int windowHeight) {
        int moveDistance = (windowWidth - ApplicationWindow.settings.getLvlMakerWidth() - RULER_WIDTH - ApplicationWindow.settings.TOOLS_WINDOW_SIZE_X);
        setLocation(startX + moveDistance, 0);
    }

    enum State {
        TOOLS(0),
        PROPERTIES(1),
        OBJECTS(2),
        SPRITES(3);

        int numVal;

        State(int numVal) {
            this.numVal = numVal;
        }

    }//State enum

}//ToolBarButton
