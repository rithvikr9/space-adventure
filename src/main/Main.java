package main;

import rendering.RenderHandler;

public class Main {
  static Model gameModel = new Model();
  static RenderHandler renderHandler = new RenderHandler(gameModel);

  public static void main(String[] args) {
    Window window = new Window(gameModel, renderHandler);
    window.setVisible(true);
  }
}
