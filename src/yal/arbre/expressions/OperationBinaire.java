package yal.arbre.expressions;

import yal.arbre.operations.Operation;

public class OperationBinaire extends Expression {

    protected Expression expGauche;
    protected Expression expDroite;
    protected Operation operation;

    public OperationBinaire(Expression e1, Operation op, Expression e2, int n) {
        super(n);
        expGauche = e1;
        expDroite = e2;
        operation = op;
    }

    @Override
    public void verifier() {
        expGauche.verifier();
        expDroite.verifier();
        operation.operandes(expGauche, expDroite);
    }

    @Override
    public String toMIPS() {
        return null;
    }

    @Override
    public String affect() {
        return null;
    }

    @Override
    public String type() {
        return operation.resultat();
    }
}
