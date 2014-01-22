//package Game;

import javax.swing.ImageIcon;


public class EndPoint extends Sprite {

    String coin = "../Game/endPoint.png";
    boolean collected;

    //constructor
    public EndPoint(int x, int y) {
      this.x = x;
      this.y = y;

      ImageIcon picture = new ImageIcon(this.getClass().getResource(coin));
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