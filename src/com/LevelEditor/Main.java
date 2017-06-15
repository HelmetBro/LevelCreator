package com.LevelEditor;

import com.LevelEditor.ScreenComponents.DarkComponents.DarkCheckBox.DarkCheckBoxIcon;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkRadioButton.DarkRadioButtonIcon;
import com.LevelEditor.ScreenComponents.DarkComponents.DarkTree.*;
import com.LevelEditor.StartWindow.InitializeWindow;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

import static com.LevelEditor.ApplicationWindow.*;

/**
 * @author Eric Parsons
 *         <p>
 *         Legal License Terms [BY USING THIS SOFTWARE YOU COMPLY TO THESE TERMS]
 *         -------------------
 *         <p>
 *         Feel free to use of this code and/or application for your own projects
 *         as long as:
 *         <p>
 *         -You give proper credit to the origional creator (me)
 *         <p>
 *         -You don't use any of it for commercial purposes
 *         <p>
 *         -You consider sharing your improvements by making the code avaliable
 *         to other enthusiasts (not required but appreciated)
 *         <p>
 *         seriously though if you try to make money off this i will end you
 *         <p>
 *         Have fun!
 *         <p>
 *         Bloodthirsty License Agreement
 *         ------------------------------
 *         <p>
 *         This is where the bloodthirsty license agreement is supposed to go,
 *         explaining that Level Editor is a copyrighted package licensed
 *         for use by a single person, and sternly warning you not to pirate
 *         copies of it and explaining, in detail, the gory consequences if you
 *         do.
 *         <p>
 *         I know that you're an honest person, and are not going to go around
 *         pirating copies of Level Editor; this is just as well with us
 *         since I worked hard to perfect it and selling copies of it is my only
 *         method of making anything out of all the hard work.
 *         <p>
 *         If, on the other hand, you are one of those few people who do go around
 *         pirating copies of software you probably aren't going to pay much
 *         attention to a license agreement, bloodthirsty or not. Just keep your
 *         doors locked and look out for the HarGreaves Firm attack shark.
 *         <p>
 *         Honest Disclaimer
 *         -----------------
 *         <p>
 *         I don't claim Level Editor is good for anything - if you
 *         think it is, great, but it's up to you to decide.  If Level
 *         Editor doesn't work: tough. If you lose a million because
 *         Level Editor messes up, it's you that's out the million, not
 *         me. If you don't like this disclaimer: tough. I reserve the right to
 *         do the absolute minimum provided by law, up to and including nothing.
 *         <p>
 *         This is basically the same disclaimer that comes with all software
 *         packages, but mine is in plain English and theirs is in "legalese".
 *         <p>
 *         I didn't really want to include any disclaimer at all, but my lawyers
 *         insisted. I tried to ignore them but they threatened me with the
 *         attack shark (see license agreement above) at which point I relented.
 */

public class Main implements Serializable {

    public static final float SMALL_FONT_SIZE = 12f;
    private static final long serialVersionUID = 90210L;
    private static final float MEDIUM_FONT_SIZE = 13f;
    public static ApplicationWindow applicationWindow;
    public static Level currentLevel;

    public static void main(String[] args) {
        changeUI();

        //creating start window
        new InitializeWindow();
    }

    private static void changeUI() {

        //changing UI for check boxes
        UIManager.put("CheckBox.icon", new DarkCheckBoxIcon());
        UIManager.put("DarkerCheckBox.checkBackground",
                BACKGROUND_SHADED_COLOR.brighter().brighter());
        UIManager.put("DarkerCheckBox.checkClickBackground",
                BACKGROUND_SHADED_COLOR.brighter().brighter().brighter());
        UIManager.put("DarkerCheckBox.checkForeground",
                LIGHT_COLOR.darker());
        UIManager.put("DarkerCheckBox.checkDisabled",
                LIGHT_COLOR.darker());

        //changing UI for radio buttons
        UIManager.put("RadioButton.icon", new DarkRadioButtonIcon());
        UIManager.put("DarkRadioButton.background",
                BACKGROUND_SHADED_COLOR.brighter().brighter());
        UIManager.put("DarkRadioButton.select",
                BACKGROUND_SHADED_COLOR.brighter().brighter().brighter());
        UIManager.put("DarkRadioButton.foreground",
                LIGHT_COLOR.darker());

        //changing UI for tree
        UIManager.put("Tree.closedIcon", new DarkTreeClosedIcon());
        UIManager.put("Tree.leafIcon", new DarkTreeLeafIcon());
        UIManager.put("Tree.openIcon", new DarkTreeOpenIcon());
        UIManager.put("Tree.expandedIcon", new DarkTreeExpandedIcon());
        UIManager.put("Tree.collapsedIcon", new DarkTreeCollapsedIcon());
        UIManager.put("Tree.drawsFocusBorderAroundIcon", false);
        UIManager.put("Tree.drawDashedFocusIndicator", false);

        //changing UI ComboBox
        UIManager.put("ComboBox.background", BACKGROUND_COLOR);
        UIManager.put("ComboBox.foreground", LIGHT_COLOR);
        UIManager.put("ComboBox.selectionBackground", ApplicationWindow.SELECTION_COLOR);
        UIManager.put("ComboBox.selectionForeground", LIGHT_COLOR);
        UIManager.put("ComboBox.font", CONSOLAS.deriveFont(SMALL_FONT_SIZE));

        //changing UI TextField
        UIManager.put("TextField.selectionBackground", ApplicationWindow.SELECTION_COLOR);
        UIManager.put("TextField.selectionForeground", LIGHT_COLOR);
        UIManager.put("TextField.caretForeground", LIGHT_COLOR);

        //changing text tool-tips
        UIManager.put("ToolTip.background", Color.BLACK);
        UIManager.put("ToolTip.foreground", LIGHT_COLOR);
        UIManager.put("ToolTip.border", LIGHT_COLOR.brighter());
        UIManager.put("ToolTip.font", CONSOLAS.deriveFont(MEDIUM_FONT_SIZE));

        //changing progress bar
        UIManager.put("ProgressBar.background", BACKGROUND_SHADED_COLOR);
        UIManager.put("ProgressBar.foreground", ApplicationWindow.SELECTION_COLOR);

        //changing Slider
        UIManager.put("Slider.focus", BACKGROUND_SHADED_COLOR);

        //changing menu bar items
        UIManager.put("MenuItem.background", BACKGROUND_SHADED_COLOR);
        UIManager.put("MenuItem.font", CONSOLAS.deriveFont(MEDIUM_FONT_SIZE));
        UIManager.put("MenuItem.foreground", LIGHT_SHADED_COLOR);
        UIManager.put("MenuItem.selectionBackground", LIGHT_SHADED_COLOR);
        UIManager.put("MenuItem.selectionForeground", Color.BLACK);
        UIManager.put("MenuItem.border", BorderFactory.createEmptyBorder());

        //changing menu bar
        UIManager.put("MenuBar.background", BACKGROUND_SHADED_COLOR);
        UIManager.put("MenuBar.border", BorderFactory.createMatteBorder(
                0, 0, 1, 0, BORDER_COLOR));

        //changing menu
        UIManager.put("Menu.font", CONSOLAS.deriveFont(MEDIUM_FONT_SIZE));
        UIManager.put("Menu.selectionBackground", LIGHT_SHADED_COLOR);
        UIManager.put("Menu.selectionForeground", Color.BLACK);
        UIManager.put("Menu.border", BorderFactory.createEmptyBorder());

        //changing popup menu
        UIManager.put("PopupMenu.border", BorderFactory.createEmptyBorder());

        //changing option pane
        UIManager.put("OptionPane.background", BACKGROUND_SHADED_COLOR);
        UIManager.put("OptionPane.messageForeground", LIGHT_COLOR);
        UIManager.put("OptionPane.messageFont", CONSOLAS.deriveFont(MEDIUM_FONT_SIZE));

        // both for file chooser
        UIManager.put("Panel.background", BACKGROUND_SHADED_COLOR); //changing panel
        UIManager.put("Label.foreground", LIGHT_COLOR); //changing label

    }

}