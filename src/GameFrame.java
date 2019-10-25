import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {

    private static String title = "Tile Master - The Amazing Puzzle Game";
    int width = 900;
    int height = 800; //Calculating height by desired ratio


    public GameFrame(){
        this.setResizable(false);
        Dimension size = new Dimension(width, height);
        this.setTitle(title);
        this.setPreferredSize(size);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setVisible(true);

    }





    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.initializeMainMenu();

    }

    public void initializeMainMenu(){
        //=====================================Adding the background panel=============================================
        JPanel backgroundPanel = new JPanel(new BorderLayout());
        this.add(backgroundPanel);

        //=====================================Making title Label and panel============================================
        JLabel titleLabel = new JLabel("Tile Master - The Puzzle Game");
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 48));
        titleLabel.setForeground(Color.BLACK);

        ImagePanel headerPanel = new ImagePanel("Graphics/Fire Background.jpg", this.width, 100);
        headerPanel.setLayout(new GridBagLayout());
        headerPanel.add(titleLabel, new GridBagConstraints()); //Centers the component


        //=====================================Making top panels======================================================
        JPanel topComponentPanel = new JPanel(new BorderLayout()); //Top component panel for toolbar and header panel

        ImagePanel topButtonPanel = new ImagePanel("Graphics/Dark Metallic Panel.jpeg", this.width, 80);
        topButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        MenuButton timerPlayButton = new MenuButton("Start Timer", "Graphics/Metallic Button.jpg", 200, 70);
        timerPlayButton.addActionListener(this);
        MenuButton shuffleButton = new MenuButton("Shuffle", "Graphics/Metallic Button.jpg", 200, 70);
        shuffleButton.addActionListener(this);

        JLabel chooseSizeLabel = new JLabel("Choose Size: ");
        chooseSizeLabel.setForeground(Color.RED);


        topButtonPanel.add(timerPlayButton);
        topButtonPanel.add(shuffleButton);
        topButtonPanel.add(chooseSizeLabel);



        //=====================Adding top panel components to background panel======================================
        topComponentPanel.add(topButtonPanel, BorderLayout.NORTH);
        topComponentPanel.add(headerPanel, BorderLayout.SOUTH);

        backgroundPanel.add(topComponentPanel, BorderLayout.NORTH);


        //=====================Making and Adding Advertising===========================================================
        JPanel advertisingBanner = new ImagePanel("Graphics/Metal Texture Pattern.jpg", 150, this.height - 260);
        backgroundPanel.add(advertisingBanner, BorderLayout.WEST);



        //=====================Making and Adding Puzzle Board===========================================================
        PuzzleBoard puzzleBoard = new PuzzleBoard("Graphics/Military Anime Girl.jpg", 500,500);
        //Add buttonList to PuzzleBoard class etc, complete game and class

        backgroundPanel.add(puzzleBoard, BorderLayout.CENTER);

        //=====================Making and Adding Right Side Panel===========================================================
        JPanel eastComponentPanel = new ImagePanel("Graphics/Metal Background Image.jpg", 250, 500);
        eastComponentPanel.setLayout(new BorderLayout());

        backgroundPanel.add(eastComponentPanel, BorderLayout.EAST);

        ImagePanel miniPicture = new ImagePanel(puzzleBoard.getImagePath(), 250, 250);
        miniPicture.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        eastComponentPanel.add(miniPicture, BorderLayout.NORTH);

        //Add Timer/move counter panel below mini picture, and add facial expressions at top of advertising banner


        //=====================================Making bottom menu panels===============================================
        ImagePanel menuButtonPanel = new ImagePanel("Graphics/Dark Metallic Panel.jpeg", this.width, 85);
        menuButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        MenuButton numberGame = new MenuButton("Number Game", "Graphics/Metallic Button.jpg", 200, 70);
        numberGame.addActionListener(this);
        MenuButton pictureGame = new MenuButton("Picture Game", "Graphics/Metallic Button.jpg", 200, 70);
        pictureGame.addActionListener(this);
        MenuButton customSizeGame = new MenuButton("Custom Size Game", "Graphics/Metallic Button.jpg", 200, 70);
        customSizeGame.addActionListener(this);
        MenuButton optionsMenu = new MenuButton("Options", "Graphics/Metallic Button.jpg", 200, 70);
        optionsMenu.addActionListener(this);

        menuButtonPanel.add(numberGame);
        menuButtonPanel.add(pictureGame);
        menuButtonPanel.add(customSizeGame);
        menuButtonPanel.add(optionsMenu);

        backgroundPanel.add(menuButtonPanel, BorderLayout.SOUTH);


        this.setVisible(true);
    }



    public void startNewGame(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
