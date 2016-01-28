package engine;

import model.Agent;
import model.Environement;
import model.Poisson;
import model.Requin;
import util.Data;
import util.Summary;
import view.ControlPanel;
import view.GridPanel;
import view.Vue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SMA {

  private Environement envi;
  private List<Agent> agents;

  /**
   * Constructeur par défaut. Ne fait rien.
   */
  public SMA() {

  }

  private void createAgents(Environement env) {
    agents = new ArrayList<Agent>();
    // création de la liste d'agents TP2
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

    // on crée les agents
    this.createAgents(this.envi);

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
    //System.out.println("#" + Data.nombreAgents);

    // initialiser la vue
    GridPanel panel = new GridPanel(this.envi);
    ControlPanel control = new ControlPanel();
    Vue v = new Vue(panel, control);
    v.addObserver(control);
    v.addObserver(panel);

    Thread.sleep(Data.vitesse);

    for (int tour = 0; tour < Data.tours; tour++) {
      if (Data.equite) {
        Collections.shuffle(this.agents);
      }
      //System.out.println("tour: "+tour);
      List<Agent> newAgents = new ArrayList<Agent>();
      List<Agent> deletedAgents = new ArrayList<Agent>();

      //printEnv();
      for (Agent agent : this.agents) {
    	  if(!containsAgent(deletedAgents,agent)){
    		  //System.out.println(agent instanceof Requin?"Requin":"Poisson");
        	  //printEnv();
        	  int oldX = agent.getPosX();
        	  int oldY = agent.getPosY();
        	  agent.setEnv(this.envi);
        	  Agent newAgent = agent.doIt();

            // S'il y a des naissances
            if (newAgent != null) {
              newAgents.add(newAgent);
              //System.out.println("new bebe");
            }

            // S'il y a des décès
            if (agent instanceof Requin && ((Requin) agent).doitMourir()) {
              deletedAgents.add(agent);
            }
            
            if(agent instanceof Requin && ((Requin) agent).getPoissonMange()!=null){
            	deletedAgents.add(((Requin)agent).getPoissonMange());
            	//System.out.println("poisson mange");
            	//newAgents.add(agent);
            	//Requin requin = new Requin(oldX,oldY);
            	//deletedAgents.add(requin);        	
            }

            Environement newEnv = agent.getEnv(); // l'environnement modifié aprés le déplacement de l'agent
            this.envi = newEnv; // On met é jour l'environnement pour les agents suivants
            v.updateVue(this.envi);
    	  }    	      	 
      }
      //this.envi.printEnv();
      //mettre a jour la liste des agents
      for (Agent agent : newAgents) {
        this.agents.add(agent);
        Boolean ajoute = this.envi.addAgent(agent);
        /*while (!ajoute) {
          agent.setPosX(obtenirPositionRandom(Data.size));
          agent.setPosY(obtenirPositionRandom(Data.size));
          ajoute = this.envi.addAgent(agent);
        }*/
      }

      // Supprimer les requins morts de l'environnement et de la liste des agents
      for (Agent agentToDelete : deletedAgents) {
        this.envi.deleteAgent(agentToDelete.getPosX(), agentToDelete.getPosY());
        deleteAgent(this.agents,agentToDelete);
        //this.agents.remove(agentToDelete);
        /*Agent realAgentToDelete = null;
        Iterator<Agent> it = this.agents.iterator();
        while(it.hasNext()){
        	Agent a = it.next();
        	if(a.getPosX()==agentToDelete.getPosX() && a.getPosY() == agentToDelete.getPosY()) {
        		realAgentToDelete = a;
        	}
        }*/
        //this.agents.remove(agentToDelete);
        if(agentToDelete instanceof Requin){
        	Data.nombreRequins--;
            Data.nombreAgents--;
        }        
      }
      //System.out.println("Fin du tour----------------");
      //printEnv();
      control.setTour(tour);
      v.updateVue(this.envi);
      System.out.println(tour+" "+Data.nombrePoissons+" "+Data.nombreRequins);
      printSummary(tour);
      Thread.sleep(Data.vitesse); // On ralentit l'exécution
    }
    //        printEnv();
  }

  private void deleteAgent(List<Agent> agents, Agent agent) {
	  int indx=-1;
	  for(int i=0;i<agents.size();i++){
		  Agent a = agents.get(i);
		  if(a.getPosX() == agent.getPosX() && a.getPosY() == agent.getPosY()) {
			  indx = i;
			  break;
		  }
	  }
	  if(indx !=-1){
		  this.agents.remove(indx);
	  }
  }
  
  private boolean containsAgent(List<Agent> agents, Agent agent) {
	  for (Agent a : agents){
		  if(a.getPosX() == agent.getPosX() && a.getPosY() == agent.getPosY()) {
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
      System.out.println("x: " + x + " | y: " + y + " | "+ (this.envi.agentIsPresent(x, y)? this.envi.getAgentInstance(x, y):" to be deleted" )); //+ this.envi.getAgentInstance(x, y)
    }
    
  }
  
  private static void printSummary(int tour){		
		Summary.writeToOutputFile(tour+" "+Data.nombrePoissons+" "+Data.nombreRequins);
	}
}
