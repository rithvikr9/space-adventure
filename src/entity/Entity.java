package entity;

import main.Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
  Model gameModel;
  BufferedImage image;

  boolean visible;

  public Rectangle hitbox= new Rectangle();

  public Entity(Model gameModel) {
    this.gameModel = gameModel;
  }

  public int x, y;
  public int speed;

  protected abstract void setHitbox();
  protected void updateHitbox() {
    this.hitbox.x = this.x;
    this.hitbox.y = this.y;
  }

  public abstract void update();
  public abstract void draw(Graphics2D g2) ;
}
