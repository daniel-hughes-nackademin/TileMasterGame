import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel(String imagePath, int width, int height){
        this.image = ImageTool.loadImage(imagePath);
        this.image = ImageTool.getResizedImage(image, width, height);
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }


    @Override
public void paintComponent(Graphics g){g.drawImage(image, 0, 0, null);}


}
