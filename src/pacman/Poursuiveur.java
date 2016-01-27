package pacman;

import core.Agent;

/**
 * Created by Pauline on 27/01/2016.
 */
public class Poursuiveur extends Agent {

  public Poursuiveur(int posX, int posY) {
    super(posX, posY);
  }

  @Override public Agent doIt() {
    System.out.println("Poursuiveur parle.");
    return null;
  }
}
