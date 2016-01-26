package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Environement;
import model.Poisson;
import util.Data;

@SuppressWarnings("serial")
public class GridPanel extends JPanel implements Observer {

  Environement env;
  private JPanel[][] grid;

  public GridPanel(Environement env) {
    this.env = env;
    int size = this.env.getTailleGrille();
    this.grid = new JPanel[size][size];
    this.setPreferredSize(new Dimension(600, 600));
    //this.setBackground(new Color(205,239,255));
  }

  public void setEnvironement(Environement env) {
    this.env = env;
  }

  @Override
  public void update(Observable arg0, Object arg1) {
    this.repaint();
  }
  
  public void paintComponent(Graphics g)
  {
	  int width = (getWidth()/env.getTailleGrille() <= 1) ? 2 : getWidth()/env.getTailleGrille();
	  int height = (getHeight()/env.getTailleGrille() <= 1) ? 2 : getHeight()/env.getTailleGrille();
	  //System.out.println(getWidth());
	  //Billes
	  for (int x = 0; x < env.getTailleGrille(); x++) {
	      for (int y = 0; y < env.getTailleGrille(); y++) {
	    	  if(Data.grilleVisible){
		    	  g.setColor(new Color(0,204,204)); //Color.lightGray)
		    	  g.drawRect((x*width), (y*height), width, height);
	    	  }
	    	  if (env.agentIsPresent(x, y)) {
	    		  if(Data.tp==1){
	    			  g.setColor(Color.BLUE);
		    		  g.fillOval((x*width), (y*height), width, height);
	    		  }
	    		  if(Data.tp==2){
	    			  if (env.getAgentInstance(x, y).equals(Poisson.class)) {
	    				  g.setColor(new Color(205,239,255));
	    	    		  g.fillRect((x*width), (y*height), width, height);
	    				  g.setColor(new Color(255,51,153));
			    		  g.fillOval((x*width), (y*height), width, height);
	    			  } else{
	    				  g.setColor(new Color(205,239,255));
	    	    		  g.fillRect((x*width), (y*height), width, height);
	    				  g.setColor(Color.blue);
	    				  g.fillPolygon(new int[] {(x*width), ((x*width)+(width/2)),(x*width)+width}, 
	    				  		  new int[] {(y*height)+height, (y*height),(y*height)+height}, 3);
	    				  //g.fillOval((x*width), (y*height), width, height);
	    			  }	    				  
	    		  }
	    	  }
	    	  else{
	    		  g.setColor(new Color(205,239,255));
	    		  g.fillRect((x*width), (y*height), width, height);
	    	  }
	      }
	  }  
  }

}
