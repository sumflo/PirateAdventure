package highSeas.events;

import highSeas.crew.Captain;
import highSeas.enums.TreasureType;
import highSeas.ship.Ship;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RepairBoat {

    public void repairBoat(Captain captain){

        int item = 0;

        System.out.println("A small ship appears on the horizon. Blue flag shows saw and hammer. This is a repair ship! ");
        System.out.println();
        System.out.println("Now you have the opportunity to repair and improve your ship.");
        captain.getShip().showCargo();

    }

    private void repairBoatMenu(Ship captainsShip){

        Scanner scanner = new Scanner(System.in);
        int interaction = 0;

        System.out.println("In exchange for your treasures, curses, equipments  and currencies, you can choose from " +
                "the following interactions:");

       // do{
            if(offerRepairAndFillRumBoatInteraction(captainsShip) && // ha van ágyú, rum, akkor fizetni is lehet, és ágyút, rumot szerelni/tölteni
                    offerRepairAndInstallCannonBoatInteraction(captainsShip)){
                System.out.println( "(0) Exit the event.\n" +
                        "(1) Increase/repair the Hit Point of the ship.\n"+
                        "(2) Install cannons. \n"+
                        "(3) Fill the captain's rum storage.\n");
            }else if(offerRepairAndFillRumBoatInteraction(captainsShip) && // ha van rum, akkor fizetni, rumot tölteni lehet, de ágyút szerelni nem
                    !offerRepairAndInstallCannonBoatInteraction(captainsShip)){
                //valami
            }else if(!offerRepairAndFillRumBoatInteraction(captainsShip) && // ha van ágyú, akkor fizetni, ágyút szerelni lehet, de rumot tölten nem
                    offerRepairAndInstallCannonBoatInteraction(captainsShip)){
                //valami
            } // ha van fizetőeszköz, vagy kincs, vagy átok, de nincs ágyú sem, és rum sem, akkor csak fizetni lehet

       // } while (ameddig valid nem lesz a beadott interakció );

    }

    /* Rájöttem, hogy ez felesleges és ráadásul rossz is arra a célra, amit én szeretnék. */
    private boolean offerOnlyRepairBoatInteraction(Ship captainsShip){

        Set<TreasureType> kind = new HashSet<>();

        for (int i = 0; i < captainsShip.getCargo().size(); i++) {
            if(captainsShip.getCargo().get(i).getType().equals(TreasureType.EQUIPMENT)){
                kind.add(TreasureType.EQUIPMENT);
            }else if(captainsShip.getCargo().get(i).getType().equals(TreasureType.CURRENCY)){
                kind.add(TreasureType.CURRENCY);
            }else if(captainsShip.getCargo().get(i).getType().equals(TreasureType.TREASURE)){
                kind.add(TreasureType.TREASURE);
            }else if(captainsShip.getCargo().get(i).getType().equals(TreasureType.CURSE)) {
                kind.add(TreasureType.TREASURE);
            }
        }

        // ha nem tartalmaz equipmentet, de bármelyik másik hármat

        return kind.size() == 3;
    }

    private boolean offerRepairAndFillRumBoatInteraction(Ship captainsShip){

        boolean haveRum = false;
        int rum = 0;

        for (int i = 0; i < captainsShip.getCargo().size(); i++) {
            if(captainsShip.getCargo().get(i).getName().contains("rum")){
                rum++;
            }
        }

        if(rum > 0){
            haveRum = true;
        }

        return haveRum;
    }

    private boolean offerRepairAndInstallCannonBoatInteraction(Ship captainsShip){

        boolean haveCannon = false;
        int cannon = 0;

        for (int i = 0; i < captainsShip.getCargo().size(); i++) {
            if(captainsShip.getCargo().get(i).getName().contains("cannon")){
                cannon++;
            }
        }

        if(cannon > 0){
            haveCannon = true;
        }

        return haveCannon;
    }
}
