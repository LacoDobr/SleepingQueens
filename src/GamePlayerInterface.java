import java.util.*;

public class GamePlayerInterface {

    public GamePlayerInterface(List<String> playersNicks, String firstOnTurn) {
        new GameAdaptor(playersNicks, firstOnTurn);
    }


    public void play(String player, String cards) {

    }
}
