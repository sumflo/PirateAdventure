package highSeas.events;

import highSeas.crew.Captain;
import highSeas.enums.TreasureType;
import highSeas.ship.Ship;

import java.util.Scanner;

public class RepairBoat {

    public void repairBoat(Captain captain){

        System.out.println("A small ship appears on the horizon. Blue flag shows saw and hammer. This is a repair ship! ");
        System.out.println("Now you have the opportunity to repair and improve your ship.");
        System.out.println("Think carefully about your choice, there is only one interaction possible in this round!");
        System.out.println();

        captain.getShip().showCargo();
        System.out.println();

        int interaction = getChoiceFromRepairBoatMenu(captain.getShip());
        System.out.println();

        executeInteraction(interaction, captain);
        System.out.println();

        showChanges(interaction, captain);
        System.out.println();

    }

    /** Felajánlja a lehetséges interakciókat attól függően, hogy mi a játékos hajójának a rakománya.
     *  Ezt addig ismétli, amíg valid értéket nem kap inputként.
     *  Majd visszaadja a választott interakció értékét.*/
    private int getChoiceFromRepairBoatMenu(Ship captainsShip){

        Scanner scanner = new Scanner(System.in);
        int interaction;

        System.out.println("In exchange for your treasures, curses, equipments  and currencies, you can choose from " +
                "the following interactions:");
        System.out.println();

       do{
            if(offerRepairAndFillRumBoatInteraction(captainsShip) && // ha van ágyú, rum, akkor fizetni is lehet, és ágyút, rumot szerelni/tölteni
                    offerRepairAndInstallCannonBoatInteraction(captainsShip)){
                System.out.println( "(0) Exit the event.\n" +
                        "(1) Increase/repair the Hit Point of the ship.\n"+
                        "(2) Install cannons. \n"+
                        "(3) Fill the captain's rum storage.\n");
            }else if(offerRepairAndFillRumBoatInteraction(captainsShip) && // ha van rum, akkor fizetni, rumot tölteni lehet, de ágyút szerelni nem
                    !offerRepairAndInstallCannonBoatInteraction(captainsShip)){
                System.out.println( "(0) Exit the event.\n" +
                        "(1) Increase/repair the Hit Point of the ship.\n"+
                        "(NOT ALLOWED NOW) Install cannons. \n"+
                        "(3) Fill the captain's rum storage.\n");
            }else if(!offerRepairAndFillRumBoatInteraction(captainsShip) && // ha van ágyú, akkor fizetni, ágyút szerelni lehet, de rumot tölten nem
                    offerRepairAndInstallCannonBoatInteraction(captainsShip)){
                System.out.println( "(0) Exit the event.\n" +
                        "(1) Increase/repair the Hit Point of the ship.\n"+
                        "(2) Install cannons. \n"+
                        "(NOT ALLOWED NOW) Fill the captain's rum storage.\n");
            }else if(offerOnlyRepairBoatInteraction(captainsShip)){ // ha van fizetőeszköz, vagy kincs, vagy átok, de nincs ágyú sem, és rum sem, akkor csak fizetni lehet
                System.out.println( "(0) Exit the event.\n" +
                        "(1) Increase/repair the Hit Point of the ship.\n"+
                        "(NOT ALLOWED NOW) Install cannons. \n"+
                        "(NOT ALLOWED NOW) Fill the captain's rum storage.\n");
            }else{ // ha nincs semilyen fizetésre alkalmas eszköz, akkor csak az eventből lehet kilépni, és befejezni a kört.
                System.out.println( "(0) Exit the event.\n" +
                        "(NOT ALLOWED NOW) Increase/repair the Hit Point of the ship.\n"+
                        "(NOT ALLOWED NOW) Install cannons. \n"+
                        "(NOT ALLOWED NOW) Fill the captain's rum storage.\n");
            }

            interaction = scanner.nextInt();

       } while (!isValidInteraction(interaction, captainsShip));

        return interaction;
    }

    private void executeInteraction(int interaction, Captain captain){

        Ship captainsShip = captain.getShip();

        if (interaction == 0){
            System.out.println(captainsShip.getName() + " continued on its way to another adventure. What will tomorrow bring to the captain?");
        }else if(interaction == 1){
           repair(captainsShip);
        }else if(interaction == 2){
            installCannons(captainsShip);
        }else if(interaction == 3){
            fillRum(captain);
        }
    }

    private void showChanges(int interaction, Captain captain){

        if(interaction == 1 || interaction == 2){
            captain.getShip().showShip();
        }else if(interaction == 3){
            System.out.println(captain.toString());
        }
    }

    /** Megvizsgálja, hogy a rakomyányban nincs-e EQUIPMENT típusú kincs, viszont van-e bármilyen más olyan típusú,
     * ami nem SYMBOL( a value-ja minden SYMBOL-nak 0, azaz nem lehet "fizetni" vele) */
    private boolean offerOnlyRepairBoatInteraction(Ship captainsShip){

        int kind = 0;

        for (int i = 0; i < captainsShip.getCargo().size(); i++) {
            if(captainsShip.getCargo().get(i).getType().equals(TreasureType.EQUIPMENT)){
                return false;
            }else if(captainsShip.getCargo().get(i).getType().equals(TreasureType.CURRENCY)){
                kind++;
            }else if(captainsShip.getCargo().get(i).getType().equals(TreasureType.TREASURE)){
                kind++;
            }else if(captainsShip.getCargo().get(i).getType().equals(TreasureType.CURSE)) {
                kind++;
            }
        }

        if(kind > 0){
            return true;
        }

        return false;
    }

    /** Megvizsgálja, hogy a rakományban van-e olyan nevű kincs, aminek a neve tartalmazza a 'rum' szót
     * -->> lehetséges a rum feltöltése */
    private boolean offerRepairAndFillRumBoatInteraction(Ship captainsShip){

        boolean haveRum = false;
        int rum = 0;

        for (int i = 0; i < captainsShip.getCargo().size(); i++) {
            if(captainsShip.getCargo().get(i).getName().contains("rum") &&  // fill-full rum barrels --> symbol
                    captainsShip.getCargo().get(i).getType().equals(TreasureType.EQUIPMENT)){
                rum++;
            }
        }

        if(rum > 0){
            haveRum = true;
        }

        return haveRum;
    }

    /** Megvizsgálja, hogy a rakományban van-e olyan nevű kincs, aminek a neve tartalmazza a 'cannon' szót
     * -->> lehetséges az ágyúk felszerelése */
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

    /** Megvizsgálja, hogy a választott interakció a rakományhoz képest valid-e, választható-e. */
    private boolean isValidInteraction(int interaction, Ship captainsShip){

        if(offerRepairAndFillRumBoatInteraction(captainsShip) &&
                offerRepairAndInstallCannonBoatInteraction(captainsShip) && interaction > -1 && interaction < 4){
            return true;
        }else if(offerRepairAndFillRumBoatInteraction(captainsShip) &&
                !offerRepairAndInstallCannonBoatInteraction(captainsShip) && interaction > -1 && interaction < 4 && interaction != 2){
           return true;
        }else if(!offerRepairAndFillRumBoatInteraction(captainsShip) &&
                offerRepairAndInstallCannonBoatInteraction(captainsShip) && interaction > -1 && interaction < 3){
            return true;
        }else if(offerOnlyRepairBoatInteraction(captainsShip) && interaction > -1 && interaction < 2){
            return true;
        }else if (interaction == 0){ // ez lehet, hogy szükségtelen?
            return true;
        }else{
            System.out.println("The chosen interaction is invalid.");
            return false;
        }
    }

    public void repair(Ship captainsShip){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of the selected item. This will increase the hit point of your ship.");
        System.out.println("Watch out! You can only select one item in this round!");

        int choice = scanner.nextInt();

        for (int i = 0; i < captainsShip.getCargo().size(); i++) {
            if(captainsShip.getCargo().get(i).getTreasureID() == choice){
                captainsShip.setHitPoint(captainsShip.getHitPoint() + captainsShip.getCargo().get(i).getValue());
                captainsShip.getCargo().remove(captainsShip.getCargo().get(i));
            }
        }
        //TODO: ellenőrzés a valid bevitelről
    }

    public void installCannons(Ship captainsShip){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the cannon(s) you want to install.");
        System.out.println("You can only select one item in this round!");

        int choice = scanner.nextInt();

        for (int i = 0; i < captainsShip.getCargo().size(); i++) {
            if(captainsShip.getCargo().get(i).getTreasureID() == choice && captainsShip.getCargo().get(i).getName().contains("1")){
                captainsShip.setNumberOfCannons(captainsShip.getNumberOfCannons() + 1);
                captainsShip.getCargo().remove(captainsShip.getCargo().get(i));
            }else if(captainsShip.getCargo().get(i).getTreasureID() == choice && captainsShip.getCargo().get(i).getName().contains("2")){
                captainsShip.setNumberOfCannons(captainsShip.getNumberOfCannons() + 2);
                captainsShip.getCargo().remove(captainsShip.getCargo().get(i));
            }
        }
        //TODO: ellenőrzés a valid bevitelről
    }

    public void fillRum(Captain captain){

        Ship captainsShip = captain.getShip();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rums with which you want to fill the captain's supply.");
        System.out.println("Watch out! You can only select one item in this round!");

        int choice = scanner.nextInt();

        for (int i = 0; i < captainsShip.getCargo().size(); i++) {
            if(captainsShip.getCargo().get(i).getTreasureID() == choice && captainsShip.getCargo().get(i).getName().contains("7")){
                captain.setRumOwned(captain.getRumOwned() + 7);
                captainsShip.getCargo().remove(captainsShip.getCargo().get(i));
            }else if(captainsShip.getCargo().get(i).getTreasureID() == choice && captainsShip.getCargo().get(i).getName().contains("14")){
                captain.setRumOwned(captain.getRumOwned() + 14);
                captainsShip.getCargo().remove(captainsShip.getCargo().get(i));
            }else if(captainsShip.getCargo().get(i).getTreasureID() == choice && captainsShip.getCargo().get(i).getName().contains("21")){
                captain.setRumOwned(captain.getRumOwned() + 21);
                captainsShip.getCargo().remove(captainsShip.getCargo().get(i));
            }
        }
        //TODO: ellenőrzés a valid bevitelről
    }

    /** Ellenőrzi, hogy a válsztott elem alkalmas-e a javításra.
     * + van-e a rakományban ilyen elem */
    private boolean isValidForRepair(int choice, Ship captainsShip){

        for (int i = 0; i < captainsShip.getCargo().size(); i++) {
            if(captainsShip.getCargo().get(i).getTreasureID() == choice &&
                    captainsShip.getCargo().get(i).getValue() != 0){
                return true;
            }
        }
        return false;
    }

    /** Ellenőrzi, hogy a válsztott elem alkalmas-e az ágyú felhelyezésre.
     * + van-e a rakományban ilyen elem */
    private boolean isValidForInstallCannon(int choice, Ship captainsShip){

        for (int i = 0; i < captainsShip.getCargo().size(); i++) {
            if(captainsShip.getCargo().get(i).getTreasureID() == choice &&
                    captainsShip.getCargo().get(i).getName().contains("cannon") &&
                    captainsShip.getCargo().get(i).getType().equals(TreasureType.EQUIPMENT)){
                return true;
            }
        }

        return false;
    }

    /** Ellenőrzi, hogy a válsztott elem alkalmas-e a rumtöltésre.
     * + van-e a rakományban ilyen elem */
    private boolean isValidForFillRum(int choice, Ship captainsShip){

        for (int i = 0; i < captainsShip.getCargo().size(); i++) {
            if(captainsShip.getCargo().get(i).getTreasureID() == choice &&
                    captainsShip.getCargo().get(i).getName().contains("rum") &&
                    captainsShip.getCargo().get(i).getType().equals(TreasureType.EQUIPMENT)){
                return true;
            }
        }

        return false;
    }

}
