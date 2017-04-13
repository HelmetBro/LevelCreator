package com.LevelEditor.StartWindow.StartListeners;

import com.LevelEditor.StartWindow.AspectSettings;
import com.LevelEditor.StartWindow.InitializeWindow;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Created by eric on 4/7/2017.
 */
public abstract class ValueDocumentListener implements DocumentListener {

    protected final AspectSettings settings;
    protected final InitializeWindow window;

    protected ValueDocumentListener(InitializeWindow window, AspectSettings settings){
        this.window = window;
        this.settings = settings;
    }

    protected abstract void updateFields(DocumentEvent e);

    protected int textToValue(DocumentEvent e) throws Exception {

        int value;

        String textValue = e.getDocument().getText(0, e.getDocument().getLength());
        textValue = textValue.trim();

        if (textValue.isEmpty())
            return -1;

        value = Integer.parseInt(textValue);

        if (value <= 0)
            throw new Exception("Number less than or equal to 0.");

        return value;

    }

    protected abstract boolean inWrongState();

}
