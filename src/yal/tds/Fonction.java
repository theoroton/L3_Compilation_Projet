package yal.tds;

public class Fonction extends Entree {

    int nbParams;

    public Fonction(String n, int nb) {
        super(n);
        nbParams = nb;
    }

    public int getTaille() {
        return 0;
    }

    public int getNbParams() {
        return nbParams;
    }
}
