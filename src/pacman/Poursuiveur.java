package pacman;

import core.Agent;
import core.Environement;

/**
 * Created by Pauline on 27/01/2016.
 */
public class Poursuiveur extends Agent {

  public Poursuiveur(int posX, int posY) {
    super(posX, posY);
  }

  @Override public Environement doItWithEnv(Environement env) {
//    System.out.println("Poursuiveur parle.");
    return env;
  }
}
