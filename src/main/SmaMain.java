package main;

import engine.SMA;
import engine.SmaBilles;
import util.Data;

public class SmaMain {

  public static void main(String[] args) {
    /*
     * Parametres :
     * - taille de la grille
     * - nombre de billes
     * - taille de la bille
     * - vitesse
     * 
     * Param�tres optionnels :
     * - visibilit� de la grille
     * - �quit� -> shuffle
     * - grille torique
     * - seed pour le Random (voir https://docs.oracle.com/javase/7/docs/api/java/util/Random.html#setSeed(long))
     */	
	
    Data.size = 50;    
    Data.tours = 25;
    Data.vitesse = 1000;
    Data.grilleVisible = false;
    Data.equite = true;
    Data.tp = 2;
    
    try {
	    //Donnes en fonction du tp
	    if(Data.tp==1){
	    	Data.nombreAgents = 10;
	    	SmaBilles sma = new SmaBilles();
	    	sma.init();
	    	sma.run();
	    }
	    if(Data.tp==2){
	    	Data.nombreAgents = 0;
	    	Data.seedPoisson = 2;
	        Data.seedRequin = 2;
	        Data.nombrePoissons=100;
	        Data.nombreRequins=100;
	        SMA sma2 = new SMA();
	        sma2.init();
	        sma2.run();
	    }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }    
  }

}
