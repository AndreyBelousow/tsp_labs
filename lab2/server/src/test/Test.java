package test;

import com.company.ImageHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {

    public static BufferedImage loadFormFile(String path){
        BufferedImage image = null;
        try {
            File file = new File(path);
            image =  ImageIO.read(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return image;
    }

    public static void writeToFile(BufferedImage image, String fileName, String extension) {
        File file = new File(fileName + "." + extension);
        try {
            ImageIO.write(image, extension, file);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void run(){
        BufferedImage source = loadFormFile("source.jpg");

        ImageHandler imageHandler = new ImageHandler();
        BufferedImage result = imageHandler.convolutionFilter(source);

        writeToFile(result, "result", "png");
    }
}
