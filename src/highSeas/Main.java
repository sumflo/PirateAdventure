package highSeas;

import highSeas.controller.GameController;

public class Main {

    public static void main(String[] args) {

        /**Mivel a program még fejlesztés alatt áll, nem került bele a gamecontroller a processorba
         * (és nem is hoztam még létre Processor classt), hanem itt tesztelek vele.*/
        GameController game = new GameController();
        game.startStory();

    }
}
