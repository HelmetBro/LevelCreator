package com.LevelEditor.Shapes;


import com.LevelEditor.Shapes.Properties.Property;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

public abstract class Shape {

    public transient static boolean isFilled = true;
    //values used for editor
    public transient boolean isSelected;
    @XmlElement
    public String name;
    @XmlElement
    private ArrayList<Property> properties = new ArrayList<>();

    //returns a copy of this object with flipped Y values
    public abstract Shape copyFlip();

    public ArrayList<Property> getProperties() {
        return this.properties;
    }

}
