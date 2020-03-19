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
            if (getClass() == SymboleVariable.class){
                depl = TDS.getInstance().getTailleZoneVariable() + deplacement;
            } else {
                depl = deplacement;
            }

        }
        return depl;
    }

    public int getBloc() {
        return bloc;
    }
}
