package test;

import com.company.ImageHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {

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

    public static void run(){
        BufferedImage source = loadImageFormFile("source.jpg");

        BufferedImage result = ImageHandler.convolutionFilter(
                source, ImageHandler.CONTRAST_FILTER_KERNEL, ImageHandler.CONTRAST_FILTER_DIV);

        saveImageToFile(result, "result", "png");
    }
}
