package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import util.Data;

public class ControlPanel extends JPanel implements Observer {

	private int tour;
	private JTextField txtTour;
	  
	public ControlPanel(){
		init();
	}
	
	private void init(){
	    this.setLayout(new FlowLayout());
	    this.setPreferredSize(new Dimension(150, 600));
	    this.setMaximumSize(new Dimension(100, 600));
	    this.setBackground(Color.white);

	    
	    JLabel lblGrille = new JLabel("Taille de la grille");
	    JTextField txtGrille = new JTextField(Data.size + "");
	    txtGrille.setPreferredSize(new Dimension(80, 20));
	    txtGrille.setEditable(false);
	    JLabel lblTour = new JLabel("Tour         ");
	    txtTour = new JTextField("1");
	    txtTour.setPreferredSize(new Dimension(80, 20));
	    txtTour.setEditable(false);
	    JLabel lblVitesse = new JLabel("Vitesse     ");
	    JTextField txtVitesse = new JTextField(Data.vitesse + "");
	    txtVitesse.setPreferredSize(new Dimension(80, 20));
	    txtVitesse.setEditable(false);
	    
	    JPanel pnlEnv = new JPanel();
	    pnlEnv.setBorder(new TitledBorder("Environement"));
	    pnlEnv.setPreferredSize(new Dimension(150, 170));	
	    pnlEnv.setLayout(new FlowLayout());
	    pnlEnv.setBackground(Color.white);
	    pnlEnv.add(lblGrille);
	    pnlEnv.add(txtGrille);
	    pnlEnv.add(lblTour);
	    pnlEnv.add(txtTour);
	    pnlEnv.add(lblVitesse);
	    pnlEnv.add(txtVitesse);
	    
	    this.add(pnlEnv);	
	    
	    JPanel pnlInit = new JPanel();
	    pnlInit.setBorder(new TitledBorder("Initial Data"));
	    pnlInit.setPreferredSize(new Dimension(150, 130));	
	    pnlInit.setLayout(new FlowLayout());
	    pnlInit.setBackground(Color.white);
	    
	    if(Data.tp==1){
	    	JLabel lblBilles = new JLabel("Nombre de billes");
		    JTextField txtBilles = new JTextField(Data.nombreAgents + "");
		    txtBilles.setPreferredSize(new Dimension(80, 20));
		    txtBilles.setEditable(false);
		    
		    
		    
		    pnlInit.add(lblBilles);
		    pnlInit.add(txtBilles);
	    }
	    if(Data.tp==2){
	    	JLabel lblPoissons = new JLabel("Poissons     ");
		    JTextField txtPoissons = new JTextField(Data.nombrePoissons + "");
		    txtPoissons.setPreferredSize(new Dimension(80, 20));
		    txtPoissons.setEditable(false);
		    JLabel lblRequins= new JLabel("Requins     ");
		    JTextField txtRequins = new JTextField(Data.nombreRequins + "");
		    txtRequins.setPreferredSize(new Dimension(80, 20));
		    txtRequins.setEditable(false);
		    pnlInit.add(lblPoissons);
		    pnlInit.add(txtPoissons);
		    pnlInit.add(lblRequins);
		    pnlInit.add(txtRequins);
	    }
	    
	    this.add(pnlInit);
	    
	    /*JButton btnStart = new JButton();
	    btnStart.setText("START");
	    btnStart.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        System.out.println("Source : " + e.getSource());
	        // sma.init();
	        // sma.run();
	      }
	    });*/	    
	  }
	
	
	public void setTour(int tour){
		this.tour = tour;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.txtTour.setText((this.tour+1) + "");
	    this.repaint();
	}
}
