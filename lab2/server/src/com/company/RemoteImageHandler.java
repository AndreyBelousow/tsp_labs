package com.company;

import java.awt.image.BufferedImage;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteImageHandler extends Remote{

    BufferedImage processImage(BufferedImage source) throws RemoteException;
}
