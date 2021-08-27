package highSeas.events;

import highSeas.crew.Captain;

public class HappyNight {

    public void feast(Captain captain){

        System.out.println("valami nagyonszöveg");

        while (captain.getRumOwned() > 0 && captain.getShip().getCrew().get(0).getDrunkLevel().level < 3){
            captain.giveRumToEveryone();
        }

        System.out.println("valamiszöveg");
    }

}
