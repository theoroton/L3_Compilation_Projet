package yal.arbre.expressions;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {
        StringBuffer mips = new StringBuffer();
        mips.append("\tli $a0, "+cste+"\n");
        return mips.toString();
    }

}
