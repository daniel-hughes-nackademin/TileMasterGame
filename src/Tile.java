import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile extends JButton {

    int sideLength;
    int value;
    int x, y;

    //Makes a SQUARE tile button for the puzzle board with image icon
    public Tile(int gridSize, int value, BufferedImage originalImage) {
        this.sideLength = originalImage.getWidth()/gridSize; //width equals height since it's a square tile
        this.value = value;
        setXandY(value, gridSize);

        if (value == gridSize*gridSize - 1) //If it's last tile in the list, make it black instead of a picture tile
            setBackground(Color.BLACK);
        else
            setIcon(makeTileImageIcon(originalImage));
    }

    private ImageIcon makeTileImageIcon(BufferedImage originalImage){
        BufferedImage tileImage = new BufferedImage(sideLength, sideLength, originalImage.getType());

        Graphics2D gr = tileImage.createGraphics();
        gr.drawImage(originalImage, 0, 0, sideLength, sideLength, sideLength*x, sideLength*y, sideLength*x + sideLength, sideLength*y + sideLength, null);
        gr.dispose();

        return new ImageIcon(tileImage);
    }

   public void setXandY(int index, int gridSize){
        this.x = index%gridSize;
        this.y = index/gridSize;

   }

    @Override
    public String toString() {
        return "Tile index: " + value + ", x = " + x + ", y = " + y;
    }
}
