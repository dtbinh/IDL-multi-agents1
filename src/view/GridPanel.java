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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Environement;
import util.Data;

@SuppressWarnings("serial")
public class GridPanel extends JPanel implements Observer {

  Environement env;
  private JPanel[][] grid;

  public GridPanel(Environement env) {
    this.env = env;
    int size = this.env.getTailleGrille();
    this.grid = new JPanel[size][size];
    //this.init(size);
  }

  public void setEnvironement(Environement env) {
    this.env = env;
  }

  @Override
  public void update(Observable arg0, Object arg1) {
    //this.updateGrid();
    this.repaint();
  }
  
  public void paintComponent(Graphics g)
  {
	  int width = getWidth()/env.getTailleGrille();
	  int height = getHeight()/env.getTailleGrille();
	  //Billes
	  for (int x = 0; x < env.getTailleGrille(); x++) {
	      for (int y = 0; y < env.getTailleGrille(); y++) {
	    	  
	    	  g.setColor(Color.lightGray);
	    	  g.drawRect((x*width), (y*height), width, height);
	    	  
	    	  if (env.agentIsPresent(x, y)) {
	    		  g.setColor(Color.BLUE);
	    		  //g.(x, y, width, height);
	    		  g.fillOval((x*width), (y*height), width, height);
	    	  }
	    	  else{
	    		  g.setColor(getBackground());
	    		  g.fillOval((x*width), (y*height), width, height);
	    	  }
	      }
	  }  
  }

  /**
   * Méthode d'initialisation appelée dans le constructeur du Panel.
   * <br/>
   * Instancie et configure un nouveau panel pour la grille,
   * <br/>
   * Instancie et configure un nouveau panel pour les informations.
   * @param size La taille de la grille (en nombre de cases)
   */
  private void init(int size) {
    this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    this.setPreferredSize(new Dimension(600, 600));
    this.setLayout(new GridLayout(size, size));// GridBagLayout

    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        grid[x][y] = new JPanel();
        int dim = 600 / size;
        this.grid[x][y].setPreferredSize(new Dimension(dim, dim));
        Border emptyBorder = BorderFactory.createLineBorder(Color.lightGray, 1);// .createLineBorder(Color.gray);
        grid[x][y].setBorder(emptyBorder);
        this.add(grid[x][y]);
      }
    }
  }

  /**
   * Met à jour l'affichage de la grille (la colorisation).
   */
  private void updateGrid() {
    for (int x = 0; x < this.grid.length; x++) {
      for (int y = 0; y < this.grid[x].length; y++) {
        if (env.agentIsPresent(x, y)) {
          this.grid[x][y].setBackground(Color.BLUE);
        } else {
          this.grid[x][y].setBackground(getBackground());
        }
      }
    }
  }
}
