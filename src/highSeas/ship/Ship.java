package highSeas.ship;

import highSeas.controller.Dice;
import highSeas.crew.Pirate;
import highSeas.enums.Condition;
import highSeas.treasure.Treasure;

import java.util.ArrayList;
import java.util.List;

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

    public void attack(Ship enemyShip, Dice dice){

        int damage = this.attackPower + this.numberOfCannons * (dice.throwDice12());

        for (int i = 0; i < this.cargo.size(); i++) {
            if(this.cargo.get(i).getTreasureID() == 6){
                int throwResult = dice.throwDice20();
                if(throwResult == 1){
                    System.out.println("Oh no! The undead monkey jumped on the Captain " +  /*this.crew.get(0).getCaptain() +*/ "'s head and took his hoe. The whole crew started chasing it. All the cannons were set aside.");
                }
            }else{
                enemyShip.setHitPoint(enemyShip.getHitPoint() - damage);
                System.out.println(this.name + " damaged " + damage + ".");
                System.out.println(enemyShip.getName() + " has " + enemyShip.getHitPoint() + " HP now.");
                System.out.println();
            }
        }



    }

    //TODO: toString
    public void showShip(){

        System.out.println("Name: " + this.name + "\n"
                + "----------------------------------------" + "\n"
                + "Captain's name: " + this.ID + "\n"
                + "Attack power: " + this.attackPower + "\n"
                + "Current HP: " + this.hitPoint + "\n"
                + "Number of cannons: " + this.numberOfCannons + "\n"
                + "Condition: " + this.state + "\n"
                + "Cargo: " + this.cargo + "\n"
                + "Crew: " + this.crew);

    }

    public void showCargo(){
        System.out.println("Your cargo:");
        System.out.println("-----------");

        for (int i = 0; i < this.cargo.size(); i++) {
            System.out.println(this.cargo.get(i).toString());
        }
    }

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
