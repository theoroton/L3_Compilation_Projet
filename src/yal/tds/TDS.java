package yal.tds;

import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;
import java.util.HashMap;

public class TDS {

    private HashMap<Entree,Symbole> table;
    private int deplacement_tot;
    private ArrayList<AnalyseSemantiqueException> exceptions_sem;
    private static TDS instance = new TDS();
    private int numSi;
    private int numTantque;
    private int blocPrincipal;

    public TDS(){
        table = new HashMap<Entree,Symbole>();
        deplacement_tot = 0;
        exceptions_sem = new ArrayList<AnalyseSemantiqueException>();
        numSi = 0;
        numTantque = 0;
        blocPrincipal = 0;
    }

    public void ajouter(Entree e,Symbole s,int noLigne){

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
            AnalyseSemantiqueException ex = new AnalyseSemantiqueException(noLigne, "Variable '"+e.getNom()+ "' : déjà déclarée");
            exceptions_sem.add(ex);
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

    public ArrayList<AnalyseSemantiqueException> getExceptions_sem() {
        return exceptions_sem;
    }

    public static TDS getInstance(){
        return instance;
    }

    public int getNumSi(){
        numSi++;
        return numSi;
    }

    public int getNumTantque(){
        numTantque++;
        return numTantque;
    }

    public boolean isBlocPrincipal() {
        return blocPrincipal == 0;
    }

    public void plusNiveau(){
        blocPrincipal++;
    }

    public void moinsNiveau(){
        blocPrincipal--;
    }
}
