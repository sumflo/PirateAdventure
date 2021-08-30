package highSeas.ship;

import highSeas.controller.Dice;
import highSeas.crew.Pirate;
import highSeas.enums.Condition;
import highSeas.enums.TreasureType;
import highSeas.treasure.Treasure;

import java.util.ArrayList;
import java.util.List;

/** A hajó tulajdonképpen kapcsolótáblaként szolgál a legénység és a kapitány között.
 * A hajók csatázni tudnak egymással. Hordozzák a rakományt (Treasure-ök) és a legénységet (Pirate-ek).
 * Azonos ID-vel rendelkeznek, mint a kapitányuk.*/
public class Ship {

    private String name;
    private int attackPower;
    private int hitPoint;
    private int numberOfCannons;
    private Condition state;
    private List<Treasure> cargo; //TODO kijavítani (= new ArrayList<>()) és az össze shelyen, ahol feltöltöttem a rakományt kivenni a listalétrehozást.....
    private List<Pirate> crew = new ArrayList<>();
    private int ID;

    public Ship() {
    }

    /** A hajók támadóerő + ágyúk számaszor 6-os "kockával" sebeznek(hasonlóan a kalandjátékokhoz).
     * Ha a hajó rakománya közt élőholt majom van, akkor Különös dolgok történhetnek vízi csata közben.*/
    public void attack(Ship enemyShip, Dice dice){

        int damage = this.attackPower + this.numberOfCannons * (dice.throwDice12());

        if(isUndeadMonkeyOnTheBoard(this.cargo)){
            undeadMonkeyAttack(enemyShip, dice);
        }else{
            enemyShip.setHitPoint(enemyShip.getHitPoint() - damage);
            System.out.println(this.name + " damaged " + damage + ".");
            System.out.println(enemyShip.getName() + " has " + enemyShip.getHitPoint() + " HP now.");
            System.out.println();
        }

    }

    /** Az élőholt majom 5% eséllyel a kapitány fejére ugrik, a kapitány pedig félre tüzel.
     * Van 0,1% esély, hogy jön a semmiből egy rózsaszín bárány, és megahadályozza a félretüzelést, de
     * ha ez megtörténi, akkor onnantól a bárány további bonyadalmakat okozhat...*/
    private void undeadMonkeyAttack(Ship enemyShip, Dice dice){

        int damage = this.attackPower + this.numberOfCannons * (dice.throwDice12());
        int throwResult = dice.throwDice20();
        int easterEggChance = (int) ((Math.random() * (1001 - 1)) + 1);

        if(throwResult == 1){
            System.out.println("Oh no! The undead monkey jumped on the Captain " +  /*this.crew.get(0).getCaptain() +*/ "'s head and took his hoe. The whole crew started chasing it. All the cannons were set aside.\n");
        }else if(throwResult == 1 && easterEggChance == 666 && !haveLamb()){

            System.out.println("Oh no! The undead monkey jumped on the captain's head. But out of nowhere, a pink lamb suddenly appeared and ate the undead monkey.");
            System.out.println("Unfortunately a few minutes later the undead monkey sprouted from the lamb's stomach ...");
            System.out.println("Interestingly, the pink lamb did not perish in its injuries, but became undead.\n");

            Treasure easterEgg = new Treasure(21, "pink lamb", TreasureType.CURSE, 2500);
            this.getCargo().add(easterEgg);

            enemyShip.setHitPoint(enemyShip.getHitPoint() - damage);
            System.out.println(this.name + " damaged " + damage + ".");
            System.out.println(enemyShip.getName() + " has " + enemyShip.getHitPoint() + " HP now.");
            System.out.println();
        }else{
            enemyShip.setHitPoint(enemyShip.getHitPoint() - damage);
            System.out.println(this.name + " damaged " + damage + ".");
            System.out.println(enemyShip.getName() + " has " + enemyShip.getHitPoint() + " HP now.");
            System.out.println();
        }
    }

    /** Megvizsgálja, hogy van-e már bárány a rakományban. */
    private boolean haveLamb(){
        for (int i = 0; i < this.cargo.size(); i++) {
            if(this.cargo.get(i).getTreasureID() == 21){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    /** kiírja a hajó adatait. (máshol a toString-et használtam) */
    public void showShip(){

        System.out.println("Name: " + this.name + "\n"
                + "----------------------------------------" + "\n"
                + "Captain's name: " + this.ID + "\n"
                + "Attack power: " + this.attackPower + "\n"
                + "Current HP: " + this.hitPoint + "\n"
                + "Number of cannons: " + this.numberOfCannons + "\n"
                + "Condition: " + this.state + "\n"
                + "Cargo: " + this.cargo + "\n"
                + "Crew: " + getCrewNames());

    }

    /** Listába szedi a legénység neveit. */
    private List<String> getCrewNames(){
        List<String> pirateNames = new ArrayList<>();

        for (int i = 0; i < this.crew.size(); i++) {
            pirateNames.add(this.crew.get(i).getName());
        }

        return pirateNames;
    }

    /** Kiírja a rakományt. */
    public void showCargo(){
        System.out.println("Your cargo:");
        System.out.println("-----------");

        for (int i = 0; i < this.cargo.size(); i++) {
            System.out.println(this.cargo.get(i).toString());
        }
    }

    /** HitPoint alapján beállítja a state-et */
    public void updateState(){
        if (this.hitPoint <= 0){
            this.state = Condition.wreck;
        }else if(this.hitPoint <= 999){
            this.state = Condition.leaked;
        }else if(this.hitPoint == 1000){
            this.state = Condition.normal;
        }else if(this.hitPoint <= 1249){
            this.state = Condition.strong;
        }else{
            this.state = Condition.overpowered;
        }
    }

    /** Vizsgálja, hogy van-e élőholt majom a hajón. */
    private boolean isUndeadMonkeyOnTheBoard(List<Treasure> cargo){
        for (int i = 0; i < cargo.size(); i++) {
            if(cargo.get(i).getTreasureID() == 6){
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public int getNumberOfCannons() {
        return numberOfCannons;
    }

    public void setNumberOfCannons(int numberOfCannons) {
        this.numberOfCannons = numberOfCannons;
    }

    public Condition getState() {
        return state;
    }

    public void setState(Condition state) {
        this.state = state;
    }

    public List<Treasure> getCargo() {
        return cargo;
    }

    public void setCargo(List<Treasure> cargo) {
        this.cargo = cargo;
    }

    public List<Pirate> getCrew() {
        return crew;
    }

    public void setCrew(List<Pirate> crew) {
        this.crew = crew;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
