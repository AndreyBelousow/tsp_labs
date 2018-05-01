package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoadingTools {

    public static BufferedImage loadImageFormFile(String path){
        BufferedImage image = null;
        try {
            File file = new File(path);
            image =  ImageIO.read(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return image;
    }

    public static void saveImageToFile(BufferedImage image, String fileName, String extension) {
        File file = new File(fileName + "." + extension);
        try {
            ImageIO.write(image, extension, file);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
