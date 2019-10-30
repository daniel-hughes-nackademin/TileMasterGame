import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

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
        showAdsFromWebAddressList();
        //showAdsFromFileFolder();
    }

    private void showAdsFromWebAddressList() {
        List<BufferedImage> webAdvertisingList = new ArrayList<>();
        try {
            BufferedImage img = ImageIO.read(new URL("https://www.shakeout.org/2008/downloads/ShakeOut_BannerAds_GetReady_160x600_v8.gif"));
            webAdvertisingList.add(img);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAdsFromFileFolder(){
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
