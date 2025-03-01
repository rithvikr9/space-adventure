package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
  @Override
  public void mouseClicked(MouseEvent e) {
    System.out.println("Mouse click at " + e.getX() + ", " + e.getY());
  }

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}
}
