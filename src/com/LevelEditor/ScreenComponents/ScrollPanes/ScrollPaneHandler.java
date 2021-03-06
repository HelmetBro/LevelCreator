package com.LevelEditor.ScreenComponents.ScrollPanes;


import com.LevelEditor.ApplicationWindow;
import com.LevelEditor.Resizable;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.ObjectsTreeScrollPane;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.PropertiesScrollPane;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.SpritesScrollPane;
import com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.ToolsScrollPane;
import com.LevelEditor.StartWindow.AspectSettings;

import java.awt.*;

import static com.LevelEditor.StartWindow.AspectSettings.RULER_WIDTH;

public class ScrollPaneHandler extends Container implements Resizable {

    public static ObjectsTreeScrollPane objSP;
    public static PropertiesScrollPane propSP;
    private static ToolsScrollPane toolsSP;
    public static SpritesScrollPane spritesSP;

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
        propSP = new PropertiesScrollPane();
        toolsSP = new ToolsScrollPane(width);
        spritesSP = new SpritesScrollPane();
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
            case 3:
                add(spritesSP);
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
        int moveDistance = (windowWidth - ApplicationWindow.settings.getLvlMakerWidth() - RULER_WIDTH - AspectSettings.TOOLS_WINDOW_SIZE_X);
        int resizeHeight = windowHeight - ApplicationWindow.settings.getLvlMakerHeight() - RULER_WIDTH;
        setBounds(startX + moveDistance, startY, startWidth, startHeight + resizeHeight + Resizable.Y_OFFSET + 5);

        revalidate();
    }
}
