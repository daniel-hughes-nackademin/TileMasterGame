import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AdvertisingManager implements Runnable {
    Thread advertisingThread;

    public synchronized void showAdvertising() {
        OptionsMenu.isShowingAdvertising = true;
        advertisingThread = new Thread(this);
        advertisingThread.start();
    }

    public synchronized void stopAdvertising() {
        OptionsMenu.isShowingAdvertising = false;
        try {
            advertisingThread.interrupt();
            advertisingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BorderLayout layout = (BorderLayout)Game.gameFrame.backgroundPanel.getLayout();
        Game.gameFrame.backgroundPanel.remove(layout.getLayoutComponent(BorderLayout.WEST));
        Game.gameFrame.advertisingBanner = new ImagePanel("Graphics/Metal Texture Pattern.jpg", 150, Game.gameFrame.height - 260);
        Game.gameFrame.backgroundPanel.add(Game.gameFrame.advertisingBanner, BorderLayout.WEST);
        Game.gameFrame.backgroundPanel.revalidate();
    }

    @Override
    public void run() {
        File folder = new File("src/AdvertisingImages");
        File[] folderFileArray = folder.listFiles();
        int i = 0;
        while (OptionsMenu.isShowingAdvertising) {
            String advertisingImagePath;


            try {
                ImageIO.read(folderFileArray[i]);
                advertisingImagePath = folderFileArray[i].getPath().replace('\\', '/');
                advertisingImagePath = advertisingImagePath.substring(advertisingImagePath.indexOf('/') + 1);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            } finally {
                i++;
                if (i == folderFileArray.length)
                    i = 0;
            }
            BorderLayout layout = (BorderLayout)Game.gameFrame.backgroundPanel.getLayout();
            Game.gameFrame.backgroundPanel.remove(layout.getLayoutComponent(BorderLayout.WEST));
            Game.gameFrame.advertisingBanner = new ImagePanel(advertisingImagePath, 150, 540);
            Game.gameFrame.backgroundPanel.add(Game.gameFrame.advertisingBanner, BorderLayout.WEST);
            Game.gameFrame.backgroundPanel.revalidate();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
