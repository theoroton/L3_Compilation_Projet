package yal.arbre.expressions;

import yal.exceptions.AnalyseSemantiqueException;

public class ExpressionNon extends Expression {

    protected Expression expression;

    public ExpressionNon(Expression exp, int n) {
        super(n);
        expression = exp;
    }

    @Override
    public String affect() {
        return null;
    }

    @Override
    public String type() {
        return "bool";
    }

    @Override
    public void verifier() {
        expression.verifier();
        if (!(expression.type().equals("bool"))){
            throw new AnalyseSemantiqueException(noLigne, expression + " : l'expression n'est pas un booléen");
        }
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
