package view;

import core.Environement;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class Vue extends Observable { // implements Observer

  Environement env;
  GridPanel grid;
  ControlPanel control;

  public Vue(final GridPanel pnl, final ControlPanel control) {

    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        grid = pnl;
        JFrame frame = new JFrame("IDL - Multiagents");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(grid, BorderLayout.CENTER);
        panel.add(control, BorderLayout.EAST);

        //panel.setBackground(new Color(205,239,255));
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
    notifyObservers(); // on notifie que la vue a changé
  }
}
