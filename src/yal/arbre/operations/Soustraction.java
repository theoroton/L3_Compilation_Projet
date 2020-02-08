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
        StringBuffer mips = new StringBuffer();
        mips.append( "sub $v0, $t8, $v0");
        return mips.toString();
    }
}
