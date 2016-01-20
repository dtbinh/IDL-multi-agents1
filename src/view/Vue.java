package view;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.Environement;

public class Vue extends Observable { // implements Observer

  Environement env;
  Panel panel;

  public Vue(Panel pnl) { // Environement env,
    // this.env = env;
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        panel = pnl;
        JFrame frame = new JFrame("Chambre a particules");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLayout(new BorderLayout());
        frame.setLayout(new FlowLayout(FlowLayout.LEADING));
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

  public void updateVue(Environement env, int tour) {
    panel.setEnvironement(env); // on donne le nouvel environnement
    panel.setTour(tour); // on actualise le nombre de tours restants
    setChanged();
    notifyObservers(); // on notifie que la vue a changé
  }

}
