package yal.arbre.operations;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Addition extends OperationResultEntier {

    public Addition(int n) {
        super(n);
    }

    @Override
    public void verifier() {}

    @Override
    public String toMIPS() {
        return null;
    }

}
