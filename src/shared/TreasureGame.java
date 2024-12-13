

package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TreasureGame extends Remote {
    
    public String registerPlayer(GameStateCallback callback) throws RemoteException;
    public boolean movePlayer(String playerId, String direction) throws RemoteException;
    public String exploreCell(String playerId) throws RemoteException;   
    public void disconnectPlayer(String playerId) throws RemoteException;
}


