package yal.arbre;

import yal.exceptions.AnalyseSemantiqueException;
import yal.factories.BoolFactory;
import yal.factories.NumFactory;
import yal.tds.Fonction;
import yal.tds.TDS;

public class Decl_Fonction extends ArbreAbstrait {

    protected ArbreAbstrait instructions;
    protected String nom;
    protected int nbParams;

    public Decl_Fonction(ArbreAbstrait li, String no, int n, int nb) {
        super(n);
        instructions = li;
        nom = no;
        nbParams = nb;
    }

    @Override
    public void verifier() {
        BoolFactory.getInstance().setDansFonction(true);

        int bloc = TDS.getInstance().identifier(new Fonction(nom, nbParams)).getBloc();
        TDS.getInstance().setNumBlocCourant(bloc);

        instructions.verifier();
        if (!instructions.verifierRetourne()){
            throw new AnalyseSemantiqueException(noLigne, "La fonction n'a pas d'instruction RETOURNE");
        }

        TDS.getInstance().setNumBlocCourant(0);

        BoolFactory.getInstance().setDansFonction(false);
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();

        int bloc = TDS.getInstance().identifier(new Fonction(nom,nbParams)).getBloc();
        TDS.getInstance().setNumBlocCourant(bloc);

        String nomF = nom + "_" + nbParams;

        mips.append("\t#Fonction " + nomF + "\n");
        mips.append(nomF + ": \n");
        mips.append("\t#Entree fonction\n");
        mips.append("\tsw $s7, ($sp)\n");
        mips.append("\tadd $sp, $sp, -4\n\n");
        mips.append(instructions.toMIPS());

        TDS.getInstance().setNumBlocCourant(0);


        return mips.toString();
    }
}
