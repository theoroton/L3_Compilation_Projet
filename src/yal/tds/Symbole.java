package yal.tds;

public abstract class Symbole {

    protected int deplacement;
    protected int bloc;

    public Symbole(int d){
        deplacement = d;
        bloc = TDS.getInstance().getNumBlocCourant();
    }

    public int getDeplacement(){
        return deplacement;
    }

    public int getBloc() {
        return bloc;
    }

    public void setTaille(int taille){}
    public int getTaille(){ return 0; }
}
