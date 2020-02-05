package yal.arbre.operations;

public abstract class OperationResultEntier extends Operation2Entiers {

    protected OperationResultEntier(int n) {
        super(n);
    }

    @Override
    public String resultat() {
        return "int";
    }
}
