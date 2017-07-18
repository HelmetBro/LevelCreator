package com.LevelEditor.Shapes;


import com.LevelEditor.Shapes.Properties.Property;

import javax.xml.bind.annotation.XmlElement;
import java.awt.*;
import java.util.ArrayList;

public abstract class Shape {

    public transient boolean isSelected;
    public transient Image image;

    @XmlElement
    public String name;

    @XmlElement
    public String spritePath;
    @XmlElement
    public int spriteW = 0;
    @XmlElement
    public int spriteH = 0;

    @XmlElement
    public float angle = 0;

    @XmlElement
    private ArrayList<Property> properties = new ArrayList<>();

    public abstract void drawShape(Graphics2D g);
    public abstract void drawName(Graphics2D g, Font font);
    public abstract void drawSprite(Graphics2D g);
    public abstract void drawRotationOutline(Graphics2D g);

    //returns a copy of this object with flipped Y values
    public abstract Shape copyFlip();

    public ArrayList<Property> getProperties() {
        return this.properties;
    }
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }
}