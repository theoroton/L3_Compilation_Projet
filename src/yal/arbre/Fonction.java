package yal.arbre;

import yal.tds.TDS;

public class Fonction extends ArbreAbstrait {
    private ArbreAbstrait instructions;

    protected Fonction(ArbreAbstrait li, int n) {
        super(n);
        instructions = li;
    }

    @Override
    public void verifier() {
        instructions.verifier();
    }

    @Override
    public String toMIPS() {
        TDS tds = TDS.getInstance();
        tds.plusNiveau();
        int num = tds.getNumFonction();

        StringBuffer mips = new StringBuffer();
        mips.append("\tFonction" + num + ": #Fonction" + num + "\n");
        mips.append(instructions.toMIPS());

        return mips.toString();
    }
}
