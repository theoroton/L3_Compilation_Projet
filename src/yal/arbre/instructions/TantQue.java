package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.Expression;

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
        instructions.verifier();
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
