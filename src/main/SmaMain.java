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
    Data.nombreAgents = 50;
    Data.tours = 500;
    Data.vitesse = 100;

    try {
      SMA sma = new SMA();
      sma.init();
      sma.run();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
  }

}
