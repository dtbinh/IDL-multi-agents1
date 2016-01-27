package main;

import pacman.SmaPacman;
import particules.SmaBilles;
import util.Data;
import util.Summary;
import wator.SMAwator;

public class SmaMain {

  public static void main(String[] args) {

    // Le Système multi agents a lancer
    Data.tp = 2;

    Data.size = 100;
    Data.tours = 1000;
    Data.vitesse = 100;
    Data.grilleVisible = false;
    Data.equite = true;

    try {
      // Donnees en fonction du systeme
      if (Data.tp == 1) {
        Data.nombreAgents = 100;
        SmaBilles sma = new SmaBilles();
        sma.init();
        sma.run();
      }
      if (Data.tp == 2) {
        Data.nombreAgents = 0;

        Data.nombrePoissons = 170;
        Data.nombreRequins = 55;

        Data.seedPoisson = 5;
        Data.seedRequin = 6;

        Data.longeviteRequin = 10;

        Summary.setOutputFile("C:\\Users\\AnaGissel\\Desktop\\wator.txt");

        SMAwator sma2 = new SMAwator();
        sma2.init();
        sma2.run();
      }
      if (Data.tp == 3) {
        Data.nombreAgents = 0;
        Data.nombreBlocs = 100; // TODO: a modifier pour mettre en pourcentage
        Data.nombrePoursuiveurs = 2;

        Data.afficherDistances = false;

        SmaPacman sma = new SmaPacman();
        sma.init();
        sma.run();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
