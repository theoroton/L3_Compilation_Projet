package yal.arbre.expressions;

public class IDF extends Expression {

    protected String nom;

    public IDF(String no, int n) {
        super(n) ;
        nom = no;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
