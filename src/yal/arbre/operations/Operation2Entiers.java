package yal.arbre.operations;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public abstract class Operation2Entiers extends Operation {

    protected Operation2Entiers(int n) {
        super(n);
    }

    @Override
    public void operandes(Expression e1, Expression e2) {
        if (!(e1.type().equals("int") && e2.type().equals("int"))){
            throw new AnalyseSemantiqueException(noLigne, e1 + " + "  + e2 + " : les opérandes ne sont pas entiers");
        }
    }
}
