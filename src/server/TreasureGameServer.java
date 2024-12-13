package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import shared.GameStateCallback;
import shared.TreasureGame;
import utils.Position;

public class TreasureGameServer extends UnicastRemoteObject implements TreasureGame {
    private final Map<String, PlayerSession> playerSessions = new ConcurrentHashMap<>();
    private final GameMap gameMap;

    protected TreasureGameServer(int rows, int cols) throws RemoteException {
        gameMap = new GameMap(rows, cols);
    }
    protected GameMap getGameMap() {
        return gameMap;
    }

    @Override
    public String registerPlayer(GameStateCallback callback) throws RemoteException {
        String playerId;
        synchronized (playerSessions) {
            playerId = "P" + (playerSessions.size() + 1);
            Player newPlayer = new Player(playerId);
            PlayerSession session = new PlayerSession(newPlayer, callback);
            playerSessions.put(playerId, session);
        }

        System.out.println("Player registered with ID: " + playerId);
        updateGameState(playerId);
        return playerId;
    }

    @Override
    public boolean movePlayer(String playerId, String direction) throws RemoteException {
        PlayerSession session = playerSessions.get(playerId);
        if (session == null) return false;

        Player player = session.getPlayer();
        List<Position> occupiedPositions;

        synchronized (playerSessions) {
            occupiedPositions = new ArrayList<>();
            for (PlayerSession s : playerSessions.values()) {
                if (!s.getPlayer().getId().equalsIgnoreCase(playerId)) {
                    occupiedPositions.add(s.getPlayer().getPosition());
                }
            }
        }
        boolean moved;
        synchronized (gameMap) {
            moved = gameMap.movePlayer(player, direction, occupiedPositions);
        }
        if (moved) {
            updateGameState(playerId);
        }
        return moved;
    }


    @Override
    public String exploreCell(String playerId) throws RemoteException {
        PlayerSession session = playerSessions.get(playerId);
        if (session == null) return "Player doesn't exist";

        boolean hasWon;
        synchronized (gameMap) {
            hasWon = gameMap.hasTreasure(session.getPlayer());
        }

        if (hasWon) {
            announceWinner(playerId);
            return "Congratulations! You have found the treasure at " + session.getPlayer().getPosition();
        } else {
            return "Nothing here.";
        }
    }
    
    @Override
    public void disconnectPlayer(String playerId) throws RemoteException {
        synchronized (playerSessions) {
            playerSessions.remove(playerId);
        }
        System.out.println("Player "+playerId+" has disconnected");

    }
    private void announceWinner(String winner) {
        List<Player> players = new ArrayList<>();
        synchronized (playerSessions) {
            for (PlayerSession session : playerSessions.values()) {
                players.add(session.getPlayer());
            }
        }
        synchronized (playerSessions) {
            for (PlayerSession session : playerSessions.values()) {
                try {
                    session.getCallback().announceWinner(
                        winner+" has found the treasure at " + GameMap.getTreasure()+" !");
                } catch (RemoteException e) {
                    System.err.println("Failed to notify player: " + session.getPlayer().getId());
                    playerSessions.remove(session.getPlayer().getId());
                }
            }
            playerSessions.clear();
        }
    }

    private void updateGameState(String playerId) {
        List<Player> players = new ArrayList<>();
        synchronized (playerSessions) {
            for (PlayerSession session : playerSessions.values()) {
                players.add(session.getPlayer());
            }
        }
        String currentState;
        synchronized (gameMap) {
            currentState = gameMap.gameState(players);
        }
        synchronized (playerSessions) {
            for (PlayerSession session : playerSessions.values()) {
                try {
                    session.getCallback().updateGameState(currentState, session.getPlayer().getId());
                } catch (RemoteException e) {
                    System.err.println("Failed to notify player: " + session.getPlayer().getId());
                    playerSessions.remove(session.getPlayer().getId());
                }
            }
        }
    }
}

