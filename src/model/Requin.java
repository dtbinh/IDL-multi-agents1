package model;

import util.Data;

import java.util.Random;

public class Requin implements Agent {

    private int posX;
    private int posY;
    private Integer pasX;
    private Integer pasY;
    private Environement envi;
    private int tour;
    private int pv; // points de vie

    public Requin(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.pasX = null;
        this.pasY = null;
        this.pv = Data.longeviteRequin;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public void setPosX(int posX) {
        this.posX = posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public Integer getPasX() {
        return pasX;
    }

    @Override
    public void setPasX(int pasX) {
        this.pasX = pasX;
    }

    @Override
    public Integer getPasY() {
        return pasY;
    }

    @Override
    public void setPasY(int pasY) {
        this.pasY = pasY;
    }

    @Override
    public void setEnv(Environement env) {
        this.envi = env;
    }

    @Override
    public Environement getEnv() {
        return this.envi;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    @Override
    public Requin doIt() {

        Coordonnees coordPoisson = peutManger();

        if (coordPoisson != null) { // Alors il y a un poisson à manger

            // On supprime le poisson
            this.envi.deleteAgent(coordPoisson.getX(), coordPoisson.getY());

            // On déplace le requin jusque la position du poisson
            this.envi.deleteAgent(this.getPosX(), this.getPosY());

            this.setPosX(coordPoisson.getX());
            this.setPosY(coordPoisson.getY());
            this.envi.addAgent(this);

        } else { // Alors il n'y a aucun poisson à manger
            Coordonnees placeLibre = chercherPlaceLibre();

            if (placeLibre != null) { // Il y a une place libre à proximité

                if (peutSeReproduire()) {
                    // Alors on place notre bébé ici et on se déplace en même temps
                    seReproduire(placeLibre.getX(), placeLibre.getY());
                } else {
                    // On se déplace uniquement
                    seDeplacer(placeLibre.getX(), placeLibre.getY());
                }
            }
        }

        // On décrémente le nombre de points de vie du requin
        this.setPv(this.getPv() - 1);

        return this;
    }

    /**
     * Vérifie si un requin doit mourir ou non
     *
     * @return <code>true</code> si le requin doit mourir, <code>false</code> sinon.
     */
    public boolean doitMourir() {
        return this.getPv() == 0;
    }

    /**
     * Vérifie si le requin peut se reproduire.
     *
     * @return <code>true</code> si le requin peut se reproduire, <code>false</code> sinon
     */
    private boolean peutSeReproduire() {
        if (this.tour == Data.seedRequin) {
            this.tour = 0;
            return true;
        } else {
            this.tour++;
            return false;
        }
    }

    /**
     * Faire se reproduire un requin. Donne naissance à un nouveau requin et se déplace.
     *
     * @param prochainX la coordonnée X du requin qui se déplace
     * @param prochainY la coordonnée Y du requin qui se déplace
     * @return le nouveau requin
     */
    private Requin seReproduire(int prochainX, int prochainY) {
        if (peutSeReproduire() && Data.nombreAgents < (Data.size * Data.size)) {
            int nouvellePositionX = this.getPosX();
            int nouvellePositionY = this.getPosY();

            // On déplace le requin qui donne naissance
            this.setPosX(prochainX);
            this.setPosY(prochainY);
            this.getEnv().deleteAgent(nouvellePositionX, nouvellePositionY);
            this.getEnv().addAgent(this);

            // On fait naître le nouveau requin
            Requin bebeRequin = new Requin(nouvellePositionX, nouvellePositionY);
            bebeRequin.setEnv(this.getEnv());

            // On met à jour les compteurs
            Data.nombreAgents++;
            Data.nombreRequins++;

            return bebeRequin;
        } else {
            return null;
        }
    }

    /**
     * Vérifie si le requin peut manger un poisson aux alentours.
     * Si c'est le cas, on retourne les coordonnées du poisson à manger.
     *
     * @return Le couple de coordonées du poisson qui peut être mangé.
     */
    private Coordonnees peutManger() {
        Coordonnees coordonnees = null;

        Direction[] directions = Direction.values();

        // On va parcourir toutes les directions en partant d'une direction au hasard
        Random rand = new Random();
        int r = rand.nextInt(directions.length);

        for (int index = r; index < directions.length + r; index++) {
            // On récupère la direction
            Direction d = directions[index % directions.length];

            if (this.getEnv().agentIsPresent(this.getPosX() + d.getDeltaX(), this.getPosY() + d.getDeltaY())) {
                if (this.getEnv().getAgent(this.getPosX() + d.getDeltaX(), this.getPosY() + d.getDeltaY()) instanceof Poisson) {
                    coordonnees.setX(this.getPosX() + d.getDeltaX());
                    coordonnees.setY(this.posY + d.getDeltaY());
                    break;
                }
            }
        }

        return coordonnees;
    }

    /**
     * Vérifie s'il y a une place libre aux alentours.
     * Si c'est le cas, on retourne les coordonées de la place libre.
     *
     * @return Le couple de coordonnées d'une place libre à proximité du requin.
     */
    private Coordonnees chercherPlaceLibre() {
        Coordonnees coordonnees = new Coordonnees(0,0);
    boolean ok = false;

        Direction[] directions = Direction.values();

        // On va parcourir toutes les directions en partant d'une direction au hasard
        Random rand = new Random();
        int r = rand.nextInt(directions.length);

        for (int index = r; index < directions.length + r; index++) {
            // On récupère la direction
            Direction d = directions[index % directions.length];

            if (!this.getEnv().agentIsPresent(this.getPosX() + d.getDeltaX(), this.getPosY() + d.getDeltaY())) {
                coordonnees.setX(this.getPosX() + d.getDeltaX());
                coordonnees.setY(this.getPosY() + d.getDeltaY());
                ok = true;
                break;
            }

        }

        return ok? coordonnees : null;
    }

    /**
     * Faire se déplacer un requin.
     */
    private void seDeplacer(int nouvellePositionX, int nouvellePositionY) {
        int oldX = this.getPosX();
        int oldY = this.getPosY();

        this.getEnv().deleteAgent(oldX, oldY);

        this.setPosX(nouvellePositionX);
        this.setPosY(nouvellePositionY);
        this.getEnv().addAgent(this);
    }

    /**
     * Génére un nombre aléatoire entre -1 et 1.
     *
     * @return un nombre aléatoire
     */
    private int genererDirection() {
        int result;
        Random r = new Random();
        do {
            result = r.nextInt(3) - 1;
        } while (result == 0);
        return result;
    }
}
