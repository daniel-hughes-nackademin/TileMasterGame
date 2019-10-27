import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuzzleBoard extends JPanel implements ActionListener {
    static List<Tile> tiles = new ArrayList<>();

    protected String imagePath;
    private int width = 500, height = 500;

    public PuzzleBoard (int gridSize){

        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.BLACK)));
        setLayout(new GridLayout(gridSize, gridSize));

        for (int i = 0; i < tiles.size(); i++) {
            tiles.get(i).setXandY(i, gridSize);
            this.add(tiles.get(i));
        }

    }


    public PuzzleBoard(String imagePath, int gridSize) { //Creates a puzzle board with shuffled image pieces
        tiles.clear();
        this.imagePath = imagePath;
        BufferedImage originalImage = ImageTool.loadResizedImage(imagePath, width, height);
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.BLACK)));

        setLayout(new GridLayout(gridSize, gridSize));

        final int totalNrOfTiles = gridSize * gridSize;

        for (int i = 0; i < totalNrOfTiles - 1; i++) {
                Tile tile = new Tile(gridSize, i, originalImage);
                tile.addActionListener(this);
                tiles.add(tile);
        }

        Collections.shuffle(tiles);

        Tile blackTile = new Tile(gridSize, totalNrOfTiles - 1, originalImage);
        blackTile.setEnabled(false);
        blackTile.addActionListener(this);
        tiles.add(blackTile);

        for (int i = 0; i < tiles.size(); i++) {
            tiles.get(i).setXandY(i, gridSize);
            this.add(tiles.get(i));
        }

    }

    public String getImagePath() {
        return imagePath;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        List<Tile> tilesToRotate = new ArrayList<>();

        int blackTileIndex = 0;
        Tile blackTile = null;
        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).icon == null) {
                blackTile = tiles.get(i);
                blackTileIndex = i;
            }
        }

        for (int nr = 0; nr < tiles.size(); nr++) {
            Tile selectedTile = tiles.get(nr);
            if (e.getSource() == selectedTile) {
                int selectedIndex = nr;
                if (selectedTile.x == blackTile.x && selectedTile.y < blackTile.y) {
                    System.out.println(selectedTile + " has same x value, but lower y than black tile: " + blackTile);

                    for (int i = selectedIndex; i <= blackTileIndex; i+=Game.gameFrame.gridSize) {
                        tilesToRotate.add(tiles.get(i));
                    }
                    Collections.rotate(tilesToRotate, 1);

                    int rotateIndex = 0;
                    for (int i = selectedIndex; i <= blackTileIndex; i+=Game.gameFrame.gridSize) {
                        tiles.set(i, tilesToRotate.get(rotateIndex));
                        rotateIndex++;
                    }

                } else if (selectedTile.x == blackTile.x && selectedTile.y > blackTile.y) {
                    System.out.println(selectedTile + " has same x value, but greater y than black tile: " + blackTile);

                    for (int i = blackTileIndex; i <= selectedIndex; i+=Game.gameFrame.gridSize) {
                        tilesToRotate.add(tiles.get(i));
                    }
                    Collections.rotate(tilesToRotate, -1);

                    int rotateIndex = 0;
                    for (int i = blackTileIndex; i <= selectedIndex; i+=Game.gameFrame.gridSize) {
                        tiles.set(i, tilesToRotate.get(rotateIndex));
                        rotateIndex++;
                    }


                } else if (selectedTile.y == blackTile.y && selectedTile.x < blackTile.x) {
                    System.out.println(selectedTile + " has same y value, but lower x than black tile: " + blackTile);

                    for (int i = selectedIndex; i <= blackTileIndex; i++) {
                        tilesToRotate.add(tiles.get(i));
                    }
                    Collections.rotate(tilesToRotate, 1);

                    int rotateIndex = 0;
                    for (int i = selectedIndex; i <= blackTileIndex; i++) {
                        tiles.set(i, tilesToRotate.get(rotateIndex));
                        rotateIndex++;
                    }



                } else if (selectedTile.y == blackTile.y && selectedTile.x > blackTile.x) {
                    System.out.println(selectedTile + " has same y value, but greater x than black tile: " + blackTile);

                    for (int i = blackTileIndex; i <= selectedIndex; i++) {
                        tilesToRotate.add(tiles.get(i));
                    }
                    Collections.rotate(tilesToRotate, -1);

                    int rotateIndex = 0;
                    for (int i = blackTileIndex; i <= selectedIndex; i++) {
                        tiles.set(i, tilesToRotate.get(rotateIndex));
                        rotateIndex++;
                    }
                } else {
                    System.out.println("Different X and Y than black tile: " + selectedTile);
                    return;
                }

                for (int i = 0; i < tiles.size(); i++) {
                    System.out.println("Index: " + i + " - " + tiles.get(i));
                }

                Game.gameFrame.refreshPuzzleBoard();

            }

        }
    }

    private void moveTiles(Tile tile){

    }
}
