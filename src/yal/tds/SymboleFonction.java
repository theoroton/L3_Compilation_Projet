package yal.tds;

public class SymboleFonction extends Symbole {

    int indiceFonction;

    public SymboleFonction(int d, int s) {
        super(d);
        indiceFonction = s;
    }

    public int getIndiceFonction() {
        return indiceFonction;
    }
}
