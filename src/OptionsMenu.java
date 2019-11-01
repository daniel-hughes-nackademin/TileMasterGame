import javax.swing.*;
import java.awt.*;

public class OptionsMenu {
    static boolean isShowingAdvertising = true;
    static boolean isActivatedGameOverMode = false;
    static AdvertisingManager advertisingManager = new AdvertisingManager();
    static GameOverGirl gameOverGirl = new GameOverGirl(false);
    static int timeLimit = 90;
    static double phases = 8;
    static double phaseDelay = timeLimit/phases;

    public static void showOptions() {


        Game.gameFrame.chronometer.stop();
        if (isActivatedGameOverMode && gameOverGirl.isGameOver || Game.gameFrame.isCompletedPuzzle){
            System.out.println(Game.gameFrame.isCompletedPuzzle);
            Game.gameFrame.timerPauseButton.setText("New Game");
        }
        else{
            Game.gameFrame.timerPauseButton.setText("Resume");
        }

        Game.gameFrame.removeCenterComponent();
        JPanel menuComponents = new JPanel(new BorderLayout());

        //======================================Main layout panels===================================================
        ImagePanel topBottomPanel = new ImagePanel("Graphics/Metal Texture Pattern.jpg", 500, 100);
        JPanel centerPanel = new JPanel(new BorderLayout());
        ImagePanel woodenPanel = new ImagePanel("Graphics/Wooden Background.jpg", 100, 300);

        ImagePanel gridOptionsPanel = new ImagePanel("Graphics/Metal Background Image.jpg", 200, 300);
        gridOptionsPanel.setLayout(new GridLayout(4, 1));

        ImagePanel buttonPanel = new ImagePanel("Graphics/Dark Metallic Panel.jpeg", 100, 300);
        buttonPanel.setLayout(null);


        //=====================================Options Labels==================================================
        Font georgia = new Font("Georgia", Font.BOLD, 20);

        JLabel advertisingLabel = new JLabel("Advertising");
        advertisingLabel.setFont(georgia);

        JLabel gameOverOptionLabel = new JLabel("Challenge Mode");
        gameOverOptionLabel.setFont(georgia);

        JLabel timeLimitLabel = new JLabel("Time Limit (sec)");
        timeLimitLabel.setFont(georgia);


        gridOptionsPanel.add(advertisingLabel);
        gridOptionsPanel.add(gameOverOptionLabel);
        gridOptionsPanel.add(timeLimitLabel);


        //Filling out the space a bit
        JLabel emptyLabel = new JLabel(" ");
        emptyLabel.setFont(georgia);
        gridOptionsPanel.add(emptyLabel);

        //================================Options Buttons============================================================

        MenuButton advertisingButton = new MenuButton("ON", "Graphics/Metallic Button.jpg", 80, 55);
        advertisingButton.setFont(new Font("Georgia", Font.BOLD, 14));
        advertisingButton.setBounds(17, 12, 80, 55);

        if (!isShowingAdvertising) {
            advertisingButton.setText("OFF");
        }

        advertisingButton.addActionListener(e -> {
            if (isShowingAdvertising) {
                advertisingManager.stopAdvertising();
            } else {
                advertisingManager.showAdvertising();
            }

            showOptions();
        });

        buttonPanel.add(advertisingButton);

        MenuButton gameOverOptionButton = new MenuButton("OFF", "Graphics/Metallic Button.jpg", 80, 55);
        gameOverOptionButton.setFont(new Font("Georgia", Font.BOLD, 14));
        gameOverOptionButton.setBounds(17, 85, 80, 55);
        if (isActivatedGameOverMode) {
            gameOverOptionButton.setText("ON");

        }
        gameOverOptionButton.addActionListener(e -> {
            if (isActivatedGameOverMode) {
                gameOverGirl.stopGameOverGirl();

            } else {
                gameOverGirl.showGameOverGirl();
            }

            showOptions();
        });

        buttonPanel.add(gameOverOptionButton);


        if(isActivatedGameOverMode) {
            JFormattedTextField timeLimitField = new JFormattedTextField();
            int number = timeLimit;
            timeLimitField.setValue(number);
            timeLimitField.setColumns(2);
            timeLimitField.setFont(georgia);
            timeLimitField.setHorizontalAlignment(JTextField.CENTER);
            timeLimitField.setBounds(32, 160, 50, 50);

            timeLimitField.addPropertyChangeListener(e -> {
                if (e.getSource() == timeLimitField) {

                    timeLimit = ((Number) timeLimitField.getValue()).intValue();
                    phaseDelay = timeLimit / phases;
                }
            });
            buttonPanel.add(timeLimitField);
        }
        else {
            JTextField timeLimitOffField = new JTextField("OFF");
            timeLimitOffField.setEditable(false);
            timeLimitOffField.setFont(georgia);
            timeLimitOffField.setHorizontalAlignment(JTextField.CENTER);
            timeLimitOffField.setBounds(32, 160, 50, 50);
            buttonPanel.add(timeLimitOffField);
        }
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
