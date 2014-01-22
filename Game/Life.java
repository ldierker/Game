//package Game;

import javax.swing.ImageIcon;


//one up 'coin' = gives one extra life 
public class Life extends Sprite {

    String life = "../Game/Life.png";
    boolean collected;

    //constructor
    public Life(int x, int y) {
      this.x = x;
      this.y = y;

      ImageIcon picture = new ImageIcon(this.getClass().getResource(life));
      //do I want to change picture? maybe have a "potion" i.e. a bottle or vase 
      image = picture.getImage();

      width = image.getWidth(null);
      heigth = image.getHeight(null);

      collected = false;
    }

    public boolean isCollected()
    {
      return collected;
    }

    public void setCollected(boolean collected)
    {
      this.collected = collected;
    }


}