//package Game;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Background extends Sprite implements Constants {

    String picstring = "../Game/background.png";

    //constructor 
    public Background(){

        ImageIcon background = new ImageIcon(this.getClass().getResource(picstring));
        image = background.getImage();

        width = image.getWidth(null);
        heigth = image.getHeight(null);
        x = 0;
        y = 0;

        resetState();
    }//end of player

    public void resetState() {
        x = 0;
        y = 0;
    }//end of resetState
}