package pacman;

import core.Agent;

/**
 * Created by Pauline on 27/01/2016.
 */
public class Bloc extends Agent {

  public Bloc(int posX, int posY) {
    super(posX, posY);
  }

  @Override public Agent doIt() {
    System.out.println("Bloc parle.");
    return null;
  }
}
