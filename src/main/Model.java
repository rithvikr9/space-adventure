package main;

import controller.*;
import entity.Alien;
import entity.Spaceship;
import utility.Sound;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Model {
  public KeyHandler keyH = new KeyHandler(this);
  MouseHandler mouseH = new MouseHandler();
  public Sound se = new Sound();

  public int gameState;
  public final int menuState = 0;
  public final int playState = 1;
  public final int pausedState = 2;
  public final int helpScreen = 3;

  public int choiceNum = 0;

  public Model() { spawnEnemies(generateSpawningPositions(10)); }

  public Spaceship spaceship = new Spaceship(this, 64, 64);
  public ArrayList<Alien> enemies = new ArrayList<>();
  
  private int generateRandomCoordinate(int min, int max) { return ThreadLocalRandom.current().nextInt(min, max + 1); }
  
  public int[][] generateSpawningPositions(int num) {
    int spawnX, spawnY;
    int[][] spawnPositions = new int[num][2];
    
    // Bound variables
    int xMin = 128;
    int xMax = 1280 - 64;
    int yMin = 150;
    int yMax = 728 - 64;
    
    for (int i = 0; i < num; i++) {
      spawnX = generateRandomCoordinate(xMin, xMax);
      spawnY = generateRandomCoordinate(yMin, yMax);
      
      for (int[] spawnPosition : spawnPositions) {
        if (spawnX >= spawnPosition[0] && spawnX <= spawnPosition[0] + 64) {
          spawnX = generateRandomCoordinate(xMin, xMax);
        }
        if (spawnY >= spawnPosition[1] && spawnY <= spawnPosition[1] + 64) {
          spawnY = generateRandomCoordinate(yMin, yMax);
        }
      }
      
      int[] spawnCoords = { spawnX, spawnY };
      
      spawnPositions[i] = spawnCoords;
    }
    
    return spawnPositions;
  }
  
  public void spawnEnemies(int[][] positions) {
    for (int[] position : positions) {
      enemies.add(new Alien(this, position[0], position[1]));
    }
  }

  public void update() {
    spaceship.update();
    for (Alien enemy : enemies) { enemy.update(); }
  }

  public void playSE(int i) {
    se.setFile(i);
    se.play();
  }
  
  public void reset() {
    enemies.clear();
    spaceship.resetAmmo();
    spawnEnemies(generateSpawningPositions(10));
    spaceship.setPosition(64, 64);
  }
}
