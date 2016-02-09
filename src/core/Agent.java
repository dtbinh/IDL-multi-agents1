package core;

import java.awt.Color;

public abstract class Agent {

  protected Integer posX;
  protected Integer posY;
  protected Integer pasX; // diagonale
  protected Integer pasY; // diagonale
  protected Environement environnement;
  protected Color couleur;
  protected Integer vitesse;

  /**
   * Constructeur par defaut de la classe Agent. Les coordonnees de l'agent sont
   * initialises a null.
   */
  public Agent() {
	this.posX = null;
	this.posY = null;
  }

  /**
   * Constructeur de la classe Agent
   * 
   * @param posX
   *          la position verticale de l'agent
   * @param posY
   *          la position horizontale de l'agent
   */
  public Agent(int posX, int posY) {
	this.posX = posX;
	this.posY = posY;
  }

  public Integer getVitesse() {
    return vitesse;
  }

  public Color getCouleur() {
	return couleur;
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

  /**
   * Methode de decision d'un agent
   * 
   * @return le nouvel agent s'il y a reproduction
   */
  public Agent doIt() {
	return null;
  }

  /**
   * Methode de decision d'un agent
   * 
   * @return l'environnement modifie
   */
  public Environement doItWithEnv(Environement env) {
	return env;
  }
}
