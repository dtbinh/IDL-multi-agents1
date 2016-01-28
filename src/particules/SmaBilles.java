package particules;

import core.Agent;
import core.Environement;
import util.Data;
import view.ControlPanel;
import view.GridPanel;
import view.Vue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SmaBilles {
  private Environement envi;
  private List<Agent> agents;

  /**
   * Constructeur par defaut. Ne fait rien.
   */
  public SmaBilles() {

  }

  private void createAgents(int nombreAgents, int size, Environement env) {
    agents = new ArrayList<Agent>();
    // cr<E9>ation de la liste d'agents
    for (int indexAgent = 0; indexAgent < nombreAgents; indexAgent++) {
      int x = obtenirPositionRandom(size);
      int y = obtenirPositionRandom(size);

      Bille newAgent = new Bille(x, y);
      newAgent.setEnv(env);
      this.agents.add(newAgent);
    }
  }

  private Integer obtenirPositionRandom(Integer size) {
    Random rand = new Random();
    return rand.nextInt((size - 1) + 1);
  }

  public void init() {
    // on initialise l'environnement
    this.envi = new Environement();
    envi.init(Data.size);

    // on cr<E9>e les agents
    this.createAgents(Data.nombreAgents, Data.size, this.envi);

    // on les place dans l'environnement
    for (Agent agent : this.agents) {
      if (!this.envi.addAgent(agent)) {
        Boolean ajoute = false;
        while (!ajoute) {
          agent.setPosX(obtenirPositionRandom(Data.size));
          agent.setPosY(obtenirPositionRandom(Data.size));
          ajoute = this.envi.addAgent(agent);
        }

      }
    }
  }

  public void run() throws InterruptedException {
    // initialiser la vue
    GridPanel panel = new GridPanel(this.envi);
    ControlPanel control = new ControlPanel();
    Vue v = new Vue(panel, control);
    v.addObserver(panel);
    v.addObserver(control);
    Thread.sleep(Data.vitesse);

    for (int tour = 0; tour < Data.tours; tour++) {
      Collections.shuffle(this.agents);
      for (Agent agent : this.agents) {
        agent.doIt();
        Environement newEnv = agent.getEnvironnement(); // l'environnement modifi<E9> apr	<E8>s le d<E9>placement de l'agent
        this.envi = newEnv; // On met <E0> jour l'environnement pour les agents suivants
      }

      // this.init();
      control.setTour(tour);
      v.updateVue(this.envi);
      Thread.sleep(Data.vitesse); // On ralentit l'ex<E9>cution
    }

  }
}





  

