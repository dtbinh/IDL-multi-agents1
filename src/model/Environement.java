package model;

public class Environement {

  private int gridSize;
	private Agent[][] space;
	
	public void init(int size){
		/*
		 * initialise le tableau
		 */
	  this.gridSize = size;
	  this.space = new Agent[size][size]; // tous les agents sont nuls
	  
	}
	
	public Boolean agentIsPresent(int x, int y) {
	  return this.space[x][y] != null;
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
	    System.out.println("WARN : an agent is already here ["+x+"]["+y+"].");
	    return false;
	  } else {
	    this.space[x][y] = agent;
	    return true;
	  }
	}
}
