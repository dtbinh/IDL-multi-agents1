package particules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import core.Agent;
import core.Coordonnees;
import core.Environement;
import util.Data;
import view.ControlPanel;
import view.GridPanel;
import view.Vue;

public class SmaBilles {
  private Environement envi;
  private List<Agent> agents;

  /**
   * Constructeur par defaut. Ne fait rien.
   */
  public SmaBilles() {

  }

  public void init() {
	// Init the environment
	this.envi = new Environement();
	envi.init(Data.size);

	// Create agents
	this.createAgents(Data.nombreAgents, Data.size, this.envi);

	// Place agents into the environment
	for (Agent agent : this.agents) {
	  if (!this.envi.addAgent(agent)) {
		Boolean ajoute = false;
		while (!ajoute) {
		  agent.setPosX(Coordonnees.obtenirPositionRandom(Data.size));
		  agent.setPosY(Coordonnees.obtenirPositionRandom(Data.size));
		  ajoute = this.envi.addAgent(agent);
		}
	  }
	}
  }

  public void run() throws InterruptedException {
	// Init the view
	GridPanel panel = new GridPanel(this.envi);
	ControlPanel control = new ControlPanel();
	Vue v = new Vue(panel, control);
	v.addObserver(panel);
	v.addObserver(control);

	for (int tour = 0; tour < Data.tours; tour++) {

	  if (Data.equite) {
		Collections.shuffle(this.agents);
	  }

	  for (Agent agent : this.agents) {
		agent.doIt();
		Environement newEnv = agent.getEnvironnement();
		this.envi = newEnv;
	  }

	  control.setTour(tour);
	  v.updateVue(this.envi);
	  Thread.sleep(Data.vitesse);
	}
  }

  /**
   * Create the agents and set the environment.
   * 
   * @param nombreAgents
   *          the number of agents
   * @param size
   *          the grid size of the environment
   * @param env
   *          the environment
   */
  private void createAgents(int nombreAgents, int size, Environement env) {
	// Create the list of agents
	this.agents = new ArrayList<Agent>();

	for (int indexAgent = 0; indexAgent < nombreAgents; indexAgent++) {
	  int x = Coordonnees.obtenirPositionRandom(size);
	  int y = Coordonnees.obtenirPositionRandom(size);

	  Bille newAgent = new Bille(x, y);
	  newAgent.setEnvironnement(env);
	  this.agents.add(newAgent);
	}
  }

}
