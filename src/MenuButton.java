import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton{

    private int width = 200, height = 65;

    MenuButton(String text, String imageFilePath){
        this.setIcon(ImageTool.makeScaledImageIcon(imageFilePath, this.width, this.height));
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setText(text);
        this.setFont(new Font("Helvetica",Font.ITALIC, 18));
        this.setForeground(Color.RED);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
    }

    MenuButton(String text, String imageFilePath, int width, int height){
        this.width = width;
        this.height = height;
        this.setIcon(ImageTool.makeScaledImageIcon(imageFilePath, this.width, this.height));
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setText(text);
        this.setFont(new Font("Helvetica",Font.ITALIC, 18));
        this.setForeground(Color.RED);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
