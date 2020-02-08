package yal.arbre.operations;

public class Different extends OperationMemeType {

    public Different(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "!=";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("beq $t8, $v0");
        return mips.toString();
    }
}
