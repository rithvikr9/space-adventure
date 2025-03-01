package entity;

import main.Model;
import main.GamePanel;
import utility.ImageLoader;

import java.awt.*;

public class Missile extends Entity {
  public Missile(Model gameModel) {
    super(gameModel);

    setDefaultValues();
  }

  private void setDefaultValues() {
    this.x = gameModel.spaceship.x + gameModel.spaceship.spaceshipSize - 20;
    this.y = gameModel.spaceship.y + (gameModel.spaceship.spaceshipSize / 4) + 5;
    this.speed = 12;
    this.visible = true;

    this.image = ImageLoader.loadImage("missile");

    setHitbox();
  }

  @Override
  protected void setHitbox() {
    this.hitbox.x = this.x;
    this.hitbox.y = this.y;
    this.hitbox.width = gameModel.spaceship.spaceshipSize + (gameModel.spaceship.spaceshipSize / 4);
    this.hitbox.height = (gameModel.spaceship.spaceshipSize) / 3;
  }

  public boolean isVisible() { return visible; }
  public void setVisible(boolean visible) { this.visible = visible; }

  @Override
  public void update() {
    this.x += this.speed;
    if (this.x > GamePanel.screenWidth) {
      visible = false;
    }

    // Collision with enemies
    for (int i = 0; i < gameModel.enemies.size(); i++) {
      if (this.hitbox.intersects(gameModel.enemies.get(i).hitbox)) {
        this.setVisible(false);
        gameModel.enemies.remove(gameModel.enemies.get(i));
        gameModel.playSE(4);
        break;
      }
    }

    updateHitbox();
  }

  @Override
  public void draw(Graphics2D g2) {
    if (gameModel.keyH.debugMode) {
      g2.setColor(Color.red);
      g2.drawRect(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
    }
    g2.drawImage(this.image, this.x , this.y,
        gameModel.spaceship.spaceshipSize + (gameModel.spaceship.spaceshipSize / 4),
        (gameModel.spaceship.spaceshipSize) / 3, null);
  }
}
