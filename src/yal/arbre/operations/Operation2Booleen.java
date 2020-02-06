package yal.arbre.operations;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public abstract class Operation2Booleen extends Operation {

    protected Operation2Booleen(int n) {
        super(n);
    }

    @Override
    public void operandes(Expression e1, Expression e2) {
        if (!(e1.type().equals("bool") && e2.type().equals("bool"))){
            throw new AnalyseSemantiqueException(noLigne, e1 + " " + symbole() + " " + e2 + " : les opérandes ne sont pas des booléens");
        }
    }

    @Override
    public String resultat() {
        return "bool";
    }
}
