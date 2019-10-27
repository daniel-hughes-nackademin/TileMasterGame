import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageTool {

    public static BufferedImage loadImage(String filePath){
        try{
            return ImageIO.read(ImageTool.class.getResource(filePath));
        } catch (IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not load file!", "ERROR!", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    private static BufferedImage getResizedImage(BufferedImage picture, int width, int height){
        BufferedImage resizedImage = new BufferedImage(width, height, picture.getType());
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(picture, 0, 0, width, height, null);
        g2d.dispose();

        return resizedImage;
    }

    public static BufferedImage loadResizedImage(String filePath, int width, int height){
        BufferedImage image = loadImage(filePath);
        image = getResizedImage(image, width, height);

        return image;
    }

    public static ImageIcon makeScaledImageIcon(String filePath, int width, int height){
        BufferedImage image = loadImage(filePath);
        Image imageScaled = image.getScaledInstance(width,height, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(imageScaled);

        return icon;
    }

}
