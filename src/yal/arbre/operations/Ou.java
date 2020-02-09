package yal.arbre.operations;

public class Ou extends Operation2Booleen {

    public Ou(int n) {
        super(n);
    }

    @Override
    public String symbole() {
        return "ou";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("\t#Ou $t8 ou $v0\n");
        mips.append("\tor $v0, $t8, $v0\n");
        return mips.toString();
    }
}
