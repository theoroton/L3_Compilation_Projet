package yal.tds;

public abstract class Symbole {

    protected int deplacement;

    public Symbole(int d){
        deplacement = d;
    }

    public int getDeplacement(){
        return deplacement;
    }

    public abstract int getIndiceFonction();
}
