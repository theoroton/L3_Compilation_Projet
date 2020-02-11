package yal.arbre;

import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.TDS;
import yal.tds.Variable;

import java.util.ArrayList;

import static java.lang.System.exit;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> programme ;

    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }
    
    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }
    
    @Override
    public String toString() {
        return programme.toString() ;
    }

    @Override
    public void verifier() {
        boolean verif = true;

        for (AnalyseSemantiqueException ex : TDS.getInstance().getExceptions_sem()){
            System.err.println(ex.getMessage());
            if (verif){
                verif = false;
            }
        }

        for (ArbreAbstrait a : programme){
            try {
                a.verifier();
            } catch (AnalyseSemantiqueException ex){
                System.err.println(ex.getMessage());
                if (verif){
                    verif = false;
                }
            }
        }

        if (!verif){
            exit(0);
        }
    }
    
    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();


        if (TDS.getInstance().isBlocPrincipal()){
            mips.append(".data\n");
            mips.append("#Chaine de texte a utiliser\n");
            mips.append("newLine: .asciiz \"\\n\"\n");
            mips.append("vrai: .asciiz \"Vrai\\n\"\n");
            mips.append("faux: .asciiz \"Faux\\n\"\n");
            mips.append("div0: .asciiz \"ERREUR : la division par 0 est interdite\\n\"\n");
            mips.append("\n");

            mips.append(".text\n");
            mips.append("main :\n");

            int taille = TDS.getInstance().getTailleZoneVariable();
            if (taille < 0){
                mips.append("\t#Reservation de l'espace\n");
                mips.append("\tmove $s7, $sp\n\n");
                mips.append("\taddi $sp, $sp, "+ taille + "\n\n");
            }
        }

        for (ArbreAbstrait a : programme){
            mips.append(a.toMIPS()+"\n");
        }

        if (TDS.getInstance().isBlocPrincipal()){
            mips.append("end :\n");
            mips.append("\tli $v0, 10\n");
            mips.append("\tsyscall\n\n");

            mips.append("\n#Ecrire un booleen\n");
            mips.append("ecrireBooleen :\n");
            mips.append("\tbeq $v0, 0, ecrireFaux\n\n");
            mips.append("\tli $v0, 4\n");
            mips.append("\tla $a0, vrai\n");
            mips.append("\tsyscall\n\n");

            mips.append("\tb finEcrireBooleen\n\n");

            mips.append("ecrireFaux :\n");
            mips.append("\tli $v0, 4\n");
            mips.append("\tla $a0, faux\n");
            mips.append("\tsyscall\n\n");

            mips.append("finEcrireBooleen :\n");
            mips.append("\tjr $ra\n\n");

            mips.append("#Division par 0\n");
            mips.append("erreurDiv0 :\n");
            mips.append("\tli $v0, 4\n");
            mips.append("\tla $a0, div0\n");
            mips.append("\tsyscall\n");
            mips.append("\tb end\n");
        }

        return mips.toString();
    }

}
