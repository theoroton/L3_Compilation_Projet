package yal.arbre.expressions;

import yal.arbre.Parametres;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.*;

public class ExpressionFonction extends Expression {

    protected String nomFonction;
    protected Parametres parametres;
    protected int nbParams;
    protected int ancParams;

    public ExpressionFonction(String no, int n, Parametres p, int an){
        super(n);
        nomFonction = no;
        parametres = p;
        if (p == null){
            nbParams = 0;
        } else {
            nbParams = p.getParams().size();
        }
        ancParams = an;
    }

    @Override
    public String type() {
        return "int";
    }

    @Override
    public void verifier() {
        Symbole s = TDS.getInstance().identifier(new Fonction(nomFonction,nbParams));
        if (s == null){
            throw new AnalyseSemantiqueException(noLigne, "Fonction '"+ nomFonction + "' avec " + nbParams + " paramètres : non trouvée");
        }
        if (parametres != null){
            parametres.verifier();
        }

    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();

        String nomF = nomFonction + "_" + nbParams;

        mips.append("\t#Appel fonction : " + nomF + "\n");

        int vars = TDS.getInstance().getDeplFonction();

        if (vars > 0 && TDS.getInstance().getNumBlocCourant() != 0){
            mips.append("\tsw $s3, ($sp)\n");
            mips.append("\tadd $sp, $sp, -4\n");
            mips.append("\tadd $s3, $s3, " + vars + "\n\n");
        }

        int add = 0;
        int sub = 0;
        if (nbParams > 0){
            add = (ancParams - nbParams) * 4;
            sub = -add;

            if (TDS.getInstance().getNumBlocCourant() != 0){
                mips.append("\tsw $s2, ($sp)\n");
                mips.append("\tadd $sp, $sp, -4\n");
            }

            mips.append("\tadd $s2, $s2, " + add + "\n\n");

            mips.append(parametres.toMIPS());
        }

        mips.append("\tsw $ra, ($sp)\n");
        mips.append("\tadd $sp, $sp, -4\n");
        mips.append("\tjal " + nomF + "\n");
        mips.append("\tadd $sp, $sp, 4\n");
        mips.append("\tlw $ra, ($sp)\n\n");

        if (nbParams > 0){
            mips.append("\tadd $s2, $s2, " + sub + "\n");

            if (TDS.getInstance().getNumBlocCourant() != 0){
                mips.append("\tadd $sp, $sp, 4\n");
                mips.append("\tlw $s2, ($sp)\n\n");
            }
        }

        if (vars > 0 && TDS.getInstance().getNumBlocCourant() != 0){
            mips.append("\tadd $s3, $s3, " + -vars + "\n");
            mips.append("\tadd $sp, $sp, 4\n");
            mips.append("\tlw $s3, ($sp)\n\n");
        }

        return mips.toString();
    }
}
