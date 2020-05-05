package yal.tds;

import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.Expression;

public class Tableau extends Entree {

    private int taille;

    public Tableau(String n, Expression e, int noLigne) {
        super(n);
        if (e != null){
            if (e.getClass() == ConstanteEntiere.class){
                int x = Integer.parseInt(e.toString());
                if (x < 1){
                    TDS.getInstance().addExceptionTableau(noLigne);
                } else {
                    taille = x;
                }
            } else {
                TDS.getInstance().addExceptionTableau(noLigne);
            }
        }
    }

    public int getTaille() {
        return taille*4;
    }
}
