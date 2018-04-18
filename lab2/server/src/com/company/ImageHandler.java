package com.company;


import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageHandler {

    public BufferedImage convolutionFilter(BufferedImage source, int div, int[][] kernel){

        int width = source.getWidth();
        int heigth = source.getHeight();

        int kernelWidth = kernel[0].length;
        int kernelHeight = kernel.length;

        BufferedImage result = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < heigth; y++) {
            for (int x = 0; x < width; x++) {

                int resultRGB = source.getRGB(x, y);

                if(x - kernelWidth/2 >= 0 &&
                   x + kernelWidth/2 <= width &&
                   y - kernelWidth/2 >= 0 &&
                   y + kernelWidth/2 <= heigth) {

                    Color color = new Color(resultRGB);

                    for (int ky = 0; ky < kernelHeight; ky++) {
                        for (int kx = 0; kx < kernelWidth; kx++) {

                            int red = source
                        }
                    }
                }

                result.setRGB(x, y, resultRGB);
            }
        }

        return source;
    }

}
