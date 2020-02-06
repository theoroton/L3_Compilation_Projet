package yal.arbre.operations;

public class Inferieur extends OperationResultBooleen {

    public Inferieur(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "<";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
