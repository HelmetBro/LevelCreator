package com.LevelEditor.TabActions.FileTabActions;


import com.LevelEditor.Level;
import com.LevelEditor.Main;
import com.LevelEditor.ScreenComponents.InfoLabelButton;
import com.LevelEditor.StartWindow.InitializeWindow;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ExportJSONActionListener extends ExportAction {

    @Override
    public void actionPerformed(ActionEvent e) {

        //getting path and GSON obj
        String path = showSaveFileDialog("Export to JSON");

        Gson gson;
        if (prettyPrint)
            gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .disableHtmlEscaping()
                    .create();
        else
            gson = new GsonBuilder().create();

        //assuring that path is not null
        if (path == null) {
            InfoLabelButton.updateLabelText("Canceled.");
            return;
        }

        //adding extension
        path += "." + JSON_EXTENSION;

        //creating writer and writing level to GSON file
        try (Writer writer = new FileWriter(path)) {

            Level level = Main.currentLevel;

            if (Main.currentLevel.flipY)
                level = Main.currentLevel.flipYCopy();

            String fileContents = gson.toJson(level);
            fileContents = fileContents.replace("\\n", System.getProperty("line.separator"));

            writer.write(fileContents);

            writer.close();

            InitializeWindow.isJSONNotXML = true;
            InitializeWindow.filePath = path;
            InitializeWindow.isFileLoaded = true;

            InfoLabelButton.updateLabelText("Exported. ( ͡° ͜ʖ ͡°)");
        } catch (IOException e1) {
            InfoLabelButton.updateLabelText("ERROR! Can't Export");
        }


    }

}
