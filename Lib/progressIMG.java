/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lib;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class progressIMG {

    public static ImageIcon resizeIMG(String imgPath, int scaleWidth, int scaleHeight) {
        try {
            File inputFile = new File(imgPath);
            BufferedImage inputImage = ImageIO.read(inputFile);
            BufferedImage outputImage = new BufferedImage(scaleWidth, scaleHeight, inputImage.getType());
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(inputImage, 0, 0, scaleWidth, scaleHeight, null);
            g2d.dispose();
            return new ImageIcon(outputImage);
        } catch (IOException ex) {
            Logger.getLogger(progressIMG.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static File getByteArrayToImgV2(byte[] data, String namephoto) {
        try {
            InputStream is = new ByteArrayInputStream(data);
            BufferedImage newBi = ImageIO.read(is);
            ImageIO.write(newBi, "png", new File(namephoto));
            return new File(namephoto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ImageIcon reSizeImg(String imgPath, int scaleWidth, int scaleHeight) {
        ImageIcon i = new ImageIcon(imgPath);
        Image image = i.getImage();
        Image newimg = image.getScaledInstance(scaleWidth, scaleHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public static String getExtensionImg(String ImgName) {
        return ImgName.substring(ImgName.lastIndexOf("."), ImgName.length());
    }

    public static byte[] getImgToByteArray(File file) {
        try {
            BufferedImage bi = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException ex) {
            Logger.getLogger(progressIMG.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static File getByteArrayToImgV1(byte[] data, String namephoto) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            BufferedImage bImage2 = ImageIO.read(bis);
            File file = new File(namephoto);
            ImageIO.write(bImage2, "jpg", new File(namephoto));
            return file;
        } catch (IOException ex) {
            Logger.getLogger(progressIMG.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
