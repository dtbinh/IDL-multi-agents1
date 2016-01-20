package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Agent;
import model.Environement;
import util.Data;
import view.Panel;
import view.Vue;

public class SMA {

  private Environement envi;
  private List<Agent> agents;

  /**
   * Constructeur par défaut. Ne fait rien.
   */
  public SMA() {
    
  }
  
  private void createAgents(int nombreAgents, int size) {
    agents = new ArrayList<Agent>();
    // création de la liste d'agents
    for (int indexAgent = 0; indexAgent < nombreAgents; indexAgent++) {
      Random rand = new Random();
      int x = rand.nextInt((size - 1) + 1);
      int y = rand.nextInt((size - 1) + 1);
      this.agents.add(new Agent(x, y));
    }
  }

  public void init() {
    // on initialise l'environnement
    this.envi = new Environement();
    envi.init(Data.size);
    
    // on crée les agents
    this.createAgents(Data.nombreAgents, Data.size);
    
    // on les place dans l'environnement
    for (Agent agent : this.agents) {
      this.envi.addAgent(agent);
    }
  }

  public void run() throws InterruptedException {
    // initialiser la vue
    Panel panel = new Panel(this.envi);
    Vue v = new Vue(panel);
    v.addObserver(panel);
    // Data.v.setEnvironement(this.envi);
    // Data.v.updateVue(this.envi, 0);
    Thread.sleep(Data.vitesse);

    for (int tour = 0; tour < Data.tours; tour++) {
      Collections.shuffle(this.agents);
      for (Agent agent : this.agents) {
        agent.doIt();
        Environement newEnv = agent.getEnv(); // l'environnement modifié après le déplacement de l'agent
//        this.envi = newEnv; // On met à jour l'environnement pour les agents suivants
      }

      this.init();
      v.updateVue(this.envi, tour + 1);
      Thread.sleep(Data.vitesse); // On ralentit l'exécution
    }
  }
}
