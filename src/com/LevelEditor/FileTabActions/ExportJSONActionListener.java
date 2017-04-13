package com.LevelEditor.FileTabActions;


import com.LevelEditor.Main;
import com.LevelEditor.ScreenComponents.InfoLabelButton;
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
        path += "." + JSONExtension;

        //creating writer and writing level to GSON file
        try (Writer writer = new FileWriter(path)) {

            String fileContents = gson.toJson(Main.currentLevel);
            fileContents = fileContents.replace("\\n", System.getProperty("line.separator"));

            writer.write(fileContents);

            writer.close();

            InfoLabelButton.updateLabelText("Exported. ( ͡° ͜ʖ ͡°)");
        } catch (IOException e1) {
            InfoLabelButton.updateLabelText("ERROR! Can't Export");
        }


    }

}
