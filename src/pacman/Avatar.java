package pacman;

import core.Agent;
import core.Direction;

/**
 * Created by Pauline on 27/01/2016.
 */
public class Avatar extends Agent {

  private static Direction DIRECTION;

  public Avatar(int posX, int posY) {
    super(posX, posY);
  }

  @Override public Agent doIt() {
    System.out.println("Avatar parle.");
    return null;
  }

  public static Direction getDIRECTION() {
    return DIRECTION;
  }

  public static void setDIRECTION(Direction DIRECTION) {
    Avatar.DIRECTION = DIRECTION;
  }
}
