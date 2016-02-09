package pacman;

import core.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ListenerClavier implements KeyListener {

  @Override public void keyTyped(KeyEvent e) {

  }

  @Override public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        Avatar.setDIRECTION(Direction.SUD);
        break;
      case KeyEvent.VK_DOWN:
        Avatar.setDIRECTION(Direction.NORD);
        break;
      case KeyEvent.VK_LEFT:
        Avatar.setDIRECTION(Direction.OUEST);
        break;
      case KeyEvent.VK_RIGHT:
        Avatar.setDIRECTION(Direction.EST);
        break;
    }
  }

  @Override public void keyReleased(KeyEvent e) {

  }
}
