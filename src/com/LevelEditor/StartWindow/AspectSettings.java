package com.LevelEditor.StartWindow;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Utilities;

import java.awt.*;

public class AspectSettings {

    //level creator multiplier
    private int multiplier; // 80

    //16:9
    private int aspectRatioX;
    private int aspectRatioY;

    //size of the window
    private int windowWidth;
    private int windowHeight;

    //size of the creation screen
    private int lvlMakerWidth;
    private int lvlMakerHeight;

    //difference between initial window and creation screen
    public static final int RULER_WIDTH = 65;
    public static final int RULER_HEIGHT = 40;

    //extra startWidth for tools window (in pixels)
    public final int toolsWindowSizeX = 180;

    AspectSettings() {
        autoScale();
    }

    public void autoScale() {

        //getting monitor data
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        int GCD = Utilities.GCD((int) width, (int) height);

        //something like 16:9
        aspectRatioX = (int) (width / GCD);
        aspectRatioY = (int) (height / GCD);

        //setting multipliers
        int preSizeMultiplier = (int) width / aspectRatioX; //120 for 1920/1080
        int autoSizeDecrease = preSizeMultiplier / 3;

        multiplier = preSizeMultiplier - autoSizeDecrease; //80 for ^

        lvlMakerWidth = aspectRatioX * multiplier;
        lvlMakerHeight = aspectRatioY * multiplier;
    }

    public void windowSettings() {
        windowWidth = lvlMakerWidth + RULER_WIDTH;
        windowHeight = lvlMakerHeight + RULER_HEIGHT;
    }

    public void changeAspectX(int value) {
        this.aspectRatioX = value;
        this.lvlMakerWidth = aspectRatioX * multiplier;
    }

    public void changeAspectY(int value) {
        this.aspectRatioY = value;
        this.lvlMakerHeight = aspectRatioY * multiplier;
    }

    public void changeMultiplier(int value) {
        this.multiplier = value;
        this.lvlMakerWidth = aspectRatioX * multiplier;
        this.lvlMakerHeight = aspectRatioY * multiplier;
    }

    public void changeWidth(int value) {
        this.lvlMakerWidth = value;

        int GCD = Utilities.GCD(value, lvlMakerHeight);

        //something like 16:9
        this.aspectRatioX = value / GCD;
        this.aspectRatioY = lvlMakerHeight / GCD;

        this.multiplier = value / aspectRatioX;
        this.lvlMakerHeight = multiplier * aspectRatioY;
    }

    public void changeHeight(int value) {
        this.lvlMakerHeight = value;

        int GCD = Utilities.GCD(value, lvlMakerWidth);

        //something like 16:9
        this.aspectRatioX = lvlMakerWidth / GCD;
        this.aspectRatioY = value / GCD;

        this.multiplier = value / aspectRatioY;
        this.lvlMakerWidth = multiplier * aspectRatioX;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getAspectRatioX() {
        return aspectRatioX;
    }
    public int getAspectRatioY() {
        return aspectRatioY;
    }

    public int getLvlMakerWidth() {
        return lvlMakerWidth;
    }
    public int getLvlMakerHeight() {
        return lvlMakerHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }
    public int getWindowHeight() {
        return windowHeight;
    }

    //changes values
    public void resizeWindowValues(int windowWidth, int windowHeight)  {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

}
