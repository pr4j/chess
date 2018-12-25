package chess;

import com.google.common.collect.Maps;
import manager.GameManager;

import java.util.Map;

public class App {
    private static Map<Integer, GameManager> gameManagerMap = Maps.newHashMap();

    public static void main(String[] args) {
        App app = new App();

        gameManagerMap.put(0, new GameManager());

        for (int i = 0; i < 100; ++i) {
            app.play(0,"a1", "b2");
        }
    }

    // Public Web API
    public void play(Integer gameId, String initPos, String finPos) {
        if (gameManagerMap.containsKey(gameId)) {
            gameManagerMap.get(gameId).play(initPos, finPos);
        } else {
            System.err.println("STUB: Log error: Invalid game ID.");
        }
    }
}
