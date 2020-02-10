package yal.arbre.expressions;

import yal.arbre.operations.Division;
import yal.arbre.operations.Operation;
import yal.exceptions.AnalyseSemantiqueException;

public class OperationBinaire extends Expression {

    protected Expression expGauche;
    protected Expression expDroite;
    protected Operation operation;

    public OperationBinaire(Expression e1, Operation op, Expression e2, int n) {
        super(n);

        expGauche = e1;
        expDroite = e2;
        operation = op;
    }

    @Override
    public void verifier() {
        expGauche.verifier();
        expDroite.verifier();
        operation.operandes(expGauche, expDroite);

        if ((operation.getClass() == Division.class) && (expDroite.toString().equals("0"))){
            throw new AnalyseSemantiqueException(noLigne, "La division par 0 est interdite");
        }
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();

        //Code qui évalue l'opérande gauche
        mips.append(expGauche.toMIPS());

        if (expDroite.getClass().getSuperclass() == Constante.class){
            mips.append("\tmove $t8, $v0\n");

            //Code qui évalue la constante de droite
            mips.append(expDroite.toMIPS() + "\n");

        } else {
            mips.append("\tsw $v0, ($sp)\n");
            mips.append("\taddi $sp, $sp, -4\n\n");

            //Code qui évalue l'opérande droit
            mips.append(expDroite.toMIPS() + "\n");

            mips.append("\taddi $sp, $sp, 4\n");
            mips.append("\tlw $t8, ($sp)\n\n");
        }

        mips.append(operation.toMIPS());

        return mips.toString();
    }

    @Override
    public String type() {
        return operation.resultat();
    }
}
