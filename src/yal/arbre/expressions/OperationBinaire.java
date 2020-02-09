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
        //System.out.println(op);
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

//        if (expGauche.getClass() == IDF.class){
//            mips.append(expGauche.toMIPS());
//        } else if (expGauche.getClass().getSuperclass() == Constante.class){
//            mips.append(expGauche.toMIPS());
//        } else {
//            mips.append(expGauche.toMIPS());
//        }
//
//        mips.append("\tsw $v0, ($sp)\n");
//        mips.append("\tadd $sp, $sp, -4\n\n");
//
//        if (expDroite.getClass() == IDF.class){
//            mips.append(expDroite.toMIPS());
//        } else if (expDroite.getClass().getSuperclass() == Constante.class){
//            mips.append(expDroite.toMIPS());
//        } else {
//            mips.append(expDroite.toMIPS() + "\n");
//        }
//
//        mips.append("\tadd $sp, $sp, 4\n");
//        mips.append("\tlw $t8,($sp)\n\n");

        mips.append(expGauche.toMIPS());

        if (expDroite.getClass().getSuperclass() == Constante.class){
            mips.append("\tmove $t8, $v0\n");
            mips.append(expDroite.toMIPS());
        } else {
            mips.append("\tsw $v0, ($sp)\n");
            mips.append("\taddi $sp, $sp, -4\n\n");
            mips.append(expDroite.toMIPS());
            mips.append("\taddi $sp, $sp, 4\n");
            mips.append("\tlw $t8, ($sp)\n\n");
        }

        mips.append("\t" + operation.toMIPS() + "\n");

        return mips.toString();
    }

    @Override
    public String type() {
        return operation.resultat();
    }
}
