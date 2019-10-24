import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GridPanel extends JPanel {

    List<JButton> tiles;
    BufferedImage picture;

    //Make pixel array?


    public GridPanel(int row, int col, BufferedImage picture){
        this.picture = picture;
        tiles = new ArrayList<>();
        this.setLayout(new GridLayout(row,col));
        this.setPreferredSize(new Dimension(this.picture.getWidth(), this.picture.getHeight()));





    }



    public void createRandomTiles(int row, int col){
        //Split the buttons into the appropriate size based on picture size and number of rows/columns



    }
}
