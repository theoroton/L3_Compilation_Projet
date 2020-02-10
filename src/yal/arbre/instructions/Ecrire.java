package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.tds.TDS;

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

        mips.append(exp.toMIPS() + "\n");

        if (exp.type().equals("int")){
            mips.append("\t#Ecrire entier\n");
            mips.append("\tmove $a0, $v0\n");
            mips.append("\tli $v0, 1\n");
            mips.append("\tsyscall\n");

            mips.append("\n");

            mips.append("\t#Retour a la ligne\n");
            mips.append("\tli $v0, 4\n");
            mips.append("\tla $a0, newLine\n");
            mips.append("\tsyscall\n");

        } else {
            mips.append("\t#Ecrire booleen\n");
            mips.append("\tjal ecrireBooleen\n");
        }

        return mips.toString();
    }

}
