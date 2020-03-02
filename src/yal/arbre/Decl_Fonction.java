package yal.arbre;

import yal.exceptions.AnalyseSemantiqueException;
import yal.factories.BoolFactory;

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
        instructions.verifier();
        if (!BoolFactory.getInstance().isYaRetourne()){
            BoolFactory.getInstance().setYaRetourne(false);
            throw new AnalyseSemantiqueException(noLigne, "La fonction n'a pas d'instruction RETOURNE");
        }
        BoolFactory.getInstance().setDansFonction(false);
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();

        mips.append("\t#Fonction " + nom + "\n");
        mips.append(nom + ": \n");
        mips.append(instructions.toMIPS());

        return mips.toString();
    }
}
