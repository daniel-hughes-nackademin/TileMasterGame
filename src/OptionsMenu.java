import javax.swing.*;
import java.awt.*;

public class OptionsMenu{

    public static void showOptions(){
        Game.gameFrame.removeCenterComponent();
        JPanel menuComponents = new JPanel(new BorderLayout());

        //======================================Main layout panels===================================================
        ImagePanel topBottomPanel = new ImagePanel("Graphics/Metal Texture Pattern.jpg", 500, 100);
        JPanel centerPanel = new JPanel(new BorderLayout());
        ImagePanel woodenPanel = new ImagePanel("Graphics/Wooden Background.jpg", 100, 300);

        ImagePanel gridOptionsPanel = new ImagePanel("Graphics/Metal Background Image.jpg", 200, 300);
        gridOptionsPanel.setLayout(new GridLayout(4,1));

        ImagePanel buttonPanel = new ImagePanel("Graphics/Dark Metallic Panel.jpeg", 100, 300);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));


        //=====================================Options Labels==================================================
        Font georgia = new Font("Georgia", Font.BOLD, 20);

        JLabel advertisingLabel = new JLabel("Advertising");
        advertisingLabel.setFont(georgia);

        JLabel gameOverGirl = new JLabel("Game Over Mode");
        gameOverGirl.setFont(georgia);



        gridOptionsPanel.add(advertisingLabel);
        gridOptionsPanel.add(gameOverGirl);

        //Filling out the space a bit
        JLabel emptyLabel = new JLabel(" ");
        emptyLabel.setFont(georgia);
        gridOptionsPanel.add(emptyLabel);
        emptyLabel = new JLabel(" ");
        emptyLabel.setFont(georgia);
        gridOptionsPanel.add(emptyLabel);

        //================================Options Buttons============================================================
            MenuButton advertisingButton = new MenuButton("ON", "Graphics/Metallic Button.jpg", 70, 55);
            advertisingButton.setFont(new Font("Georgia", Font.BOLD, 14));

            if (!Game.gameFrame.isShowingAdvertising)
                advertisingButton.setText("OFF");

            advertisingButton.addActionListener(e -> {
                if (Game.gameFrame.isShowingAdvertising){
                    Game.gameFrame.isShowingAdvertising = false;
                    Game.gameFrame.advertisingManager.stopAdvertising();
                }
                else {
                    Game.gameFrame.isShowingAdvertising = true;
                    Game.gameFrame.advertisingManager.showAdvertising();
                }

                showOptions();
            });

            buttonPanel.add(advertisingButton);

            MenuButton gameOverOptionButton = new MenuButton("OFF", "Graphics/Metallic Button.jpg", 70, 55);
            gameOverOptionButton.setFont(new Font("Georgia", Font.BOLD, 14));
            if (Game.gameFrame.isActivatedGameOver){
                gameOverOptionButton.setText("ON");
            }
            gameOverOptionButton.addActionListener(e -> {
                if (Game.gameFrame.isActivatedGameOver){
                    Game.gameFrame.isActivatedGameOver = false;
                    gameOverOptionButton.setText("OFF");
                    //Turn off Game Over Feature
                }
                else{
                    Game.gameFrame.isActivatedGameOver = true;
                    gameOverOptionButton.setText("ON");
                    //Turn on Game Over Feature
                }
            });

            buttonPanel.add(gameOverOptionButton);

        //==============================Adding all the components to the menuPanel====================================
        centerPanel.add(gridOptionsPanel, BorderLayout.WEST);
        centerPanel.add(buttonPanel, BorderLayout.EAST);

        menuComponents.add(topBottomPanel, BorderLayout.NORTH);
        menuComponents.add(woodenPanel, BorderLayout.WEST);
        menuComponents.add(centerPanel, BorderLayout.CENTER);
        woodenPanel = new ImagePanel("Graphics/Wooden Background.jpg", 100, 300);
        menuComponents.add(woodenPanel, BorderLayout.EAST);
        topBottomPanel = new ImagePanel("Graphics/Metal Texture Pattern.jpg", 500, 100);
        menuComponents.add(topBottomPanel, BorderLayout.SOUTH);


        Game.gameFrame.backgroundPanel.add(menuComponents);
        Game.gameFrame.backgroundPanel.revalidate();




    }
}
