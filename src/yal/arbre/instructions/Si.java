package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.TDS;

public class Si extends Instruction {

    protected Expression condition;
    protected ArbreAbstrait instructions_Si;
    protected ArbreAbstrait instructions_Sinon;

    public Si(Expression exp, ArbreAbstrait si, ArbreAbstrait sinon, int n){
        super(n);
        condition = exp;
        instructions_Si = si;
        instructions_Sinon = sinon;
    }

    @Override
    public void verifier() {
        condition.verifier();

        if (!condition.type().equals("bool")){
            throw new AnalyseSemantiqueException(noLigne, "La condition n'est pas un booléen");
        }

        instructions_Si.verifier();

        if (instructions_Sinon != null) {
            instructions_Sinon.verifier();
        }
    }

    @Override
    public String toMIPS() {
        int numSi = TDS.getInstance().getNumSi();
        StringBuffer mips = new StringBuffer();
        mips.append("\tSi" + numSi + " : #SI\n");

        mips.append("\t" + condition.toMIPS());

        if (instructions_Sinon != null) {
            mips.append(" Sinon" + numSi + " #ALLER A SINON\n\n");
        } else {
            mips.append(" FinSi" + numSi + " #ALLER A FINSI\n\n");
        }

        TDS.getInstance().setBlocPrincipal(false);

        mips.append("\t" + instructions_Si.toMIPS());
        mips.append("\t\tb FinSi" + numSi + " #ALLER A FIN SI\n\n");

        if (instructions_Sinon != null) {
            mips.append("\tSinon" + numSi + " : #SINON\n");
            mips.append("\t" + instructions_Sinon.toMIPS());
        }

        TDS.getInstance().setBlocPrincipal(true);

        mips.append("\tFinSi" + numSi + " : #FINSI\n");
        return mips.toString();
    }
}
