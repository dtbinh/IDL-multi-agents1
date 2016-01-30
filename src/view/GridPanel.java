package view;

import core.Environement;
import pacman.Avatar;
import pacman.Bloc;
import pacman.Poursuiveur;
import util.Data;
import wator.Poisson;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("serial")
public class GridPanel extends JPanel implements Observer {

  Environement env;
  private JPanel[][] grid;

  public GridPanel(Environement env) {
    this.env = env;
    int size = this.env.getTailleGrille();
    this.grid = new JPanel[size][size];
    this.setPreferredSize(new Dimension(600, 600));
  }

  public void setEnvironement(Environement env) {
    this.env = env;
  }

  @Override
  public void update(Observable arg0, Object arg1) {
    this.repaint();
  }

  public void paintComponent(Graphics g) {
    int width = (getWidth() / env.getTailleGrille() <= 1) ? 2 : getWidth() / env.getTailleGrille();
    int height = (getHeight() / env.getTailleGrille() <= 1) ? 2 : getHeight() / env.getTailleGrille();

    //g.setFont(new Font("TimesRoman", width, height));
    
    for (int x = 0; x < env.getTailleGrille(); x++) {
      for (int y = 0; y < env.getTailleGrille(); y++) {
        
        if (env.agentIsPresent(x, y)) {
          // Billes
          if (Data.tp == 1) {
            g.setColor(Color.BLUE);
            g.fillOval((x * width), (y * height), width, height);
          }
          // Wator
          if (Data.tp == 2) {
            if (env.getAgentInstance(x, y).equals(Poisson.class)) {
              g.setColor(new Color(205, 239, 255));
              g.fillRect((x * width), (y * height), width, height);
              g.setColor(new Color(255, 51, 153));
              g.fillOval((x * width), (y * height), width, height);
            } else {
              g.setColor(new Color(205, 239, 255));
              g.fillRect((x * width), (y * height), width, height);
              g.setColor(Color.blue);
              g.fillPolygon(new int[] {(x * width), ((x * width) + (width / 2)), (x * width) + width},
                new int[] {(y * height) + height, (y * height), (y * height) + height}, 3);
            }
          }
          //Pacman
          if(Data.tp ==3){
        	  if (env.getAgentInstance(x, y).equals(Bloc.class)) {
        		  g.setColor(Color.gray);
        		  g.fillRect((x * width), (y * height), width, height);
        	  }
        	  if (env.getAgentInstance(x, y).equals(Avatar.class)) {
        		  g.setColor(Color.black);
                  g.fillRect((x * width), (y * height), width, height);
        		  g.setColor(Color.yellow);
        		  g.fillOval((x * width), (y * height), width, height);
        	  }
        	  if (env.getAgentInstance(x, y).equals(Poursuiveur.class)) {
        		  g.setColor(Color.black);
                  g.fillRect((x * width), (y * height), width, height);
        		  g.setColor(Color.blue);
        		  g.fillOval((x * width), (y * height), width, height);
        	  }
          }
        } else {
        	if(Data.tp ==3){
        		g.setColor(Color.black);
        	}
        	else {      		
        		g.setColor(new Color(205, 239, 255));
        	}
          g.fillRect((x * width), (y * height), width, height);
          if(Data.tp ==3 && Data.afficherDistances){    	
          	g.setColor(Color.lightGray);
          	int d = this.env.getDistance(x, y);
          	g.drawString(d+"", (x * width), (y * height)+height);
          }
        }
        if (Data.grilleVisible) {
        	if(Data.tp==3){
        		g.setColor(Color.darkGray); 
        	}
        	else{
        		g.setColor(new Color(0, 204, 204)); //Color.lightGray)
        	}
          g.drawRect((x * width), (y * height), width, height);
        }
        
      }
    }
    
    
  }

}
