package model;

import util.Data;

import java.util.Random;

public class Poisson implements Agent{

	private int posX;
    private int posY;
    private Integer pasX;
    private Integer pasY;
    private Environement envi;
    private int tour;
	  
	public Poisson(int posX, int posY) {
	    super();
	    this.posX = posX;
	    this.posY = posY;
	    this.pasX = null;
	    this.pasY = null;
	    this.tour=0;
	  }
	
	@Override
	public int getPosX() {
		return posX;
	}
	
	@Override
	public void setPosX(int posX) {
		this.posX = posX;		
	}

	@Override
	public int getPosY() {
		return posY;
	}

	@Override
	public void setPosY(int posY) {
		this.posY = posY;
		
	}

	@Override
	public Integer getPasX() {
		return pasX;
	}

	@Override
	public void setPasX(int pasX) {
		this.pasX = pasX;
	}

	@Override
	public Integer getPasY() {
		return pasY;
	}

	@Override
	public void setPasY(int pasY) {
		this.pasY = pasY;		
	}

	@Override
	public void setEnv(Environement env) {
		this.envi = env;		
	}

	@Override
	public Environement getEnv() {
		return this.envi;
	}

	@Override
	public Poisson doIt() {
		int oldX = this.getPosX();
	    int oldY = this.getPosY();
		seDeplacer();
	    Poisson newPoisson = seReproduire(oldX,oldY);
		
		return newPoisson;
	}

	/**
	 * V&eacute;rifie si un poisson peut se reproduire.
	 * @return <code>true</code> si un poisson peut se reproduire, <code>false</code> sinon.
	 */
	private boolean peutSeReproduire(){
		if (this.tour == Data.seedPoisson){
			this.tour=0;
			return true;
		} else{
			this.tour++;
			return false;
		}			
	}

	/**
	 * Faire se repoduire un poisson.
	 * @param x la position X du nouveau poisson
	 * @param y la position Y du nouveau poisson
     * @return le nouveau poisson si créé, null sinon.
     */
	private Poisson seReproduire(int x, int y){
		if (peutSeReproduire() && Data.nombreAgents <(Data.size*Data.size)){
			Poisson poisson = new Poisson(x,y);
			poisson.setEnv(this.envi);
			Data.nombreAgents++;
			Data.nombrePoissons++;
			return poisson;
		}
		else
			return null;
	}

	/**
	 * Déplace un poisson dans son environnement.
	 */
	private void seDeplacer(){
		// On sauvegarde les anciennes coordonnées
	    int oldX = this.getPosX();
	    int oldY = this.getPosY();
	    
	    // Si aucune direction déjé choisie (ie. pasX et pasY = 0)
	    if (this.getPasX() == null || this.getPasY() == null) {
	      // Alors on génére les directions pour les diagonales
	      this.pasX = genererDirection();
	      this.pasY = genererDirection();
	    }

	    // Calcul des nouvelles coordonnées
	    int nouveauX = (this.posX + this.pasX);
	    int nouveauY = (this.posY + this.pasY);
	    // S'il y a une borne de grille, on change de direction
	    if (nouveauX == Data.size) {
	      if (nouveauY == Data.size) {
	        // on est dans le coin haut droite
	        // on décrémente X et on part en bas é gauche
	        //this.posX--;
	        //this.posY--;
	        this.pasX = -1;
	        this.pasY = -1;
	      } else if (nouveauY == -1) {
	        // on est dans le coin bas droite
	        // on incrémente Y et on part en haut é gauche
	        //this.posY++;
	        this.pasX = -1;
	        this.pasY = 1;
	      } else { // on rebondit sur la tranche de droite
	        this.pasX = this.pasX * -1;
	      }
	    } else if (nouveauX == -1) {
	      if (nouveauY == Data.size) {
	        //this.posX++;
	        this.pasX = 1;
	        this.pasY = -1;
	      }
	      else if (nouveauY == -1) {
	        // on est dans le coin en bas é gauche
	        // on incrémente X et on part en haut é droite
	        //this.posX++;
	        this.pasX = 1;
	        this.pasY = 1;
	      } else if(nouveauX!=0){
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
	    if (this.envi.agentIsPresent(nouveauX, nouveauY)) {
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
	    if (this.envi.addAgent(this)) {
	      this.envi.deleteAgent(oldX, oldY);
	    }
	    else {
	      // Si on ne peut pas ajouter l'agent é la nouvelle position
	      // Alors on reste statique et on genere une nouvelle direction aleatoire
	      // puis on attend le prochain tour
	      this.setPosX(oldX);
	      this.setPosY(oldY);
	      this.setPasX(genererDirection());
	      this.setPasY(genererDirection());
	    }
	}

	/**
	 * Génére un nombre aléatoire entre -1 et 1
	 * @return un nombre aléatoire
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
