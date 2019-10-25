import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GameFrame extends JFrame implements ActionListener {

    private static String title = "Tile Master - The Amazing Puzzle Game";
    int width = 900;
    int height = width*1; //Calculating height by desired ratio

    JLayeredPane layeredPane = new JLayeredPane();


    public GameFrame(){
        this.setResizable(false);
        Dimension size = new Dimension(width, height);
        this.setTitle(title);
        this.setPreferredSize(size);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(layeredPane);
        this.setVisible(true);

    }





    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.initializeMainMenu();

    }

    public void initializeMainMenu(){
        this.layeredPane.add(new ImagePanel("Graphics/Metal Background Image.jpg", this.width, this.height), 0);


        JLabel headerTitle = new JLabel("Tile Master - Are You Ready?");
        headerTitle.setFont(new Font("Georgia", Font.BOLD, 48));
        headerTitle.setForeground(Color.BLACK);
        headerTitle.setBounds(this.width/2 - 370, 0, 850, 100);

        ImagePanel headerPanel = new ImagePanel("Graphics/Fire Background.jpg", this.width, 100);
        headerPanel.setBounds(0,0, this.width, 100);
        headerPanel.add(headerTitle);
        layeredPane.add(headerPanel, 1);

        //ImagePanel newGamePanel = new ImagePanel("Graphics/Dark Metallic Panel.jpeg", 200, 400);
        //newGamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        ImagePanel buttonGridPanel = new ImagePanel("Graphics/Metal Texture Pattern.jpg", 200, 400);
        buttonGridPanel.setLayout(new GridLayout(0,1, 0,40));
        buttonGridPanel.setBounds(width - 200, 100, 200, 400);


        //newGamePanel.setBounds(this.width/2 - 100,this.height/2 - 200, 200, 400);

        MenuButton numberGame = new MenuButton("Number Game", "Graphics/Metallic Button.jpg", 200, 70);
        numberGame.addActionListener(this);
        MenuButton pictureGame = new MenuButton("Picture Game", "Graphics/Metallic Button.jpg", 200, 70);
        pictureGame.addActionListener(this);
        MenuButton customSizeGame = new MenuButton("Custom Size Game", "Graphics/Metallic Button.jpg", 200, 70);
        customSizeGame.addActionListener(this);
        MenuButton optionsMenu = new MenuButton("Options", "Graphics/Metallic Button.jpg", 200, 70);
        optionsMenu.addActionListener(this);

        buttonGridPanel.add(numberGame);
        buttonGridPanel.add(pictureGame);
        buttonGridPanel.add(customSizeGame);
        buttonGridPanel.add(optionsMenu);

        //newGamePanel.add(buttonGridPanel);


        layeredPane.add(buttonGridPanel, 1);




    }



    public void startNewGame(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
