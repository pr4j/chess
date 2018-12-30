package chess;

import board.Position;
import com.google.common.collect.Maps;
import manager.GameManager;

import java.util.Map;

public class App {
    private static Map<Integer, GameManager> gameManagerMap = Maps.newHashMap();

    public static void main(String[] args) {
        App app = new App();

        gameManagerMap.put(0, new GameManager());

        app.play(0, new Position(1,0), new Position(2,0));
    }

    // Public Web API
    public void play(Integer gameId, Position initPos, Position finPos) {
        if (gameManagerMap.containsKey(gameId)) {
            gameManagerMap.get(gameId).play(initPos, finPos);
        } else {
            System.err.println("STUB: Log error: Invalid game ID.");
        }
    }
}
