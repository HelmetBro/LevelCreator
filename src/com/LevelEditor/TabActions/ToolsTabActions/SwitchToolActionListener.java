package com.LevelEditor.TabActions.ToolsTabActions;

import com.LevelEditor.GlobalMouseListeners.CustomMouseWheelListener;
import com.LevelEditor.MouseStates.MouseState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SwitchToolActionListener implements ActionListener {

    private MouseState.EMouseStates state;

    public SwitchToolActionListener(MouseState.EMouseStates state) {
        this.state = state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CustomMouseWheelListener.switchState(state);
    }

}
