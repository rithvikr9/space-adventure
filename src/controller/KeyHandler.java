package controller;

import main.Model;
import ui.UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
  Model gameModel;

  public boolean upPressed, downPressed, leftPressed, rightPressed, firePressed, reloadPressed;
  public boolean missileMode, debugMode;

  public KeyHandler(Model gameModel) {
    this.gameModel = gameModel;
    missileMode = false;
    debugMode = false;
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    if (gameModel.gameState == gameModel.menuState) { menuState(code); }
    else if (gameModel.gameState == gameModel.playState) { playState(code); }
    else if (gameModel.gameState == gameModel.pausedState) { pausedState(code); }
    else if (gameModel.gameState == gameModel.helpScreen) { helpScreen(code); }
//    else if (gameModel.gameState == gameModel.gameOverState) { gameOverState(code); }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    if (code == KeyEvent.VK_W) { upPressed = false; }
    if (code == KeyEvent.VK_A) { leftPressed = false; }
    if (code == KeyEvent.VK_S) { downPressed = false; }
    if (code == KeyEvent.VK_D) { rightPressed = false; }
    if (code == KeyEvent.VK_SPACE) { firePressed = false; }
    if (code == KeyEvent.VK_R) { reloadPressed = false; }
  }

  // State functions
  private void menuState(int code) {
    if (code == KeyEvent.VK_UP) {
      gameModel.choiceNum--;
      if (gameModel.choiceNum < 0) { gameModel.choiceNum = 2; }
      gameModel.playSE(5);
    }
    if (code == KeyEvent.VK_DOWN) {
      gameModel.choiceNum++;
      if (gameModel.choiceNum > 2) { gameModel.choiceNum = 0; }
      gameModel.playSE(5);
    }

    if (code == KeyEvent.VK_ENTER) {
      if (gameModel.choiceNum == 0) {
        gameModel.gameState = gameModel.playState;
        gameModel.reset();
        gameModel.playSE(6);
      } else if (gameModel.choiceNum == 1) {
        // do something
        gameModel.playSE(6);
      } else if (gameModel.choiceNum == 2) {
        System.exit(0);
      }
    }
  }

  private void playState(int code) {
    // Player movement
    if (code == KeyEvent.VK_W) { upPressed = true; }
    if (code == KeyEvent.VK_A) { leftPressed = true; }
    if (code == KeyEvent.VK_S) { downPressed = true; }
    if (code == KeyEvent.VK_D) { rightPressed = true; }

    // Other controls
    if (code == KeyEvent.VK_ESCAPE) { gameModel.gameState = gameModel.pausedState; }

    switch(code) {
      case KeyEvent.VK_SPACE -> {
        firePressed = true;
        reloadPressed = false;
      }
      case KeyEvent.VK_R -> {
        reloadPressed = true;
        firePressed = false;
      }
      case KeyEvent.VK_M -> missileMode = !missileMode;
      case KeyEvent.VK_B -> debugMode = !debugMode;
      case KeyEvent.VK_O -> {
        if (gameModel.enemies.isEmpty()) {
          gameModel.spawnEnemies(gameModel.generateSpawningPositions(10));
        }
      }
    }
  }

  private void pausedState(int code) {
    if (code == KeyEvent.VK_ESCAPE) { gameModel.gameState = gameModel.pausedState; }

    if (code == KeyEvent.VK_UP) {
      gameModel.choiceNum--;
      if (gameModel.choiceNum < 0) { gameModel.choiceNum = 2; }
      gameModel.playSE(5);
    }
    if (code == KeyEvent.VK_DOWN) {
      gameModel.choiceNum++;
      if (gameModel.choiceNum > 2) { gameModel.choiceNum = 0; }
      gameModel.playSE(5);
    }

    if (code == KeyEvent.VK_ENTER) {
      if (gameModel.choiceNum == 0) {
        gameModel.gameState = gameModel.playState;
        gameModel.playSE(6);
      } else if (gameModel.choiceNum == 1) {
        gameModel.gameState = gameModel.helpScreen;
        gameModel.choiceNum = 0;
        gameModel.playSE(6);
      } else if (gameModel.choiceNum == 2) {
        gameModel.gameState = gameModel.menuState;
        gameModel.choiceNum = 0;
        gameModel.playSE(6);
      }
    }
  }

  private void helpScreen(int code) {
    if (code == KeyEvent.VK_ENTER) {
      if (gameModel.choiceNum == 0) {
        gameModel.gameState = gameModel.pausedState;
        gameModel.playSE(6);
      }
    }
  }
  
//  private void gameOverState(int code) {
//    if (code == KeyEvent.VK_UP) {
//      gameModel.choiceNum--;
//      if (gameModel.choiceNum < 0) { gameModel.choiceNum = 2; }
//      gameModel.playSE(5);
//    }
//    if (code == KeyEvent.VK_DOWN) {
//      gameModel.choiceNum++;
//      if (gameModel.choiceNum > 2) { gameModel.choiceNum = 0; }
//      gameModel.playSE(5);
//    }
//
//    if (code == KeyEvent.VK_ENTER) {
//      if (gameModel.choiceNum == 0) {
//        gameModel.gameState = gameModel.playState;
//        gameModel.reset();
//        gameModel.playSE(6);
//      } else if (gameModel.choiceNum == 1) {
//        gameModel.gameState = gameModel.menuState;
//        gameModel.choiceNum = 0;
//        gameModel.playSE(6);
//      } else if (gameModel.choiceNum == 2) {
//        System.exit(0);
//      }
//    }
//  }
}
