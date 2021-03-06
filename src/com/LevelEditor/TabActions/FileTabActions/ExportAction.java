package com.LevelEditor.TabActions.FileTabActions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class ExportAction implements ActionListener {

    public static final String JSON_EXTENSION = "json";
    public static final String XML_EXTENSION = "xml";
    public static boolean prettyPrint;
    private JFrame browser = new JFrame();

    protected String showSaveFileDialog(String fileChooserTitle) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(fileChooserTitle);

        int userSelection = fileChooser.showSaveDialog(browser);

        if (userSelection == JFileChooser.APPROVE_OPTION)
            return fileChooser.getSelectedFile().getAbsoluteFile().toString();

        return null;
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
