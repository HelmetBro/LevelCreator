package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.SpriteLinkerListeners;

import com.LevelEditor.Shapes.Shape;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 * Created by EricP on 7/16/2017.
 */
public class PathTextListener implements DocumentListener {

    private Shape shape;

    public PathTextListener(Shape shape){
        this.shape = shape;
    }

    private void updateField(DocumentEvent e) {

        String text = null;

        try {
            text = e.getDocument().getText(0, e.getDocument().getLength());
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }

        shape.spritePath = text != null ? text.trim() : null;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateField(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateField(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateField(e);
    }
}
