package yal.tds;

import java.util.HashMap;

public class TDS {

    private HashMap<Entree,Symbole> table;
    private int depl;
    private static  TDS instance = new TDS();

    public TDS(){
        table = new HashMap<Entree,Symbole>();
        depl = 0;
    }

    public void ajouter(Entree e,Symbole s){

    }

    public void identifier(Entree e){

    }

    public int getTailleZoneVariable(){
        return depl;
    }

    public static TDS getInstance(){
        return instance;
    }
}
