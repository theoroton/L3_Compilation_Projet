package yal.arbre.expressions;

import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.*;

public class Longueur extends Expression {

    private String nomTab;

    public Longueur(String i, int n) {
        super(n);
        nomTab = i;
    }

    public String type() {
        return "int";
    }

    @Override
    public void verifier() {
        Symbole s = TDS.getInstance().identifier(new Tableau(nomTab,null, noLigne));
        if (s == null){
            throw new AnalyseSemantiqueException(noLigne, "Tableau '"+nomTab+ "' : non déclarée");
        } else if (s.getClass() != SymboleTableau.class){
            throw new AnalyseSemantiqueException(noLigne, nomTab+" n'est pas un tableau");
        }
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();

        Symbole s = TDS.getInstance().identifier(new Tableau(nomTab,null, noLigne));
        mips.append("\t#Taille tableau\n");
        mips.append("\tli $v0, " + s.getTaille() + "\n");
        return mips.toString();
    }
}
