package entity;

import main.Model;
import utility.ImageLoader;

import java.awt.*;
import java.util.ArrayList;

public class Spaceship extends Entity {
  private ArrayList<Missile> missiles;
  private ArrayList<Bullet> bullets;

  private boolean canShootMissiles, canShootBullets;
  private double missileFireTime, bulletFireTime;
  public int maxMissiles;
  public int missileQuantity;

  public int maxBullets;
  public int bulletQuantity;

  public final int spaceshipSize = 64;

  public Spaceship(Model gameModel, int x, int y) {
    super(gameModel);

    setPosition(x, y);
    setDefaults();
  }

  private void setDefaults() {
    this.speed = 6;

    this.image = ImageLoader.loadImage("spaceship");

    missiles = new ArrayList<>();
    bullets = new ArrayList<>();
    canShootMissiles = true;
    canShootBullets = true;
    
    setAmmo();
    setHitbox();
  }
  
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void setAmmo() {
    maxMissiles = 3;
    maxBullets = 10;
    
    missileQuantity = maxMissiles;
    bulletQuantity = maxBullets;
    
  }
  
  public void resetAmmo() {
    missileQuantity = maxMissiles;
    bulletQuantity = maxBullets;
  }

  // Hitbox
  @Override
  protected void setHitbox() {
    this.hitbox.x = this.x;
    this.hitbox.y = this.y;
    this.hitbox.width = spaceshipSize;
    this.hitbox.height = spaceshipSize;
  }

  @Override
  protected void updateHitbox() {
    this.hitbox.x = this.x;
    this.hitbox.y = this.y;
  }

  public ArrayList<Missile> getMissiles() { return missiles; }
  public ArrayList<Bullet> getBullets() { return bullets; }

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

  private void reload() {
    if (gameModel.keyH.missileMode) {
      missileQuantity = maxMissiles;
    } else {
      bulletQuantity = maxBullets;
    }
  }

  @Override
  public void update() {
    // Movement
    if (gameModel.keyH.upPressed || gameModel.keyH.downPressed ||
        gameModel.keyH.leftPressed || gameModel.keyH.rightPressed) {
      if (gameModel.keyH.upPressed) { this.y -= this.speed; }
      if (gameModel.keyH.downPressed) { this.y += this.speed; }
      if (gameModel.keyH.leftPressed) { this.x -= this.speed; }
      if (gameModel.keyH.rightPressed) { this.x += this.speed; }
    }
    
    // Fire & reload
    if (gameModel.keyH.firePressed) {
      if (gameModel.keyH.missileMode) {
        if (canShootMissiles) { fireMissile(); }
        if (System.nanoTime() - missileFireTime > 500000000) { canShootMissiles = true; }
      } else {
        if (canShootBullets) { fireBullet(); }
        if (System.nanoTime() - bulletFireTime > 100000000) { canShootBullets = true; }
      }
    }
    if (gameModel.keyH.reloadPressed) { reload(); }
    
    // Checking for collision with enemies
    for (int i = 0; i < gameModel.enemies.size(); i++) {
      if (hitbox.intersects(gameModel.enemies.get(i).hitbox)) {
        gameModel.enemies.remove(gameModel.enemies.get(i));
        gameModel.playSE(4);
      }
    }
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
    g2.drawImage(this.image, this.x, this.y, spaceshipSize, spaceshipSize, null);
  }
}
