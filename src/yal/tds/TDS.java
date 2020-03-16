package yal.tds;

import yal.arbre.Decl_Fonction;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class TDS {

    private HashMap<Entree,Symbole> tableA;
    private Vector<HashMap<Entree, Symbole>> table;
    private int deplacement_tot;
    private int deplacement_fonction;
    private ArrayList<AnalyseSemantiqueException> exceptions_sem;
    private ArrayList<Decl_Fonction> fonctions;
    private static TDS instance = new TDS();
    private int numBloc;
    private int numBlocCourant;

    public TDS(){
        tableA = new HashMap<Entree,Symbole>();
        table = new Vector<>();

        deplacement_tot = 0;
        deplacement_fonction = 0;
        exceptions_sem = new ArrayList<AnalyseSemantiqueException>();
        fonctions = new ArrayList<Decl_Fonction>();
        numBloc = -1;
        numBlocCourant = 0;
    }

    public void ajouter(Entree e, Symbole s, int noLigne){

        boolean existe = false;

        if (numBlocCourant != 0) {
            for (Entree entry : getBloc(numBlocCourant).keySet()){
                if (entry.getNom().equals(e.getNom()) && entry.getClass() == e.getClass()){
                    existe = true;
                }
            }
        }

        for (Entree entry :  getBloc(0).keySet()){
            if (entry.getNom().equals(e.getNom()) && entry.getClass() == e.getClass()){
                existe = true;
            }
        }

        if (!existe){
            if (e.getClass() == Fonction.class){
                getBloc(0).put(e,s);
            } else {
                getBloc(numBlocCourant).put(e,s);
            }

            if (numBlocCourant == 0){
                deplacement_tot -= e.getTaille();
            } else {
                deplacement_fonction -= e.getTaille();
            }
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

        if (e.getClass() == Fonction.class){
            for (Entree entry : getBloc(0).keySet()){
                if (entry.getNom().equals(e.getNom()) && e.getClass() == entry.getClass()){
                    s = getBloc(0).get(entry);
                }
            }
        } else {
            for (Entree entry : getBloc(numBlocCourant).keySet()){
                if (entry.getNom().equals(e.getNom()) && e.getClass() == entry.getClass()){
                    s = getBloc(numBlocCourant).get(entry);
                }
            }

            if (s == null){
                for (Entree entry : getBloc(0).keySet()){
                    if (entry.getNom().equals(e.getNom()) && e.getClass() == entry.getClass()){
                        s = getBloc(0).get(entry);
                    }
                }
            }
        }

        return s;
    }

    public void entreeBloc(){
        numBloc++;
        numBlocCourant = numBloc;
        deplacement_fonction = 0;
        table.add(numBlocCourant, new HashMap<Entree,Symbole>());
    }

    public void sortieBloc(){
        numBlocCourant = 0;
        deplacement_fonction = 0;
    }

    public static TDS getInstance(){
        return instance;
    }

    public int getTailleZoneVariable(){
        return deplacement_tot;
    }

    public int getDeplacement(){
        int depl = 0;
        if (numBlocCourant == 0){
            depl = deplacement_tot;
        } else {
            depl = deplacement_fonction;
        }
        return depl;
    }

    public ArrayList<AnalyseSemantiqueException> getExceptions_sem() {
        return exceptions_sem;
    }

    public ArrayList<Decl_Fonction> getFonctions() {
        return fonctions;
    }

    public void addFonction(Decl_Fonction f){
        fonctions.add(f);
    }

    public int getNumBlocCourant() {
        return numBlocCourant;
    }

    public void setNumBlocCourant(int n) {
        numBlocCourant = n;
    }

    public HashMap<Entree, Symbole> getBloc(int n){
        return table.elementAt(n);
    }

    public void afficher(){
        for (HashMap<Entree, Symbole> v : table){
            for (Entree entry : v.keySet()){
                if (entry.getClass() == Fonction.class){
                    System.out.println("Fonction " + entry.getNom());
                } else {
                    System.out.println("Variable " + entry.getNom() + " - Deplacement : " + v.get(entry).getDeplacement());
                }
            }
        }
    }

}
