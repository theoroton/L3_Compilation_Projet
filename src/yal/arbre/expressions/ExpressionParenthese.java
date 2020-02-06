package yal.arbre.expressions;

public class ExpressionParenthese extends Expression {

    protected Expression expression;

    public ExpressionParenthese(Expression exp, int n) {
        super(n);
        expression = exp;
    }

    @Override
    public String affect() {
        return null;
    }

    @Override
    public String type() {
        return expression.type();
    }

    @Override
    public void verifier() {
        expression.verifier();
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
