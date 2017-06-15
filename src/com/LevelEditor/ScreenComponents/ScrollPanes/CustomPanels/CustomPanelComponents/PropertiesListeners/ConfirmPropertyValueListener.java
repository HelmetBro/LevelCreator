package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners;

import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.PropertiesScrollPane;
import com.LevelEditor.Shapes.Properties.Property;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;


public class ConfirmPropertyValueListener implements DocumentListener {

    private Property p;

    public ConfirmPropertyValueListener(Property p) {
        this.p = p;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String lastVal = PropertiesScrollPane.DEFAULT_BOX_VALUE;

        if (e == null)
            return;

        try {
            lastVal = e.getDocument().getText(0, e.getDocument().getLength());
        } catch (BadLocationException ignored) {
        }


        p.data = lastVal.trim();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        insertUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        insertUpdate(e);
    }

    public Property getP() {
        return p;
    }
}
