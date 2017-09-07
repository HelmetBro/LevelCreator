package com.LevelEditor.ScreenComponents.ScrollPanes.CustomPanels.CustomPanelComponents.SpriteLinkerListeners;

import com.LevelEditor.ScreenComponents.InfoLabelButton;
import com.LevelEditor.Shapes.Shape;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.LevelEditor.StartWindow.StartListeners.OpenDirectoryListener.getExtension;

/**
 * Created by EricP on 7/15/2017.
 */
public class OpenSpriteListener implements ActionListener {

    private JFrame browser = new JFrame();

    //java supported extensions
    private final static String BMP_U =  "BMP";
    private final static String BMP_L =  "bmp";

    private final static String JPEG_U =  "JPEG";
    private final static String JPEG_L =  "jpeg";

    private final static String WBMP_U =  "WBMP";
    private final static String WBMP_L =  "wbmp";

    private final static String JPG_U =  "JPG";
    private final static String JPG_L =  "jpg";

    private final static String GIF =  "gif";
    private final static String PNG =  "png";

    //references
    private Shape shape;
    private JTextField pathField;

    private JTextField widthField;
    private JTextField heightField;

    public OpenSpriteListener(Shape shape, JTextField pathField, JTextField w, JTextField h){
        this.shape = shape;
        this.pathField = pathField;
        this.widthField = w;
        this.heightField = h;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String path = showSaveFileDialog();

        //assuring that path is not null
        if (path == null) {
            InfoLabelButton.updateLabelText("Invalid File");
            return;
        }

        //transient
        shape.image = Toolkit.getDefaultToolkit().getImage(path);

        BufferedImage bimg;
        try {
            bimg = ImageIO.read(new File(path));
        } catch (IOException e1) {
            InfoLabelButton.updateLabelText("Invalid File");
            shape.image = null;
            return;
        }
        shape.spriteW = bimg.getWidth();
        shape.spriteH = bimg.getHeight();
        widthField.setText("" + shape.spriteW);
        heightField.setText("" + shape.spriteH);

        //written to file
        path = path.replace("\\", "/");
        shape.spritePath = path;

        pathField.setText(path);

    }

    private String showSaveFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Sprite");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDragEnabled(true);

        int userSelection = fileChooser.showOpenDialog(browser);

        File f = fileChooser.getSelectedFile();
        if (f == null)
            return null;

        if (    !Objects.equals(getExtension(f), BMP_U) &&
                !Objects.equals(getExtension(f), BMP_L) &&
                !Objects.equals(getExtension(f), JPEG_U) &&
                !Objects.equals(getExtension(f), JPEG_L) &&
                !Objects.equals(getExtension(f), WBMP_U) &&
                !Objects.equals(getExtension(f), WBMP_L) &&
                !Objects.equals(getExtension(f), JPG_U) &&
                !Objects.equals(getExtension(f), JPG_L) &&
                !Objects.equals(getExtension(f), GIF) &&
                !Objects.equals(getExtension(f), PNG))
            return null;

        if (userSelection == JFileChooser.APPROVE_OPTION)
            return fileChooser.getSelectedFile().getAbsoluteFile().toString();

        return null;
    }

}
