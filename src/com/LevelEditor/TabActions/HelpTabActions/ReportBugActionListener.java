package com.LevelEditor.TabActions.HelpTabActions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportBugActionListener implements ActionListener {

    private String type = "Bug Report";

    @Override
    public void actionPerformed(ActionEvent e) {

        String message = "Bugs are natural in code. As I've tried to exterminate all I could find, there still may be \n" +
                "some hiding in corners that I haven't found yet. Thanks for taking your time to report any \n" +
                "bugs to keep this software clean.\n\n" +
                "Please provide:\n\n" +
                "- A descriptive explanation of what happened.\n" +
                "- The steps you took in replicating that bug.\n" +
                "- Your OS and its version.\n" +
                "- Other programs that may influence this bug.\n" +
                "- Any other information you think helps.\n\n" +
                "I'll try to be quick to release a patch, and fix any errors you may be having. Thanks again!\n\n";

        String body = JOptionPane.showInputDialog(null, message, type, JOptionPane.INFORMATION_MESSAGE);

        Email email = new Email(type, body);
        email.send();

    }

}
