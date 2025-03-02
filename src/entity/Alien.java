package entity;

import main.Model;
import utility.ImageLoader;

import java.awt.*;

public class Alien extends Entity {
  public final int shipSize = 64;

  public Alien(Model gameModel, int x, int y) {
    super(gameModel);

    this.x = x;
    this.y = y;

    setDefaults();
  }

  private void setDefaults() {
    this.image = ImageLoader.loadImage("alien");

    setHitbox();
  }

  @Override
  protected void setHitbox() {
    this.hitbox.x = this.x;
    this.hitbox.y = this.y;
    this.hitbox.width = shipSize;
    this.hitbox.height = shipSize;
  }
  
  @Override
  public void update() {
    updateHitbox();
  }

  @Override
  public void draw(Graphics2D g2) {
    if (gameModel.keyH.debugMode) {
      g2.setColor(Color.red);
      g2.drawRect(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
    }
    g2.drawImage(this.image, this.x, this.y, shipSize, shipSize, null);
  }
}
