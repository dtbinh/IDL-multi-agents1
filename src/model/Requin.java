package model;

import java.util.Random;

import util.Data;

public class Requin implements Agent{

	private int posX;
	  private int posY;
	  private Integer pasX;
	  private Integer pasY;
	  private Environement envi;
	  
	  public Requin(int posX, int posY) {
		    super();
		    this.posX = posX;
		    this.posY = posY;
		    this.pasX = null;
		    this.pasY = null;
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
	public void doIt() {
		// On sauvegarde les anciennes coordonn�es
	    int oldX = this.getPosX();
	    int oldY = this.getPosY();
	    
	    // Si aucune direction d�j� choisie (ie. pasX et pasY = 0)
	    if (this.getPasX() == null || this.getPasY() == null) {
	      // Alors on g�n�re les directions pour les diagonales
	      this.pasX = genererDirection();
	      this.pasY = genererDirection();
	    }

	    // Calcul des nouvelles coordonn�es
	    int nouveauX = (this.posX + this.pasX);
	    int nouveauY = (this.posY + this.pasY);
	    System.out.println("newX:"+nouveauX+" | newY:"+nouveauY);
	    // S'il y a une borne de grille, on change de direction
	    if (nouveauX == Data.size) {
	      if (nouveauY == Data.size) {
	        // on est dans le coin haut droite
	        // on d�cr�mente X et on part en bas � gauche
	        //this.posX--;
	        //this.posY--;
	        this.pasX = -1;
	        this.pasY = -1;
	      } else if (nouveauY == -1) {
	        // on est dans le coin bas droite
	        // on incr�mente Y et on part en haut � gauche
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
	        // on est dans le coin en bas � gauche
	        // on incr�mente X et on part en haut � droite
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

	    // on recalcule les nouvelles coordonn�es
	    nouveauX = (this.posX + this.pasX);
	    nouveauY = (this.posY + this.pasY);
	       
	    // S'il y a un agent qui se trouve � ces nouvelles coord.
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
	      // Si on ne peut pas ajouter l'agent � la nouvelle position
	      // Alors on reste statique et on genere une nouvelle direction aleatoire
	      // puis on attend le prochain tour
	      this.setPosX(oldX);
	      this.setPosY(oldY);
	      this.setPasX(genererDirection());
	      this.setPasY(genererDirection());
	    }		
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