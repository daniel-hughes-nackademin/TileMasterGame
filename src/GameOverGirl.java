import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameOverGirl implements Runnable {

    Thread gameOverThread;
    ImagePanel gameFacesPanel;
    boolean isVictory;
    int phaseDelay;


    public GameOverGirl(boolean isVictory) {
        this.isVictory = isVictory;
    }

    public synchronized void showGameOverGirl() {
        OptionsMenu.isActivatedGameFaces = true;
        Game.gameFrame.westComponentPanel.removeAll();
        Game.gameFrame.westComponentPanel.add(new JPanel(), BorderLayout.NORTH);
        Game.gameFrame.advertisingBanner = new ImagePanel("Graphics/Metal Texture Pattern.jpg", 150, 390);
        Game.gameFrame.westComponentPanel.add(Game.gameFrame.advertisingBanner, BorderLayout.SOUTH);
        Game.gameFrame.westComponentPanel.revalidate();
        gameOverThread = new Thread(this);
        gameOverThread.start();
    }

    public synchronized void stopGameOverGirl() {
        OptionsMenu.isActivatedGameFaces = false;
        System.out.println("stopping");
        try {
            gameOverThread.interrupt();
            gameOverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BorderLayout layout = (BorderLayout) Game.gameFrame.eastComponentPanel.getLayout();
        Game.gameFrame.eastComponentPanel.remove(layout.getLayoutComponent(BorderLayout.NORTH));
        if (Game.gameFrame.isImageGame)
            Game.gameFrame.miniPicture = new ImagePanel(Game.gameFrame.imagePath, 250, 250);
        else
            Game.gameFrame.miniPicture = new ImagePanel("Graphics/Sort The Numbers.jpg", 250, 250);
        Game.gameFrame.eastComponentPanel.add(Game.gameFrame.miniPicture, BorderLayout.NORTH);
        Game.gameFrame.backgroundPanel.revalidate();
    }


    @Override
    public void run() {
        File folder;
        File[] folderFileArray;
        int i = 0;
        while (OptionsMenu.isActivatedGameFaces) {

            if (isVictory) {
                folder = new File("src/Victory Faces");
                folderFileArray = folder.listFiles();
                showVictoryFaces(i, folderFileArray);
                i++;
                if (i == folderFileArray.length) {
                    i = 0;
                    continue;
                }
            } else {
                folder = new File("src/Game Over Faces");
                folderFileArray = folder.listFiles();
                showGameFaces(folderFileArray);
            }
        }
    }

    private void showGameFaces(File[] folderFileArray) {
        int totalSeconds = Game.gameFrame.seconds + Game.gameFrame.minutes * 60;
        phaseDelay = 10;
        String faceExpressionFilePath;
        for (int i = 0; i < folderFileArray.length; i++) {
            if (phaseDelay * i == totalSeconds) {
                try {
                    ImageIO.read(folderFileArray[i]);
                    faceExpressionFilePath = folderFileArray[i].getPath().replace('\\', '/');
                    faceExpressionFilePath = faceExpressionFilePath.substring(faceExpressionFilePath.indexOf('/') + 1);

                    BorderLayout layout = (BorderLayout) Game.gameFrame.eastComponentPanel.getLayout();
                    Game.gameFrame.eastComponentPanel.remove(layout.getLayoutComponent(BorderLayout.NORTH));
                    gameFacesPanel = new ImagePanel(faceExpressionFilePath, 250, 250);
                    Game.gameFrame.eastComponentPanel.add(gameFacesPanel, BorderLayout.NORTH);
                    Game.gameFrame.backgroundPanel.revalidate();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }

                if (i >= folderFileArray.length - 1) {
                    for (Tile tile : PuzzleBoard.tiles) {
                        tile.setEnabled(false);
                    }
                    JOptionPane.showMessageDialog(Game.gameFrame, "Oh NOOO! You ran out of time!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                }


            }
        }
    }

    private void showVictoryFaces(int i, File[] folderFileArray) {
        int delayTimeMilliSec = 2_000;

        String faceExpressionFilePath;

        try {
            ImageIO.read(folderFileArray[i]);
            faceExpressionFilePath = folderFileArray[i].getPath().replace('\\', '/');
            faceExpressionFilePath = faceExpressionFilePath.substring(faceExpressionFilePath.indexOf('/') + 1);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        BorderLayout layout = (BorderLayout) Game.gameFrame.eastComponentPanel.getLayout();
        Game.gameFrame.eastComponentPanel.remove(layout.getLayoutComponent(BorderLayout.NORTH));
        gameFacesPanel = new ImagePanel(faceExpressionFilePath, 250, 250);
        Game.gameFrame.eastComponentPanel.add(gameFacesPanel, BorderLayout.NORTH);
        Game.gameFrame.backgroundPanel.revalidate();

        try {
            Thread.sleep(delayTimeMilliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

