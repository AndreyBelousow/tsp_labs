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

    public static void saveImageToFile(BufferedImage image, String fileName) {
        File file = new File(fileName);
        try {
            ImageIO.write(image, "png", file);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
