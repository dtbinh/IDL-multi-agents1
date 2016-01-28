package wator;

import core.Agent;
import core.Coordonnees;
import core.Direction;
import util.Data;

import java.util.Random;

public class Requin extends Agent {

  private Integer pasX;
  private Integer pasY;
  private int tour;
  private int pv; // points de vie
  private Poisson poissonMange; // le poisson qui est mange à ce tour

  public Requin(int posX, int posY) {
    super();
    this.posX = posX;
    this.posY = posY;
    this.pasX = null;
    this.pasY = null;
    this.pv = Data.longeviteRequin;
    this.tour = 0;
    this.poissonMange = null;
  }

  public Poisson getPoissonMange() {
    return poissonMange;
  }

  public Integer getPasX() {
    return pasX;
  }

  public void setPasX(int pasX) {
    this.pasX = pasX;
  }

  public Integer getPasY() {
    return pasY;
  }

  public void setPasY(int pasY) {
    this.pasY = pasY;
  }

  public int getPv() {
    return pv;
  }

  public void setPv(int pv) {
    this.pv = pv;
  }

  public Requin doIt() {
    this.poissonMange = null;
    Requin requin = null;

    // On decremente le nombre de points de vie du requin
    this.setPv(this.getPv() - 1);

    Coordonnees coordPoisson = peutManger();

    if (coordPoisson != null) { // Alors il y a un poisson à manger

      // On met à jour le nombre de pv du requin
      this.setPv(Data.longeviteRequin);

      // On supprime le poisson
      this.poissonMange = new Poisson(coordPoisson.getX(), coordPoisson.getY());
      this.poissonMange.setEnvironnement(this.environnement);
      this.environnement.deleteAgent(coordPoisson.getX(), coordPoisson.getY());

      if (Data.nombrePoissons > 0) {
        Data.nombrePoissons--;
        Data.nombreAgents--;
      }
      //seDeplacer(coordPoisson.getX(), coordPoisson.getY());
      // On déplace le requin jusque la position du poisson
      //seDeplacer(coordPoisson.getX(), coordPoisson.getY());
      /*this.envi.deleteAgent(this.getPosX(), this.getPosY());
      this.setPosX(coordPoisson.getX());
      this.setPosY(coordPoisson.getY());*/
      //System.out.println();

    } else { // Alors il n'y a aucun poisson à manger*/
      Coordonnees placeLibre = chercherPlaceLibre();
      if (placeLibre != null) { // Il y a une place libre à proximite

        // Alors on place notre bebe ici et on se deplace en même temps
        int oldX = this.getPosX();
        int oldY = this.getPosY();
        seDeplacer(placeLibre.getX(), placeLibre.getY());
        requin = seReproduire(oldX, oldY);
      }
    }

    return requin;
  }

  /**
   * Verifie si un requin doit mourir ou non
   *
   * @return <code>true</code> si le requin doit mourir, <code>false</code> sinon.
   */

  public boolean doitMourir() {
    return this.getPv() == 0;
  }

  /**
   * Verifie si le requin peut se reproduire.
   *
   * @return <code>true</code> si le requin peut se reproduire, <code>false</code> sinon
   */
  private boolean peutSeReproduire() {
    if (this.tour == Data.seedRequin) {
      this.tour = 0;
      return true;
    } else {
      this.tour++;
      return false;
    }
  }

  private Requin seReproduire(int x, int y) {
    if (peutSeReproduire() && Data.nombreAgents < (Data.size * Data.size)) {
      Requin requin = new Requin(x, y);
      requin.setEnvironnement(this.environnement);
      Data.nombreRequins++;
      Data.nombreAgents++;
      return requin;
    } else {
      return null;
    }
  }

  /**
   * Verifie si le requin peut manger un poisson aux alentours.
   * Si c'est le cas, on retourne les coordonnees du poisson à manger.
   *
   * @return Le couple de coordonees du poisson qui peut être mange.
   */
  private Coordonnees peutManger() {
    Coordonnees coordonnees = new Coordonnees();
    Direction[] directions = Direction.values();

    // On va parcourir toutes les directions en partant d'une direction au hasard
    Random rand = new Random();
    int r = rand.nextInt(directions.length);

    for (int index = r; index < directions.length + r; index++) {
      // On recupère la direction
      Direction d = directions[index % directions.length];

      int testX = (this.getPosX() + d.getDeltaX());
      int testY = (this.getPosY() + d.getDeltaY());

      if (testX < 0) {
        testX = 0;
      }
      if (testX >= Data.size) {
        testX = Data.size - 1;
      }
      if (testY < 0) {
        testY = 0;
      }
      if (testY >= Data.size) {
        testY = Data.size - 1;
      }

      if (this.environnement.agentIsPresent(testX, testY)) {
        if (this.environnement.getAgent(testX, testY) instanceof Poisson) {
          coordonnees.setX(testX);
          coordonnees.setY(testY);
          return coordonnees;
        }
      }
    }
    return null;
  }

  /**
   * Verifie s'il y a une place libre aux alentours.
   * Si c'est le cas, on retourne les coordonees de la place libre.
   *
   * @return Le couple de coordonnees d'une place libre à proximite du requin.
   */
  private Coordonnees chercherPlaceLibre() {
    Coordonnees coordonnees = new Coordonnees(0, 0);
    boolean ok = false;

    Direction[] directions = Direction.values();

    // On va parcourir toutes les directions en partant d'une direction au hasard
    Random rand = new Random();
    int r = rand.nextInt(directions.length);

    for (int index = r; index < directions.length + r; index++) {
      // On recupère la direction
      Direction d = directions[index % directions.length];
      int testX = (this.getPosX() + d.getDeltaX());
      int testY = (this.getPosY() + d.getDeltaY());

      if (testX < 0) {
        testX = 0;
      }
      if (testX >= Data.size) {
        testX = Data.size - 1;
      }
      if (testY < 0) {
        testY = 0;
      }
      if (testY >= Data.size) {
        testY = Data.size - 1;
      }

      if (!this.getEnvironnement().agentIsPresent(testX, testY)) {
        coordonnees.setX(testX);
        coordonnees.setY(testY);
        ok = true;
        break;
      }
    }

    return ok ? coordonnees : null;
  }

  /**
   * Faire se deplacer un requin.
   */
  private void seDeplacer(int nouvellePositionX, int nouvellePositionY) {
    int oldX = this.getPosX();
    int oldY = this.getPosY();

    this.setPosX(nouvellePositionX);
    this.setPosY(nouvellePositionY);
    if (this.environnement.addAgent(this)) {
      this.environnement.deleteAgent(oldX, oldY);
    } else {
      this.setPosX(oldX);
      this.setPosY(oldY);
    }
  }

  /**
   * Genere un nombre aleatoire entre -1 et 1.
   *
   * @return un nombre aleatoire
   */
  private int genererDirection() {
    int result;
    Random r = new Random();
    do {
      result = r.nextInt(3) - 1;
    } while (result == 0);
    return result;
  }
}
