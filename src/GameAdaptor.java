import DataType.Position.AwokenQueenPosition;
import DataType.Position.HandPosition;
import DataType.Position.Position;
import DataType.Position.SleepingQueenPosition;

import java.util.*;

public class GameAdaptor implements GamePlayerInterface {

    private List<String> players;
    private Game game;
    private GameObservable gameObservable;

    public GameAdaptor(GameObservable gameObservable, String firstOnTurn) {
        this.gameObservable = gameObservable;
        players = new ArrayList<>(gameObservable.getPlayers());
        game = new Game(players.indexOf(firstOnTurn), players.size());
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