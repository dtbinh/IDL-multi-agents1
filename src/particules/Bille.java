package particules;

import java.awt.Color;
import java.util.Random;

import core.Agent;
import core.Direction;
import util.Data;

public class Bille extends Agent {

  public Bille(int posX, int posY) {
    this.posX = posX;
    this.posY = posY;
    this.pasX = null;
    this.pasY = null;
    Random r = new Random();
    couleur = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
  }

  public Bille doIt() {
    // On sauvegarde les anciennes coordonnées
    int oldX = this.getPosX();
    int oldY = this.getPosY();

    // Si aucune direction déjà choisie (ie. pasX et pasY = 0)
    if (this.getPasX() == null || this.getPasY() == null) {
      // Alors on génére les directions pour les diagonales
      this.pasX = Direction.genererDirection();
      this.pasY = Direction.genererDirection();
    }

    // Calcul des nouvelles coordonnées
    int nouveauX = (this.posX + this.pasX);
    int nouveauY = (this.posY + this.pasY);

    // S'il y a une borne de grille, on change de direction
    if (nouveauX == Data.size) {
      if (nouveauY == Data.size) {
        // on est dans le coin haut droite
        // on décrémente X et on part en bas é gauche
        this.posX--;
        this.posY--;
        this.pasX = -1;
        this.pasY = -1;
      } else if (nouveauY == -1) {
        // on est dans le coin bas droite
        // on incrémente Y et on part en haut é gauche
        this.posY++;
        this.pasX = -1;
        this.pasY = 1;
      } else { // on rebondit sur la tranche de droite
        this.pasX = this.pasX * -1;
      }
    } else if (nouveauX == -1) {
      if (nouveauY == Data.size) {
        this.posX++;
        this.pasX = 1;
        this.pasY = -1;
      }
      if (nouveauY == -1) {
        // on est dans le coin en bas é gauche
        // on incrémente X et on part en haut é droite
        this.posX++;
        this.pasX = 1;
        this.pasY = 1;
      } else {
        // on rebondit sur la tranche de gauche
        this.pasX = this.pasX * -1;
      }
    } else {
      if (nouveauY == -1 || nouveauY == Data.size) {
        // on rebondit sur la tranche du bas ou sur celle du haut
        this.pasY = this.pasY * -1;
      }
    }

    // on recalcule les nouvelles coordonnées
    nouveauX = (this.posX + this.pasX);
    nouveauY = (this.posY + this.pasY);

    // S'il y a un agent qui se trouve é ces nouvelles coord.
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
      // Si on ne peut pas ajouter l'agent é la nouvelle position
      // Alors on reste statique et on genere une nouvelle direction aleatoire
      // puis on attend le prochain tour
      this.setPosX(oldX);
      this.setPosY(oldY);
      this.setPasX(Direction.genererDirection());
      this.setPasY(Direction.genererDirection());
    }
    return null;
  }

}
