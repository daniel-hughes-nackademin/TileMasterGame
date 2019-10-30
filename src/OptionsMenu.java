import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OptionsMenu {

    static AdvertisingManager advertisingManager = new AdvertisingManager();
    static GameOverGirl gameOverGirl = new GameOverGirl(false);
    static boolean isShowingAdvertising = true;
    static boolean isActivatedGameFaces = false;
    static int phaseDelay = 10;

    public static void showOptions() {

        Game.gameFrame.chronometer.stop();
        Game.gameFrame.timerPauseButton.setText("Resume");

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

        JLabel gameOverOptionLabel = new JLabel("Game Over Mode");
        gameOverOptionLabel.setFont(georgia);

        JLabel phaseDelayLabel = new JLabel("Phase Delay");
        phaseDelayLabel.setFont(georgia);


        gridOptionsPanel.add(advertisingLabel);
        gridOptionsPanel.add(gameOverOptionLabel);
        gridOptionsPanel.add(phaseDelayLabel);


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
        if (isActivatedGameFaces) {
            gameOverOptionButton.setText("ON");
        }
        gameOverOptionButton.addActionListener(e -> {
            if (isActivatedGameFaces) {
                gameOverGirl.stopGameOverGirl();
            } else {
                gameOverGirl.showGameOverGirl();
            }

            showOptions();
        });

        buttonPanel.add(gameOverOptionButton);

        JFormattedTextField phaseDelayField = new JFormattedTextField();
        int number = phaseDelay;
        phaseDelayField.setValue(number);
        phaseDelayField.setColumns(2);
        phaseDelayField.setFont(georgia);
        phaseDelayField.setHorizontalAlignment(JTextField.CENTER);
        phaseDelayField.setBounds(32, 160, 50, 50);
        phaseDelayField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if (e.getSource() == phaseDelayField) {

                    phaseDelay = ((Number)phaseDelayField.getValue()).intValue();
                    System.out.println(phaseDelay);
                }
            }
        });

        /*phaseDelayField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String input = ((JTextField)e.getSource()).getText() + e.getKeyChar();

                System.out.println(input);
                boolean isCorrectInput = true;
                for (int i = 0; i < input.length(); i++) {
                    char c = input.charAt(i);
                    if (!Character.isDigit(c))
                        isCorrectInput = false;
                }
                if(Integer.parseInt(input) <= 0)
                    isCorrectInput = false;
                if (isCorrectInput){
                    System.out.println("Correct: \n" + input);

                    int test = Integer.parseInt(input);
                    System.out.println(test);
                    phaseDelay = test;
                }
            }
        });*/
        buttonPanel.add(phaseDelayField);

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
