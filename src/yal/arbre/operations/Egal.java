package yal.arbre.operations;

public class Egal extends OperationMemeType {

    public Egal(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "==";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("\t#Egal $t8 == $v0\n");
        mips.append("\tbne $t8, $v0");
        return mips.toString();
    }
}
