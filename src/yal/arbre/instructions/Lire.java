package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.IDF;

public class Lire extends Instruction {

    protected Expression idf;

    public Lire (Expression i, int n) {
        super(n) ;
        idf = i;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
