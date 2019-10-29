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

    public PuzzleBoard(int gridSize) {

        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.BLACK)));
        setLayout(new GridLayout(gridSize, gridSize));

        for (int i = 0; i < tiles.size(); i++) {
            tiles.get(i).setXandY(i, gridSize);
            this.add(tiles.get(i));
        }

    }


    public PuzzleBoard(String imagePath, int gridSize) { //Creates a puzzle board with image pieces
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

        Tile blackTile = new Tile(gridSize, totalNrOfTiles - 1, originalImage);
        blackTile.setEnabled(false);
        blackTile.addActionListener(this);
        tiles.add(blackTile);

        for (int i = 0; i < tiles.size(); i++) {
            tiles.get(i).setXandY(i, gridSize);
            this.add(tiles.get(i));
        }

    }

    public PuzzleBoard(ImageIcon icon, int gridSize) { //Creates a puzzle board with number tiles
        tiles.clear();
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.BLACK)));

        setLayout(new GridLayout(gridSize, gridSize));

        final int totalNrOfTiles = gridSize * gridSize;

        for (int i = 0; i < totalNrOfTiles - 1; i++) {
            NumberTile numberTile = new NumberTile(gridSize, i, icon);
            numberTile.addActionListener(this);
            tiles.add(numberTile);
        }

        NumberTile blackTile = new NumberTile(gridSize, totalNrOfTiles - 1, icon);
        blackTile.setEnabled(false);
        blackTile.addActionListener(this);
        tiles.add(blackTile);

        for (int i = 0; i < tiles.size(); i++) {
            tiles.get(i).setXandY(i, gridSize);
            this.add(tiles.get(i));
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < tiles.size(); i++) {

            if (e.getSource() == tiles.get(i)) {
                Tile selectedTile = tiles.get(i);
                int selectedIndex = i;

                swapTiles(selectedTile, selectedIndex);

                Game.gameFrame.refreshPuzzleBoard();

                checkWinCondition();
                break;
            }
        }
    }

    void swapTiles(Tile selectedTile, int selectedIndex) {
        List<Tile> tilesToRotate = new ArrayList<>();

        int blackTileIndex = 0;
        Tile blackTile = new Tile();
        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).icon == null) {
                blackTile = tiles.get(i);
                blackTileIndex = i;
                break;
            }
        }

        if (selectedTile.x == blackTile.x && selectedTile.y < blackTile.y) {

            for (int i = selectedIndex; i <= blackTileIndex; i += Game.gameFrame.gridSize) {
                tilesToRotate.add(tiles.get(i));
            }
            Collections.rotate(tilesToRotate, 1);

            int rotateIndex = 0;
            for (int i = selectedIndex; i <= blackTileIndex; i += Game.gameFrame.gridSize) {
                tiles.set(i, tilesToRotate.get(rotateIndex));
                rotateIndex++;
            }

        } else if (selectedTile.x == blackTile.x && selectedTile.y > blackTile.y) {

            for (int i = blackTileIndex; i <= selectedIndex; i += Game.gameFrame.gridSize) {
                tilesToRotate.add(tiles.get(i));
            }
            Collections.rotate(tilesToRotate, -1);

            int rotateIndex = 0;
            for (int i = blackTileIndex; i <= selectedIndex; i += Game.gameFrame.gridSize) {
                tiles.set(i, tilesToRotate.get(rotateIndex));
                rotateIndex++;
            }

        } else if (selectedTile.y == blackTile.y && selectedTile.x < blackTile.x) {

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
            return; //Illegal Move - do nothing
        }
        Game.gameFrame.moveCount++;
        Game.gameFrame.moveCountLabel.setText("Moves: " + Game.gameFrame.moveCount);
    }

    private void checkWinCondition() {
        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).correctOrderIndex != i)
                return;
        }

        if (Game.gameFrame.isImageGame) {
            Game.gameFrame.removeCenterComponent();
            Game.gameFrame.backgroundPanel.add(new ImagePanel(Game.gameFrame.imagePath, 500, 500), BorderLayout.CENTER);
            Game.gameFrame.revalidate();
        } else {
            JOptionPane.showMessageDialog(Game.gameFrame, "Congratulations!", "Tile Master - Victory", JOptionPane.PLAIN_MESSAGE);
            for (Tile tile : tiles) {
                tile.setEnabled(false);
            }
        }

    }
}
