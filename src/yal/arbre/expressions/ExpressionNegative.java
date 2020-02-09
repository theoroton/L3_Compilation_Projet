package yal.arbre.expressions;

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

    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append(expression.toMIPS());
        mips.append("\tneg $v0, $v0\n");
        return mips.toString();
    }
}
