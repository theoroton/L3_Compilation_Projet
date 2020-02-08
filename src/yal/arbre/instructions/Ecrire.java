package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("\tli $v0, 1\n");
        mips.append(exp.toMIPS());
        mips.append("\tsyscall\n");

        mips.append("\n");

        mips.append("\tli $v0, 4\n");
        mips.append("\tla $a0, newLine\n");
        mips.append("\tsyscall\n");
        return mips.toString();
    }

}
