package highSeas.treasure;

import highSeas.enums.TreasureType;

public class Treasure {

    private int treasureID;
    private String name;
    private TreasureType type;
    private int value;

    public Treasure() {
    }

    public String toString(){
        return "(" + this.treasureID + ")" + " " + this.name + " - value: " + this.value;
    }

    public int getTreasureID() {
        return treasureID;
    }

    public void setTreasureID(int treasureID) {
        this.treasureID = treasureID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreasureType getType() {
        return type;
    }

    public void setType(TreasureType type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
