package com.LevelEditor.StartWindow.StartListeners;


import com.LevelEditor.StartWindow.InitializeWindow;
import com.LevelEditor.TabActions.FileTabActions.ExportAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class OpenDirectoryListener implements ActionListener {

    private JFrame browser = new JFrame();

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String path = showSaveFileDialog();

        //assuring that path is not null
        if (path == null) {
            InitializeWindow.pathField.setText("[Invalid Path]");
            return;
        }

        InitializeWindow.pathField.setText(path);

    }

    private String showSaveFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDragEnabled(true);

        int userSelection = fileChooser.showOpenDialog(browser);

        File f = fileChooser.getSelectedFile();
        if (f == null)
            return null;

        if (!Objects.equals(getExtension(f), ExportAction.JSON_EXTENSION) &&
                !Objects.equals(getExtension(f), ExportAction.XML_EXTENSION))
            return null;

        if (userSelection == JFileChooser.APPROVE_OPTION)
            return fileChooser.getSelectedFile().getAbsoluteFile().toString();

        return null;
    }

}
