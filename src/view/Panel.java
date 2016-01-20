package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Environement;
import util.Data;

@SuppressWarnings("serial")
public class Panel extends JPanel implements Observer {

  Environement env;
  private JPanel[][] grid;
  private JPanel panel;
  private JPanel panelInfo;
  private int tour;
  private JTextField txtTour;

  public Panel(Environement env) {
    this.env = env;
    int size = this.env.getTailleGrille();
    this.grid = new JPanel[size][size];
    this.init(size);
  }

  public void setEnvironement(Environement env) {
    this.env = env;
  }

  public void setTour(int tour) {
    this.tour = tour;
  }

  @Override
  public void update(Observable arg0, Object arg1) {
    this.updateGrid();
    this.updateInfo();
    this.repaint();
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
    this.setPreferredSize(new Dimension(750, 600));

    this.panel = new JPanel();
    this.panel.setLayout(new GridLayout(size, size));// GridBagLayout

    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        grid[x][y] = new JPanel();
        int dim = 600 / size;
        this.grid[x][y].setPreferredSize(new Dimension(dim, dim));
        if (Data.grilleVisible) {
          Border emptyBorder = BorderFactory.createLineBorder(Color.lightGray, 1);// .createLineBorder(Color.gray);
          grid[x][y].setBorder(emptyBorder);
        }
        this.panel.add(grid[x][y]);
      }
    }
    this.add(this.panel);
    this.panelInfo = initInfo();
    this.add(panelInfo);
    // this.updateGrid();
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

  /**
   * Met à jour le nombre de tours dans le panel d'informations.
   */
  private void updateInfo() {
    this.txtTour.setText(this.tour + "");
    this.panelInfo.repaint();
  }

  /**
   * Initialise les composents pour le panel contenant les textes d'infos
   * (nombre de billes, taille de la grille, nombre de tours, vitesse).
   * @return <code>JPanel</code> un JPanel construit
   */
  private JPanel initInfo() {
    JPanel info = new JPanel();
    info.setLayout(new FlowLayout());
    info.setPreferredSize(new Dimension(100, 600));

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

    info.add(lblBilles);
    info.add(txtBilles);
    info.add(lblGrille);
    info.add(txtGrille);
    info.add(lblTour);
    info.add(txtTour);
    info.add(lblVitesse);
    info.add(txtVitesse);

    return info;
  }
}
