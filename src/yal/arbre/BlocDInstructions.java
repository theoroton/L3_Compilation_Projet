package yal.arbre;

import java.util.ArrayList;

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
    }
    
    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append(".data\n");
        mips.append("newLine: .asciiz \"\\n\"");
        mips.append("\n\n");

        mips.append(".text\n");
        mips.append("main :\n");

        for (ArbreAbstrait a : programme){
            mips.append(a.toMIPS()+"\n");
        }

        mips.append("end :\n");
        mips.append("\tli $v0, 10\n");
        mips.append("\tsyscall\n");
        return mips.toString();
    }

}
