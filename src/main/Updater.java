package main;

public class Updater implements Runnable {
  public final double FPS = 60.0;
  Thread gameThread;

  private final Model gameModel;
  private final GamePanel gp;

  public Updater(Model gameModel, GamePanel gp) {
    this.gameModel = gameModel;
    this.gp = gp;
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {
    double drawInterval = 1000000000 / FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while (gameThread != null) {
      currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / drawInterval;

      lastTime = currentTime;

      if (delta >= 1) {
        this.gameModel.update();
        this.gp.repaint();
        delta--;
      }
    }
  }
}
