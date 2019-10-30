import javax.swing.*;

public class Game {

    static GameFrame gameFrame;

    //= = = = = = = = = = = = = = =MAIN METHOD = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    public static void main(String[] args) {
        gameFrame = new GameFrame();
        gameFrame.initializeMainMenu();
    }
    //= = = = = = = = = = = = = = =MAIN METHOD END = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
}
