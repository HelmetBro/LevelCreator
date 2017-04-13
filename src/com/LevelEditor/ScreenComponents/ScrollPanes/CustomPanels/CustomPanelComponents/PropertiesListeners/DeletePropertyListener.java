package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners;

import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;
import com.LevelEditor.Shapes.Properties.Property;
import com.LevelEditor.Shapes.Shape;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by eric on 2/19/2017.
 */
public class DeletePropertyListener implements ActionListener{

    private Property p;
    private Shape shape;

    public DeletePropertyListener(Property p, Shape shape) {
        this.p = p;
        this.shape = shape;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        shape.getProperties().remove(p);
        ScrollPaneHandler.propSP.updatePropertyEditor();
    }


}
