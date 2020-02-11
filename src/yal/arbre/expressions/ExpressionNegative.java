package yal.arbre.expressions;

import yal.exceptions.AnalyseSemantiqueException;

public class ExpressionNegative extends Expression {

    protected Expression expression;

    public ExpressionNegative(Expression exp, int n) {
        super(n);
        expression = exp;
    }

    @Override
    public String type() {
        return "int";
    }

    @Override
    public void verifier() {
        expression.verifier();
        if (!(expression.type().equals("int"))){
            throw new AnalyseSemantiqueException(noLigne, expression + " : l'expression n'est pas un entier");
        }
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append(expression.toMIPS());
        mips.append("\t#Expression - EXP\n");
        mips.append("\tneg $v0, $v0\n");
        return mips.toString();
    }
}
