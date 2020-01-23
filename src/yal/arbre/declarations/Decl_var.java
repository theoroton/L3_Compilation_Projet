package yal.arbre.declarations;

import yal.arbre.expressions.Expression;

public class Decl_var extends Declaration {

    protected Expression var;

    public Decl_var(Expression e, int n){
        super(n);
        var = e;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
