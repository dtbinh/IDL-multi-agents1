package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Observable;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.Environement;

public class Vue extends Observable { // implements Observer

  Environement env;
  GridPanel grid;
  ControlPanel control;

  public Vue(GridPanel pnl,ControlPanel control) {
    
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        grid = pnl;
        JFrame frame = new JFrame("Chambre a particules");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(grid,BorderLayout.CENTER);
        panel.add(control,BorderLayout.EAST);
        panel.setBackground(new Color(205,239,255));
        frame.add(panel);        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    });
  }

  public void setEnvironement(Environement env) {
    this.env = env;
  }

  public void updateVue(Environement env) {
    setChanged();
    notifyObservers(); // on notifie que la vue a chang�
  }
}
