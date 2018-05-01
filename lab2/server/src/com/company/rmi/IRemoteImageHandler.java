package com.company.rmi;

        import com.company.images.BufferedImageDTO;

        import java.awt.image.BufferedImage;
        import java.rmi.Remote;
        import java.rmi.RemoteException;

public interface IRemoteImageHandler extends Remote{

    BufferedImageDTO processImage(BufferedImageDTO source) throws RemoteException;
}
