package pacman;

import core.Agent;
import core.Environement;

public class Bloc extends Agent {

  public Bloc(int posX, int posY) {
    super(posX, posY);
  }

  @Override public Environement doItWithEnv(Environement env) {
    //System.out.println("Bloc parle.");
    return env;
  }
}
