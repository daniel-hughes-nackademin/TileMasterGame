import javax.swing.*;
import java.awt.image.BufferedImage;

public class Tile extends JButton {

    int sideLength;
    int value;
    int x, y;

    //Makes a SQUARE tile button for the puzzle board with image icon
    public Tile(int gridSize, int value, BufferedImage originalImage) {

        this.sideLength = originalImage.getWidth()/gridSize;
        System.out.println(sideLength);
        this.sideLength = originalImage.getHeight()/gridSize;
        System.out.println(sideLength);
        this.value = value;
        this.x = value%gridSize;
        this.y = value/gridSize;
    }




    private ImageIcon makeTileImageIcon(){



        return null;
    }
}
