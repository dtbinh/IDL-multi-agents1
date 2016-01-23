package main;

import engine.SMA;
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
	
    Data.size = 40;    
    Data.tours = 10;
    Data.vitesse = 1000;
    Data.grilleVisible = false;
    Data.equite = true;
    Data.tp=2;
    
    //Donnes en fonction du tp
    if(Data.tp==1)
    	Data.nombreAgents = 100;
    if(Data.tp==2){
    	Data.seedPoisson = 2;
        Data.seedRequin = 0;
        Data.nombrePoissons=100;
        Data.nombreRequins=100;
    }
    

    try {
      SMA sma = new SMA();
      sma.init();
      sma.run();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
  }

}
