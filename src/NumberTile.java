import javax.swing.*;
import java.awt.*;

class NumberTile extends Tile {

    //Makes a SQUARE tile button for the puzzle board with image icon
    NumberTile(int gridSize, int value, ImageIcon icon) {
        this.sideLength = icon.getIconWidth(); //width equals height since it's a square tile
        this.correctOrderIndex = value;
        setXandY(value, gridSize);

        if (value == gridSize*gridSize - 1) //If it's last tile in the list, make it black instead of a picture tile
            setBackground(Color.BLACK);
        else{
            this.icon = icon;
            setIcon(this.icon);
            this.setText(String.valueOf(value + 1));
            this.setHorizontalTextPosition(JButton.CENTER);
            this.setVerticalTextPosition(JButton.CENTER);
            this.setFont(new Font("Georgia", Font.BOLD, 18));
            this.setForeground(Color.BLACK);
        }
    }
}
