import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SMA {

	private Environement envi;
	private List<Agent> agents;
	
	private void createAgents(int nombreAgents, int size) {
	  // création de la liste d'agents
	  for (int indexAgent=0; indexAgent < nombreAgents; indexAgent++) {
	    Random rand = new Random();
	    int x = rand.nextInt(size + 1);
	    int y = rand.nextInt(size + 1);
	    this.agents.add(new Agent(x, y));
	  }
	}
	
	public void init(int size, int nombreAgents){
	  // on initialise l'environnement
	  this.envi = new Environement();
	  envi.init(size);
	  // on crée les agents
	  this.createAgents(nombreAgents, size);
	  // on les place dans l'environnement
	  for (Agent agent : this.agents) {
	    this.envi.addAgent(agent);
	  }
	}
	
	public void run(int tours, long vitesse) throws InterruptedException{
	  for (int tour=0; tour < tours; tour++) {
	    Collections.shuffle(this.agents);
	    for (Agent agent : this.agents) {
	      agent.doIt();
	      Environement newEnv = agent.getEnv(); // l'environnement modifié après le déplacement de l'agent
	      this.envi = newEnv; // On met à jour l'environnement pour les agents suivants
	    }
	    
	    Thread.sleep(vitesse); // On ralentit l'exécution
	  }
	}
}
