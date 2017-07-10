package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents;


import com.LevelEditor.ScreenComponents.DarkComponents.DarkJButton;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.ConfirmPropertyValueListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.DeletePropertyListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.EditPropertyNameListener;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.PropertiesListeners.EditShapeNameListener;
import com.LevelEditor.Shapes.Properties.Property;
import com.LevelEditor.Shapes.Shape;

import javax.swing.*;
import java.awt.*;

import static com.LevelEditor.ApplicationWindow.*;
import static com.LevelEditor.Main.SMALL_FONT_SIZE;
import static com.LevelEditor.StartWindow.AspectSettings.TOOLS_WINDOW_SIZE_X;

/**
 * Created by EricP on 7/9/2017.
 */
public class PropertiesBox extends Container {

    //reference
    private Shape shape;

    //components
    private JTextField title;
    private DarkAddButton addButton;

    private Integer currentHeight;

    public static final String DEFAULT_BOX_VALUE = "value";
    public static final String DEFAULT_BOX_NAME = "name";
    private final static int BOX_HEIGHT = 20;
    private static final Color INFO_BOX_COLOR = new Color(
            LIGHT_COLOR.getRed(),
            LIGHT_COLOR.getGreen(),
            LIGHT_COLOR.getBlue(),
            Math.abs(LIGHT_COLOR.getAlpha() - 200));

    public PropertiesBox(Shape shape) {
        this.shape = shape;
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.currentHeight = BOX_HEIGHT + DarkAddButton.WIDTH;
        this.setPreferredSize(new Dimension(
                TOOLS_WINDOW_SIZE_X - (int)scrollHolder.getVerticalScrollBar().getPreferredSize().getWidth(),
                currentHeight));

        title = title();
        addButton = new DarkAddButton(shape);

        this.add(title);
    }

    private JTextField title(){

        JTextField title = new JTextField(shape.name, SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(TOOLS_WINDOW_SIZE_X, BOX_HEIGHT));
        title.setFont(CONSOLAS);
        title.setForeground(LIGHT_COLOR);
        title.setBackground(BACKGROUND_SHADED_COLOR);
        title.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, INFO_BOX_COLOR));
        title.getDocument().addDocumentListener(new EditShapeNameListener(shape));

        return title;
    }

    public void addPropertyBox(Property p){

        Container c = new Container();
        c.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        //DELETE BUTTON
        DarkJButton delete = new DarkJButton("x", 20, 20,
                CONSOLAS.deriveFont(16f), BACKGROUND_COLOR.darker(), LIGHT_COLOR);
        delete.setBorder(BorderFactory.createEmptyBorder());
        delete.setToolTipText("Click to delete this property");
        delete.addActionListener(new DeletePropertyListener(p, shape));
        Container nameValueContainer = new Container();
        nameValueContainer.setPreferredSize(new Dimension(
                TOOLS_WINDOW_SIZE_X - (int)scrollHolder.getVerticalScrollBar().getPreferredSize().getWidth() - BOX_HEIGHT,
                BOX_HEIGHT));
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

        c.add(nameValueContainer);
        c.add(delete);

        this.currentHeight += BOX_HEIGHT;
        this.setPreferredSize(new Dimension(
                TOOLS_WINDOW_SIZE_X - (int)scrollHolder.getVerticalScrollBar().getPreferredSize().getWidth(),
                currentHeight));
        this.add(c);
    }

    public void showAddButton(){
        this.add(addButton);
    }

}
