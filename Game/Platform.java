//package Breakout;

import javax.swing.ImageIcon;


public class Platform extends Sprite {

    String platform = "../Game/platform2.png";

    boolean present;


    public Platform(int x, int y) {
      this.x = x;
      this.y = y;

      ImageIcon picture = new ImageIcon(this.getClass().getResource(platform));
      image = picture.getImage();

      width = image.getWidth(null);
      heigth = image.getHeight(null);

      present = false;
    }

    public boolean isPresent()
    {
      return present;
    }

    public void setPresent(boolean present)
    {
      this.present = present;
    }

}