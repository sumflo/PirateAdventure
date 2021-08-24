package highSeas.enums;

public enum Rum {
    bone_dry(0),
    tipsy(1),
    drunk(2),
    blind_drunk(3);

    public int level;

    Rum(int level){
        this.level = level;
    }
}
