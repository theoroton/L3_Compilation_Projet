package yal.arbre.expressions;

import yal.tds.Symbole;
import yal.tds.TDS;
import yal.tds.Variable;

public class IDF extends Expression {

    protected String nom;

    public IDF(String no, int n) {
        super(n) ;
        nom = no;
    }

    @Override
    public void verifier() {
        Symbole s = TDS.getInstance().identifier(new Variable(nom));
        if (s == null){
            //Ajouter exception non déclarée
            System.out.println(nom+ " NON DECLAREE");
        }
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append(TDS.getInstance().identifier(new Variable(nom)).getDeplacement()+"($s7)");
        return mips.toString();
    }

    public String toString(){
        return nom;
    }

    @Override
    public String affect() {
        return "lw";
    }
}
