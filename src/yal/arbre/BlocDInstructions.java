package yal.arbre;

import yal.arbre.instructions.Retourne;
import yal.arbre.instructions.Si;

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

    public ArrayList<ArbreAbstrait> getProgramme() {
        return programme;
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
        for (ArbreAbstrait a : programme){
            a.verifier();
        }
    }
    
    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();

        for (ArbreAbstrait a : programme){
            mips.append(a.toMIPS()+"\n");
        }

        return mips.toString();
    }

    @Override
    public boolean verifierRetourne() {
        boolean yaretourne = false;
        ArrayList<Si> si = new ArrayList<>();
        for (ArbreAbstrait a : programme){

            if (a.getClass() == Si.class){
                si.add((Si) a);
            } else if (a.getClass() == Retourne.class) {
                yaretourne = true;
            }

        }

        if (!yaretourne && si.size() > 0){
            int nb = 0;
            for (Si s : si){
                yaretourne = s.verifierRetourne();
                if (yaretourne){
                    nb++;
                }
            }

            if (nb == si.size()){
                yaretourne = true;
            } else {
                yaretourne = false;
            }
        }

        return yaretourne;
    }
}
