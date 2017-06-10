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

    public static final float smallFontSize = 12f;
    private static final long serialVersionUID = 90210L;
    private static final float mediumFontSize = 13f;
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
                backgroundShadedColor.brighter().brighter());
        UIManager.put("DarkerCheckBox.checkClickBackground",
                backgroundShadedColor.brighter().brighter().brighter());
        UIManager.put("DarkerCheckBox.checkForeground",
                lightColor.darker());
        UIManager.put("DarkerCheckBox.checkDisabled",
                lightColor.darker());

        //changing UI for radio buttons
        UIManager.put("RadioButton.icon", new DarkRadioButtonIcon());
        UIManager.put("DarkRadioButton.background",
                backgroundShadedColor.brighter().brighter());
        UIManager.put("DarkRadioButton.select",
                backgroundShadedColor.brighter().brighter().brighter());
        UIManager.put("DarkRadioButton.foreground",
                lightColor.darker());

        //changing UI for tree
        UIManager.put("Tree.closedIcon", new DarkTreeClosedIcon());
        UIManager.put("Tree.leafIcon", new DarkTreeLeafIcon());
        UIManager.put("Tree.openIcon", new DarkTreeOpenIcon());
        UIManager.put("Tree.expandedIcon", new DarkTreeExpandedIcon());
        UIManager.put("Tree.collapsedIcon", new DarkTreeCollapsedIcon());
        UIManager.put("Tree.drawsFocusBorderAroundIcon", false);
        UIManager.put("Tree.drawDashedFocusIndicator", false);

        //changing UI ComboBox
        UIManager.put("ComboBox.background", backgroundColor);
        UIManager.put("ComboBox.foreground", lightColor);
        UIManager.put("ComboBox.selectionBackground", ApplicationWindow.selectionColor);
        UIManager.put("ComboBox.selectionForeground", lightColor);
        UIManager.put("ComboBox.font", basicFont.deriveFont(smallFontSize));

        //changing UI TextField
        UIManager.put("TextField.selectionBackground", ApplicationWindow.selectionColor);
        UIManager.put("TextField.selectionForeground", lightColor);
        UIManager.put("TextField.caretForeground", lightColor);

        //changing text tool-tips
        UIManager.put("ToolTip.background", Color.BLACK);
        UIManager.put("ToolTip.foreground", lightColor);
        UIManager.put("ToolTip.border", lightColor.brighter());
        UIManager.put("ToolTip.font", basicFont.deriveFont(mediumFontSize));

        //changing progress bar
        UIManager.put("ProgressBar.background", backgroundShadedColor);
        UIManager.put("ProgressBar.foreground", ApplicationWindow.selectionColor);

        //changing Slider
        UIManager.put("Slider.focus", backgroundShadedColor);

        //changing menu bar items
        UIManager.put("MenuItem.background", backgroundShadedColor);
        UIManager.put("MenuItem.font", basicFont.deriveFont(mediumFontSize));
        UIManager.put("MenuItem.foreground", lightShadedColor);
        UIManager.put("MenuItem.selectionBackground", lightShadedColor);
        UIManager.put("MenuItem.selectionForeground", Color.BLACK);
        UIManager.put("MenuItem.border", BorderFactory.createEmptyBorder());

        //changing menu bar
        UIManager.put("MenuBar.background", backgroundShadedColor);
        UIManager.put("MenuBar.border", BorderFactory.createMatteBorder(
                0, 0, 1, 0, borderColor));

        //changing menu
        UIManager.put("Menu.font", basicFont.deriveFont(mediumFontSize));
        UIManager.put("Menu.selectionBackground", lightShadedColor);
        UIManager.put("Menu.selectionForeground", Color.BLACK);
        UIManager.put("Menu.border", BorderFactory.createEmptyBorder());

        //changing popup menu
        UIManager.put("PopupMenu.border", BorderFactory.createEmptyBorder());

        //changing option pane
        UIManager.put("OptionPane.background", backgroundShadedColor);
        UIManager.put("OptionPane.messageForeground", lightColor);
        UIManager.put("OptionPane.messageFont", basicFont.deriveFont(mediumFontSize));

        // both for file chooser
        UIManager.put("Panel.background", backgroundShadedColor); //changing panel
        UIManager.put("Label.foreground", lightColor); //changing label

    }

}