

package server;

import shared.GameStateCallback;

public class PlayerSession {
    private final Player player;
    private final GameStateCallback callback;

    public PlayerSession(Player player, GameStateCallback callback) {
        this.player = player;
        this.callback = callback;
    }

    public Player getPlayer() {
        return player;
    }

    public GameStateCallback getCallback() {
        return callback;
    }
}


