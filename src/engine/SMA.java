package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Agent;
import model.Environement;
import util.Data;
import view.ControlPanel;
import view.GridPanel;
import view.Vue;

public class SMA {

  private Environement envi;
  private List<Agent> agents;

  /**
   * Constructeur par défaut. Ne fait rien.
   */
  public SMA() {

  }

  private void createAgents(int nombreAgents, int size, Environement env) {
    agents = new ArrayList<Agent>();
    // création de la liste d'agents
    for (int indexAgent = 0; indexAgent < nombreAgents; indexAgent++) {
      int x = obtenirPositionRandom(size);
      int y = obtenirPositionRandom(size);

      Agent newAgent = new Agent(x, y);
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

    // on crée les agents
    this.createAgents(Data.nombreAgents, Data.size, this.envi);

    // on les place dans l'environnement
    for (Agent agent : this.agents) {
      Boolean ajoute = this.envi.addAgent(agent);
      while (!ajoute) { // tant que non ajouté
        agent.setPosX(obtenirPositionRandom(Data.size));
        agent.setPosY(obtenirPositionRandom(Data.size));
        ajoute = this.envi.addAgent(agent);
      }
    }
  }

  public void run() throws InterruptedException {
    // initialiser la vue
    GridPanel panel = new GridPanel(this.envi);
    ControlPanel control = new ControlPanel();
    Vue v = new Vue(panel,control);
    v.addObserver(panel);
    // Data.v.setEnvironement(this.envi);
    // Data.v.updateVue(this.envi, 0);
    Thread.sleep(Data.vitesse);

    for (int tour = 0; tour < Data.tours; tour++) {
      if (Data.equite) {
        Collections.shuffle(this.agents);
      }
      for (Agent agent : this.agents) {
        agent.doIt();
        Environement newEnv = agent.getEnv(); // l'environnement modifié après le déplacement de l'agent
        this.envi = newEnv; // On met à jour l'environnement pour les agents suivants
      }

      // this.init();
      v.updateVue(this.envi, tour + 1);
      Thread.sleep(Data.vitesse); // On ralentit l'exécution
    }
  }
}
