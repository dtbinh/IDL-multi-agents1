package model;

public interface Agent {

	public int getPosX();
	public int getPosY();
	public void setPosY(int posY);
	public void setPosX(int posX);
	public Integer getPasX();
	public void setPasX(int pasX);
	public Integer getPasY();
	public void setPasY(int pasY);
	public void setEnv(Environement env);
	public Environement getEnv();
	/**
	 * 
	 * @return Le nouveau agent apres la reproduction
	 */
	public Agent doIt();
}
