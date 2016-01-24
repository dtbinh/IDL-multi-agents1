package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Agent;
import model.Bille;
import model.Environement;
import model.Poisson;
import model.Requin;
import util.Data;
import view.ControlPanel;
import view.GridPanel;
import view.Vue;

public class SMA {

  private Environement envi;
  private List<Agent> agents;

  /**
   * Constructeur par d�faut. Ne fait rien.
   */
  public SMA() {

  }
  
  private void createAgents(Environement env) {
    agents = new ArrayList<Agent>();
    // cr�ation de la liste d'agents TP2
	//Poissons
	for (int indexAgent = 0; indexAgent < Data.nombrePoissons; indexAgent++) {
      int x = obtenirPositionRandom(Data.size);
      int y = obtenirPositionRandom(Data.size);

      Poisson newAgent = new Poisson(x, y);
      newAgent.setEnv(env);
      this.agents.add(newAgent);
      Data.nombreAgents++;
    }
	//Requins
	for (int indexAgent = 0; indexAgent < Data.nombreRequins; indexAgent++) {
      int x = obtenirPositionRandom(Data.size);
      int y = obtenirPositionRandom(Data.size);

      Requin newAgent = new Requin(x, y);
      newAgent.setEnv(env);
      this.agents.add(newAgent);
      Data.nombreAgents++;
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

    // on cr�e les agents
    this.createAgents(this.envi);

    // on les place dans l'environnement
    for (Agent agent : this.agents) {
      Boolean ajoute = this.envi.addAgent(agent);
      while (!ajoute) { // tant que non ajout�
        agent.setPosX(obtenirPositionRandom(Data.size));
        agent.setPosY(obtenirPositionRandom(Data.size));
        ajoute = this.envi.addAgent(agent);
      }
    }
  }
  
  public void run() throws InterruptedException {
	  System.out.println("#"+Data.nombreAgents);
    // initialiser la vue
    GridPanel panel = new GridPanel(this.envi);
    ControlPanel control = new ControlPanel();
    Vue v = new Vue(panel,control);
    v.addObserver(control);
    v.addObserver(panel);
    
    Thread.sleep(Data.vitesse);

    for (int tour = 0; tour < Data.tours; tour++) {
      if (Data.equite) {
        Collections.shuffle(this.agents);
      }
      
      List<Agent> newAgents = new ArrayList<Agent>();  
      for (Agent agent : this.agents) {    	
        Agent newAgent = agent.doIt();
        if(newAgent!=null){
        	newAgents.add(newAgent);
        }
        Environement newEnv = agent.getEnv(); // l'environnement modifi� apr�s le d�placement de l'agent
        this.envi = newEnv; // On met � jour l'environnement pour les agents suivants
        v.updateVue(this.envi);
      }
      
      //mettre a jour la liste des agents
      for(Agent agent:newAgents){
    	  this.agents.add(agent);
    	  Boolean ajoute = this.envi.addAgent(agent);
    	  while(!ajoute){
    		  agent.setPosX(obtenirPositionRandom(Data.size));
			  agent.setPosY(obtenirPositionRandom(Data.size));
			  ajoute = this.envi.addAgent(agent);
    	  }
      }
      
      control.setTour(tour);
      v.updateVue(this.envi);
      //printEnv();
      Thread.sleep(Data.vitesse); // On ralentit l'ex�cution
    }
    //printEnv();
  }
  
  private void printEnv(){
	  
	  for(Agent a: this.agents){
		  int x = a.getPosX();
		  int y = a.getPosY();
		  System.out.println("x: "+x+" | y: "+y+" | "+this.envi.getAgentInstance(x, y));
	  }
	  System.out.println("----------------------");
  }
}
