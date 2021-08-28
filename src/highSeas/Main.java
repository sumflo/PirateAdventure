package highSeas;

import highSeas.controller.GameController;
import highSeas.treasure.Treasure;
import highSeas.utils.DocReader;
import highSeas.utils.FileRouting;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        GameController game = new GameController();
        game.startStory();

    }
}
