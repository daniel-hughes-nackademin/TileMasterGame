import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameOverGirl implements Runnable {

    Thread gameOverThread;
    ImagePanel gameFacesPanel;
    boolean isVictory;
    boolean isGameOver = false;


    public GameOverGirl(boolean isVictory) {
        this.isVictory = isVictory;
    }

    public synchronized void showGameOverGirl() {
        OptionsMenu.isActivatedGameOverMode = true;
        gameOverThread = new Thread(this);
        gameOverThread.start();
    }

    public synchronized void stopGameOverGirl() {
        OptionsMenu.isActivatedGameOverMode = false;
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
        while (OptionsMenu.isActivatedGameOverMode) {

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
                if(!isGameOver)
                    showGameFaces(folderFileArray);
            }
        }
    }

    private void showGameFaces(File[] folderFileArray) {


        int totalSeconds = Game.gameFrame.seconds + Game.gameFrame.minutes * 60;
        String faceExpressionFilePath;
        for (int i = 0; i < folderFileArray.length; i++) {
            if ((int)(OptionsMenu.phaseDelay * i) == totalSeconds) {
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

                if (totalSeconds >= OptionsMenu.timeLimit) {
                    for (Tile tile : PuzzleBoard.tiles) {
                        tile.setEnabled(false);
                    }
                    Game.gameFrame.chronometer.stop();
                    Game.gameFrame.timerPauseButton.setText("New Game");
                    isGameOver = true;
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

