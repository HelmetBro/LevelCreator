package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.SpriteLinkerListeners;

import com.LevelEditor.Shapes.Shape;
import com.LevelEditor.UpdatePaint;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 * Created by EricP on 7/16/2017.
 */
public class SizeTextListener implements DocumentListener {

    private Shape shape;
    private boolean isWidth;

    public SizeTextListener(Shape shape, boolean isWidth){
        this.shape = shape;
        this.isWidth = isWidth;
    }

    private void updateField(DocumentEvent e) {

        int value;

        String textValue;

        try {

            textValue = e.getDocument().getText(0, e.getDocument().getLength());
            textValue = textValue.trim();

            if (textValue.isEmpty())
                return;

            value = Integer.parseInt(textValue);

        } catch (BadLocationException | NumberFormatException ignored) {
            return;
        }

        if (value <= 0)
            return;

        if (isWidth)
            shape.spriteW = value;
        else
            shape.spriteH = value;

        UpdatePaint.remakeWindow();

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
