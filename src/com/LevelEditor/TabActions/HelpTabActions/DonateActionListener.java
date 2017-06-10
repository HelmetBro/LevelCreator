package com.LevelEditor.TabActions.HelpTabActions;

import com.LevelEditor.ApplicationWindow;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.LevelEditor.ApplicationWindow.backgroundShadedColor;
import static com.LevelEditor.ApplicationWindow.lightColor;

public class DonateActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        String messageP1 = "You. I like you. Thanks for at least considering to support what I do," +
                " you have<br />no idea how many hours and missed calls from mom this program took to create.<br />You can send any amount to" +
                " my ";
        String messageP2 = " whether it be $1, $5, $10, or even a<br />pretty " +
                "penny, I thank you so much for your support. This means a lot!<br /><br />" +
                "From the Developer & Creator,<br />" +
                "Eric Parsons";

        // for copying style
        JLabel label = new JLabel();
        Font font = ApplicationWindow.basicFont;

        // create some css from the label's font
        StringBuilder style = new StringBuilder("font-family:" + font.getFamily() + ";");
        style.append("font-weight:").append("normal").append(";");
        style.append("font-size:").append(12).append("pt;"); //12 is font size
        style.append("color:").append(
                "rgb(" + lightColor.getRed() + "," + lightColor.getGreen() + "," + lightColor.getBlue() + ")"
        ).append("pt;");

        // html content
        JEditorPane ep = new JEditorPane("text/html", "<html><body style=\"" + style + "\">"
                + messageP1 + "<a href=\"http://www.paypal.me/ETPSoftware\" style=\"color: #FFFFFF\">Paypal</a>" + messageP2
                + "</body></html>");

        // handle link events
        ep.addHyperlinkListener(e12 -> {
            if (e12.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(e12.getURL().toURI());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
        ep.setEditable(false);
        ep.setBackground(label.getBackground());

        ep.setBackground(backgroundShadedColor);

        JOptionPane.showMessageDialog(null, ep);

    }

}
