package pacman;

import core.Agent;
import core.Direction;
import core.Environement;
import util.Data;

/**
 * Created by Pauline on 27/01/2016.
 */
public class Avatar extends Agent {

  private static Direction DIRECTION;

  public Avatar(int posX, int posY) {
    super(posX, posY);
    setDIRECTION(Direction.EST);
  }

  @Override public Environement doItWithEnv(Environement env) {
    System.out.println("Avatar parle.");

    // On recupere la direction de l'avatar
    Direction direction = getDIRECTION();

    if (direction != null) {

      int nouveauX = (this.getPosX() + direction.getDeltaX()) % Data.size;
      int nouveauY = (this.getPosY() + direction.getDeltaY()) % Data.size;

      System.out.println("x = "+this.posX + " delta = "+direction.getDeltaX() +" size = "+Data.size);
      System.out.println("y = "+this.posY + " delta = "+direction.getDeltaY() +" size = "+Data.size);
      System.out.println("{ "+nouveauX+" ; "+nouveauY+"}");

      // On verifie s'il y a un agent sur la prochaine case
      boolean occuped = env.agentIsPresent(nouveauX, nouveauY);

      // Si la prochaine case est occupee alors on ne bouge pas
      if (occuped) {

        System.out.println("Avatar dit : 'Je suis bloqué !'");
        return env;

      } else {
        // On supprime de la grille
        env.deleteAgent(this.getPosX(), this.getPosY());
        // On ajoute dans la grille
        this.setPosX(nouveauX);
        this.setPosY(nouveauY);
        env.addAgent(this);

        // On modifie les coord de l'agent dans la liste des agents (dans l'env)
        int indexAgent = env.getAgents().indexOf(this);
        env.getAgents().get(indexAgent).setPosX(nouveauX);
        env.getAgents().get(indexAgent).setPosY(nouveauY);

        return env;
      }

    }
    return env;
  }

  public static Direction getDIRECTION() {
    return DIRECTION;
  }

  public static void setDIRECTION(Direction DIRECTION) {
    Avatar.DIRECTION = DIRECTION;
  }
}
