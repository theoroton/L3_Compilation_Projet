package yal.arbre;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;

public class Parametres extends ArbreAbstrait {

    protected ArrayList<Expression> params ;

    public Parametres(int n) {
        super(n);
        params = new ArrayList<>() ;
    }

    public void ajouter(Expression e) {
        params.add(e) ;
    }

    public ArrayList<Expression> getParams() {
        return params;
    }

    @Override
    public void verifier() {
        for (Expression e : params){
            e.verifier();
            if (e.type().equals("bool")){
                throw new AnalyseSemantiqueException(noLigne, "On ne peut pas passer de paramètres booléen à une fonction");
            }
        }
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();

        int depl = 0;
        for (Expression e : params){
            mips.append(e.toMIPS());
            mips.append("\tsw $v0, " + depl + "($s2)\n\n");
            depl -= 4;
        }

        return mips.toString();
    }
}
