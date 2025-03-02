package main;

import rendering.RenderHandler;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {
  Model gameModel;
  RenderHandler renderHandler;

  public static final int screenWidth = 1280;
  public static final int screenHeight = 728;

  public GamePanel(Model gameModel, RenderHandler renderHandler) {
    this.gameModel = gameModel;
    this.renderHandler = renderHandler;

    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(new Color(10, 2, 18));
    this.setDoubleBuffered(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    if (gameModel.gameState == gameModel.menuState) { renderHandler.renderMenuState(g2); }
    else if (gameModel.gameState == gameModel.playState) { renderHandler.renderPlayState(g2); }
    else if (gameModel.gameState == gameModel.pausedState) { renderHandler.renderPausedState(g2); }
    else if (gameModel.gameState == gameModel.helpScreen) { renderHandler.renderHelpScreen(g2); }
  }
}
