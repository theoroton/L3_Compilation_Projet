package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.IDF;
import yal.tds.TDS;
import yal.tds.Variable;

public class Affect extends Instruction {

    protected Expression idfGauche;
    protected Expression expDroite;


    public Affect(Expression i, Expression e, int n){
        super(n);
        idfGauche = i;
        expDroite = e;
    }

    @Override
    public void verifier() {
        idfGauche.verifier();
        expDroite.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();

        mips.append("\t" + expDroite.toMIPS() + "\n");
        mips.append("\tsw $v0, "+ TDS.getInstance().identifier(new Variable(idfGauche.toString())).getDeplacement()+"($s7)\n");

        return mips.toString();
    }
}
