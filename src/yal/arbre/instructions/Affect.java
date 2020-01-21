package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.IDF;

public class Affect extends Instruction {

    protected Expression exp;
    protected Expression idf;

    public Affect(Expression i, Expression e, int n){
        super(n);
        idf = i;
        exp = e;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
