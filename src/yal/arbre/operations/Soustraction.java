package yal.arbre.operations;

public class Soustraction extends OperationResultEntier {

    public Soustraction(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "-";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("\t#Soustraction $t8 - $v0\n");
        mips.append("\tsub $v0, $t8, $v0\n");
        return mips.toString();
    }
}
