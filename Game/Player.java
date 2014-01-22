//package Game;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Player extends Sprite implements Constants {

    String player = "../Game/square.png";

    int dx;
    int dy;
    
    int life = 2;
    int score = 0;

    //constructor 
    public Player(){

        ImageIcon picture = new ImageIcon(this.getClass().getResource(player));
        image = picture.getImage();

        width = image.getWidth(null);
        heigth = image.getHeight(null);

        resetState();
    }//end of player

    public void move(){
        x += dx;
        if (x <= 2){
          x = 2;
        }
        if (x >= Constants.PLAYER_RIGHT){
          x = Constants.PLAYER_RIGHT;
        }
  
        y += dy;
        if (y <= 2){
          y = 2;
        }
        if (x <= Constants.PLAYER_TOP){
          y = Constants.PLAYER_TOP;
        }
        
    }//end of move

    //changes state based on keys pressed
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT){
            dx = -2;
        }
        if (key == KeyEvent.VK_RIGHT){
            dx = 2;
        }
        if(key == KeyEvent.VK_UP){
           dy = -2;
        }
        if(key == KeyEvent.VK_DOWN){
          dy = 2;
        }
    }//end of keyPressed

    //changes state based on keys released
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT){
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT){
            dx = 0;
        }
        if(key == KeyEvent.VK_UP){
          dy = 0;
        }
        if(key == KeyEvent.VK_DOWN){
          dy = 0;
        }
    }//end of keyReleased

    public void resetState() {
        x = Constants.WIDTH / 2;
        y = Constants.HEIGHT - 100;
    }//end of resetState
    
    public int getScore(){
      return score;
    }
    
    public void increaseScore(){
      score++;
    }
    
    public void setScore( int score){
      this.score = score;
    }
    
    public void addLife(){
      life++;
    }
    
    public void subLife(){
      life--;
    }
    
    public int getLife(){
      return life;
    }
    
    public void movePlayer() {
        x = 50;
        y = 600;
    }
    
}