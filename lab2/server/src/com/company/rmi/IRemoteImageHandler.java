package com.company.rmi;

import com.company.images.BufferedImageDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *  Provides an remote interface for image processing
 */
public interface IRemoteImageHandler extends Remote{

    BufferedImageDTO processImage(BufferedImageDTO source) throws RemoteException;
}
