package com.company;

import java.awt.image.BufferedImage;
import com.company.images.ImageFilter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImageHandlerImpl extends UnicastRemoteObject implements RemoteImageHandler {

    protected ImageHandlerImpl() throws RemoteException {
    }

    @Override
    public BufferedImage processImage(BufferedImage source) throws RemoteException {

        return ImageFilter.applyConvolutionFilter(
                source,
                ImageFilter.CONTRAST_FILTER_KERNEL,
                ImageFilter.CONTRAST_FILTER_DIV);
    }
}
