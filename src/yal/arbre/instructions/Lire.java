package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.tds.*;

public class Lire extends Instruction {

    protected Expression idf;

    public Lire (Expression i, int n) {
        super(n) ;
        idf = i;
    }

    @Override
    public void verifier() {
        idf.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("\tli $v0, 5\n");
        mips.append("\tsyscall\n");
        mips.append("\tsw $v0, "+TDS.getInstance().identifier(new Variable(idf.toString())).getDeplacement()+"($s7)\n");
        return mips.toString();
    }
}
