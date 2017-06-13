package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners;

import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;
import com.LevelEditor.Shapes.Shape;
import com.LevelEditor.UpdatePaint;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class EditShapeNameListener implements DocumentListener {

    private Shape shape;

    public EditShapeNameListener(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

        try {
            shape.name = e.getDocument().getText(0, e.getDocument().getLength());
            shape.hasUniqueName = true;
            ScrollPaneHandler.objSP.updateList();
            UpdatePaint.remakeWindow();
        } catch (BadLocationException ignored) {
        }

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
