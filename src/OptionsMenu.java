import javax.swing.*;
import java.awt.*;

public class OptionsMenu{

    public static void showOptions(){
        Game.gameFrame.removeCenterComponent();
        JPanel menuComponents = new JPanel(new BorderLayout());

        ImagePanel topBottomPanel = new ImagePanel("Graphics/Metal Texture Pattern.jpg", 500, 100);
        ImagePanel menuPanel = new ImagePanel("Graphics/Metal Background Image.jpg", 400, 300);
        ImagePanel optionButtonsPanel = new ImagePanel("Graphics/Wooden Background.jpg", 100, 300);


        menuComponents.add(topBottomPanel, BorderLayout.NORTH);
        menuComponents.add(topBottomPanel, BorderLayout.SOUTH);
        menuComponents.add(menuPanel, BorderLayout.CENTER);
        menuComponents.add(optionButtonsPanel, BorderLayout.EAST);


        Game.gameFrame.backgroundPanel.add(menuComponents);
        Game.gameFrame.backgroundPanel.revalidate();




    }
}
