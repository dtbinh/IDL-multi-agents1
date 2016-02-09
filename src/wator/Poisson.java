package wator;

import core.Agent;
import core.Direction;
import util.Data;

public class Poisson extends Agent {

  private int tour;

  public Poisson(int posX, int posY) {
    super();
    this.posX = posX;
    this.posY = posY;
    this.pasX = null;
    this.pasY = null;
    this.tour = 0;
  }

  public Poisson doIt() {
    int oldX = this.getPosX();
    int oldY = this.getPosY();
    seDeplacer();
    Poisson newPoisson = seReproduire(oldX, oldY);

    return newPoisson;
  }

  /**
   * Verifie si un poisson peut se reproduire.
   * @return <code>true</code> si un poisson peut se reproduire, <code>false</code> sinon.
   */
  private boolean peutSeReproduire() {
    if (this.tour == Data.seedPoisson) {
      this.tour = 0;
      return true;
    } else {
      this.tour++;
      return false;
    }
  }

  /**
   * Faire se repoduire un poisson.
   * @param x la position X du nouveau poisson
   * @param y la position Y du nouveau poisson
   * @return le nouveau poisson si cree, null sinon.
   */
  private Poisson seReproduire(int x, int y) {
    if (peutSeReproduire() && Data.nombreAgents < (Data.size * Data.size)) {
      Poisson poisson = new Poisson(x, y);
      poisson.setEnvironnement(this.environnement);
      Data.nombreAgents++;
      Data.nombrePoissons++;
      return poisson;
    } else
      return null;
  }

  /**
   * Deplace un poisson dans son environnement.
   */
  private void seDeplacer() {
    // On sauvegarde les anciennes coordonnees
    int oldX = this.getPosX();
    int oldY = this.getPosY();

    // Si aucune direction deje choisie (ie. pasX et pasY = 0)
    if (this.getPasX() == null || this.getPasY() == null) {
      // Alors on genere les directions pour les diagonales
      this.pasX = Direction.genererDirection();
      this.pasY = Direction.genererDirection();
    }

    // Calcul des nouvelles coordonnees
    int nouveauX = (this.posX + this.pasX);
    int nouveauY = (this.posY + this.pasY);
    // S'il y a une borne de grille, on change de direction
    if (nouveauX == Data.size) {
      if (nouveauY == Data.size) {
        // on est dans le coin haut droite
        // on decremente X et on part en bas a gauche
        this.pasX = -1;
        this.pasY = -1;
      } else if (nouveauY == -1) {
        // on est dans le coin bas droite
        // on incremente Y et on part en haut a gauche
        this.pasX = -1;
        this.pasY = 1;
      } else { // on rebondit sur la tranche de droite
        this.pasX = this.pasX * -1;
      }
    } else if (nouveauX == -1) {
      if (nouveauY == Data.size) {
        this.pasX = 1;
        this.pasY = -1;
      } else if (nouveauY == -1) {
        // on est dans le coin en bas e gauche
        // on incremente X et on part en haut a droite
        this.pasX = 1;
        this.pasY = 1;
      } else if (nouveauX != 0) {
        // on rebondit sur la tranche de gauche
        this.pasX = this.pasX * -1;
      }
    } else {
      if (nouveauY == -1 || nouveauY == Data.size) {
        // on rebondit sur la tranche du bas ou sur celle du haut
        this.pasY = this.pasY * -1;
      }
    }

    // on recalcule les nouvelles coordonnees
    nouveauX = (this.posX + this.pasX);
    nouveauY = (this.posY + this.pasY);

    // S'il y a un agent qui se trouve a ces nouvelles coord.
    if (this.environnement.agentIsPresent(nouveauX, nouveauY)) {
      // on part sur la gauche de notre direction initiale
      if (this.pasX == this.pasY) {
        nouveauX = (nouveauX + (this.pasX * -1));
      } else {
        nouveauY = (nouveauY + (this.pasY * -1));
      }
    }

    // Modification de l'agent
    this.setPosX(nouveauX);
    this.setPosY(nouveauY);

    // Modification de l'environnement
    if (this.environnement.addAgent(this)) {
      this.environnement.deleteAgent(oldX, oldY);
    } else {
      // Si on ne peut pas ajouter l'agent a la nouvelle position
      // Alors on reste statique et on genere une nouvelle direction aleatoire
      // puis on attend le prochain tour
      this.setPosX(oldX);
      this.setPosY(oldY);
      this.setPasX(Direction.genererDirection());
      this.setPasY(Direction.genererDirection());
    }
  }

}
