package yal.tds;

public abstract class Entree {

    protected String nom;

    public Entree(String n){
        nom = n;
    }

    public String getNom(){
        return nom;
    }
    public abstract int getTaille();

}
