package yal.arbre.expressions;

import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.Symbole;
import yal.tds.SymboleVariable;
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

        Symbole s = TDS.getInstance().identifier(new Variable(nom));

        String registre;
        if (s.getClass() == SymboleVariable.class){
            registre = "($s7)";
        } else {
            registre = "($s2)";
        }

        mips.append("\t#Variable entiere\n");
        mips.append("\tlw $v0, " + s.getDeplacement() + registre + "\n");
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
