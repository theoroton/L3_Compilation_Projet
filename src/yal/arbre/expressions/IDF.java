package yal.arbre.expressions;

import yal.exceptions.AnalyseSemantiqueException;
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
            throw new AnalyseSemantiqueException(noLigne, "Variable '"+nom+ "' : non déclarée");
        }
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("\tlw $v0, " + TDS.getInstance().identifier(new Variable(nom)).getDeplacement()+"($s7)\n");
        return mips.toString();
    }

    public String toString(){
        return nom;
    }

    @Override
    public String type() {
        return "int";
    }
}
