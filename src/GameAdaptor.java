import java.util.*;

public class GameAdaptor {

    private List<String> players;
    private Game game;

    public GameAdaptor(List<String> playersNicks, String firstOnTurn) {
        if ((playersNicks.size() >= 2) &&(playersNicks.size() <= 5)) {
            players = new ArrayList<>();
            int firstOnTurnIdx = 0;
            for (int i = 0; i < playersNicks.size(); i++) {
                players.add(playersNicks.get(i));
                if (playersNicks.get(i).equals(firstOnTurn)) {
                    firstOnTurnIdx = i;
                }
            }
            game = new Game(firstOnTurnIdx, players.size());
        }
    }

    public void play(String player, String cards) {
        if (players.contains(player)) {
            //game.play(players.indexOf(player), );
        }
    }
}