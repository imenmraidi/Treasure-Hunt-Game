
package client;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import shared.GameStateCallback;







public class GameStateCallbackImpl extends UnicastRemoteObject implements GameStateCallback {
    protected GameStateCallbackImpl() throws RemoteException {
        super();
    }
    @Override
    public void updateGameState(String gameState, String playerId) throws RemoteException {
        
        System.out.print("\033[H\033[2J"); // Clear the screen and move cursor to top
        System.out.flush();
        System.out.println(gameState);
        System.out.println("\nEnter your move: Up/Down/Left/Right"
        +"\nType 'x' to explore current cell\n");
    }
    @Override
    public void announceWinner(String notification) throws RemoteException{
        System.out.println(notification);
        System.exit(0);
    }
}
    
