package yal.tds;

import yal.arbre.Decl_Fonction;

public class Fonction extends Entree {

    protected int nbParams;

    public Fonction(String n) {
        super(n);
        nbParams = 0;
    }

    public int getTaille() {
        return 0;
    }
}
