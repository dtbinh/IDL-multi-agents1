package pacman;

import java.util.Random;

import core.Agent;
import core.Coordonnees;
import core.Direction;
import core.Environement;
import util.Data;

public class Poursuiveur extends Agent {

  public Poursuiveur(int posX, int posY, Integer vitesse) {
    super(posX, posY);
    this.vitesse = vitesse;
  }

  @Override public Environement doItWithEnv(Environement env) {
    Coordonnees coordOptimales = findOptimalDirection(env);

    // Si on a des coord optimales alors on bouge
    if (coordOptimales != null) {
      // On supprime de la grille
      env.deleteAgent(this.getPosX(), this.getPosY());
      // On ajoute dans la grille
      this.setPosX(coordOptimales.getX());
      this.setPosY(coordOptimales.getY());
      env.addAgent(this);

      // On modifie les coord de l'agent dans la liste des agents (dans l'env)
      int indexAgent = env.getAgents().indexOf(this);
      env.getAgents().get(indexAgent).setPosX(coordOptimales.getX());
      env.getAgents().get(indexAgent).setPosY(coordOptimales.getY());
    }
    return env;
  }

  private Coordonnees findOptimalDirection(Environement env) {
    // Utiles pour parcourir les 8 voisins
    Direction[] directions = Direction.values();
    Random rand = new Random();
    int r = rand.nextInt(directions.length-1);

    // Utiles pour choix de la direction optimale
    Integer distanceOptimale = Data.size * Data.size;
    Coordonnees coordonnees = null;

    for (int index = r; index < directions.length + r; index++) {
      // On recupere la direction
      Direction direction = directions[index % directions.length];

      int nouveauX = (this.getPosX() + direction.getDeltaX()) % Data.size;
      int nouveauY = (this.getPosY() + direction.getDeltaY()) % Data.size;

      if (nouveauX == -1) {
        nouveauX = Data.size - 1;
      }
      if (nouveauY == -1) {
        nouveauY = Data.size - 1;
      }

      Integer distanceCalculee = env.getDistance(nouveauX, nouveauY);

      if (distanceCalculee < distanceOptimale && !env.agentIsPresent(nouveauX, nouveauY)) {
        distanceOptimale = distanceCalculee;
        coordonnees = new Coordonnees(nouveauX, nouveauY);
      }
    }

    return coordonnees;
  }

}
