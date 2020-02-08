package yal.arbre.operations;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Addition extends OperationResultEntier {

    public Addition(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "+";
    }

    @Override
    public void verifier() {}

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("add $v0, $t8, $v0");
        return mips.toString();
    }

}
