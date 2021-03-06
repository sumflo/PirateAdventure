package highSeas.treasure;

import highSeas.enums.TreasureType;

/** A kincsek típusuktól(TreasureType) függően vagy egy-egy kapitányhoz tartoznak kezdéskor, és csak 1 példány jelenik
 * meg egy adott fajtából(name && treasureID),vagy a játék "betöltésekor" random generálódnak le (db, fajta).
 * A kincsekkel kereskedni lehet -->> javítani, rumkészletet tölteni, ágyút felszerelni, valamint
 * fontos szerepük lehet az összecsapások, és az eventek során -->> bizonyos kincsek biztosítják a túlélést,
 * ezért nem érdemes "eladni" ész nélkül*/

public class Treasure {

    private int treasureID;
    private String name;
    private TreasureType type;
    private int value;

    public Treasure() {
    }

    public Treasure(int treasureID, String name, TreasureType type, int value) {
        this.treasureID = treasureID;
        this.name = name;
        this.type = type;
        this.value = value;
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
