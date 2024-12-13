


package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameStateCallback extends Remote {
    public void updateGameState(String gameState, String playerId) throws RemoteException;
    public void announceWinner(String notification) throws RemoteException;
}



