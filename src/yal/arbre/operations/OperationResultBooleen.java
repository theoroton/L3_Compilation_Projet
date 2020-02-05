package yal.arbre.operations;

public abstract class OperationResultBooleen extends Operation2Entiers {

    protected OperationResultBooleen(int n) {
        super(n);
    }

    @Override
    public String resultat() {
        return "bool";
    }
}
