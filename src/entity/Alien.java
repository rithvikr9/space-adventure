package entity;

import main.Model;
import utility.ImageLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Alien extends Entity {
  public final int shipSize = 64;
  private ArrayList<Missile> missiles;
  private ArrayList<Bullet> bullets;

  private boolean canShootMissiles, canShootBullets;
  private double missileFireTime, bulletFireTime;
  private int maxMissiles;
  public int missileQuantity;

  private int maxBullets;
  public int bulletQuantity;

  public Alien(Model gameModel, int x, int y) {
    super(gameModel);

    this.x = x;
    this.y = y;

    setDefaults();
  }

  private void setDefaults() {
    this.image = ImageLoader.loadImage("alien");
    
    missiles = new ArrayList<>();
    bullets = new ArrayList<>();
    canShootMissiles = true;
    canShootBullets = true;

    setHitbox();
    setAmmo();
  }

  @Override
  protected void setHitbox() {
    this.hitbox.x = this.x;
    this.hitbox.y = this.y;
    this.hitbox.width = shipSize;
    this.hitbox.height = shipSize;
  }

  private void setAmmo() {
    maxMissiles = 10;
    maxBullets = 50;

    missileQuantity = maxMissiles;
    bulletQuantity = maxBullets;
  }

  private void fireMissile() {
    if (missileQuantity > 0) {
      missileFireTime = System.nanoTime();
      missiles.add(new Missile(gameModel));
      missileQuantity--;
      canShootMissiles = false;
      gameModel.playSE(0);
    }
  }

  private void fireBullet() {
    if (bulletQuantity > 0) {
      bulletFireTime = System.nanoTime();
      bullets.add(new Bullet(gameModel));
      bulletQuantity--;
      canShootBullets = false;
      gameModel.playSE(1);
    }
  }

  private void updateMissiles() {
    for (int i = 0; i < missiles.size(); i++) {
      Missile missile = missiles.get(i);
      if (missile.isVisible()) {
        missile.update();
      } else {
        missiles.remove(i);
      }
    }
  }

  private void updateBullets() {
    for (int i = 0; i < bullets.size(); i++) {
      Bullet bullet = bullets.get(i);
      if (bullet.isVisible()) {
        bullet.update();
      } else {
        bullets.remove(i);
      }
    }
  }

  @Override
  public void update() {
    updateMissiles();
    updateBullets();
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
