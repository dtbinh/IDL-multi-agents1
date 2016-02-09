package pacman;

import core.Agent;
import core.Direction;
import core.Environement;
import util.Data;

public class Avatar extends Agent {

  private static Direction DIRECTION;

  public Avatar(int posX, int posY, Integer vitesse) {
    super(posX, posY);
    setDIRECTION(Direction.OUEST);
    this.vitesse = vitesse;
  }

  @Override public Environement doItWithEnv(Environement env) {
    // On recupere la direction de l'avatar
    Direction direction = getDIRECTION();

    if (direction != null) {

      int nouveauX;
      int nouveauY;

      if (Data.avatarTorique) {
        nouveauX = (this.getPosX() + direction.getDeltaX()) % Data.size;
        nouveauY = (this.getPosY() + direction.getDeltaY()) % Data.size;

        if(nouveauX == -1) {
          nouveauX = Data.size - 1;
        }
        if (nouveauY == -1) {
          nouveauY = Data.size - 1;
        }
      } else {
        nouveauX = this.getPosX() + direction.getDeltaX();
        nouveauY = this.getPosY() + direction.getDeltaY();

        if (nouveauX == -1) {
          nouveauX = 0;
        }
        if (nouveauX == Data.size) {
          nouveauX = Data.size -1;
        }
        if (nouveauY == -1) {
          nouveauY = 0;
        }
        if (nouveauY == Data.size) {
          nouveauY = Data.size - 1;
        }
      }

      // On verifie s'il y a un agent sur la prochaine case
      boolean occuped = env.agentIsPresent(nouveauX, nouveauY);

      // Si la prochaine case est occupee alors on ne bouge pas
      if (occuped) {

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

  public Boolean isCatched(Environement env) {
    Direction directions[] = Direction.values();

    for (Direction direction : directions) {
      int nouveauX = (this.getPosX() + direction.getDeltaX()) % Data.size;
      int nouveauY = (this.getPosY() + direction.getDeltaY()) % Data.size;

      if (nouveauX == -1) {
        nouveauX = Data.size - 1;
      }
      if (nouveauY == -1) {
        nouveauY = Data.size - 1;
      }

      if (env.agentIsPresent(nouveauX, nouveauY)) {
        return env.getAgent(nouveauX, nouveauY) instanceof Poursuiveur;
      }
    }
    return false;
  }
}
