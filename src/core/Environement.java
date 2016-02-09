package core;

import java.util.ArrayList;
import java.util.List;

public class Environement {

  private int gridSize;
  private Agent[][] space;
  private Integer[][] distances;
  private List<Agent> agents;

  public void init(int size) {
	/*
	 * initialise le tableau
	 */
	this.gridSize = size;
	this.space = new Agent[size][size]; // tous les agents sont nuls
	this.distances = new Integer[size][size];
	this.agents = new ArrayList<Agent>();
  }

  public Boolean agentIsPresent(int x, int y) {
	return this.space[x][y] != null;
  }

  public Class<?> getAgentInstance(int x, int y) {
	return this.space[x][y].getClass();
  }

  public Agent getAgent(int x, int y) {
	return this.space[x][y];
  }

  public int getTailleGrille() {
	return this.gridSize;
  }

  public void deleteAgent(int x, int y) {
	if (this.agentIsPresent(x, y)) {
	  this.space[x][y] = null;
	}
  }

  public Boolean addAgent(Agent agent) {
	int x = agent.getPosX();
	int y = agent.getPosY();
	if (this.agentIsPresent(x, y)) {
	  return false;
	} else {
	  this.space[x][y] = agent;
	  return true;
	}
  }

  public void setDistance(int distance, int x, int y) {
	this.distances[x][y] = distance;
  }

  public int getDistance(int x, int y) {
	try {
	  return this.distances[x][y];
	} catch (NullPointerException ex) {
	  return -1;
	}
  }

  public List<Agent> getAgents() {
	return agents;
  }

  public void setAgents(List<Agent> agents) {
	this.agents = agents;
  }

  public void printEnv() {
	System.out.println("----------------------------- Print Environment -----------------------------");
	for (int x = 0; x < this.gridSize; x++) {
	  for (int i = 0; i < this.gridSize; i++) {
		if (this.space[x][i] != null)
		  System.out.println(this.space[x][i].getPosX() + " | " + this.space[x][i].getPosY());
	  }
	}
  }
}
