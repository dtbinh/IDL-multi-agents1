package pacman;

import core.Agent;
import core.Environement;
import core.SMA;
import util.Data;
import view.ControlPanel;
import view.GridPanel;
import view.Vue;

import java.util.*;

/**
 * Created by Pauline on 27/01/2016.
 */
public class SmaPacman implements SMA {

  private Environement env;
  private Dijkstra dijkstra;

  public SmaPacman() {
    this.dijkstra = new Dijkstra();
  }

  @Override public void init() {
    // on initialise l'environnement
    this.env = new Environement();
    this.env.init(Data.size);

    if (Data.pourcentageBlocs > 99) {
      System.out.println("Attention, trop de blocs");
      Data.pourcentageBlocs = 99;
    }

    int nombreblocs = ((Data.size * Data.size) / 100) * Data.pourcentageBlocs;

    /* ************************************ Creation des blocs ************************************ */
    for (int indexBlocs = 0; indexBlocs < nombreblocs; indexBlocs++) {
      int x = obtenirPositionRandom(Data.size);
      int y = obtenirPositionRandom(Data.size);

      Bloc bloc = new Bloc(x, y);
      Boolean ajoute = this.env.addAgent(bloc);
      while (!ajoute) { // tant que non ajoute
        bloc.setPosX(obtenirPositionRandom(Data.size));
        bloc.setPosY(obtenirPositionRandom(Data.size));
        ajoute = this.env.addAgent(bloc);
      }
      this.env.getAgents().add(bloc);
      Data.nombreAgents++;
    }

    /* ************************************ Creation des poursuiveurs ************************************ */
    for (int indexPoursuiveurs = 0; indexPoursuiveurs < Data.nombrePoursuiveurs; indexPoursuiveurs++) {
      int x = obtenirPositionRandom(Data.size);
      int y = obtenirPositionRandom(Data.size);

      Poursuiveur poursuiveur = new Poursuiveur(x, y);
      Boolean ajoute = this.env.addAgent(poursuiveur);
      while (!ajoute) { // tant que non ajoute
        poursuiveur.setPosX(obtenirPositionRandom(Data.size));
        poursuiveur.setPosY(obtenirPositionRandom(Data.size));
        ajoute = this.env.addAgent(poursuiveur);
      }
      this.env.getAgents().add(poursuiveur);
      Data.nombreAgents++;
    }

    /* ************************************ Creation de l'avatar ************************************ */
    int x = obtenirPositionRandom(Data.size);
    int y = obtenirPositionRandom(Data.size);
    Avatar avatar = new Avatar(x, y);
    Boolean ajoute = this.env.addAgent(avatar);
    while (!ajoute) { // tant que non ajoute
      avatar.setPosX(obtenirPositionRandom(Data.size));
      avatar.setPosY(obtenirPositionRandom(Data.size));
      ajoute = this.env.addAgent(avatar);
    }
    this.env.getAgents().add(avatar);
    Data.nombreAgents++;

    this.env = this.dijkstra.calculateDistances(this.env, x, y);
  }

  @Override public void run() {

    // initialiser la vue
    GridPanel panel = new GridPanel(this.env);
    ControlPanel control = new ControlPanel();
    Vue v = new Vue(panel, control);
    v.addObserver(control);
    v.addObserver(panel);

    for (int tour = 0; tour < Data.tours; tour++) {
      if (Data.equite) {
        Collections.shuffle(this.env.getAgents());
      }

      Environement newEnv = null;

      // Recuperer les nouvelles coord de l'Avatar
      Integer xAvatar = null;
      Integer yAvatar = null;

      // On fait parler chaque agent
      for (Agent agent : this.env.getAgents()) {
        newEnv = agent.doItWithEnv(this.env);

        if (agent instanceof Avatar) {
          xAvatar = agent.getPosX();
          yAvatar = agent.getPosY();

          if (((Avatar) agent).isCatched(this.env)){
            Data.tours = 0;
            System.out.println("GAME OVER.");
            continue;
          }
        }

        // Mise a jour de l'environnement, potentiellement modifie par un agent
        this.env = newEnv;
      }

      v.updateVue(this.env);

      // Mettre a jour les distances
      this.env = this.dijkstra.calculateDistances(this.env, xAvatar, yAvatar);

      try {
        Thread.sleep(Data.vitesse);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /* ************************************************************************************** */

  private Integer obtenirPositionRandom(Integer size) {
    Random rand = new Random();
    return rand.nextInt(size);
  }
}
