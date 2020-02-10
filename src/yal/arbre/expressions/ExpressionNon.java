package yal.arbre.expressions;

import yal.exceptions.AnalyseSemantiqueException;

public class ExpressionNon extends Expression {

    protected Expression expression;

    public ExpressionNon(Expression exp, int n) {
        super(n);
        expression = exp;
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
        StringBuffer mips = new StringBuffer();
        mips.append(expression.toMIPS() + "\n");
        mips.append("\t#Expression non EXP\n");
        mips.append("\txor $v0, $v0, 1\n");
        return mips.toString();
    }
}
