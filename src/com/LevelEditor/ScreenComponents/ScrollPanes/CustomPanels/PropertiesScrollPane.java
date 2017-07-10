package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels;


import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkJButton;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.DarkAddButton;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.ConfirmPropertyValueListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.DeletePropertyListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.EditPropertyNameListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.EditShapeNameListener;
import com.LevelEditor.Shapes.Properties.Property;
import com.LevelEditor.Shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.LevelEditor.ApplicationWindow.*;
import static com.LevelEditor.Main.SMALL_FONT_SIZE;

public class PropertiesScrollPane extends CustomScrollPane {

    public static final String DEFAULT_BOX_VALUE = "value";
    public static final String DEFAULT_BOX_NAME = "name";
    private static final Color INFO_BOX_COLOR = new Color(
            LIGHT_COLOR.getRed(),
            LIGHT_COLOR.getGreen(),
            LIGHT_COLOR.getBlue(),
            Math.abs(LIGHT_COLOR.getAlpha() - 200));
    private static final int PROP_BOX_HEIGHT = 22;
    private static final int TITLE_BOX_HEIGHT = 20;
    private JLabel infoTip;
    private int windowHeight;
    private int width;

    public PropertiesScrollPane(int width, int windowHeight) {
        setFocusable(false);
        this.windowHeight = windowHeight;
        this.width = width - (int) getVerticalScrollBar().getPreferredSize().getWidth();

        infoTip = createInfoLabel();

        scrollPanel.add(infoTip);

        setViewportView(scrollPanel);
    }

    private static Container createPropertyBox(int y, Property p, int width, Shape shape) {

        Container c = new Container();
        c.setLayout(new BorderLayout(0, 0));
        c.setBounds(-1, y, width + 1, PROP_BOX_HEIGHT);

        //DELETE BUTTON
        DarkJButton delete = new DarkJButton("x", 20, 20,
                CONSOLAS.deriveFont(16f), BACKGROUND_COLOR.darker(), LIGHT_COLOR);
        delete.setBorder(BorderFactory.createEmptyBorder());
        delete.setToolTipText("Click to delete this property");
        delete.addActionListener(new DeletePropertyListener(p, shape));

        Container nameValueContainer = new Container();
        nameValueContainer.setLayout(new GridBagLayout());

        //NAME
        JTextField name = new JTextField(p.name);
        name.setFont(CONSOLAS.deriveFont(SMALL_FONT_SIZE));
        name.setForeground(LIGHT_COLOR);
        name.setBackground(BACKGROUND_COLOR);
        name.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 1, LIGHT_COLOR));
        name.setHorizontalAlignment(JTextField.CENTER);
        name.getDocument().addDocumentListener(new EditPropertyNameListener(p));
        name.setToolTipText("Name of the property [String]");

        //VALUE
        JTextField value;
        if (p.data == null)
            value = new JTextField(DEFAULT_BOX_VALUE);
        else
            value = new JTextField(p.data);
        value.setFont(CONSOLAS.deriveFont(SMALL_FONT_SIZE));
        value.setForeground(LIGHT_COLOR);
        value.setBackground(BACKGROUND_COLOR);
        value.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 1, LIGHT_COLOR));
        value.setHorizontalAlignment(JTextField.CENTER);
        value.getDocument().addDocumentListener(new ConfirmPropertyValueListener(p));
        value.setToolTipText("Value that the property holds [String]");

        //editing constraints
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.35f;
        gridBagConstraints.weighty = 0.5f;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        nameValueContainer.add(name, gridBagConstraints);

        gridBagConstraints.weightx = 0.65f;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        nameValueContainer.add(value, gridBagConstraints);

        c.add(nameValueContainer, BorderLayout.CENTER);
        c.add(delete, BorderLayout.EAST);

        return c;
    }

    public void updatePropertyEditor() {

        //all selected shapes
        ArrayList<Shape> shapes = ManageLevelArrayLists.getSelectedShapes();

        scrollPanel.removeAll();

        //manages is info tip is there or not based off # of shapes
        if (shapes.size() > 0) {
            scrollPanel.remove(infoTip);
        } else {
            scrollPanel.add(infoTip);

            //resetting the window startHeight without spacing
            scrollPanel.setPreferredSize(new Dimension(width, windowHeight));

            //redrawing pane
            revalidate();
            repaint();

            return;
        }


        //starting position is 5
        int lastHeight = 5;

        for (Shape shape : shapes) {

            //TITLE
            JTextField title = new JTextField(shape.name, SwingConstants.CENTER);
            title.setHorizontalAlignment(SwingConstants.CENTER);
            title.setBounds(0, lastHeight, width - 1, TITLE_BOX_HEIGHT);
            title.setFont(CONSOLAS);
            title.setForeground(LIGHT_COLOR);
            title.setBackground(BACKGROUND_SHADED_COLOR);
            title.setBorder(BorderFactory.createMatteBorder(
                    0, 0, 1, 0, INFO_BOX_COLOR));
            title.getDocument().addDocumentListener(new EditShapeNameListener(shape));

            scrollPanel.add(title);

            //PROPERTIES
            ArrayList<Property> properties = shape.getProperties();

            for (int j = 0; j < properties.size(); j++) {
                Property p = properties.get(j);
                lastHeight = title.getY() + TITLE_BOX_HEIGHT + j * PROP_BOX_HEIGHT;
                scrollPanel.add(createPropertyBox(lastHeight, p, width, shape));
            }

            //ADD BUTTON
            DarkAddButton button = new DarkAddButton(
                    5,
                    lastHeight + PROP_BOX_HEIGHT,
                    shape,
                    "+");
            scrollPanel.add(button);
            lastHeight = button.getY() + button.getHeight();

        }//big for

        //setting position
        if (lastHeight > windowHeight) {
            scrollPanel.setPreferredSize(new Dimension(width, windowHeight + (lastHeight - windowHeight) + 10)); //+10 spacing
        } else {
            scrollPanel.setPreferredSize(new Dimension(width, windowHeight + 10));
        }

        revalidate();
        repaint();
    }

    private JLabel createInfoLabel() {

        JLabel infoTip;

        int labelWidth = 130;
        int labelHeight = 70;

        infoTip = new JLabel("<html><div style='text-align: center;'>" +
                "[select shapes<br>to add<br>properties]" +
                "</div></html>", SwingConstants.CENTER);
        infoTip.setBounds((width / 2 - labelWidth / 2) + (int) getVerticalScrollBar().getPreferredSize().getWidth() / 2,
                20, labelWidth, labelHeight);
        infoTip.setFont(CONSOLAS);

        infoTip.setForeground(INFO_BOX_COLOR);
        infoTip.setBorder(BorderFactory.createLineBorder(INFO_BOX_COLOR));
        infoTip.setBackground(super.getBackground());

        return infoTip;
    }

}