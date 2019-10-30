import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameOverGirl implements Runnable {

    Thread gameOverThread;
    ImagePanel gameFacesPanel;
    boolean isVictory;


    public GameOverGirl(boolean isVictory) {
        this.isVictory = isVictory;
    }

    public synchronized void showGameOverGirl() {
        OptionsMenu.isActivatedGameFaces = true;
        System.out.println("showing");
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

        BorderLayout layout = (BorderLayout)Game.gameFrame.eastComponentPanel.getLayout();
        Game.gameFrame.eastComponentPanel.remove(layout.getLayoutComponent(BorderLayout.NORTH));
        if(Game.gameFrame.isImageGame)
            Game.gameFrame.miniPicture = new ImagePanel(Game.gameFrame.imagePath, 250, 250);
        else
            Game.gameFrame.miniPicture = new ImagePanel("Graphics/Sort The Numbers.jpg", 250, 250);
        Game.gameFrame.eastComponentPanel.add(Game.gameFrame.miniPicture, BorderLayout.NORTH);
        Game.gameFrame.backgroundPanel.revalidate();
    }


    @Override
    public void run() {
        System.out.println("running");
        File folder;
        File[] folderFileArray;
        int delayTimeMilliSec;
        int i = 0;
        while (OptionsMenu.isActivatedGameFaces) {

            if (isVictory) {
                folder = new File("src/Victory Faces");
                delayTimeMilliSec = 2_000;
            } else {
                folder = new File("src/Game Over Faces");
                delayTimeMilliSec = 10_000;
            }

            folderFileArray = folder.listFiles();
            String faceExpressionFilePath;


            try {
                ImageIO.read(folderFileArray[i]);
                faceExpressionFilePath = folderFileArray[i].getPath().replace('\\', '/');
                faceExpressionFilePath = faceExpressionFilePath.substring(faceExpressionFilePath.indexOf('/') + 1);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            } finally {
                i++;
                if (i == folderFileArray.length){
                    i = 0;
                    if(!isVictory){
                        Game.gameFrame.chronometer.stop();
                        for (Tile tile: PuzzleBoard.tiles) {
                            tile.setEnabled(false);
                        }
                        JOptionPane.showMessageDialog(Game.gameFrame, "You ran out of time!", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
            }
            BorderLayout layout = (BorderLayout)Game.gameFrame.eastComponentPanel.getLayout();
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
}
