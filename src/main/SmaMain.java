package main;

import engine.SMA;
import model.Environement;
import util.Data;
import view.Panel;
import view.Vue;

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
    Data.nombreAgents = 15;
    Data.tours = 25;
    Data.vitesse = 100;

//    Panel panel = new Panel(50);
//    Data.v = new Vue(panel);
//    Data.v.addObserver(panel);

    try {
      SMA sma = new SMA();
      sma.init();
      sma.run();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
  }

}
