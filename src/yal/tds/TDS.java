package yal.tds;

import yal.arbre.Decl_Fonction;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;
import java.util.HashMap;

public class TDS {

    private HashMap<Entree,Symbole> table;
    private int deplacement_tot;
    private ArrayList<AnalyseSemantiqueException> exceptions_sem;
    private ArrayList<Decl_Fonction> fonctions;
    private static TDS instance = new TDS();

    public TDS(){
        table = new HashMap<Entree,Symbole>();
        deplacement_tot = 0;
        exceptions_sem = new ArrayList<AnalyseSemantiqueException>();
        fonctions = new ArrayList<Decl_Fonction>();
    }

    public void ajouter(Entree e, Symbole s, int noLigne){

        boolean existe = false;

        for (Entree entry : table.keySet()){
            if (entry.getNom().equals(e.getNom()) && entry.getClass() == e.getClass()){
                existe = true;
            }
        }

        if (!existe){
            table.put(e,s);
            deplacement_tot -= e.getTaille();
        } else {
            AnalyseSemantiqueException ex;
            if (e.getClass() == Variable.class){
                 ex = new AnalyseSemantiqueException(noLigne, "Variable '"+e.getNom()+ "' : déjà déclarée");
            } else {
                 ex = new AnalyseSemantiqueException(noLigne, "Fonction '"+e.getNom()+ "' : déjà déclarée");
            }

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

    public static TDS getInstance(){
        return instance;
    }

    public int getTailleZoneVariable(){
        return deplacement_tot;
    }

    public ArrayList<AnalyseSemantiqueException> getExceptions_sem() {
        return exceptions_sem;
    }

    public ArrayList<Decl_Fonction> getFonctions() {
        return fonctions;
    }

    public void addException(AnalyseSemantiqueException e) {
        exceptions_sem.add(e);
    }

    public void addFonction(Decl_Fonction f){
        fonctions.add(f);
    }


}
