import javax.swing.*;
import javax.swing.border.BevelBorder;
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
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.BLACK)));
    }

    public ImagePanel(BufferedImage image, int width, int height){
        this.image = ImageTool.getResizedImage(image, width, height);
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.BLACK)));
    }


    @Override
public void paintComponent(Graphics g){
        g.drawImage(image, 0, 0, null);
    }


}
