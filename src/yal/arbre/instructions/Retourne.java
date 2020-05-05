package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.factories.BoolFactory;
import yal.tds.TDS;

public class Retourne extends Instruction {

    protected Expression expression;

    public Retourne(Expression e, int n) {
        super(n);
        expression = e;
    }

    @Override
    public void verifier() {
        expression.verifier();

        if (!BoolFactory.getInstance().isDansFonction()){
            throw new AnalyseSemantiqueException(noLigne, "L'instruction RETOURNE n'est pas dans une fonction");
        } else {
            if (!(expression.type().equals("int"))){
                throw new AnalyseSemantiqueException(noLigne, "L'instruction RETOURNE ne renvoie pas un entier");
            }
        }
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();

        mips.append(expression.toMIPS() + "\n");

        mips.append("\t#Sortie fonction\n");
        int vars = TDS.getInstance().getDeplFonction();

        if (vars > 0){
            mips.append("\tadd $s3, $s3, " + vars + "\n\n");
        }

        mips.append("\tjr $ra\n\n");

        return mips.toString();
    }
}
