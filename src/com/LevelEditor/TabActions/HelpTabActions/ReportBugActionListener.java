package com.LevelEditor.TabActions.HelpTabActions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportBugActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(
                null,
                "k don't tell anyone lol",
                "Shhhh...", JOptionPane.WARNING_MESSAGE);
    }

}
