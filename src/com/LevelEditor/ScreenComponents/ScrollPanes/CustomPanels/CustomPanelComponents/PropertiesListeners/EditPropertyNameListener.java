package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners;


import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.PropertiesScrollPane;
import com.LevelEditor.Shapes.Properties.Property;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class EditPropertyNameListener implements DocumentListener {

    private Property p;

    public EditPropertyNameListener(Property p) {
        this.p = p;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String newName = PropertiesScrollPane.defaultBoxName;

        if (e == null)
            return;

        try {
            newName = e.getDocument().getText(0, e.getDocument().getLength());
        } catch (BadLocationException ignored) {
        }

        p.name = newName;
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        insertUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        insertUpdate(e);
    }
}
