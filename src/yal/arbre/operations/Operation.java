package yal.arbre.operations;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.Expression;

public abstract class Operation extends ArbreAbstrait {

    protected Operation(int n) {
        super(n);
    }

    public abstract void operandes(Expression e1, Expression e2);
    public abstract String resultat();
    public abstract String symbole();
}
