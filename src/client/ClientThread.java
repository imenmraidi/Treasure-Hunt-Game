package client;

import java.rmi.RemoteException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import shared.GameStateCallback;
import shared.TreasureGame;

public class ClientThread extends Thread {
    private String playerId;
    private final TreasureGame server;
    private final GameStateCallback callback;

    public ClientThread(TreasureGame server,  GameStateCallback callback) {
        this.server = server;
        this.callback = callback;
    }
    @Override
    public void run() {
        try {
            
            this.playerId=server.registerPlayer(callback);
            // Simulate random moves and exploration
            Random random = new Random();
            String[] directions = {"up", "down", "left", "right"};
            while (true) {
                TimeUnit.SECONDS.sleep( 1);  
                // Randomly move or explore
                if (random.nextBoolean()) {
                    String direction = directions[random.nextInt(directions.length)];
                    boolean moveResult = server.movePlayer(playerId, direction);
                    if (moveResult) {
                        System.out.println(playerId + " moved in direction: " + direction);
                    } else {
                        System.out.println(playerId + " invalid or occupied move!");
                    }
                } else {
                    String result = server.exploreCell(playerId);
                    System.out.println(playerId + " explored: " + result);
                }

            }
        } catch (RemoteException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

