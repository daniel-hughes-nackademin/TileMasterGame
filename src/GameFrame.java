import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class GameFrame extends JFrame {

    private static String title = "Tile Master - The Amazing Puzzle Game";
    int width = 900;
    int height = 800;
    int gridSize = 4;
    int moveCount = 0;
    JLabel moveCountLabel;
    JLabel timeLabel;


    JLabel showSizeLabel;
    JSlider sizeSlider;
    PuzzleBoard puzzleBoard;
    JPanel backgroundPanel;
    ImagePanel advertisingBanner;
    JPanel eastComponentPanel;
    ImagePanel westComponentPanel;
    ImagePanel miniPicture;

    File lastDirectory = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures");
    String imagePath = "Graphics/Military Anime Girl.jpg";
    boolean isImageGame = true;

    Timer chronometer;
    int seconds, minutes, hours;
    MenuButton timerPauseButton;

    public GameFrame() {
        this.setResizable(false);
        Dimension size = new Dimension(width, height);
        this.setTitle(title);
        this.setPreferredSize(size);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }


    public void initializeMainMenu() {
        //=====================================Adding the background panel=============================================
        backgroundPanel = new JPanel(new BorderLayout());
        this.add(backgroundPanel);

        //=====================================Making title Label and panel============================================
        JLabel titleLabel = new JLabel("Tile Master - The Puzzle Game");
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 48));
        titleLabel.setForeground(Color.BLACK);

        ImagePanel headerPanel = new ImagePanel("Graphics/Fire Background.jpg", this.width, 100);
        headerPanel.setLayout(new GridBagLayout());
        headerPanel.add(titleLabel, new GridBagConstraints()); //Centers Label


        //=====================================Making top panels======================================================
        JPanel topComponentPanel = new JPanel(new BorderLayout()); //Top component panel for toolbar and header panel

        ImagePanel topButtonPanel = new ImagePanel("Graphics/Dark Metallic Panel.jpeg", this.width, 80);
        topButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        timerPauseButton = new MenuButton("Pause", "Graphics/Metallic Button.jpg");
        timerPauseButton.addActionListener(e -> {
            if (timerPauseButton.getText() == "Pause") {
                chronometer.stop();
                timerPauseButton.setText("Resume");

                removeCenterComponent();
                JLabel pauseLabel = new JLabel("PAUSED");
                pauseLabel.setFont(new Font("Georgia", Font.BOLD, 60));
                pauseLabel.setForeground(Color.WHITE);

                JPanel pausePanel = new JPanel(new GridBagLayout());
                pausePanel.setBackground(Color.BLACK);
                pausePanel.add(pauseLabel, new GridBagConstraints()); //Centers Label
                backgroundPanel.add(pausePanel, BorderLayout.CENTER);

            } else {
                if(!(OptionsMenu.isActivatedGameOverMode && OptionsMenu.gameOverGirl.isGameOver)){
                    chronometer.start();
                }
                timerPauseButton.setText("Pause");
                refreshPuzzleBoard();
                puzzleBoard.checkWinCondition();
            }
        });

        MenuButton shuffleButton = new MenuButton("Shuffle", "Graphics/Metallic Button.jpg");
        shuffleButton.addActionListener(e -> {
            if (isImageGame)
                startNewPictureGame();
            else
                startNewNumberGame();
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

        sizeSlider = new JSlider(JSlider.HORIZONTAL, 3, 7, gridSize);
        sizeSlider.setPreferredSize(new Dimension(150, 30));
        sizeSlider.setMinorTickSpacing(1);
        sizeSlider.setPaintTicks(true);
        sizeSlider.addChangeListener(e -> {
            showSizeLabel.setText("  " + sizeSlider.getValue());
            gridSize = sizeSlider.getValue();

            if (isImageGame)
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
        topButtonPanel.add(timerPauseButton);
        topButtonPanel.add(shuffleButton);
        topButtonPanel.add(new JLabel("                              "));
        topButtonPanel.add(scalingPanel);


        //=====================Adding top panel components to background panel======================================
        topComponentPanel.add(topButtonPanel, BorderLayout.NORTH);
        topComponentPanel.add(headerPanel, BorderLayout.SOUTH);

        backgroundPanel.add(topComponentPanel, BorderLayout.NORTH);


        //=====================Making and Adding Advertising===========================================================
        westComponentPanel = new ImagePanel("Graphics/Metal Texture Pattern.jpg", 150, this.height - 260);
        westComponentPanel.setLayout(new BorderLayout());

        if (OptionsMenu.isActivatedGameOverMode) {
            OptionsMenu.gameOverGirl.showGameOverGirl();
        }

        if (OptionsMenu.isShowingAdvertising) {
            OptionsMenu.advertisingManager.showAdvertising();
        }

        backgroundPanel.add(westComponentPanel, BorderLayout.WEST);

        //=====================Making and Adding Right Side Panel===========================================================
        Font georgia = new Font("Georgia", Font.BOLD, 24);

        eastComponentPanel = new ImagePanel("Graphics/Metal Background Image.jpg", 250, 500);
        eastComponentPanel.setLayout(new BorderLayout());

        miniPicture = new ImagePanel(imagePath, 250, 250);
        ImagePanel moveCountAndTimerPanel = new ImagePanel("Graphics/Metal Background Image.jpg", 250, 240);
        moveCountAndTimerPanel.setLayout(new GridLayout(2, 1));

        timeLabel = new JLabel("Time: " + minutes + " : " + seconds);
        timeLabel.setFont(georgia);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        if (hours > 0)
            timeLabel.setText("Time: " + hours + " : " + minutes + " : " + seconds);

        chronometer = new Timer(1000, e -> {
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                minutes++;
                if (minutes == 60) {
                    minutes = 0;
                    hours++;
                }
            }
            timeLabel.setText("Time: " + minutes + " : " + seconds);
            if (hours > 0)
                timeLabel.setText("Time: " + hours + " : " + minutes + " : " + seconds);
        });
        moveCountAndTimerPanel.add(timeLabel);

        moveCountLabel = new JLabel("Moves: " + moveCount);
        moveCountLabel.setFont(georgia);
        moveCountLabel.setHorizontalAlignment(JLabel.CENTER);
        moveCountAndTimerPanel.add(moveCountLabel);


        eastComponentPanel.add(miniPicture, BorderLayout.NORTH);
        eastComponentPanel.add(moveCountAndTimerPanel, BorderLayout.SOUTH);
        backgroundPanel.add(eastComponentPanel, BorderLayout.EAST);

        if (OptionsMenu.isActivatedGameOverMode)
            OptionsMenu.gameOverGirl.showGameOverGirl();

        //=====================Making and Adding Puzzle Board===========================================================
        puzzleBoard = new PuzzleBoard(imagePath, gridSize);
        automaticallySwapTilesRandomly();
        backgroundPanel.add(puzzleBoard, BorderLayout.CENTER);

        resetMoveCounter();

        //=====================================Making bottom menu panels===============================================
        ImagePanel menuButtonPanel = new ImagePanel("Graphics/Dark Metallic Panel.jpeg", this.width, 85);
        menuButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        MenuButton numberGame = new MenuButton("Number Game", "Graphics/Metallic Button.jpg");
        numberGame.addActionListener(e -> startNewNumberGame());
        MenuButton pictureGame = new MenuButton("Picture Game", "Graphics/Metallic Button.jpg");
        pictureGame.addActionListener(e -> startNewPictureGame());
        MenuButton customPictureGame = new MenuButton("Choose Picture", "Graphics/Metallic Button.jpg");
        customPictureGame.addActionListener(e -> {
            chooseCustomFile();
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

        chronometer.start();

        this.setVisible(true);

    }

    void resetMoveCounter() {
        moveCount = 0;
        moveCountLabel.setText("Moves: " + moveCount);
    }


    public void refreshPuzzleBoard() {
        removeCenterComponent();
        puzzleBoard = new PuzzleBoard(gridSize);
        backgroundPanel.add(puzzleBoard, BorderLayout.CENTER);
        this.revalidate();
    }


    public void startNewPictureGame() {
        resetTimer();

        isImageGame = true;
        removeCenterComponent();
        puzzleBoard = new PuzzleBoard(imagePath, gridSize);
        automaticallySwapTilesRandomly();
        backgroundPanel.add(puzzleBoard, BorderLayout.CENTER);

        eastComponentPanel.remove(miniPicture);
        miniPicture = new ImagePanel(imagePath, 250, 250);
        eastComponentPanel.add(miniPicture, BorderLayout.NORTH);
        if (OptionsMenu.isActivatedGameOverMode) {
            OptionsMenu.gameOverGirl.stopGameOverGirl();
            OptionsMenu.gameOverGirl = new GameOverGirl(false);
            OptionsMenu.gameOverGirl.showGameOverGirl();
        }
        Game.gameFrame.resetMoveCounter();
        this.revalidate();
    }


    public void startNewNumberGame() {
        resetTimer();

        isImageGame = false;
        removeCenterComponent();
        int iconWidth = puzzleBoard.getWidth() / gridSize;

        ImageIcon icon = ImageTool.makeScaledImageIcon("Graphics/Number Button.jpg", iconWidth, iconWidth);
        puzzleBoard = new PuzzleBoard(icon, gridSize);
        automaticallySwapTilesRandomly();
        backgroundPanel.add(puzzleBoard);

        eastComponentPanel.remove(miniPicture);
        miniPicture = new ImagePanel("Graphics/Sort The Numbers.jpg", 250, 250);
        eastComponentPanel.add(miniPicture, BorderLayout.NORTH);
        if (OptionsMenu.isActivatedGameOverMode) {
            OptionsMenu.gameOverGirl.stopGameOverGirl();
            OptionsMenu.gameOverGirl = new GameOverGirl(false);
            OptionsMenu.gameOverGirl.showGameOverGirl();
        }
        Game.gameFrame.resetMoveCounter();
        this.revalidate();
    }

    private void automaticallySwapTilesRandomly() {
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            int randomIndex = random.nextInt(PuzzleBoard.tiles.size());
            puzzleBoard.swapTiles(PuzzleBoard.tiles.get(randomIndex), randomIndex);
            puzzleBoard = new PuzzleBoard(gridSize);
        }
    }

    protected void removeCenterComponent() {
        BorderLayout layout = (BorderLayout) backgroundPanel.getLayout();
        backgroundPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
    }

    public void resetTimer() {
        seconds = 0;
        minutes = 0;
        hours = 0;
        timeLabel.setText("Time: " + minutes + " : " + seconds);

        chronometer.stop();
        chronometer.start();
        timerPauseButton.setText("Pause");
    }

    public void chooseCustomFile() {
        LookAndFeel originalLookAndFeel = UIManager.getLookAndFeel();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isCorrectFile = false;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(lastDirectory);
        int choice = fileChooser.showDialog(Game.gameFrame, "New Game");

        if (choice == JFileChooser.APPROVE_OPTION) {
            File chosenFile = fileChooser.getSelectedFile();

            try {
                FileInputStream inputStream = new FileInputStream(chosenFile);
                if (ImageIO.read(inputStream) == null) {
                    JOptionPane.showMessageDialog(Game.gameFrame, "The chosen file must be an image file", "Tile Master - Incorrect File Type", JOptionPane.INFORMATION_MESSAGE);
                    UIManager.setLookAndFeel(originalLookAndFeel);
                    chooseCustomFile();
                    return;
                }
                isCorrectFile = true;
                lastDirectory = new File(chosenFile.getParent());

                String filePath = chosenFile.getPath();
                this.imagePath = filePath.replace('\\', '/');

            } catch (IOException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }


        }

        try {
            UIManager.setLookAndFeel(originalLookAndFeel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(isCorrectFile)
            Game.gameFrame.startNewPictureGame();
    }
}
