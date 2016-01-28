package wator;

import core.Agent;
import core.Environement;
import core.SMA;
import util.Data;
import util.Summary;
import view.ControlPanel;
import view.GridPanel;
import view.Vue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SMAwator implements SMA {

  private Environement envi;
  private List<Agent> agents;

  /**
   * Constructeur par defaut. Ne fait rien.
   */
  public SMAwator() {

  }

  private void createAgents(Environement env) {
    agents = new ArrayList<Agent>();
    // creation de la liste d'agents TP2
    //Poissons
    for (int indexAgent = 0; indexAgent < Data.nombrePoissons; indexAgent++) {
      int x = obtenirPositionRandom(Data.size);
      int y = obtenirPositionRandom(Data.size);

      Poisson newAgent = new Poisson(x, y);
      newAgent.setEnvironnement(env);
      this.agents.add(newAgent);
      Data.nombreAgents++;
    }
    //Requins
    for (int indexAgent = 0; indexAgent < Data.nombreRequins; indexAgent++) {
      int x = obtenirPositionRandom(Data.size);
      int y = obtenirPositionRandom(Data.size);

      Requin newAgent = new Requin(x, y);
      newAgent.setEnvironnement(env);
      this.agents.add(newAgent);
      Data.nombreAgents++;
    }

  }

  private Integer obtenirPositionRandom(Integer size) {
    Random rand = new Random();
    return rand.nextInt((size - 1) + 1);
  }

  @Override
  public void init() {
    // on initialise l'environnement
    this.envi = new Environement();
    envi.init(Data.size);

    // on cree les agents
    this.createAgents(this.envi);

    // on les place dans l'environnement
    for (Agent agent : this.agents) {
      Boolean ajoute = this.envi.addAgent(agent);
      while (!ajoute) { // tant que non ajoute
        agent.setPosX(obtenirPositionRandom(Data.size));
        agent.setPosY(obtenirPositionRandom(Data.size));
        ajoute = this.envi.addAgent(agent);
      }
    }
  }

  @Override
  public void run() {

    // initialiser la vue
    GridPanel panel = new GridPanel(this.envi);
    ControlPanel control = new ControlPanel();
    Vue v = new Vue(panel, control);
    v.addObserver(control);
    v.addObserver(panel);

    try {
      Thread.sleep(Data.vitesse);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    for (int tour = 0; tour < Data.tours; tour++) {
      if (Data.equite) {
        Collections.shuffle(this.agents);
      }

      List<Agent> newAgents = new ArrayList<Agent>();
      List<Agent> deletedAgents = new ArrayList<Agent>();

      for (Agent agent : this.agents) {
        if (!containsAgent(deletedAgents, agent)) {
          int oldX = agent.getPosX();
          int oldY = agent.getPosY();
          agent.setEnvironnement(this.envi);
          Agent newAgent = agent.doIt();

          // S'il y a des naissances
          if (newAgent != null) {
            newAgents.add(newAgent);
          }

          // S'il y a des decès
          if (agent instanceof Requin && ((Requin) agent).doitMourir()) {
            deletedAgents.add(agent);
          }

          if (agent instanceof Requin && ((Requin) agent).getPoissonMange() != null) {
            deletedAgents.add(((Requin) agent).getPoissonMange());
          }

          this.envi = agent.getEnvironnement(); // On met e jour l'environnement pour les agents suivants
          v.updateVue(this.envi);
        }
      }

      //mettre a jour la liste des agents
      for (Agent agent : newAgents) {
        this.agents.add(agent);
        Boolean ajoute = this.envi.addAgent(agent);
        while (!ajoute) {
          agent.setPosX(obtenirPositionRandom(Data.size));
          agent.setPosY(obtenirPositionRandom(Data.size));
          ajoute = this.envi.addAgent(agent);
        }
      }

      // Supprimer les requins morts de l'environnement et de la liste des agents
      for (Agent agentToDelete : deletedAgents) {
        this.envi.deleteAgent(agentToDelete.getPosX(), agentToDelete.getPosY());
        deleteAgent(this.agents, agentToDelete);

        if (agentToDelete instanceof Requin) {
          Data.nombreRequins--;
          Data.nombreAgents--;
        }
      }

      control.setTour(tour);
      v.updateVue(this.envi);

      // Sortie pour generation de code
      System.out.println(tour + " " + Data.nombrePoissons + " " + Data.nombreRequins);

      printSummary(tour);
      try {
        Thread.sleep(Data.vitesse); // On ralentit l'execution
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void deleteAgent(List<Agent> agents, Agent agent) {
    int indx = -1;
    for (int i = 0; i < agents.size(); i++) {
      Agent a = agents.get(i);
      if (a.getPosX() == agent.getPosX() && a.getPosY() == agent.getPosY()) {
        indx = i;
        break;
      }
    }
    if (indx != -1) {
      this.agents.remove(indx);
    }
  }

  private boolean containsAgent(List<Agent> agents, Agent agent) {
    for (Agent a : agents) {
      if (a.getPosX() == agent.getPosX() && a.getPosY() == agent.getPosY()) {
        return true;
      }
    }
    return false;
  }

  private void printEnv() {
    System.out.println("----------------------");
    for (Agent a : this.agents) {
      int x = a.getPosX();
      int y = a.getPosY();
      System.out
        .println("x: " + x + " | y: " + y + " | " + (this.envi.agentIsPresent(x, y) ? this.envi.getAgentInstance(x, y) : " to be deleted")); //+ this.envi.getAgentInstance(x, y)
    }
  }

  private static void printSummary(int tour) {
    Summary.writeToOutputFile(tour + " " + Data.nombrePoissons + " " + Data.nombreRequins);
  }

}
