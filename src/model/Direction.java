package model;

/**
 * Classe qui représente une direction
 * Created by Pauline on 25/01/2016.
 */
public enum Direction {
  NORD_OUEST(-1, 1),
  NORD(0, 1),
  NORD_EST(1, 1),
  EST(1, 0),
  SUD_EST(1, -1),
  SUD(0, -1),
  SUD_OUEST(-1, -1),
  OUEST(-1, 0);

  private int deltaX;
  private int deltaY;

  Direction(int deltaX, int deltaY) {
    this.deltaX = deltaX;
    this.deltaY = deltaY;
  }

  public int getDeltaX() {
    return deltaX;
  }

  public int getDeltaY() {
    return deltaY;
  }
}
