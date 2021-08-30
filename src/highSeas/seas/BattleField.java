package highSeas.seas;

import highSeas.controller.Dice;
import highSeas.crew.Captain;
import highSeas.crew.Pirate;
import highSeas.ship.Ship;

public class BattleField {

    /** Hajók közti csata - ha a csatát elveszted, a metódus false értéket ad vissza, és a játék nem folytatódik.
     * Ha nyersz, a visszaadott érték true lesz, és a játék folytatódik.*/
    public void shipBattle(Captain playerCaptain, Captain enemyCaptain, Dice dice){

        Ship playerShip = playerCaptain.getShip();
        Ship enemyShip = enemyCaptain.getShip();

        /** A csata addig tart, amíg az egyik hajó hitPoint-ja 0-ra vagy kevesebbre csökken. */
        while(playerShip.getHitPoint() > 0 && enemyShip.getHitPoint() > 0){

                playerShip.attack(enemyShip, dice);

            /**Itt meg kell vizsgálni, hogy az ellenséges hajó hitpointja nem ment-e 0-ra vagy alá, hogy ne
             * sebezhessen a végső találat után. A játékos sebez először a "kör" elején. A ciklus csak úgy lép be
             * a körbe, ha a HP-ja nem 0 vagy kissebb, szóval azt nem kell többször vizsgálni.*/
            if(enemyShip.getHitPoint() > 0) {
                enemyShip.attack(playerShip, dice);
            }
        }

        /** A csata után frissítem a győztes hajó status-át, és kiíratom a kellő szöveget, majd return-ölöm az eredményt. */
        if(playerShip.getHitPoint() > 0){
            playerShip.updateState();
            System.out.println("Nice work! You won the battle.");
            System.out.println("The enemy ship sank with all his cargo and the captain's rum supply.");
            System.out.println("Current hit point of your ship is " + playerShip.getHitPoint() + ".");
            System.out.println("Current status of your ship is " + playerShip.getState() + ".");
            System.out.println();
        }else if(enemyShip.getHitPoint() > 0){
            playerShip.updateState();
            playerCaptain.setAlive(false);
            System.out.println("You are defeated!");
            System.out.println("The ship " + enemyShip.getName() + " is the winner!");
            System.out.println("Remaining vitality of " + enemyShip.getName() + " is "+ playerShip.getHitPoint() + ".");
            System.out.println();
        }else if(playerShip.getHitPoint() < 0 && enemyShip.getHitPoint() < 0){ // elvileg ilyen verzió nem lehetséges, de...
            playerCaptain.setAlive(false);
            System.out.println("Incredible! Both ships sank. You are defeated!");
            System.out.println();
        }

    }


    /** Csata a fedélzeten a kalózok, és, ha szükséges a kapitányok közt - ha a csatát elveszted, a metódus false
     * értéket ad vissza, és a játék nem folytatódik. Ha nyersz, a visszaadott érték true lesz, és a játék folytatódik.*/
    public void boardBattle(Captain playerCaptain, Captain enemyCaptain, Dice dice){

        /** Először a legénység csap össze.*/
        crewFight(playerCaptain, enemyCaptain, dice);

        if(playerCaptain.getFightCounter() > enemyCaptain.getFightCounter()){
            playerCaptain.getShip().getCargo().addAll(enemyCaptain.getShip().getCargo());
            System.out.println("Congratulations! You won the board battle. You received the cargo of " +
                    enemyCaptain.getShip().getName() + ".");
            System.out.println("Your cargo is now: " + playerCaptain.getShip().getCargo()); //TODO: kiíratni a rakományt -->> tostring a Ship osztályban
            System.out.println("Your crew tied Captain " + enemyCaptain.getName() + " to the mast and sent him to the " +
                    "bottom of the ocean with his ship, accompanied by a spectacular cannon fire.");
            System.out.println();

        }else if(enemyCaptain.getFightCounter() > playerCaptain.getFightCounter()){
            playerCaptain.setAlive(false);
            System.out.println("Unfortunately, you lost the battle on board. Captain " + enemyCaptain.getName() +
                    " sank your ship with the crew.");
            System.out.println();

        }else if(playerCaptain.getFightCounter() == enemyCaptain.getFightCounter()){

            /**Ha az állás döntetlen, a kapitányok is összecsapnak*/
            captainFight(playerCaptain, enemyCaptain, dice);

            if (playerCaptain.getHealthPoint() > 0) {
                /**Ha a játékos kapitánya nyer, a kapitány életét, és harci számlálóját vissza kell állítani az eredeti
                 * állapotba a további csaták megvívásához.*/
                playerCaptain.healthRestore();
                /** A játékos kapitány hajója megkapja az ellenfél rakományát, és az ellenfél kapitány rumkészletékt is! */
                playerCaptain.getShip().getCargo().addAll(enemyCaptain.getShip().getCargo());
                playerCaptain.loot(enemyCaptain);
                System.out.println("Nice work! " + playerCaptain.getName() + " won the final fight. You received " +
                        "the cargo of " + enemyCaptain.getShip().getName() + " and the rum of Captain "
                        + enemyCaptain.getName() + ".");
                System.out.println("Your crew sent the enemy ship to the bottom of the ocean.");
                System.out.println();

            } else if (enemyCaptain.getHealthPoint() > 0) {
                playerCaptain.setAlive(false);
                System.out.println("Unfortunately " + enemyCaptain.getName() + " won the final fight.");
                System.out.println();

            }
        }

        playerCaptain.decreaseDrunkLevel();
        playerCaptain.setFightCounter(0);

    }

    private void crewFight(Captain playerCaptain, Captain enemyCaptain, Dice dice){
        /** Az összecsapásokat annyiszor ismétlem meg, ahány kalóz tartozik a kapitányhoz (az egyszerűség miatt
         * mindegyik kapitányhoz ugyanannyi, 6-6 kalóz tartozik. A kapitányok csak akkor csapnak össze, ha harcok
         * győzelmi aránya döntetlen.)*/
        for (int i = 0; i < playerCaptain.getShip().getCrew().size(); i++) {

            /**Rövidítés (lustaság és társai) miatt erltárolom az éppen aktuális összecsapókat egy-egy ideiglenes változóban
             * pl.: -->> player -><- playerCaptain.getShip().getCrew().get(i) */
            Pirate player = playerCaptain.getShip().getCrew().get(i);
            Pirate enemy = enemyCaptain.getShip().getCrew().get(i);

            /**Ellenőrzöm, hogy a harcosok életpontja nem 0, vagy kisebb --> a küzdelem addig tart, amíg a harcosok
             * életpontja 0, vagy kevesebb. */
            while (player.getHealthPoint() > 0 && enemy.getHealthPoint() > 0) {

                player.attack(enemy, dice);

                /**Itt újra ellenőriznem kell az ellenséges kalóz HP-ját, mivel a játékos üt először a körben.
                 * Ha leviszi az ellenség HP-ját, akkor az már nem tud sebezni többé.*/
                if (enemy.getHealthPoint() > 0) {
                    enemy.attack(player, dice);
                }
            }

            /** Akinek a kalóza nyer, annak a kapitányának növelem eggyel a harci számlálóját, a kalóznak pedig eggyel
             * csökkentem a drunkLevel-ét, majd kiíratom az összecsapás számát, és kimenetelét. Az ellenfélnél a
             * drunkLevel csökkentése nem szükséges, hisz ha megsemmisül, nem foglalkozunk vele tovább, ha legyőz,
             * vége a játéknak, és nem foglalkozunk vele tovább.*/
            if (player.getHealthPoint() > 0) {
                playerCaptain.setFightCounter(playerCaptain.getFightCounter() + 1);
                player.decreaseDrunkLevel();
                System.out.println("Nice work! " + player.getName() + " won the " + (i+1) + ". fight.");
                System.out.println();
            } else if (enemy.getHealthPoint() > 0) {
                enemyCaptain.setFightCounter(enemyCaptain.getFightCounter() + 1);
                enemy.decreaseDrunkLevel();
                System.out.println("Unfortunately " + enemy.getName() + " won the " + (i+1) + ". fight.");
                System.out.println();
            }

            /** A küzdelem végén visszaállítom a kalóz életét a kezdőértékre. A miérteket most nem firtatnám.*/
            player.healthRestore();
        }
    }

    /** A kapitányok összecsapása.*/
    private void captainFight(Captain playerCaptain, Captain enemyCaptain, Dice dice){

        while (playerCaptain.getHealthPoint() > 0 && enemyCaptain.getHealthPoint() > 0) {

            playerCaptain.attack(enemyCaptain, dice);

            if (enemyCaptain.getHealthPoint() > 0) {
                enemyCaptain.attack(playerCaptain, dice);
            }
        }
    }




}
