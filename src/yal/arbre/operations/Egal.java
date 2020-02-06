package yal.arbre.operations;

public class Egal extends OperationMemeType {

    public Egal(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "==";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
