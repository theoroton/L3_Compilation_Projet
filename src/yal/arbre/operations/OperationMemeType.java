package yal.arbre.operations;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public abstract class OperationMemeType extends Operation {

    protected OperationMemeType(int n) {
        super(n);
    }

    @Override
    public void operandes(Expression e1, Expression e2) {
        if (!(e1.type().equals(e2.type()))){
            throw new AnalyseSemantiqueException(noLigne, e1 + " " + symbole() + " " + e2 + " : les opérandes ne sont pas de même types");
        }
    }

    @Override
    public String resultat() {
        return "bool";
    }
}
