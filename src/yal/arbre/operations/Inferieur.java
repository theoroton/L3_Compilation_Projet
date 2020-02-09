package yal.arbre.operations;

public class Inferieur extends OperationResultBooleen {

    public Inferieur(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "<";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("\t#Inferieur $t8 < $v0\n");
        mips.append("\tslt $v0, $t8, $v0\n");
        return mips.toString();
    }
}
