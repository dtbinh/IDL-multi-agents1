package pacman;

import core.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Pauline on 28/01/2016.
 */
public class ListenerClavier implements KeyListener {

  @Override public void keyTyped(KeyEvent e) {

  }

  @Override public void keyPressed(KeyEvent e) {
    System.out.println(e.getKeyCode());
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
