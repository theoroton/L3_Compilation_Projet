package yal.tds;

public class Variable extends Entree {

    public Variable(String n){
        super(n);
    }

    public String getNom(){
        return nom;
    }

    public int getTaille() {
        return 4;
    }
}
