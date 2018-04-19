package com.company;

import test.Test;

import java.awt.image.BufferedImage;
import com.company.images.ImageFilter;
import java.rmi.RemoteException;

public class ImageHandlerServer implements RemoteImageHandler {

    @Override
    public BufferedImage processImage(BufferedImage source) throws RemoteException {

        return ImageFilter.applyConvolutionFilter(
                source,
                ImageFilter.CONTRAST_FILTER_KERNEL,
                ImageFilter.CONTRAST_FILTER_DIV);
    }

    public static void main(String[] args) {
        System.out.println("\nServer started\n");
        Test.run();
    }
}
