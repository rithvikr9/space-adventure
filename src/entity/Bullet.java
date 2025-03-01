package entity;

import main.GamePanel;
import main.Model;
import utility.ImageLoader;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Bullet extends Entity {
  public Bullet(Model gameModel) {
    super(gameModel);

    setDefaultValues();
  }

  private void setDefaultValues() {
    this.x = gameModel.spaceship.x + gameModel.spaceship.spaceshipSize - 20;
    this.y = setBulletY();
    this.speed = 12;
    this.visible = true;

    this.image = ImageLoader.loadImage("bullet");

    setHitbox();
  }

  private int setBulletY() {
    int random = ThreadLocalRandom.current().nextInt(1, 1000 + 1);
    if (random <= 500) {
      return gameModel.spaceship.y + 2;
    } else if (random <= 1000) {
      return gameModel.spaceship.y + gameModel.spaceship.spaceshipSize - 25;
    }
    return 0;
  }

  @Override
  protected void setHitbox() {
    this.hitbox.x = this.x;
    this.hitbox.y = this.y;
    this.hitbox.width = (gameModel.spaceship.spaceshipSize) / 3;
    this.hitbox.height = (gameModel.spaceship.spaceshipSize) / 3;
  }

  @Override
  protected void updateHitbox() {
    this.hitbox.x = this.x;
    this.hitbox.y = this.y;
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
        gameModel.playSE(3);
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
        (gameModel.spaceship.spaceshipSize) / 3, (gameModel.spaceship.spaceshipSize) / 3, null);
  }
}
