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
     * Paramètres optionnels :
     * - visibilité de la grille
     * - équité -> shuffle
     * - grille torique
     * - seed pour le Random (voir https://docs.oracle.com/javase/7/docs/api/java/util/Random.html#setSeed(long))
     */

    Data.size = 50;
    Data.nombreAgents = 150;
    Data.tours = 1000;
    Data.vitesse = 150;
    Data.grilleVisible = true;
    Data.equite = true;

    try {
      SMA sma = new SMA();
      sma.init();
      sma.run();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
  }

}
