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
	
    Data.size = 2;    
    Data.tours = 20;
    Data.vitesse = 1000;
    Data.grilleVisible = false;
    Data.equite = true;
    Data.tp=1;
    
    //Donnes en fonction du tp
    if(Data.tp==1)
    	Data.nombreAgents = 100;
    if(Data.tp==2){
    	Data.seedPoisson = 2;
        Data.seedRequin = 3;
        Data.nombrePoissons=1;
        Data.nombreRequins=1;
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
