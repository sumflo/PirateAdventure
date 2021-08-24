package highSeas;

import highSeas.controller.GameController;
import highSeas.treasure.Treasure;
import highSeas.utils.DocReader;
import highSeas.utils.FileRouting;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        DocReader docReader = new DocReader();
        GameController game = new GameController();
        game.startStory();

        /*DocReader docReader = new DocReader();
        List<Treasure> treasureList = docReader.convertDocToTreasureArrayList(FileRouting.getSourceTreasure());

        System.out.println(treasureList);*/
    }
}
