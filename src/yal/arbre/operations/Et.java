package yal.arbre.operations;

public class Et extends Operation2Booleen {

    public Et(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "et";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("\t#Et $t8 et $v0\n");
        mips.append("\tand $v0, $t8, $v0\n");
        return mips.toString();
    }
}
