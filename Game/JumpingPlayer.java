//package Game;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class JumpingPlayer extends Sprite implements Constants {

    String player = "../Game/square.png";

    int dx;
    int dy;
    
    int life = 3;
    int score = 0;
    
    boolean jumping = false;
    int jumpPower = 20;
    int jump = 0;
    
    double height = 0;
    double speed = 4;
    public static final double gravity = 9.81;
    private double x = 25;
    boolean left = false, right = false,up = false;
    long previous, start;

    //constructor 
    public JumpingPlayer(){

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

         start= System.nanoTime();  
            if(/*previous != 0 &&*/  up){
                double delta = start-previous;
                dy += (delta/1000000000) *speed;        
                speed -= (delta/1000000000)  * gravity;
                }  
            if(left)
                dx-=3;
            if(right)
                dx+=3;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(height < 0){
                height=0;
                speed=4; 
                up=false;
            }      
            previous= start;
    
    }//end of move

    //changes state based on keys pressed
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

          if (key == KeyEvent.VK_LEFT){
            left = true;
          }
          if (key == KeyEvent.VK_RIGHT){
            right = true;
          }
          if(key == KeyEvent.VK_UP){
            up = true;
          }
    }//end of keyPressed

    //changes state based on keys released
    public void keyReleased(KeyEvent e) {
        /*int key = e.getKeyCode();
         if (key == KeyEvent.VK_LEFT){
            dx = 0;
          }
          if (key == KeyEvent.VK_RIGHT){
            dx = 0;
          }
        //if(key == KeyEvent.VK_UP ){
          if(jumping = true){
            if(jump <= jumpPower && jump >= 0 ){
              dy = -2;
              jump++;
            }
            if( jump > jumpPower){
              jump = -jumpPower;
              dy = 0;
            }
            if( jump < 0){
              jump++;
              dy = 2;
            }  
          }
          if(jumping = false){
            dy = 0;
          }
        //}
        */
           if(e.getKeyCode() == KeyEvent.VK_LEFT)
            left=false;
           
           if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            right=false; 
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
    public void addLife(){
       life++;
    }
    
    public void subLife(){
      life--;
    }
    
    public int getLife(){
      return life;
    }
}