package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners;

import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.PropertiesScrollPane;
import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;
import com.LevelEditor.Shapes.Properties.Property;
import com.LevelEditor.Shapes.Shape;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddPropertyListener implements ActionListener {

    private Shape shape;

    public AddPropertyListener(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        shape.getProperties().add(new Property(PropertiesScrollPane.DEFAULT_BOX_NAME, PropertiesScrollPane.DEFAULT_BOX_VALUE));
        ScrollPaneHandler.propSP.updatePropertyEditor();
    }

}
