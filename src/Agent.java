
public class Agent {

	private int posX;
	private int posY;
	private int pasX;
	private int pasY;
	private Environement envi;
		
	public Agent(int posX, int posY) {
    super();
    this.posX = posX;
    this.posY = posY;
  }
	
	public int getX(){
	  return this.posX;
	}
	
	public int getY() {
	  return this.posY;
	}

	public void setEnv(Environement env) {
	  this.envi = env;
	}
	
	public Environement getEnv() {
	  return this.envi;
	}
	
	public void doIt(){
	  // Ne pas oublier de modifier l'environnement
	}
}
