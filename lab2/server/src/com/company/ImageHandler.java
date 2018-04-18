package com.company;


import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageHandler {

    public static final float[][] CONTRAST_FILTER_KERNEL =
           {{-1, -1, -1},
            {-1,  9, -1},
            {-1, -1, -1}};

    public static final float CONTRAST_FILTER_DIV = 9;

    public static BufferedImage convolutionFilter(BufferedImage source, float[][] kernel, float div){

        int width = source.getWidth();
        int heigth = source.getHeight();

        int kernelWidth = kernel[0].length;
        int kernelHeight = kernel.length;

        BufferedImage result = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < heigth; y++) {
            for (int x = 0; x < width; x++) {

                int resultRGB = source.getRGB(x, y);

                if(x - kernelWidth/2 > 0 &&
                   x + kernelWidth/2 < width &&
                   y - kernelWidth/2 > 0 &&
                   y + kernelWidth/2 < heigth) {

                    float r = 0;
                    float g = 0;
                    float b = 0;

                    for (int ky = 0; ky < kernelHeight; ky++) {
                        for (int kx = 0; kx < kernelWidth; kx++) {

                            int sourceRGB = source.getRGB(x + kx - kernelWidth/2, y + ky - kernelHeight/2);

                            float factor = kernel[ky][kx];

                            float red = ((sourceRGB & 0xff0000) >> 16) * factor;
                            float green = ((sourceRGB & 0xff00) >> 8) * factor;
                            float blue = (sourceRGB & 0xff) * factor;

                            r += red;
                            g += green;
                            b += blue;
                        }
                    }

                    r = (r > 255) ? 255 : r;
                    g = (g > 255) ? 255 : g;
                    b = (b > 255) ? 255 : b;

                    r = (r < 0) ? 0 : r;
                    g = (g < 0) ? 0 : g;
                    b = (b < 0) ? 0 : b;

                    Color color = new Color((int)r, (int)g, (int)b);
                    resultRGB = color.getRGB();
                }
                result.setRGB(x, y, resultRGB);
            }
        }

        return result;
    }

}
