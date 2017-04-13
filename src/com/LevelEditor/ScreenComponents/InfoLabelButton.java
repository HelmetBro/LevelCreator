package com.LevelEditor.ScreenComponents;

import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.GlobalMouseListeners.CustomMouseWheelListener;
import com.LevelEditor.MouseStates.MouseState;
import com.LevelEditor.Resizable;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.LevelEditor.ApplicationWindow.*;
import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class InfoLabelButton extends JButton implements Resizable {

    private int startX;
    private int startY;

    public static final int heightOfLabel = 30;

    public InfoLabelButton(int x, int y, int width) {
        this.startX = x;
        this.startY = y;
        setFocusable(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setForeground(lightColor);
        setBackground(backgroundShadedColor);
        setText("Welcome!");
        setToolTipText("To change the state, move your mouse wheel!");
        setFont(basicFont);
        setBounds(x, y, width, heightOfLabel);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, lightColor));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);

        addMouseListener(new InfoLabelListener());
    }

    public static void updateStateLabelText(MouseState.EMouseStates state) {
        ApplicationWindow.infoLabelButton.setText(
                CustomMouseWheelListener.stateStrings[state.getIndex()]
        );
    }

    public static void updateLabelText(String text) {
        ApplicationWindow.infoLabelButton.setText(text);
    }

    @Override
    public void moveComponent(int windowWidth, int windowHeight) {
        int moveDistance = (windowWidth - ApplicationWindow.settings.getLvlMakerWidth() - RULER_WIDTH - ApplicationWindow.settings.toolsWindowSizeX);
        setLocation(startX + moveDistance, startY);
    }

    class InfoLabelListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                ((CustomMouseWheelListener) (ApplicationWindow.lvlWindow.getMouseWheelListeners()[0])).manualWheelMove(1);
            } else {
                ((CustomMouseWheelListener) (ApplicationWindow.lvlWindow.getMouseWheelListeners()[0])).manualWheelMove(-1);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

}
