package yal.tds;

public class SymboleTableau extends Symbole {

    private int taille;

    public SymboleTableau(int d) {
        super(d);
    }

    public void setTaille(int t) {
        taille = t/4;
    }

    public int getTaille(){
        return taille;
    }
}
