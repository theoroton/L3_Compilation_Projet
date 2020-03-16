package yal.arbre;

import yal.exceptions.AnalyseSemantiqueException;
import yal.factories.BoolFactory;
import yal.tds.Fonction;
import yal.tds.TDS;

public class Decl_Fonction extends ArbreAbstrait {

    protected ArbreAbstrait instructions;
    protected String nom;

    public Decl_Fonction(ArbreAbstrait li, String no, int n) {
        super(n);
        instructions = li;
        nom = no;
    }

    @Override
    public void verifier() {
        BoolFactory.getInstance().setDansFonction(true);

        int bloc = TDS.getInstance().identifier(new Fonction(nom)).getBloc();
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

        int bloc = TDS.getInstance().identifier(new Fonction(nom)).getBloc();
        TDS.getInstance().setNumBlocCourant(bloc);

        mips.append("\t#Fonction " + nom + "\n");
        mips.append(nom + ": \n");
        mips.append("\t#Entree fonction\n");
        mips.append("\tsw $s7, ($sp)\n");
        mips.append("\tadd $sp, $sp, -4\n\n");
        mips.append(instructions.toMIPS());

        TDS.getInstance().setNumBlocCourant(0);

        return mips.toString();
    }
}
