import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AdvertisingManager implements Runnable {
    Thread advertisingThread;

    public AdvertisingManager() {
        advertisingThread = new Thread(this);
    }

    public void showAdvertising() {
        advertisingThread.start();
    }

    public void stopAdvertising() {
        advertisingThread.interrupt();
    }

    @Override
    public void run() {
        File folder = new File("src/AdvertisingImages");
        File[] folderFileArray = folder.listFiles();
        int i = 0;
        while (!Thread.interrupted()) {
            String advertisingImagePath;


            try {
                ImageIO.read(folderFileArray[i]);
                advertisingImagePath = folderFileArray.toString();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            } finally {
                i++;
                if (i == folderFileArray.length)
                    i = 0;
            }
            System.out.println(advertisingImagePath);
            Game.gameFrame.backgroundPanel.remove(Game.gameFrame.advertisingBanner);
            Game.gameFrame.advertisingBanner = new ImagePanel(advertisingImagePath, 150, Game.gameFrame.height - 260);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
