package yal.arbre.expressions;

import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.*;

public class AccesTableau extends Expression {

    private String nomTab;
    private Expression exp;
    private int indice;

    public AccesTableau(String i, Expression e, int n) {
        super(n);
        nomTab = i;
        exp = e;
        if (exp.getClass() == ConstanteEntiere.class){
            int x = Integer.parseInt(e.toString());
            if (x >= 0){
                indice = x;
            }
        }
    }

    public void verifier() {
        int x;
        boolean ok = true;

        if (exp.getClass() == ConstanteEntiere.class){
            x = Integer.parseInt(exp.toString());
            if (x < 0){
                ok = false;
                throw new AnalyseSemantiqueException(noLigne, "La taille du tableau doit être une constante entière supérieure ou égale à 0");
            }
        } else {
            ok = false;
            throw new AnalyseSemantiqueException(noLigne, "La taille du tableau doit être une constante entière supérieure ou égale à 0");
        }

        if (ok){
            Symbole s = TDS.getInstance().identifier(new Tableau(nomTab,null, noLigne));
            if (s == null){
                throw new AnalyseSemantiqueException(noLigne, "Tableau '"+nomTab+ "' : non déclaré");

            } else if (s.getClass().getSuperclass() != SymboleTableau.class){
                throw new AnalyseSemantiqueException(noLigne, nomTab+" n'est pas un tableau");
            }

            x = Integer.parseInt(exp.toString());
            if (x < 0 || x >= s.getTaille()){
                throw new AnalyseSemantiqueException(noLigne, "L'indice "+ x + " est introuvable dans le tableau "+ nomTab);
            }
        }

    }

    public String toMIPS() {
        StringBuffer mips = new StringBuffer();

        Symbole s = TDS.getInstance().identifier(new Tableau(nomTab,null, noLigne));

        String registre;
        if (s.getClass() == SymboleTableauPrincipal.class){
            registre = "($s7)";
        } else {
            registre = "($s3)";
        }

        mips.append("\t#Acces tableau\n");
        int depl = s.getDeplacement() + indice * (-4);
        mips.append("\tlw $v0, " + depl + registre + "\n\n");
        return mips.toString();
    }

    public String toString(){
        return nomTab;
    }

    public String type() {
        return "int";
    }

    public int getIndice() {
        return indice;
    }
}
