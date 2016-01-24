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
	private JTextField txtPoissons;
	private JTextField txtRequins;
	  
	public ControlPanel(){
		init();
	}
	
	private void init(){
	    this.setLayout(new FlowLayout());
	    this.setPreferredSize(new Dimension(150, 600));
	    this.setMaximumSize(new Dimension(100, 600));
	    this.setBackground(Color.white);

	    //Environement Data
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
	    pnlEnv.setPreferredSize(new Dimension(120, 170));	
	    pnlEnv.setLayout(new FlowLayout());
	    pnlEnv.setBackground(Color.white);
	    pnlEnv.add(lblGrille);
	    pnlEnv.add(txtGrille);
	    pnlEnv.add(lblTour);
	    pnlEnv.add(txtTour);
	    pnlEnv.add(lblVitesse);
	    pnlEnv.add(txtVitesse);	    
	    this.add(pnlEnv);	
	    
	    //Initial Data
	    JPanel pnlInit = new JPanel();
	    pnlInit.setBorder(new TitledBorder("Initial Data"));	    
	    pnlInit.setLayout(new FlowLayout());
	    pnlInit.setBackground(Color.white);
	    
	    if(Data.tp==1){
	    	pnlInit.setPreferredSize(new Dimension(120, 130));	
	    	JLabel lblBilles = new JLabel("Nombre de billes");
		    JTextField txtBilles = new JTextField(Data.nombreAgents + "");
		    txtBilles.setPreferredSize(new Dimension(80, 20));
		    txtBilles.setEditable(false);
		    pnlInit.add(lblBilles);
		    pnlInit.add(txtBilles);
	    }
	    if(Data.tp==2){
	    	pnlInit.setPreferredSize(new Dimension(120, 220));	
	    	JLabel lblPoissons = new JLabel("Poissons");
		    JTextField txtPoissons = new JTextField(Data.nombrePoissons + "");
		    txtPoissons.setPreferredSize(new Dimension(80, 20));
		    txtPoissons.setEditable(false);
		    JLabel lblPoissonsS = new JLabel("Poissons Seed ");
		    JTextField txtPoissonsS = new JTextField(Data.seedPoisson + "");
		    txtPoissonsS.setPreferredSize(new Dimension(80, 20));
		    txtPoissonsS.setEditable(false);
		    JLabel lblRequins= new JLabel("Requins");
		    JTextField txtRequins = new JTextField(Data.nombreRequins + "");
		    txtRequins.setPreferredSize(new Dimension(80, 20));
		    txtRequins.setEditable(false);
		    JLabel lblRequinsS= new JLabel("Requins Seed ");
		    JTextField txtRequinsS = new JTextField(Data.seedRequin + "");
		    txtRequinsS.setPreferredSize(new Dimension(80, 20));
		    txtRequinsS.setEditable(false);
		    Label poisson = new Label(1);
		    poisson.setPreferredSize(new Dimension(20,20));
		    Label requin = new Label(2);
		    requin.setPreferredSize(new Dimension(20,20));
		    pnlInit.add(poisson);
		    pnlInit.add(lblPoissons);
		    pnlInit.add(txtPoissons);
		    pnlInit.add(requin);
		    pnlInit.add(lblRequins);
		    pnlInit.add(txtRequins);
		    pnlInit.add(lblPoissonsS);
		    pnlInit.add(txtPoissonsS);
		    pnlInit.add(lblRequinsS);
		    pnlInit.add(txtRequinsS);
	    }	    
	    this.add(pnlInit);	
	    
	    //Current Data
	    if(Data.tp==2){
	    	JPanel pnlCurrent = new JPanel();
		    pnlCurrent.setBorder(new TitledBorder("Current Data"));	    
		    pnlCurrent.setLayout(new FlowLayout());
		    pnlCurrent.setBackground(Color.white);
		    pnlCurrent.setPreferredSize(new Dimension(120, 130));
		    JLabel lblPoissons = new JLabel("Poissons     ");
		    txtPoissons = new JTextField(Data.nombrePoissons + "");
		    txtPoissons.setPreferredSize(new Dimension(80, 20));
		    txtPoissons.setEditable(false);
		    JLabel lblRequins= new JLabel("Requins     ");
		    txtRequins = new JTextField(Data.nombreRequins + "");
		    txtRequins.setPreferredSize(new Dimension(80, 20));
		    txtRequins.setEditable(false);
		    pnlCurrent.add(lblPoissons);
		    pnlCurrent.add(txtPoissons);		    
		    pnlCurrent.add(lblRequins);
		    pnlCurrent.add(txtRequins);
		    this.add(pnlCurrent);	
	    }	    
	  }	
	
	public void setTour(int tour){
		this.tour = tour;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.txtTour.setText((this.tour+1) + "");
		if(Data.tp==2){
			this.txtPoissons.setText(Data.nombrePoissons + "");
			this.txtRequins.setText(Data.nombreRequins + "");
		}
	    this.repaint();
	}
}
