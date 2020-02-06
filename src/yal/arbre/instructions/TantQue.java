package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.TDS;

import java.util.ArrayList;

public class TantQue extends Instruction {

    protected Expression condition;
    protected ArbreAbstrait instructions ;

    public TantQue(Expression exp, ArbreAbstrait intrs, int n){
        super(n);
        condition = exp;
        instructions = intrs;
    }

    @Override
    public void verifier() {
        condition.verifier();

        if (!condition.type().equals("bool")){
            throw new AnalyseSemantiqueException(noLigne, "La condition n'est pas un booléen");
        }

        instructions.verifier();
    }

    @Override
    public String toMIPS() {
        int numTantque = TDS.getInstance().getNumTantque();
        StringBuffer mips = new StringBuffer();
        mips.append("\tTantQue" + numTantque + " :\n");

        mips.append("\t" + condition.toMIPS());
        mips.append("\n");

        TDS.getInstance().setBlocPrincipal(false);

        mips.append("\t" + instructions.toMIPS());

        TDS.getInstance().setBlocPrincipal(true);

        mips.append("\t\tb FinTantQue" + numTantque + "\n");
        mips.append("\tFinTantQue" + numTantque + " :\n");
        return mips.toString();
    }
}