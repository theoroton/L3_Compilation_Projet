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
        StringBuffer mips = new StringBuffer();
        mips.append("div $t8, $v0\n");
        mips.append("\tmflo $v0");
        return mips.toString();
    }
}
