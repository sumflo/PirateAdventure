package highSeas.utils;


/** Ez a class csupán az elérési útvonalat tárolja. Akár ki is lehetne hagyni. Nem feltétlenül szükséges osztály, de én szeretem, ha "rend" van.*/
public class FileRouting {

    private static final String SOURCE_DIR = "src/resources/";
    private static final String SOURCE_PIRATE = SOURCE_DIR + "pirate.txt";
    private static final String SOURCE_CAPTAIN = SOURCE_DIR + "captain.txt";
    private static final String SOURCE_SHIP = SOURCE_DIR + "ship.txt";
    private static final String SOURCE_TREASURE = SOURCE_DIR + "treasure.txt";

    public FileRouting() {
    }

    public static String getSourceDir() {
        return SOURCE_DIR;
    }

    public static String getSourcePirate() {
        return SOURCE_PIRATE;
    }

    public static String getSourceCaptain() {
        return SOURCE_CAPTAIN;
    }

    public static String getSourceShip() {
        return SOURCE_SHIP;
    }

    public static String getSourceTreasure() {
        return SOURCE_TREASURE;
    }
}
