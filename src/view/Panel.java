package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import engine.SMA;
import model.Environement;
import util.Data;


public class Panel extends JPanel implements Observer {

	Environement env;
	private JPanel[][] grid;
	private JPanel panel;
	private JPanel panelInfo;
	private int tour;
	private JTextField txtTour;
	//private SMA sma;
	
	/*public Panel(int size){
		this.grid = new JPanel[size][size];		
        this.init(size);
        //this.sma = new SMA();
	}*/
	
	public Panel(Environement env) {
		this.env = env;
		int size = this.env.getTailleGrille();
		this.grid = new JPanel[size][size];		
        this.init(size);
    }
	
	public void setEnvironement(Environement env){
		this.env = env;
	}
	
	public void setTour(int tour){
		this.tour = tour;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
	    this.updateGrid();
	    this.updateInfo();
		this.repaint();
	}
	
	private void init(int size){
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setPreferredSize(new Dimension(750,600));
		
		this.panel = new JPanel();
		this.panel.setLayout(new GridLayout(size,size));// GridBagLayout
		
		for(int x = 0 ; x < size; x++){
			for( int y = 0; y < size; y++){
				grid[x][y] = new JPanel();
				int dim = 600/size;
				this.grid[x][y].setPreferredSize(new Dimension(dim, dim));
				Border emptyBorder = BorderFactory.createLineBorder(Color.lightGray, 1);//.createLineBorder(Color.gray);
				grid[x][y].setBorder(emptyBorder);
				this.panel.add(grid[x][y]);
			}
		}
		this.add(this.panel);
		this.panelInfo = initInfo();
		this.add(panelInfo);
		//this.updateGrid();		
	}
	
	private void updateGrid(){
		for(int x = 0 ; x < this.grid.length; x++){
			for(int y = 0; y < this.grid[x].length; y++){
				if(env.agentIsPresent(x, y)){
					this.grid[x][y].setBackground(Color.BLUE);
				}else{
					this.grid[x][y].setBackground(getBackground());
				}
			}
		}
		this.panel.repaint();
	}
	
	private void updateInfo(){
		this.txtTour.setText(this.tour+"");
		this.panelInfo.repaint();
	}
	
	private JPanel initInfo(){
		JPanel info = new JPanel();
		info.setLayout(new FlowLayout());
		info.setPreferredSize(new Dimension(100,600));
		
		JLabel lblBilles = new JLabel("Nombre de billes");
		JTextField txtBilles = new JTextField(Data.nombreAgents+"");
		txtBilles.setPreferredSize(new Dimension(80,20));
		JLabel lblGrille = new JLabel("Taille de la grille");
		JTextField txtGrille = new JTextField(Data.size+"");
		txtGrille.setPreferredSize(new Dimension(80,20));
		JLabel lblTour = new JLabel("Tour     ");
		txtTour = new JTextField("1");
		txtTour.setPreferredSize(new Dimension(80,20));
		JLabel lblVitesse = new JLabel("Vitesse     ");
		JTextField txtVitesse = new JTextField(Data.vitesse+"");
		txtVitesse.setPreferredSize(new Dimension(80,20));
		JButton btnStart = new JButton();
		btnStart.setText("START");
		btnStart.addActionListener(new ActionListener() {			 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                /*try {                    
                    sma.init();
                    sma.run();
                  } catch (InterruptedException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                  }*/
            }
        });
		
		info.add(lblBilles);
		info.add(txtBilles);
		info.add(lblGrille);
		info.add(txtGrille);
		info.add(lblTour);
		info.add(txtTour);
		info.add(lblVitesse);
		info.add(txtVitesse);
		info.add(btnStart);
		
		return info;
	}
}

