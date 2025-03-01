package main;

import rendering.RenderHandler;
import utility.ImageLoader;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Window extends JFrame {
  public GamePanel gp;
  public Updater updater;

  public Window(Model gameModel, RenderHandler renderHandler) {
    this.setTitle("Space Adventure");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);

    gp = new GamePanel(gameModel, renderHandler);
    this.setContentPane(gp);
    this.pack();

    this.setLocationRelativeTo(null);

    // Set icon image
    BufferedImage image = ImageLoader.loadImage("spaceship");
    this.setIconImage(image);

    this.updater = new Updater(gameModel, gp);
    this.updater.startGameThread();

    // Event listeners
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        updater.gameThread = null;
      }
    });

    this.addKeyListener(gameModel.keyH);
    this.addMouseListener(gameModel.mouseH);
  }
}
