package com.LevelEditor.ScreenComponents.ScrollPanes;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Resizable;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.ObjectsTreeScrollPane;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.PropertiesScrollPane;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.ToolsScrollPane;

import java.awt.*;

import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class ScrollPaneHandler extends Container implements Resizable {

    public static ObjectsTreeScrollPane objSP;
    public static PropertiesScrollPane propSP;
    private static ToolsScrollPane toolsSP;

    private int startX;
    private int startY;

    private int startWidth;
    private int startHeight;

    public ScrollPaneHandler(int x, int y, int width, int height) {
        super();

        this.startX = x;
        this.startY = y;
        this.startWidth = width;
        this.startHeight = height;

        setLayout(new BorderLayout());
        setBounds(x, y, width, height);

        objSP = new ObjectsTreeScrollPane();
        propSP = new PropertiesScrollPane(width, height);
        toolsSP = new ToolsScrollPane(width);
    }

    public void switchTab(int state) {

        removeAll();

        switch (state) {
            case 0:
                add(toolsSP);
                break;
            case 1:
                add(propSP);
                break;
            case 2:
                add(objSP);
                break;
            default:
                System.out.println("ERROR - Scroll pane state not defined! [ScrollPaneHandler]");
                break;
        }

        repaint();
        revalidate();

    }

    @Override
    public void moveComponent(int windowWidth, int windowHeight) {
        int moveDistance = (windowWidth - ApplicationWindow.settings.getLvlMakerWidth() - RULER_WIDTH - ApplicationWindow.settings.toolsWindowSizeX);
        int resizeHeight = windowHeight - ApplicationWindow.settings.getLvlMakerHeight() - RULER_WIDTH;
        setBounds(startX + moveDistance, startY, startWidth, startHeight + resizeHeight + Resizable.YOffset + 5);
    }
}
