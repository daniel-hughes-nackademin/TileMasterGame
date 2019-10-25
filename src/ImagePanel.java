import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private BufferedImage image;
    protected String imagePath;

    public ImagePanel(String imagePath, int width, int height){
        this.imagePath = imagePath;
        this.image = ImageTool.loadResizedImage(this.imagePath, width, height);
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }


    public BufferedImage getImage() {
        return image;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
public void paintComponent(Graphics g){
        g.drawImage(image, 0, 0, null);
    }


}
