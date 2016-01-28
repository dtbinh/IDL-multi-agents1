package core;

public abstract class Agent {

  protected Integer posX;
  protected Integer posY;
  protected Environement environnement;

  /**
   * Constructeur par defaut de la classe Agent.
   * Les coordonnees de l'agent sont initialises a null.
   */
  public Agent() {
    this.posX = null;
    this.posY = null;
  }

  /**
   * Constructeur de la classe Agent
   * @param posX la position verticale de l'agent
   * @param posY la position horizontale de l'agent
   */
  public Agent(int posX, int posY) {
    this.posX = posX;
    this.posY = posY;
  }

  public int getPosX() {
    return posX;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosX(int posX) {
    this.posX = posX;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }

  public void setEnvironnement(Environement environnement) {
    this.environnement = environnement;
  }

  public Environement getEnvironnement() {

    return environnement;
  }

  /**
   * Methode de decision d'un agent
   * @return le nouvel agent s'il y a reproduction
   */
  public Agent doIt() {
    return null;
  }
}
