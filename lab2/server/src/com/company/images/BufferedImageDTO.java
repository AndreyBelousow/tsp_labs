package com.company.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *  BufferedImage doesn't implements Serializable,
 *  so here's the serializable wrapper for it
 */
public class BufferedImageDTO implements Serializable{

    private transient BufferedImage image;

    public BufferedImageDTO(BufferedImage image){
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        ImageIO.write(image, "png", out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        image = ImageIO.read(in);
    }
}
