package highSeas.controller;

public class Dice {

    public int throwDice6(){
        return (int) ((Math.random() * (7 - 1)) + 1);
    }

    public int throwDice10(){
        return (int) ((Math.random() * (11 - 1)) + 1);
    }

    public int throwDice12(){
        return (int) ((Math.random() * (13 - 1)) + 1);
    }

    public int throwDice20(){
        return (int) ((Math.random() * (21 - 1)) + 1);
    }

}
