package core;

/**
 * Classe qui représente un couple de coordonnées
 * Created by Pauline on 25/01/2016.
 */
public class Coordonnees {

  private int x;
  private int y;

  public Coordonnees(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Coordonnees() {

  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
