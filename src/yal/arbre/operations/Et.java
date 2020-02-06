package yal.arbre.operations;

public class Et extends Operation2Booleen {

    public Et(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "et";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
