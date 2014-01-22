//package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import javax.swing.ImageIcon;

/**
 * Main game file
 * this file contains all game logic, updating, and painting
 */
public class Board extends JPanel implements Constants{

    Image picture;
    Timer timer;
    Player player;  //change to JumpingPlayer
    String message = "Game Over Score: "; 
    String score = "Score: ";
    String lives = "Lives: ";
    Coin coins[];
    Life life1;
    Life life2;
    Bug bug;
    Bug bug2;
    Platform platform;
    Background background1;
    EndPoint endPoint;

    boolean ingame = true;
    int timerId;


    //constructor 
    public Board(){

        addKeyListener(new TAdapter());
        setFocusable(true);

        //Array of coins
        coins = new Coin[30];
        setDoubleBuffered(true);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);
        
        setBackground(Color.BLACK);
        
    }//end Board

        public void addNotify(){
            super.addNotify();
            gameInit();
        }

    //starts game
    //makes a new player and 30 coins
    public void gameInit(){

        player = new Player();  //change to jumpingPlayer
        bug = new Bug(200);
        bug2 = new Bug(400);
        platform = new Platform(400,500);
        background1 = new Background();
        life1 = new Life(350, 400);
        life2 = new Life(400, 500);
        endPoint = new EndPoint(500, 100);

        //creates all coins and sets locations
        int k = 0;
        int edgeDistance = 10;
        int yDistance = 100;
        int xDistance = 120;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 6; j++){
                coins[k] = new Coin(j * (30 + xDistance) + edgeDistance, i * (30 + yDistance) + edgeDistance);
                k++;
            }
        }
    }//end gameInit

    
    public void paint(Graphics g){
        super.paint(g);

        //draw all the things!!!
        if (ingame){
              g.drawImage(background1.getImage(), background1.getX(), background1.getY(),
                        background1.getWidth(), background1.getHeight(), this);
              g.drawImage(player.getImage(), player.getX(), player.getY(),
                        player.getWidth(), player.getHeight(), this);
              g.drawImage(bug.getImage(), bug.getX(), bug.getY(),
                        bug.getWidth(), bug.getHeight(), this);
              g.drawImage(bug2.getImage(), bug2.getX(), bug2.getY(),
                        bug2.getWidth(), bug2.getHeight(), this);
              g.drawImage(platform.getImage(), platform.getX(), platform.getY(),
                        platform.getWidth(), platform.getHeight(), this);
              if (!life1.isCollected()){
                g.drawImage(life1.getImage(), life1.getX(), life1.getY(),
                        life1.getWidth(), life1.getHeight(), this);
              }
             if (!life2.isCollected()){
                g.drawImage(life2.getImage(), life2.getX(), life2.getY(),
                        life2.getWidth(), life2.getHeight(), this);
              }
             g.drawImage(endPoint.getImage(), endPoint.getX(), endPoint.getY(),
                        endPoint.getWidth(), endPoint.getHeight(), this);
              

            for (int i = 0; i < 30; i++){
              if (!coins[i].isCollected()){
                    g.drawImage(coins[i].getImage(), coins[i].getX(),
                                coins[i].getY(), coins[i].getWidth(),
                                coins[i].getHeight(), this);
              }
            }
            
            g.drawString(score + player.getScore() + " " + lives + player.getLife(),
                         Constants.WIDTH - 150,
                         Constants.HEIGHT - 600);
            
            g.drawString(lives + player.getLife(),
                         Constants.WIDTH - 150,
                         Constants.HEIGHT - 700);
        } else{

            Font font = new Font("Verdana", Font.BOLD, 18);
            FontMetrics metr = this.getFontMetrics(font);

            g.setColor(Color.WHITE);
            g.setFont(font);
            int collectedNum = 0;
            for (int i = 0; i < 30; i++){
              if (coins[i].isCollected()) {
                collectedNum++;
              }
            }
            player.setScore(collectedNum);
            g.drawString(message,
                         (Constants.WIDTH - metr.stringWidth(message)) / 2,
                         Constants.HEIGHT / 2);
            
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }//end paint

    private class TAdapter extends KeyAdapter{

        public void keyReleased(KeyEvent e){
            player.keyReleased(e);
        }
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
    }//end KeyAdapter


    class ScheduleTask extends TimerTask{

        public void run(){

            player.move();
            bug.move();
            bug2.move();
            checkCoins();
            checkLives();
            repaint();

        }
    }//end TimerTask

    public void stopGame(){
        ingame = false;
        timer.cancel();
    }//end stopGame


    public void checkCoins(){
      int coinsCollected = 0;
        //checks how many coins are left
        //win game if they are all collected 
        for (int i = 0; i < 30; i++){
            if (coins[i].isCollected()){
                coinsCollected++;
            }
            //if (j == 30){
            //    message = "You Win!";
            //    stopGame();
            //}
        }
        player.setScore(coinsCollected);
        for (int i = 0; i < 30; i++){
          if ((player.getRect()).intersects(coins[i].getRect())){
                    coins[i].setCollected(true);
          }
        }
    }//end CheckCoins
    
     public void checkLives(){
        if ((player.getRect()).intersects(life1.getRect()) && !(life1.isCollected()) ){
            player.addLife();
            life1.setCollected(true);
          }
        if ((player.getRect()).intersects(life2.getRect()) && !(life2.isCollected()) ){
            player.addLife();
            life2.setCollected(true);
          }
        if((player.getRect()).intersects(bug.getRect()) ){
          player.subLife();
          player.movePlayer();
            }
        if((player.getRect()).intersects(bug2.getRect()) ){
          player.subLife();
          player.movePlayer();
            }
        if(player.getLife() <= 0){
          stopGame();
        }
     }
     
     public void checkEndPoint(){
       if ((player.getRect()).intersects(endPoint.getRect()) ){
            stopGame();
            //stop game but new screen
            //later will transition to new level
            //do I want a animated screen or picture? Use gimp?
            //have screen show score, lives, anything else?
          }
     }
}