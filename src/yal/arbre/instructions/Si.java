package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.Expression;
import yal.tds.TDS;

public class Si extends Instruction {

    protected Expression condition;
    protected ArbreAbstrait instructions_Si;
    protected ArbreAbstrait instructions_Alors;

    public Si(Expression exp, ArbreAbstrait si, ArbreAbstrait alors, int n){
        super(n);
        condition = exp;
        instructions_Si = si;
        instructions_Alors = alors;
    }

    @Override
    public void verifier() {
        instructions_Si.verifier();
        System.out.println(TDS.getInstance().getNumSi());

        if (instructions_Alors != null){
            instructions_Alors.verifier();
        }

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
