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
            mips.append("newLine: .asciiz \"\\n\"");
            mips.append("\n\n");

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
            mips.append("\tsyscall\n");
        }

        return mips.toString();
    }

}
