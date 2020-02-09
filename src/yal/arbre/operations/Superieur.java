package yal.arbre.operations;

public class Superieur extends OperationResultBooleen {

    public Superieur(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return ">";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("\t#Superieur $t8 > $v0\n");
        mips.append("\tsgt $v0, $t8, $v0\n");
        return mips.toString();
    }
}
