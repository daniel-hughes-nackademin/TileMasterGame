import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton{

    public MenuButton(String text, String imageFilePath, int width, int height){
        this.setIcon(ImageTool.makeScaledImageIcon(imageFilePath, width, height));
        this.setPreferredSize(new Dimension(width, height));
        this.setText(text);
        this.setFont(new Font("Helvetica",Font.ITALIC, 18));
        this.setForeground(Color.RED);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);


    }


}
