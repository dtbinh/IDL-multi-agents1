package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class Label extends JLabel{

	private int type;
	
	public Label(int type){
		this.type = type;
	}
	
	public void paintComponent(Graphics g){
		if(type==1){
			g.setColor(new Color(255,51,153));
  		  	g.fillOval(0, 0, 20, 20);
		}
		else{
			g.setColor(Color.blue);
			  g.fillPolygon(new int[] {0, 10,20}, 
			  		  new int[] {20, 0,20}, 3);
		}
	}
}
