/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Jithinpv
 */
public class Configuration {

    public static String iconFolder = "images/";
    public static String videoPool = "vedioPool/";
    public static String fileTransfers = "fileTransfers/";
    public static String tempFiles = "tempFiles/";
    public static String sendPasswordSubject = "Video data hiding- forgot password ";
    public static String rsaKeys = "rsaKeys/";
     public static String sendImageSubject = "video - Authenticated data ";

    public static void setIconOnLabel(String fileString, JLabel label) {
        // convert string file path to image icona and set on this label
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(iconFolder + fileString));
            Image scaledInstance = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledInstance);
            label.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setDefaultFileIcon(File file, JLabel label) {
        try {
            Icon ico = FileSystemView.getFileSystemView().getSystemIcon(file);
            Image image = ((ImageIcon) ico).getImage();
            Image scaledInstance = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledInstance);
            label.setIcon(imageIcon);
        } catch (Exception e) {
            System.out.println("Could not load image " + file.getAbsolutePath());
        }
    }

    public static void loadUserFileTransferLocation(String userId) {
        File f_Folder = new File(userId);
        if (!f_Folder.exists()) {
            f_Folder.mkdir();
        }
    }

    public static void initializeEnvironment() {
        try {
            File f_iconFolder = new File(iconFolder);
            if (!f_iconFolder.exists()) {
                f_iconFolder.mkdir();
            }

            File f_masterPoolLocation = new File(videoPool);
            if (!f_masterPoolLocation.exists()) {
                f_masterPoolLocation.mkdir();
            }

            File f_fileTransfers = new File(fileTransfers);
            if (!f_fileTransfers.exists()) {
                f_fileTransfers.mkdir();
            }

            File f_tempFiles = new File(tempFiles);
            if (!f_tempFiles.exists()) {
                f_tempFiles.mkdir();
            }

            File f_rsaKeys = new File(rsaKeys);
            if (!f_rsaKeys.exists()) {
                f_rsaKeys.mkdir();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
