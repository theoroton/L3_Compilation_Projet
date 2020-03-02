package yal.arbre.expressions;

import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.*;

public class ExpressionFonction extends Expression {

    protected String nomFonction;

    public ExpressionFonction(String no, int n){
        super(n);
        nomFonction = no;
    }

    @Override
    public String type() {
        return "int";
    }

    @Override
    public void verifier() {
        Symbole s = TDS.getInstance().identifier(new Fonction(nomFonction));
        if (s == null){
            throw new AnalyseSemantiqueException(noLigne, "Fonction '"+nomFonction+ "' : non déclarée");
        }
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("\t#Appel fonction : " + nomFonction + "\n");
        mips.append("\tsw $ra, 0($sp)\n");
        mips.append("\tadd $sp, $sp, -4\n");
        mips.append("\tjal " + nomFonction + "\n");
        mips.append("\tadd $sp, $sp, 4\n");
        mips.append("\tlw $ra, ($sp)\n");

        return mips.toString();
    }
}
