package highSeas.utils;

import highSeas.crew.Captain;
import highSeas.crew.Pirate;
import highSeas.enums.Condition;
import highSeas.enums.Rum;
import highSeas.enums.TreasureType;
import highSeas.ship.Ship;
import highSeas.treasure.Treasure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**Ez az osztály a dokumentumok beolvasásáért, és a belőlük nyert információ feldolgozásáért felel.
 * Alapvetően kétféle metódus van. 1.-->> beolvasott sorokból példányosítani tud, 2.--> listába rendez az első segítségével*/

public class DocReader {

    public List<Captain> convertDocToCaptainArrayList(String filename){

        List<Captain> captainList = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (String line; (line = bufferedReader.readLine()) != null;){
                Captain currentCaptain = getCaptainFromDocLine(line);
                captainList.add(currentCaptain);
            }
        }catch (IOException e){
            System.out.println("Error: wrong path!");
        }

        return captainList;

    }

    private Captain getCaptainFromDocLine(String line){

        String parts[] = line.split(";");

        Captain captain = new Captain();

        for (int i = 0; i < parts.length; i++) {
            captain.setName(parts[0]);
            captain.setCaptainID(Integer.parseInt(parts[1]));
            captain.setStrength(Integer.parseInt(parts[2]));
            captain.setMaxHealthPoint(Integer.parseInt(parts[3]));
            captain.setHealthPoint(Integer.parseInt(parts[3]));

            if(parts[4].equals("0")){
                captain.setDrunkLevel(Rum.bone_dry);
            }else if(parts[4].equals("1")){
                captain.setDrunkLevel(Rum.tipsy);
            }else if(parts[4].equals("2")){
                captain.setDrunkLevel(Rum.drunk);
            }else if(parts[4].equals("3")){
                captain.setDrunkLevel(Rum.blind_drunk);
            }

            captain.setCanFight(parts[5].equals("true"));
            //captain ship null!!!
            captain.setRumOwned(Integer.parseInt(parts[7]));
            captain.setFightCounter(Integer.parseInt(parts[8]));
            captain.setStory(parts[9]);
        }

        return captain;
    }

    public List<Ship> convertDocToShipArrayList(String filename){

        List<Ship> shipList = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (String line; (line = bufferedReader.readLine()) != null;){
                Ship currentShip = getShipFromDocLine(line);
                shipList.add(currentShip);
            }
        }catch (IOException e){
            System.out.println("Error: wrong path!");
        }

        return shipList;

    }

    private Ship getShipFromDocLine(String line){

        String parts[] = line.split(";");

        Ship ship = new Ship();

        ship.setName(parts[0]);
        ship.setAttackPower(Integer.parseInt(parts[1]));
        ship.setHitPoint(Integer.parseInt(parts[2]));
        ship.setNumberOfCannons(Integer.parseInt(parts[3]));

        if(parts[4].equals("wreck")){
            ship.setState(Condition.wreck);
        }else if(parts[4].equals("leaked")){
            ship.setState(Condition.leaked);
        }else if(parts[4].equals("normal")){
            ship.setState(Condition.normal);
        }else if(parts[4].equals("strong")){
            ship.setState(Condition.strong);;
        }else{
            ship.setState(Condition.overpowered);
        }

        ship.setID(Integer.parseInt(parts[5]));

        return ship;
    }

    public List<Pirate> convertDocToPirateArrayList(String filename){

        List<Pirate> pirateList = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (String line; (line = bufferedReader.readLine()) != null;){
                Pirate currentPirate = getPirateFromDocLine(line);
                pirateList.add(currentPirate);
            }
        }catch (IOException e){
            System.out.println("Error: wrong path!");
        }

        return pirateList;

    }

    private Pirate getPirateFromDocLine(String line){

        String parts[] = line.split(";");

        Pirate pirate = new Pirate();

        pirate.setName(parts[0]);
        pirate.setCaptainID(Integer.parseInt(parts[1]));
        pirate.setStrength(Integer.parseInt(parts[2]));
        pirate.setMaxHealthPoint(Integer.parseInt(parts[3]));
        pirate.setHealthPoint(Integer.parseInt(parts[3]));

        if(parts[4].equals("0")){
            pirate.setDrunkLevel(Rum.bone_dry);
        }else if(parts[4].equals("1")){
            pirate.setDrunkLevel(Rum.tipsy);
        }else if(parts[4].equals("2")){
            pirate.setDrunkLevel(Rum.drunk);
        }else if(parts[4].equals("3")){
            pirate.setDrunkLevel(Rum.blind_drunk);
        }

        pirate.setCanFight(parts[5].equals("true"));

        return pirate;
    }

    public List<Treasure> convertDocToTreasureArrayList(String filename){

        List<Treasure> treasureList = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (String line; (line = bufferedReader.readLine()) != null;){
                Treasure currentTreasure = getTreasureFromDocLine(line);
                treasureList.add(currentTreasure);
            }
        }catch (IOException e){
            System.out.println("Error: wrong path!");
        }

        return treasureList;

    }

    private Treasure getTreasureFromDocLine(String line){

        String parts[] = line.split(";");

        Treasure treasure = new Treasure();

        treasure.setTreasureID(Integer.parseInt(parts[0]));
        treasure.setName(parts[1]);

        if(parts[2].equals("TREASURE")){
            treasure.setType(TreasureType.TREASURE);
        }else if(parts[2].equals("CURSE")){
            treasure.setType(TreasureType.CURSE);
        }else if(parts[2].equals("SYMBOL")){
            treasure.setType(TreasureType.SYMBOL);
        }else if(parts[2].equals("EQUIPMENT")){
            treasure.setType(TreasureType.EQUIPMENT);
        }else if(parts[2].equals("CURRENCY")) {
            treasure.setType(TreasureType.CURRENCY);
        }

        treasure.setValue(Integer.parseInt(parts[3]));

        return treasure;
    }

}
