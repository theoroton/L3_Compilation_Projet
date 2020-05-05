package yal.arbre.instructions;

import yal.arbre.expressions.AccesTableau;
import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.IDF;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.*;

import java.util.ArrayList;

public class Affect extends Instruction {

    protected Expression idfGauche;
    protected Expression expDroite;
    protected Boolean tabtab;


    public Affect(Expression i, Expression e, int n){
        super(n);
        idfGauche = i;
        expDroite = e;
        tabtab = false;
    }

    @Override
    public void verifier() {
        idfGauche.verifier();
        expDroite.verifier();
        if (expDroite.type() == "bool"){
            throw new AnalyseSemantiqueException(noLigne, "On ne peut pas affecter de booléen à " + idfGauche.toString());
        }

        if (idfGauche.getClass() == IDF.class && expDroite.getClass() == IDF.class){
            Symbole s1 = TDS.getInstance().identifier(new Tableau(idfGauche.toString(),null, noLigne));
            Symbole s2 = TDS.getInstance().identifier(new Tableau(expDroite.toString(),null, noLigne));

            if (s1.getClass().getSuperclass() == SymboleTableau.class && s2.getClass().getSuperclass() == SymboleTableau.class){
               if (s1.getTaille() != s2.getTaille()){
                   throw new AnalyseSemantiqueException(noLigne, "On ne peut pas affecter un tableau à un autre tableau si ils n'ont pas la même taille");
               } else {
                   tabtab = true;
               }

            } else if (s1.getClass().getSuperclass() == SymboleTableau.class && s2.getClass().getSuperclass() != SymboleTableau.class) {
                throw new AnalyseSemantiqueException(noLigne, "On ne peut pas affecter d'entier à un tableau");

            } else if (s1.getClass().getSuperclass() != SymboleTableau.class && s2.getClass().getSuperclass() == SymboleTableau.class){
                throw new AnalyseSemantiqueException(noLigne, "On ne peut pas affecter un tableau à un entier");

            }

        }
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();

        if (tabtab){

            Symbole s = TDS.getInstance().identifier(new Tableau(idfGauche.toString(), null, noLigne));
            int taille = s.getTaille();

            ArrayList<Affect> ats = new ArrayList<>();
            Affect at;

            for (int i = 0; i < taille; i++){
                at = new Affect(new AccesTableau(idfGauche.toString(),
                                                 new ConstanteEntiere(Integer.toString(i), noLigne),
                                                 noLigne),
                                new AccesTableau(expDroite.toString(),
                                                 new ConstanteEntiere(Integer.toString(i), noLigne),
                                                 noLigne),
                                noLigne);
                ats.add(at);
            }

            for (Affect a : ats){
                mips.append(a.toMIPS());
            }

        } else {

            mips.append(expDroite.toMIPS());
            mips.append("\t#Affectation : " + idfGauche.toString() + "\n");

            Symbole s;
            int depl;

            if (idfGauche.getClass() == IDF.class){
                s = TDS.getInstance().identifier(new Variable(idfGauche.toString()));
                depl = s.getDeplacement();
            } else {
                s = TDS.getInstance().identifier(new Tableau(idfGauche.toString(), null, noLigne));
                depl = s.getDeplacement() + idfGauche.getIndice() * (-4);
            }

            String registre;
            if (s.getClass() == SymboleVariablePrincipal.class || s.getClass() == SymboleTableauPrincipal.class){
                registre = "($s7)";
            } else {
                registre = "($s3)";
            }

            mips.append("\tsw $v0, "+ depl + registre +"\n\n");
        }

        return mips.toString();
    }
}
