import DataType.GameState;

import java.util.*;

public class GameObservable {
    private List<GameObserver> observers;
    private List<String> players;

    public GameObservable() {
        observers = new ArrayList<>();
        players = new ArrayList<>();
    }

    public void add(GameObserver observer) {
        observers.add(observer);
    }

    public void addPlayer(String player, GameObserver observer) {
        if (!(players.contains(player)) && (players.size() < 5)) {
            players.add(player);
            observers.add(observer);
        }
    }

    public void remove(GameObserver observer) {
        observers.remove(observer);
    }

    public void notifyAll(GameState message) {
        for (GameObserver obs : observers) {
            obs.notify(message.toString());
        }
    }
}
