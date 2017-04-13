package com.LevelEditor.FileTabActions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class ExportAction implements ActionListener {

    private JFrame browser = new JFrame();

    public static final String JSONExtension = "json";
    public static final String XMLExtension = "xml";

    public static boolean prettyPrint;

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
