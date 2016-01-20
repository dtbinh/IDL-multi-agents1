package model;

import java.util.Random;

import util.Data;

public class Agent {

  private int posX;
  private int posY;
  private int pasX;
  private int pasY;
  private Environement envi;

  public Agent(int posX, int posY) {
    super();
    this.posX = posX;
    this.posY = posY;
  }

  public int getPosX() {
    return posX;
  }

  public void setPosX(int posX) {
    this.posX = posX;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }

  public int getPasX() {
    return pasX;
  }

  public void setPasX(int pasX) {
    this.pasX = pasX;
  }

  public int getPasY() {
    return pasY;
  }

  public void setPasY(int pasY) {
    this.pasY = pasY;
  }

  public void setEnv(Environement env) {
    this.envi = env;
  }

  public Environement getEnv() {
    return this.envi;
  }

  public void doIt() {
    // On sauvegarde les anciennes coordonnées
    int oldX = this.getPosX();
    int oldY = this.getPosY();

    // On récupère les anciennes valeurs diagonales
    int oldPasX = this.getPasX();
    int oldPaxY = this.getPasY();

    // Si aucune direction déjà choisie (ie. pasX et pasY = 0)
    if (oldPasX == 0 || oldPaxY == 0) {
      // Alors on génère les directions pour les diagonales
      this.pasX = genererDirection();
      this.pasY = genererDirection();

    }

    // Calcul des nouvelles coordonnées
    // int nouveauX = (this.posX + this.pasX) % (Data.size-1);
    // int nouveauY = (this.posY + this.pasY) % (Data.size-1);

    int nouveauX = (this.posX + this.pasX);
    int nouveauY = (this.posY + this.pasY);

    // S'il y a une borne de grille, on change de direction
    if (nouveauX == 50) {
      if (nouveauY == 50) {
        // on est dans le coin haut droite
        // on décrémente X et on part en bas à gauche
        this.posX--;
        this.pasX = -1;
        this.pasY = -1;
      } else if (nouveauY == -1) {
        // on est dans le coin bas droite
        // on incrément Y et on part en haut à gauche
        this.posY++;
        this.pasX = -1;
        this.pasY = 1;
      } else { // on rebondit sur la tranche de droite
        this.pasX = this.pasX * -1;
      }
    } else if (nouveauX == -1) {
      if (nouveauY == -1) {
        // on est dans le coin en bas à gauche
        // on incrément X et on part en haut à droite
        this.posX++;
        this.pasX = 1;
        this.pasY = 1;
      } else {
        // on rebondit sur la tranche de gauche
        this.pasX = this.pasX * -1;
      }
    } else {
      if (nouveauY == -1 || nouveauY == 50) {
        // on rebondit sur la tranche du bas ou sur celle du haut
        this.pasY = this.pasY * -1;
      }
    }

    // on recalcule les nouvelles coordonnées
    nouveauX = (this.posX + this.pasX);
    nouveauY = (this.posY + this.pasY);

    // S'il y a un agent qui se trouve à ces nouvelles coord.
    if (this.envi.agentIsPresent(nouveauX, nouveauY)) {
      // on part dans la direction opposée
      nouveauX = (this.posX + (this.pasX * -1)) % (Data.size - 1);
      nouveauY = (this.posY + (this.pasY * -1)) % (Data.size - 1);
    }

    // Modification de l'agent
    this.setPosX(nouveauX);
    this.setPosY(nouveauY);

    // Modification de l'environnement
    this.envi.deleteAgent(oldX, oldY);
    this.envi.addAgent(this);

  }

  private int genererDirection() {
    int result;
    Random r = new Random();
    do {
      result = r.nextInt(3) - 1;
    } while (result == 0);
    return result;
  }
}
