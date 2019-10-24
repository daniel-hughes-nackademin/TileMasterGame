import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameFrame extends JFrame {

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

        ImagePanel newGamePanel = new ImagePanel("Graphics/Metal Background Image.jpg", 200, 400);
        newGamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        JPanel buttonGridPanel = new JPanel(new GridLayout(0,1));


        newGamePanel.setBounds(this.width/2 - 100,this.height/2 - 200, 200, 400);
        newGamePanel.setOpaque(false);

        JButton numberGame = new JButton("Number Game");
        numberGame.setPreferredSize(new Dimension(200, 50));
        buttonGridPanel.add(numberGame);

        newGamePanel.add(buttonGridPanel);


        layeredPane.add(newGamePanel, 1);



    }



    public void startNewGame(){
    }

}
