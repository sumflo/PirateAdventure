package highSeas.characters;

import highSeas.controller.Dice;
import highSeas.crew.Captain;
import highSeas.crew.Pirate;
import highSeas.enums.TreasureType;
import highSeas.ship.Ship;
import highSeas.treasure.Treasure;
import highSeas.utils.DocReader;
import highSeas.utils.FileRouting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlayableCharacters {

    List<Captain> captains;

    public PlayableCharacters() {
        DocReader docreader = new DocReader();
        captains = docreader.convertDocToCaptainArrayList(FileRouting.getSourceCaptain());
    }

    //TODO: random + dice 10 strength minden kapiánynak, és random dice20 HP
    public void setCaptainsShips(){
        DocReader docreader = new DocReader();
        List<Ship> ships = docreader.convertDocToShipArrayList(FileRouting.getSourceShip());

        for (int i = 0; i < this.captains.size(); i++) {
            for (int j = 0; j < ships.size(); j++) {
                if(this.captains.get(i).getCaptainID() == ships.get(j).getID())
                    this.captains.get(i).setShip(ships.get(j));
            }
        }
    }

    //TODO: random + dice 6 strength minden kalóznak, és random dice10 HP
    public void setCaptainsCrews(){
        DocReader docreader = new DocReader();
        List<Pirate> pirates = docreader.convertDocToPirateArrayList(FileRouting.getSourcePirate());

        for (int i = 0; i < this.captains.size(); i++) {
            for (int j = 0; j < pirates.size(); j++) {
                if(this.captains.get(i).getCaptainID() == pirates.get(j).getCaptainID())
                    this.captains.get(i).getShip().getCrew().add(pirates.get(j));
            }
        }
    }

    public void fillAllShipCargo(Dice dice){

        Random rand = new Random();

        DocReader docReader = new DocReader();
        List<Treasure> treasureList = docReader.convertDocToTreasureArrayList(FileRouting.getSourceTreasure());
        List<Treasure> commonTreasures = new ArrayList<>();

        for (int i = 0; i <treasureList.size() ; i++) {
            if(treasureList.get(i).getType().equals(TreasureType.CURRENCY) || treasureList.get(i).getType().equals(TreasureType.EQUIPMENT)){
                commonTreasures.add(treasureList.get(i));
            }
        }

        for (int i = 0; i < captains.size(); i++) {

            List<Treasure> cargo = new ArrayList<>();
            this.captains.get(i).getShip().setCargo(cargo);

            int numberOfCommonTreasures = dice.throwDice10();

            if( numberOfCommonTreasures % 2 == 0){
                int randInt1 = rand.nextInt(commonTreasures.size());
                this.captains.get(i).getShip().getCargo().add(commonTreasures.get(randInt1));
                int randInt2 = rand.nextInt(commonTreasures.size());
                this.captains.get(i).getShip().getCargo().add(commonTreasures.get(randInt2));
            }else{
                int randInt3 = rand.nextInt(commonTreasures.size());
                this.captains.get(i).getShip().getCargo().add(commonTreasures.get(randInt3));
            }
        }

        for (int i = 0; i < captains.size(); i++) {
            for (int j = 0; j < treasureList.size(); j++) {

                if(this.captains.get(i).getCaptainID() == 2 && treasureList.get(j).getTreasureID() == 9){
                    this.captains.get(i).getShip().getCargo().add(treasureList.get(j));
                }else if(this.captains.get(i).getCaptainID() == 5 && treasureList.get(j).getTreasureID() == 4){
                    this.captains.get(i).getShip().getCargo().add(treasureList.get(j));
                }else if(this.captains.get(i).getCaptainID() == 6 && treasureList.get(j).getTreasureID() == 10){
                    this.captains.get(i).getShip().getCargo().add(treasureList.get(j));
                }else if(this.captains.get(i).getCaptainID() == 7 && treasureList.get(j).getTreasureID() == 1){
                    this.captains.get(i).getShip().getCargo().add(treasureList.get(j));
                }else if(this.captains.get(i).getCaptainID() == 8 && treasureList.get(j).getTreasureID() == 6){
                    this.captains.get(i).getShip().getCargo().add(treasureList.get(j));
                }else if(this.captains.get(i).getCaptainID() == 10 && treasureList.get(j).getTreasureID() == 8){
                    this.captains.get(i).getShip().getCargo().add(treasureList.get(j));
                }
            }
        }

    }


    public List<Captain> getCaptains() {
        return captains;
    }
}
