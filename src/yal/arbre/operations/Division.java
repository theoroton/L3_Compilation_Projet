package yal.arbre.operations;

import yal.arbre.expressions.Expression;

public class Division extends OperationResultEntier {

    public Division(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "/";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
