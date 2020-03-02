package yal.factories;

public class NumFactory {

    private static NumFactory instance = new NumFactory();
    private int numSi;
    private int numTantque;

    public NumFactory(){
        numSi = 0;
        numTantque = 0;
    }

    public static NumFactory getInstance(){
        return instance;
    }

    public int getNumSi(){
        numSi++;
        return numSi;
    }

    public int getNumTantque(){
        numTantque++;
        return numTantque;
    }
}
