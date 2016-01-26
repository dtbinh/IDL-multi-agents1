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

    Data.size = 10;
    Data.tours = 25;
    Data.vitesse = 1000;
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

        Data.nombrePoissons = 10;
        Data.nombreRequins = 1;

        Data.seedPoisson = 2;
        Data.seedRequin = 100;

        Data.longeviteRequin = 11;

        SMA sma2 = new SMA();
        sma2.init();
        sma2.run();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
