package yal.factories;

public class BoolFactory {

    private static BoolFactory instance = new BoolFactory();
    private boolean ecrireBooleen;
    private boolean ecrireDiv;
    private boolean dansFonction;
    private boolean yaRetourne;

    public BoolFactory(){
        ecrireBooleen = false;
        ecrireDiv = false;
        dansFonction = false;
        yaRetourne = false;
    }

    public static BoolFactory getInstance(){
        return instance;
    }

    public void ecrireBooleen(){
        ecrireBooleen = true;
    }

    public boolean isEcrireBooleen() {
        return ecrireBooleen;
    }

    public void ecrireDiv(){
        ecrireDiv = true;
    }

    public boolean isEcrireDiv() {
        return ecrireDiv;
    }

    public void setDansFonction(boolean e){
        dansFonction = e;
    }

    public boolean isDansFonction() {
        return dansFonction;
    }

    public void setYaRetourne(boolean e) {
        yaRetourne = e;
    }

    public boolean isYaRetourne() {
        return yaRetourne;
    }
}
