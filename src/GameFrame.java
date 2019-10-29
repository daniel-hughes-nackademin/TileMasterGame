import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameFrame extends JFrame{

    private static String title = "Tile Master - The Amazing Puzzle Game";
    int width = 900;
    int height = 800; //Calculating height by desired ratio
    JLabel showSizeLabel;
    JSlider sizeSlider;
    int gridSize = 4;
    PuzzleBoard puzzleBoard;
    JPanel backgroundPanel;
    ImagePanel advertisingBanner;
    JPanel eastComponentPanel;
    ImagePanel miniPicture;
    String imagePath = "Graphics/Military Anime Girl.jpg";
    boolean isImageGame = true;
    AdvertisingManager advertisingManager = new AdvertisingManager();
    boolean isShowingAdvertising = true;

    public GameFrame(){
        this.setResizable(false);
        Dimension size = new Dimension(width, height);
        this.setTitle(title);
        this.setPreferredSize(size);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }



    public void initializeMainMenu(){
        //=====================================Adding the background panel=============================================
        backgroundPanel = new JPanel(new BorderLayout());
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

        MenuButton timerPlayButton = new MenuButton("Start Timer", "Graphics/Metallic Button.jpg");
        timerPlayButton.addActionListener(e -> {});
        MenuButton shuffleButton = new MenuButton("Shuffle", "Graphics/Metallic Button.jpg");
        shuffleButton.addActionListener(e -> {
            Collections.shuffle(PuzzleBoard.tiles);
            refreshPuzzleBoard();
        });


        ImagePanel scalingPanel = new ImagePanel("Graphics/Wooden Background.jpg", 350, 60);
        scalingPanel.setLayout(new FlowLayout());
        scalingPanel.setBorder(BorderFactory.createLoweredBevelBorder());


        //=====================================Making Slider and Labels======================================================
        JLabel chooseSizeLabel = new JLabel();
        chooseSizeLabel.setText("Choose Size: ");
        chooseSizeLabel.setPreferredSize(new Dimension(120, 50));
        chooseSizeLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        chooseSizeLabel.setForeground(Color.WHITE);
        scalingPanel.add(chooseSizeLabel);

        sizeSlider = new JSlider(JSlider.HORIZONTAL, 3,7, gridSize);
        sizeSlider.setPreferredSize(new Dimension(150, 30));
        sizeSlider.setMinorTickSpacing(1);
        sizeSlider.setPaintTicks(true);
        sizeSlider.addChangeListener(e -> {
            showSizeLabel.setText("  " + sizeSlider.getValue());
            gridSize = sizeSlider.getValue();

            if(isImageGame)
                startNewPictureGame();
            else
                startNewNumberGame();
        });
        scalingPanel.add(sizeSlider);

        showSizeLabel = new JLabel("  " + sizeSlider.getValue());
        showSizeLabel.setFont(new Font("Georgia", Font.BOLD, 32));
        showSizeLabel.setForeground(Color.WHITE);
        scalingPanel.add(showSizeLabel);


        //=====================================Adding top components to panel======================================================
        topButtonPanel.add(timerPlayButton);
        topButtonPanel.add(shuffleButton);
        topButtonPanel.add(new JLabel("                              "));
        topButtonPanel.add(scalingPanel);




        //=====================Adding top panel components to background panel======================================
        topComponentPanel.add(topButtonPanel, BorderLayout.NORTH);
        topComponentPanel.add(headerPanel, BorderLayout.SOUTH);

        backgroundPanel.add(topComponentPanel, BorderLayout.NORTH);


        //=====================Making and Adding Advertising===========================================================
        if (isShowingAdvertising){
            advertisingBanner = new ImagePanel("Graphics/Metal Texture Pattern.jpg", 150, this.height - 260);
            backgroundPanel.add(advertisingBanner, BorderLayout.WEST);
            advertisingManager.showAdvertising();
        }
        else{
            advertisingBanner = new ImagePanel("Graphics/Metal Texture Pattern.jpg", 150, this.height - 260);
            backgroundPanel.add(advertisingBanner, BorderLayout.WEST);
        }




        //=====================Making and Adding Right Side Panel===========================================================
        eastComponentPanel = new ImagePanel("Graphics/Metal Background Image.jpg", 250, 500);
        eastComponentPanel.setLayout(new BorderLayout());

        backgroundPanel.add(eastComponentPanel, BorderLayout.EAST);

        miniPicture = new ImagePanel(imagePath, 250, 250);
        eastComponentPanel.add(miniPicture, BorderLayout.NORTH);

        //Add Timer/move counter panel and facial expressions below mini picture, add advertising banner

        //=====================Making and Adding Puzzle Board===========================================================
        puzzleBoard = new PuzzleBoard(imagePath, gridSize);
        automaticallySwapTilesRandomly();
        backgroundPanel.add(puzzleBoard, BorderLayout.CENTER);

        //=====================================Making bottom menu panels===============================================
        ImagePanel menuButtonPanel = new ImagePanel("Graphics/Dark Metallic Panel.jpeg", this.width, 85);
        menuButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        MenuButton numberGame = new MenuButton("Number Game", "Graphics/Metallic Button.jpg");
        numberGame.addActionListener(e -> startNewNumberGame());
        MenuButton pictureGame = new MenuButton("Picture Game", "Graphics/Metallic Button.jpg");
        pictureGame.addActionListener(e -> startNewPictureGame());
        MenuButton customPictureGame = new MenuButton("Choose Picture", "Graphics/Metallic Button.jpg");
        customPictureGame.addActionListener(e -> {
            if(chooseCustomFile())
            startNewPictureGame();
        });
        MenuButton optionsMenu = new MenuButton("Options", "Graphics/Metallic Button.jpg");
        optionsMenu.addActionListener(e -> {
            OptionsMenu.showOptions();
        });

        menuButtonPanel.add(numberGame);
        menuButtonPanel.add(pictureGame);
        menuButtonPanel.add(customPictureGame);
        menuButtonPanel.add(optionsMenu);

        backgroundPanel.add(menuButtonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }


    public void refreshPuzzleBoard() {
        removeCenterComponent();
        puzzleBoard = new PuzzleBoard(gridSize);
        backgroundPanel.add(puzzleBoard, BorderLayout.CENTER);
        this.revalidate();
    }


    public void startNewPictureGame(){
        isImageGame = true;
        removeCenterComponent();
        puzzleBoard = new PuzzleBoard(imagePath, gridSize);
        automaticallySwapTilesRandomly();
        backgroundPanel.add(puzzleBoard, BorderLayout.CENTER);

        eastComponentPanel.remove(miniPicture);
        miniPicture = new ImagePanel(imagePath, 250, 250);
        eastComponentPanel.add(miniPicture, BorderLayout.NORTH);
        this.revalidate();
    }


    public void startNewNumberGame(){
        isImageGame = false;
        int iconWidth = puzzleBoard.getWidth()/gridSize;

        ImageIcon icon = ImageTool.makeScaledImageIcon("Graphics/Number Button.jpg", iconWidth, iconWidth);
        backgroundPanel.remove(puzzleBoard);
        puzzleBoard = new PuzzleBoard(icon, gridSize);
        automaticallySwapTilesRandomly();
        backgroundPanel.add(puzzleBoard);

        eastComponentPanel.remove(miniPicture);
        miniPicture = new ImagePanel("Graphics/Sort The Numbers.jpg", 250, 250);
        eastComponentPanel.add(miniPicture, BorderLayout.NORTH);
        this.revalidate();
    }

    private void automaticallySwapTilesRandomly() {
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            int randomIndex = random.nextInt(PuzzleBoard.tiles.size());
                puzzleBoard.swapTiles(PuzzleBoard.tiles.get(randomIndex), randomIndex);
                System.out.println(PuzzleBoard.tiles.get(randomIndex));
                puzzleBoard = new PuzzleBoard(gridSize);
        }
    }

    protected void removeCenterComponent() {
        BorderLayout layout = (BorderLayout)backgroundPanel.getLayout();
        backgroundPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
    }

    public boolean chooseCustomFile(){
        boolean isCorrectFile = false;

        JFileChooser fileChooser = new JFileChooser();
        int choice = fileChooser.showDialog(Game.gameFrame, "New Game");

        if (choice == JFileChooser.APPROVE_OPTION){
            File chosenFile = fileChooser.getSelectedFile();

            try {
                FileInputStream inputStream = new FileInputStream(chosenFile);
                if(ImageIO.read(inputStream) == null){
                    JOptionPane.showMessageDialog(Game.gameFrame, "The chosen file must be an image file", "Tile Master - Incorrect File Type", JOptionPane.INFORMATION_MESSAGE);
                    return isCorrectFile;
                }
                isCorrectFile = true;

                String filePath = chosenFile.getPath();
                this.imagePath = filePath.replace('\\', '/');

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        return isCorrectFile;
    }
}
