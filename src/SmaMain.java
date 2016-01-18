
public class SmaMain {

  public static void main(String[] args) {
    /*
     * Parametres :
     * - taille de la grille
     * - nombre de billes
     * - taille de la bille
     * - vitesse
     * 
     * Paramètres optionnels :
     * - visibilité de la grille
     * - équité -> shuffle
     * - grille torique
     * - seed pour le Random (voir https://docs.oracle.com/javase/7/docs/api/java/util/Random.html#setSeed(long))
     */

    int size = 100;
    int nombreAgents = 50;
    int tours = 10;
    long vitesse = 2000;

    try {
      SMA sma = new SMA();
      sma.init(size, nombreAgents);
      sma.run(tours, vitesse);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
