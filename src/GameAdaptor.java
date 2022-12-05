import DataType.Position.AwokenQueenPosition;
import DataType.Position.HandPosition;
import DataType.Position.Position;
import DataType.Position.SleepingQueenPosition;

import java.util.*;

public class GameAdaptor implements GamePlayerInterface {

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

    @Override
    public String play(String player, String cards) {
        if (players.contains(player)) {
            List<Position> cardsList = new ArrayList<>();
            Scanner scanner = new Scanner(cards);

            while (scanner.hasNext()) {
                String command = scanner.next();
                Position position;
                switch (command.charAt(0)) {
                    case 'h':
                        HandPosition handPosition = new HandPosition(Character.getNumericValue(command.charAt(1)), players.indexOf(player));
                        position = new Position(handPosition);
                        break;
                    case 'a':
                        AwokenQueenPosition awokenQueenPosition = new AwokenQueenPosition(Character.getNumericValue(command.charAt(2)), Character.getNumericValue(command.charAt(1)));
                        position = new Position(awokenQueenPosition);
                        break;
                    case 's':
                        command = command.substring(1);
                        SleepingQueenPosition sleepingQueenPosition = new SleepingQueenPosition(Integer.parseInt(command));
                        position = new Position(sleepingQueenPosition);
                        break;
                    default:
                        position = null;
                }
                cardsList.add(position);
            }
            game.play(players.indexOf(player), cardsList);
            return "Success";
        } else {
            return "Failure";
        }
    }
}