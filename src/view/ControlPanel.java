package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.Data;

public class ControlPanel extends JPanel{

	private int tour;
	private JTextField txtTour;
	  
	public ControlPanel(){
		init();
	}
	
	private void init(){
	    this.setLayout(new FlowLayout());
	    this.setPreferredSize(new Dimension(100, 600));
	    this.setMaximumSize(new Dimension(100, 600));

	    JLabel lblBilles = new JLabel("Nombre de billes");
	    JTextField txtBilles = new JTextField(Data.nombreAgents + "");
	    txtBilles.setPreferredSize(new Dimension(80, 20));
	    JLabel lblGrille = new JLabel("Taille de la grille");
	    JTextField txtGrille = new JTextField(Data.size + "");
	    txtGrille.setPreferredSize(new Dimension(80, 20));
	    JLabel lblTour = new JLabel("Tour     ");
	    txtTour = new JTextField("1");
	    txtTour.setPreferredSize(new Dimension(80, 20));
	    JLabel lblVitesse = new JLabel("Vitesse     ");
	    JTextField txtVitesse = new JTextField(Data.vitesse + "");
	    txtVitesse.setPreferredSize(new Dimension(80, 20));
	    
	    JButton btnStart = new JButton();
	    btnStart.setText("START");
	    btnStart.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        System.out.println("Source : " + e.getSource());
	        // sma.init();
	        // sma.run();
	      }
	    });

	    this.add(lblBilles);
	    this.add(txtBilles);
	    this.add(lblGrille);
	    this.add(txtGrille);
	    this.add(lblTour);
	    this.add(txtTour);
	    this.add(lblVitesse);
	    this.add(txtVitesse);
	    //info.add(btnStart);
	  }
	
	private void updateInfo(){
	    this.txtTour.setText(this.tour + "");
	    this.repaint();
	  }
}
