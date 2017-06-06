package com.LevelEditor.TabActions.HelpTabActions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by EricP on 6/5/2017.
 */
public class SuggestionActionListener implements ActionListener {

    private String type = "Request/Change a Feature";

    @Override
    public void actionPerformed(ActionEvent e) {

        String message =
                "Programs aren’t always suited to fit our every need. However, this one can be. If you have\n" +
                "any ideas to improve this software, or any feature you want to request, let me know by \n" +
                "sending me a message below. Want to rate this program? Put that on the steam store as a\n" +
                "review - you have no idea how much that helps me.\n\n" +
                "Include your email in the message if you want a response. I’ll try to get back to you quickly.\n\n";

        String body = JOptionPane.showInputDialog(null, message, type, JOptionPane.INFORMATION_MESSAGE);

        Email email = new Email(type, body);
        email.send();

    }


}
