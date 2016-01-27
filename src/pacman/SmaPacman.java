package pacman;

import core.Agent;
import core.Environement;
import core.SMA;
import util.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Pauline on 27/01/2016.
 */
public class SmaPacman implements SMA {

  private Environement env;
  private List<Agent> agents;

  @Override public void init() {
    this.agents = new ArrayList<Agent>();

    // Creation des blocs
    for (int indexBlocs = 0; indexBlocs < Data.nombreBlocs; indexBlocs++) {
      int x = obtenirPositionRandom(Data.size);
      int y = obtenirPositionRandom(Data.size);

      // TODO: a modifier pour mettre en pourcentage
      Bloc bloc = new Bloc(x, y);
      this.agents.add(bloc);
      Data.nombreAgents++;
    }

    // Poursuiveurs
    for (int indexPoursuiveurs = 0; indexPoursuiveurs < Data.nombrePoursuiveurs; indexPoursuiveurs++) {
      int x = obtenirPositionRandom(Data.size);
      int y = obtenirPositionRandom(Data.size);

      Poursuiveur poursuiveur = new Poursuiveur(x, y);
      this.agents.add(poursuiveur);
      Data.nombreAgents++;
    }

    // Avatar
    int x = obtenirPositionRandom(Data.size);
    int y = obtenirPositionRandom(Data.size);
    Avatar avatar = new Avatar(x, y);
    this.agents.add(avatar);
    Data.nombreAgents++;
  }

  @Override public void run() {

    for (int tour = 0; tour < Data.tours; tour++) {
      if (Data.equite) {
        Collections.shuffle(this.agents);
      }

      // On fait parler chaque agent
      for (Agent agent : this.agents) {
        agent.doIt();
      }

      // A la fin de chaque tour, on reset la direction de l'avatar
      Avatar.setDIRECTION(null);
    }
  }

  private Integer obtenirPositionRandom(Integer size) {
    Random rand = new Random();
    return rand.nextInt(size);
  }
}
