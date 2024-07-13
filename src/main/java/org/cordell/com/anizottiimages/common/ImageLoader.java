package org.cordell.com.anizottiimages.common;

import org.bukkit.map.MapPalette;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URI;


public class ImageLoader {
    public static BufferedImage loadImageFromURL(String urlString) throws Exception {
        var url = new URI(urlString).toURL();
        try (var in = url.openStream()) {
            return ImageIO.read(in);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static BufferedImage[] splitImage(BufferedImage image) {
        int rows = (int) Math.ceil(image.getHeight() / 128.0);
        int cols = (int) Math.ceil(image.getWidth() / 128.0);
        BufferedImage[] images = new BufferedImage[rows * cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                images[y * cols + x] = image.getSubimage(x * 128, y * 128,
                        Math.min(128, image.getWidth() - x * 128),
                        Math.min(128, image.getHeight() - y * 128));
            }
        }

        return images;
    }

    public static byte[] convertImageToMap(BufferedImage image) {
        int width = 128;
        int height = 128;
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        scaledImage.getGraphics().drawImage(image, 0, 0, width, height, null);

        byte[] mapData = new byte[width * height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = scaledImage.getRGB(x, y);
                mapData[y * width + x] = MapPalette.matchColor(new Color(rgb));
            }
        }

        return mapData;
    }
}
