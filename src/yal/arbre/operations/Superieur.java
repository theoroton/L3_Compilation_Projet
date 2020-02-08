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
        mips.append("ble $t8, $v0");
        return mips.toString();
    }
}
