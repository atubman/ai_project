package ravensproject;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DisplayImage {

    public DisplayImage(BufferedImage theImage) {
       // displayImage(theImage);
    }



    public static void displayImage(Image image) {
        JLabel label = new JLabel(new ImageIcon(image));

        JPanel panel = new JPanel();
        panel.add(label);

        JScrollPane scrollPane = new JScrollPane(panel);
        JOptionPane.showMessageDialog(null, scrollPane);
    }



}