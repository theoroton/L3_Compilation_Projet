package yal.arbre.operations;

public class Ou extends Operation2Booleen {

    public Ou(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "ou";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
