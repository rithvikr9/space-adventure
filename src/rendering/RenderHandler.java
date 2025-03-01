package rendering;

import entity.Alien;
import entity.Bullet;
import entity.Missile;
import main.Model;
import ui.UI;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class RenderHandler {
  Model gameModel;
  UI ui;

  Font mysteryFont, pixeloidSans;

  public RenderHandler(Model gameModel) {
    this.gameModel = gameModel;
    this.ui = new UI(gameModel);
    loadFonts();
  }

  public void renderMenuState(Graphics2D g2) {
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

    g2.setFont(pixeloidSans);

    ui.drawMenuState(g2);
  }

  public void renderPlayState(Graphics2D g2) {
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

    g2.setFont(mysteryFont);

    // Draw enemies
    for (Alien enemy : gameModel.enemies) {
      enemy.draw(g2);
    }

    // Draw text on screen
    ui.drawPlayStateText(g2);

    // Draw player
    gameModel.spaceship.draw(g2);

    // Draw missiles
    for (Missile missile : gameModel.spaceship.getMissiles()) {
      missile.draw(g2);
    }

    // Draw bullets
    for (Bullet bullet : gameModel.spaceship.getBullets()) {
      bullet.draw(g2);
    }
  }

  public void renderPausedState(Graphics2D g2) {
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

    g2.setFont(pixeloidSans);

    ui.drawPausedState(g2);
  }

  public void renderHelpScreen(Graphics2D g2) {
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

    g2.setFont(pixeloidSans);

    ui.drawHelpScreen(g2);
  }
  
//  public void renderGameOverState(Graphics2D g2) {
//    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//
//    g2.setFont(pixeloidSans);
//
//    ui.drawGameOverState(g2);
//  }

  void loadFonts() {
    InputStream is = getClass().getResourceAsStream("/fonts/Mystery Font.ttf");
    try {
      mysteryFont = Font.createFont(Font.TRUETYPE_FONT, is);
      is = getClass().getResourceAsStream("/fonts/Pixeloid Sans.ttf");
      pixeloidSans = Font.createFont(Font.TRUETYPE_FONT, is);
    } catch (FontFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }
}
