package com.LevelEditor.TabActions.FileTabActions;


import com.LevelEditor.Level;
import com.LevelEditor.Main;
import com.LevelEditor.ScreenComponents.InfoLabelButton;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.ToolsListeners.FlipYListener;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.awt.event.ActionEvent;
import java.io.File;

public class ExportXMLActionListener extends ExportAction {

    @Override
    public void actionPerformed(ActionEvent e) {

        //getting path and GSON obj
        String path = showSaveFileDialog("Export to XML");

        //assuring that path is not null
        if (path == null) {
            InfoLabelButton.updateLabelText("Canceled.");
            return;
        }

        //adding extension
        path += "." + XMLExtension;

        try {

            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            if (prettyPrint)
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            if (Main.currentLevel.flipY)
                jaxbMarshaller.marshal(Main.currentLevel.flipYCopy(), file);
            else
                jaxbMarshaller.marshal(Main.currentLevel, file);

            InfoLabelButton.updateLabelText("Exported. ( ͡° ͜ʖ ͡°)");

        } catch (JAXBException e1) {
            e1.printStackTrace();
            InfoLabelButton.updateLabelText("ERROR! Can't Export");
        }

    }

}
