package yal.arbre.operations;

public class Different extends OperationMemeType {

    public Different(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "!=";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
