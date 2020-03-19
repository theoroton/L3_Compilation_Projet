package yal.tds;

import yal.arbre.Decl_Fonction;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class TDS {

    private Vector<HashMap<Entree, Symbole>> table;
    private int deplacement_tot;
    private int deplacement_fonction;
    private int deplacement_parametres;
    private ArrayList<AnalyseSemantiqueException> exceptions_sem;
    private ArrayList<Decl_Fonction> fonctions;
    private static TDS instance = new TDS();
    private int numBloc;
    private int numBlocCourant;
    private int nbParamsCourant;

    public TDS(){
        table = new Vector<>();
        table.add(0, new HashMap<Entree,Symbole>());

        deplacement_tot = 0;
        deplacement_fonction = 0;
        deplacement_parametres = 0;
        exceptions_sem = new ArrayList<AnalyseSemantiqueException>();
        fonctions = new ArrayList<Decl_Fonction>();
        numBloc = 0;
        numBlocCourant = 0;
        nbParamsCourant = 0;
    }

    public void ajouter(Entree e, Symbole s, int noLigne){
        boolean existe = false;

        if (e.getClass() == Fonction.class){
            for (Entree entry : getBloc(0).keySet()){
                if (entry.getNom().equals(e.getNom()) && entry.getClass() == Fonction.class && ((Fonction) entry).getNbParams() == ((Fonction) e).getNbParams()){
                    existe = true;
                }
            }
        } else {
            for (Entree entry : getBloc(numBlocCourant).keySet()){
                if (entry.getNom().equals(e.getNom())){
                    existe = true;
                }
            }
        }

        if (numBlocCourant >= 0 && e.getClass() != Fonction.class) {
            for (Entree entry : getBloc(numBlocCourant).keySet()){
                if (entry.getNom().equals(e.getNom())) {
                    existe = true;
                }
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
                if (e.getClass() == Variable.class){
                    deplacement_fonction -= e.getTaille();
                } else {
                    deplacement_parametres -= e.getTaille();
                }
            }
        } else {
            AnalyseSemantiqueException ex;
            if (e.getClass() == Variable.class){
                 ex = new AnalyseSemantiqueException(noLigne, "Variable '"+e.getNom()+ " : déjà déclarée");
            } else if (e.getClass() == Fonction.class){
                 ex = new AnalyseSemantiqueException(noLigne, "Fonction '"+e.getNom()+ "' avec " + ((Fonction) e).getNbParams() + " paramètres : déjà déclarée");
            } else {
                 ex = new AnalyseSemantiqueException(noLigne, "Paramètre '"+e.getNom()+ "' : déjà déclaré");
            }

            exceptions_sem.add(ex);
        }
    }

    public Symbole identifier(Entree e){
        Symbole s = null;

        if (e.getClass() == Fonction.class){
            for (Entree entry : getBloc(0).keySet()){
                if (entry.getNom().equals(e.getNom()) && entry.getClass() == Fonction.class && ((Fonction) entry).getNbParams() == ((Fonction) e).getNbParams()){
                    s = getBloc(0).get(entry);
                }
            }
        } else {
            for (Entree entry : getBloc(numBlocCourant).keySet()){
                if (entry.getNom().equals(e.getNom()) && entry.getClass() != Fonction.class){
                    s = getBloc(numBlocCourant).get(entry);
                }
            }

            if (s == null){
                for (Entree entry : getBloc(0).keySet()){
                    if (entry.getNom().equals(e.getNom()) && entry.getClass() != Fonction.class){
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
        deplacement_parametres = 0;
        nbParamsCourant = 0;
        table.add(numBlocCourant, new HashMap<Entree,Symbole>());
    }

    public void sortieBloc(){
        numBlocCourant = 0;
        deplacement_parametres = 0;
        deplacement_fonction = 0;
        nbParamsCourant = 0;
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

    public int getDeplacementParametre(){
        return deplacement_parametres;
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

    public void addParam(){
        nbParamsCourant++;
    }

    public int getNbParamsCourant() {
        return nbParamsCourant;
    }

    public HashMap<Entree, Symbole> getBloc(int n){
        return table.elementAt(n);
    }

    public void afficher(){
        for (HashMap<Entree, Symbole> v : table){
            System.out.println("-----------");
            for (Entree entry : v.keySet()){
                if (entry.getClass() == Fonction.class){
                    System.out.println("Fonction " + entry.getNom() + " - Parametres : " + ((Fonction) entry).getNbParams());
                } else if (entry.getClass() == Variable.class) {
                    System.out.println("Variable " + entry.getNom() + " - Deplacement : " + v.get(entry).getDeplacement());
                } else {
                    System.out.println("Parametre " + entry.getNom() + " - Deplacement : " + v.get(entry).getDeplacement());
                }
            }
        }
        System.out.println(table);
    }

}
