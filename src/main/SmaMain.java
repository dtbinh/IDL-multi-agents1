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
     * Paramétres optionnels :
     * - visibilité de la grille
     * - équité -> shuffle
     * - grille torique
     * - seed pour le Random (voir https://docs.oracle.com/javase/7/docs/api/java/util/Random.html#setSeed(long))
     */

    Data.size = 5;
    Data.tours = 1000;
    Data.vitesse = 100;
    Data.grilleVisible = false;
    Data.equite = true;
    Data.tp = 2;

    try {
      //Donnes en fonction du tp
      if (Data.tp == 1) {
        Data.nombreAgents = 10;
        SmaBilles sma = new SmaBilles();
        sma.init();
        sma.run();
      }
      if (Data.tp == 2) {
        Data.nombreAgents = 0;

        Data.nombrePoissons = 1;
        Data.nombreRequins = 1;

        Data.seedPoisson = 1;
        Data.seedRequin = 5;

        Data.longeviteRequin = 10;

        SMA sma2 = new SMA();
        sma2.init();
        sma2.run();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
