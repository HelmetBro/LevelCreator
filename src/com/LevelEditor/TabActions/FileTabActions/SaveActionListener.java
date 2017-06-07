package com.LevelEditor.TabActions.FileTabActions;

import com.LevelEditor.Level;
import com.LevelEditor.Main;
import com.LevelEditor.ScreenComponents.InfoLabelButton;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.FlipYListener;
import com.LevelEditor.StartWindow.InitializeWindow;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class SaveActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!InitializeWindow.isFileLoaded) {

            JOptionPane.showMessageDialog(null,
                    "A file wasn't loaded, so there's nothing to save too.\n" +
                            "Try exporting to JSON or an XML file to create one.",
                    "Can't Save", JOptionPane.ERROR_MESSAGE, null);

            return;
        }

        File f = new File(InitializeWindow.filePath);

        //check if file still exists
        if (!f.exists() || f.isDirectory()) {

            //file does not exist
            JOptionPane.showMessageDialog(null,
                    "That file doesn't exist anymore! The file was\n" +
                            "loaded, but the location is unknown, or the file\n" +
                            "was deleted. Export to create a new one.",
                    "Can't find file", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //get extension of the file
        if (InitializeWindow.isJSONNotXML) {

            //saving by JSON
            Gson gson;
            if (ExportAction.prettyPrint)
                gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .disableHtmlEscaping()
                        .create();
            else
                gson = new GsonBuilder().create();

            //creating writer and writing level to GSON file
            try (Writer writer = new FileWriter(InitializeWindow.filePath)) {

                Level level = Main.currentLevel;

                if (FlipYListener.flipY)
                    level = Main.currentLevel.flipYBeforeWrite();

                String fileContents = gson.toJson(level);
                fileContents = fileContents.replace("\\n", System.getProperty("line.separator"));

                writer.write(fileContents);
                writer.close();
            } catch (IOException e1) {
                InfoLabelButton.updateLabelText("ERROR! Can't Save");
            }

        } else {

            //saving by XML
            try {
                File file = new File(InitializeWindow.filePath);
                JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                // output pretty printed
                if (ExportAction.prettyPrint)
                    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                if (FlipYListener.flipY)
                    jaxbMarshaller.marshal(Main.currentLevel.flipYBeforeWrite(), file);
                else
                    jaxbMarshaller.marshal(Main.currentLevel, file);

            } catch (JAXBException e1) {
                e1.printStackTrace();
                InfoLabelButton.updateLabelText("ERROR! Can't Save");
            }

        }

        InfoLabelButton.updateLabelText("Saved!");

    }

}
