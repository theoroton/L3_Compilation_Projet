package yal.arbre.operations;

import yal.arbre.expressions.Expression;

public class Multiplication extends OperationResultEntier {

    public Multiplication(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "*";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("mult $v0, $t8\n");
        mips.append("\tmflo $v0");
        return mips.toString();
    }
}
