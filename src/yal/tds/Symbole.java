package yal.tds;

public abstract class Symbole {

    protected int deplacement;
    protected int bloc;

    public Symbole(int d){
        deplacement = d;
        bloc = TDS.getInstance().getNumBlocCourant();
    }

    public int getDeplacement(){
        int depl = 0;
        if (getBloc() == 0){
            depl = deplacement;
        } else {
            depl = TDS.getInstance().getTailleZoneVariable() + deplacement;
        }
        return depl;
    }

    public int getBloc() {
        return bloc;
    }
}
