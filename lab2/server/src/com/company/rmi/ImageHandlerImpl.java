package com.company.rmi;

import java.awt.image.BufferedImage;

import com.company.images.BufferedImageDTO;
import com.company.images.ImageFilter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImageHandlerImpl extends UnicastRemoteObject implements IRemoteImageHandler {

    public ImageHandlerImpl() throws RemoteException {
    }

    public BufferedImage processBufferedImage(BufferedImage source) throws RemoteException {

        return ImageFilter.applyConvolutionFilter(
                source,
                ImageFilter.CONTRAST_FILTER_KERNEL,
                ImageFilter.CONTRAST_FILTER_DIV);
    }

    @Override
    public BufferedImageDTO processImage(BufferedImageDTO source) throws RemoteException {
        return new BufferedImageDTO(processBufferedImage(source.getImage()));
    }
}
