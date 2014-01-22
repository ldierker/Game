//package Game;

import javax.swing.ImageIcon;


public class Bug extends Sprite implements Constants {

   private int xdir;
   private int ydir;
   private int y;

   protected String bug = "../Game/square2.png";

   public Bug(int starty) {
     this.y = starty;
     setY(starty);
     
     xdir = 1;

     ImageIcon picture = new ImageIcon(this.getClass().getResource(bug));
     image = picture.getImage();

     width = image.getWidth(null);
     heigth = image.getHeight(null);

     resetState();
    }
   
   //going to add bugs with different movements
   //some go up and down
   //some go side to side
   //add different cond=tructor with specifer or make new class?


    public void move()//
    {
      x += xdir;

      if (x == 0) {
        setXDir(1);
      }

      if (x == BUG_RIGHT) {
        setXDir(-1);
      }
    }

    public void resetState() 
    {
      x = 230;
      y = y;
    }

    public void setXDir(int x)
    {
      xdir = x;
    }

    public void setYDir(int y)
    {
      ydir = y;
    }

    public int getYDir()
    {
      return ydir;
    }
}