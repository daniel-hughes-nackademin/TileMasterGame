import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile extends JButton {

    int sideLength;
    int correctOrderIndex;
    int x, y;
    ImageIcon icon = null;

    Tile(){}

    //Makes a SQUARE tile button for the puzzle board with image icon
    Tile(int gridSize, int correctOrderIndex, BufferedImage originalImage) {
        this.sideLength = originalImage.getWidth()/gridSize; //width equals height since it's a square tile
        this.correctOrderIndex = correctOrderIndex;
        setXandY(correctOrderIndex, gridSize);

        if (correctOrderIndex == gridSize*gridSize - 1) //If it's last tile in the list, make it black instead of a picture tile
            setBackground(Color.BLACK);
        else{
            this.icon = makeTileImageIcon(originalImage);
            setIcon(this.icon);
        }
    }

    private ImageIcon makeTileImageIcon(BufferedImage originalImage){
        BufferedImage tileImage = new BufferedImage(sideLength, sideLength, originalImage.getType());

        Graphics2D gr = tileImage.createGraphics();
        gr.drawImage(originalImage, 0, 0, sideLength, sideLength, sideLength*x, sideLength*y, sideLength*x + sideLength, sideLength*y + sideLength, null);
        gr.dispose();

        return new ImageIcon(tileImage);
    }

   void setXandY(int index, int gridSize){
        this.x = index%gridSize;
        this.y = index/gridSize;

   }

    @Override
    public String toString() {
        return "Tile value: " + correctOrderIndex + ", x = " + x + ", y = " + y;
    }
}
