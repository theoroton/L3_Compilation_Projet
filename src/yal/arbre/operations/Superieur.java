package yal.arbre.operations;

public class Superieur extends OperationResultBooleen {

    public Superieur(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return ">";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return "ble";
    }
}
