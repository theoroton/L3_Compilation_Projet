package yal.tds;

import java.util.HashMap;

public class TDS {

    private HashMap<Entree,Symbole> table;
    private int deplacement_tot;
    private static TDS instance = new TDS();

    public TDS(){
        table = new HashMap<Entree,Symbole>();
        deplacement_tot = 0;
    }

    public void ajouter(Entree e,Symbole s){

        boolean existe = false;

        for (Entree entry : table.keySet()){
            if (entry.getNom().equals(e.getNom())){
                existe = true;
            }
        }

        if (!existe){
            table.put(e,s);
            deplacement_tot -= e.getTaille();
        } else {
            //A ajouter en exception
            System.out.println(e.getNom() + " DEJA DECLAREE");
        }

    }

    public Symbole identifier(Entree e){
        Symbole s = null;
        for (Entree entry : table.keySet()){
            if (entry.getNom().equals(e.getNom())){
                s = table.get(entry);
            }
        }

        return s;
    }

    public int getTailleZoneVariable(){
        return deplacement_tot;
    }

    public static TDS getInstance(){
        return instance;
    }
}
