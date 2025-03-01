package ui;

import main.GamePanel;
import main.Model;
import utility.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
  Model gameModel;

  public UI(Model gameModel) { this.gameModel = gameModel; }

  public void drawMenuState(Graphics2D g2) {
    displayGameTitle(g2);
    drawImages(g2);
    displayMenuOptions(g2);
  }

  public void drawPlayStateText(Graphics2D g2) {
    displayMissileQuantity(g2);
    displayBulletQuantity(g2);
    displayEnemiesRemaining(g2);
    displayFiringMode(g2);
    if (gameModel.keyH.debugMode) { displayPlayerCoordinates(g2); }
  }

  public void drawPausedState(Graphics2D g2) {
    drawMenuTitleText("Paused", g2);
    displayPausedOptions(g2);
  }

  public void drawHelpScreen(Graphics2D g2) {
    drawMenuTitleText("Help", g2);
    drawControlsText(g2);
  }
  
//  public void drawGameOverState(Graphics2D g2) {
//    drawMenuTitleText("Game Over", g2);
//    displayGameOverOptions(g2);
//  }

  // Menu state helper functions
  private void displayGameTitle(Graphics2D g2) {
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
    g2.drawString("Space Adventure", centerText("Space Adventure", g2), 150);
  }

  private void drawImages(Graphics2D g2) {
    BufferedImage spaceship = ImageLoader.loadImage("spaceship");
    BufferedImage alien = ImageLoader.loadImage("alien");
    BufferedImage missile = ImageLoader.loadImage("missile");

    g2.drawImage(spaceship, 400, 250, 100, 100, null);
    g2.drawImage(missile, 570, 280, 110, 30, null);
    g2.drawImage(alien, 750, 250, 100, 100, null);
  }

  private void displayMenuOptions(Graphics2D g2) {
    String text;
    int x, y;

    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
    text = "New Game";
    x = centerText(text, g2);
    y = 475;
    g2.drawString(text, x, y);
    if (gameModel.choiceNum == 0) { g2.drawString(">", x - 50, y); }

    text = "Load Game";
    x = centerText(text, g2);
    y += 100;
    g2.drawString(text, x, y);
    if (gameModel.choiceNum == 1) { g2.drawString(">", x - 50, y); }

    text = "Quit";
    x = centerText(text, g2);
    y += 100;
    g2.drawString(text, x, y);
    if (gameModel.choiceNum == 2) { g2.drawString(">", x - 50, y); }
  }

  // Play state helper functions
  private void displayMissileQuantity(Graphics2D g2) {
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24));
    g2.drawString("Missiles: " + gameModel.spaceship.missileQuantity, 1120, 50); // x: 1088;
  }

  private void displayBulletQuantity(Graphics2D g2) {
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24));
    g2.drawString("Bullets: " + gameModel.spaceship.bulletQuantity, 1120, 100);
  }

  private void displayPlayerCoordinates(Graphics2D g2) {
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24));
    g2.drawString("X: " + gameModel.spaceship.x + ", Y: " + gameModel.spaceship.y, 30, 660);
  }

  private void displayEnemiesRemaining(Graphics2D g2) {
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24));
    g2.drawString("Enemies left: " + gameModel.enemies.size(), 30, 50);
  }

  private void displayFiringMode(Graphics2D g2) {
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24));
    g2.drawString("Firing mode: " + firingModeText(), 30, 700);
  }

  // Paused state helper functions
  private void displayPausedOptions(Graphics2D g2) {
    String text;
    int x, y;

    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
    text = "Resume";
    x = centerText(text, g2);
    y = 350;
    g2.drawString(text, x, y);
    if (gameModel.choiceNum == 0) { g2.drawString(">", x - 50, y); }

    text = "Help";
    x = centerText(text, g2);
    y += 100;
    g2.drawString(text, x, y);
    if (gameModel.choiceNum == 1) { g2.drawString(">", x - 50, y); }

    text = "Exit to Main Menu";
    x = centerText(text, g2);
    y += 100;
    g2.drawString(text, x, y);
    if (gameModel.choiceNum == 2) { g2.drawString(">", x - 50, y); }
  }

  // Help screen helper functions
  private void drawControlsText(Graphics2D g2) {
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
    g2.drawString("Controls", 200, 250);
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));

    String text = "WASD - movement";
    int x = 200;
    int y = 300;
    g2.drawString(text, x, y);

    text = "Space - fire";
    y += 50;
    g2.drawString(text, x, y);

    text = "M - change fire mode";
    y += 50;
    g2.drawString(text, x, y);

    text = "R - reload";
    y += 50;
    g2.drawString(text, x, y);

    text = "B - enable debug mode";
    y += 50;
    g2.drawString(text, x, y);

    text = "O - spawn new enemies";
    y += 50;
    g2.drawString(text, x, y);

    x = centerText("Back", g2);
    y = 650;
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
    g2.drawString("Back", x, y);
    g2.drawString(">", x - 50, y);
  }
  
  // Game over state helper functions
//  private void displayGameOverOptions(Graphics2D g2) {
//    String text;
//    int x, y;
//
//    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
//    text = "New Game";
//    x = centerText(text, g2);
//    y = 350;
//    g2.drawString(text, x, y);
//    if (gameModel.choiceNum == 0) { g2.drawString(">", x - 50, y); }
//
//    text = "Main Menu";
//    x = centerText(text, g2);
//    y += 100;
//    g2.drawString(text, x, y);
//    if (gameModel.choiceNum == 1) { g2.drawString(">", x - 50, y); }
//
//    text = "Quit";
//    x = centerText(text, g2);
//    y += 100;
//    g2.drawString(text, x, y);
//    if (gameModel.choiceNum == 2) { g2.drawString(">", x - 50, y); }
//  }

  // Helper functions
  private String firingModeText() { return gameModel.keyH.missileMode ? "Missile" : "Gun"; }

  private int centerText(String text, Graphics2D g2) {
    int len = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    return (GamePanel.screenWidth / 2) - (len / 2);
  }
  
  private void drawMenuTitleText(String text, Graphics2D g2) {
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 72F));
    g2.drawString(text, centerText(text, g2), 150);
  }
}
