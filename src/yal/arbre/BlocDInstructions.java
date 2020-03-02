package yal.arbre;

import yal.arbre.instructions.Retourne;
import yal.exceptions.AnalyseSemantiqueException;
import yal.factories.BoolFactory;
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
            if (a.getClass() == Retourne.class && BoolFactory.getInstance().isDansFonction()) {
                BoolFactory.getInstance().setYaRetourne(true);
            }
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

}
