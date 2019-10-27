import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PuzzleBoard extends JPanel {

    protected String imagePath;
    private int width = 500, height = 500;

    public PuzzleBoard(String imagePath, int gridSize) {
        this.imagePath = imagePath;
        BufferedImage originalImage = ImageTool.loadResizedImage(imagePath, width, height);
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.BLACK)));

        setLayout(new GridLayout(gridSize, gridSize));


        for (int i = 0; i < gridSize*gridSize; i++) {
                Tile tile = new Tile(gridSize, i, originalImage);
                this.add(tile);
        }


    }

    public String getImagePath() {
        return imagePath;
    }
}
