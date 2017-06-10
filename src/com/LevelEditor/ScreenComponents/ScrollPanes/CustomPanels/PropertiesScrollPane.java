package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels;


import com.LevelEditor.ManageLevelArrayLists;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.DarkAddButton;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.ConfirmPropertyValueListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.DeletePropertyListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.EditPropertyNameListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.EditShapeNameListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.ScrollPaneHandler;
import com.LevelEditor.Shapes.Properties.Property;
import com.LevelEditor.Shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.LevelEditor.ApplicationWindow.*;
import static com.LevelEditor.Main.smallFontSize;

public class PropertiesScrollPane extends CustomScrollPane {

    public static final String defaultBoxValue = "value";
    public static final String defaultBoxName = "name";
    private static final Color infoBoxColor = new Color(
            lightColor.getRed(),
            lightColor.getGreen(),
            lightColor.getBlue(),
            Math.abs(lightColor.getAlpha() - 200));
    private static final int propBoxHeight = 22;
    private static final int titleBoxHeight = 20;
    private JLabel infoTip;
    private int windowHeight;
    private int width;

    public PropertiesScrollPane(int width, int windowHeight) {
        setFocusable(false);
        this.windowHeight = windowHeight;
        this.width = width - (int) getVerticalScrollBar().getPreferredSize().getWidth();
        createInfoLabel();

        scrollPanel.add(infoTip);

        setViewportView(scrollPanel);
    }

    private static Container createPropertyBox(int y, Property p, int width, Shape shape) {

        Container c = new Container();
        //FlowLayout flowLayout = new FlowLayout(0);
        c.setLayout(new BorderLayout(0, 0));
        c.setBounds(-1, y, width + 1, propBoxHeight);

        //SUBTRACT BUTTON
        JButton delete = new JButton();
        delete.setFocusPainted(false);
        delete.setContentAreaFilled(false);
        delete.setPreferredSize(new Dimension(20, 20));
        delete.setBorder(BorderFactory.createEmptyBorder());
        delete.setFocusable(false);
        delete.setBackground(backgroundColor);
        delete.setForeground(lightColor);
        delete.setFont(basicFont.deriveFont(16f));
        delete.setText("x");
        delete.setToolTipText("Click to delete this property");
        delete.addActionListener(new DeletePropertyListener(p, shape));

        Container nameValueContainer = new Container();
        nameValueContainer.setLayout(new GridBagLayout());

        //NAME
        JTextField name = new JTextField(p.name);
        name.setFont(basicFont.deriveFont(smallFontSize));
        name.setForeground(lightColor);
        name.setBackground(backgroundColor);
        name.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 1, lightColor));
        name.setHorizontalAlignment(JTextField.CENTER);
        name.getDocument().addDocumentListener(new EditPropertyNameListener(p));
        name.setToolTipText("Name of the property [String]");

        //VALUE
        JTextField value;
        if (p.data == null)
            value = new JTextField(defaultBoxValue);
        else
            value = new JTextField(p.data);
        value.setFont(basicFont.deriveFont(smallFontSize));
        value.setForeground(lightColor);
        value.setBackground(backgroundColor);
        value.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 1, lightColor));
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
            ScrollPaneHandler.propSP.removeInfoTip();
        } else {
            ScrollPaneHandler.propSP.addInfoTip();

            //resetting the window startHeight without spacing
            scrollPanel.setPreferredSize(new Dimension(width, windowHeight));

            //redrawing pane
            revalidate();
            repaint();

            return;
        }


        //starting position is 5
        int lastHeight = 5;

        //iterate through shapes
        for (Shape shape : shapes) {

            //TITLE
            JTextField title = new JTextField(shape.name, SwingConstants.CENTER); //later make this the name of the shape
            title.setHorizontalAlignment(SwingConstants.CENTER);
            title.setBounds(0, lastHeight, width - 1, titleBoxHeight);
            title.setFont(basicFont);
            title.setForeground(lightColor);
            title.setBackground(backgroundShadedColor);
            title.setBorder(BorderFactory.createMatteBorder(
                    0, 0, 1, 0, infoBoxColor));
            title.getDocument().addDocumentListener(new EditShapeNameListener(shape));

            scrollPanel.add(title);

            //PROPERTIES
            ArrayList<Property> properties = shape.getProperties();

            for (int j = 0; j < properties.size(); j++) {
                Property p = properties.get(j);
                lastHeight = title.getY() + titleBoxHeight + j * propBoxHeight;
                scrollPanel.add(createPropertyBox(lastHeight, p, width, shape));
            }

            //ADD BUTTON
            DarkAddButton button = new DarkAddButton(
                    5,
                    lastHeight + propBoxHeight,
                    shape,
                    "+");
            scrollPanel.add(button);
            lastHeight = button.getY() + button.getHeight();

        }//big for


        if (lastHeight > windowHeight) {
            scrollPanel.setPreferredSize(new Dimension(width, windowHeight + (lastHeight - windowHeight) + 10)); //+10 spacing
        } else {
            scrollPanel.setPreferredSize(new Dimension(width, windowHeight + 10));
        }

        revalidate();
        repaint();
    }

    private void createInfoLabel() {

        int labelWidth = 150;

        infoTip = new JLabel("<html><div style='text-align: center;'>" +
                "[select shapes<br>to add<br>properties]" +
                "</div></html>", SwingConstants.CENTER);
        infoTip.setBounds((width / 2 - labelWidth / 2) + (int) getVerticalScrollBar().getPreferredSize().getWidth() / 2,
                20, labelWidth, 80);
        infoTip.setFont(basicFont);

        infoTip.setForeground(infoBoxColor);
        infoTip.setBorder(BorderFactory.createLineBorder(infoBoxColor));
        infoTip.setBackground(super.getBackground());
    }

    private void removeInfoTip() {
        scrollPanel.remove(infoTip);
    }

    private void addInfoTip() {
        scrollPanel.add(infoTip);
    }

}