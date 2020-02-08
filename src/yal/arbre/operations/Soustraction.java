package yal.arbre.operations;

import yal.arbre.expressions.Expression;

public class Soustraction extends OperationResultEntier {

    public Soustraction(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "-";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return "sub $v0,";
    }
}
