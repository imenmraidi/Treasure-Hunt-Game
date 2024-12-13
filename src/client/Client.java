package client;
import shared.TreasureGame;
import shared.GameStateCallback;
import java.rmi.Naming;
// import java.util.Scanner;
public class Client {

    public static void main(String[] args) {
        try {
            // Lookup the server
            TreasureGame server = (TreasureGame) Naming.lookup("rmi://127.0.0.1/TreasureGame");

            // Create and start several client threads
            for (int i = 1; i <= 6; i++) {
                GameStateCallback callback = new GameStateCallbackImpl();
                new ClientThread(server,  callback).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //////////////////////////////////////////////////// to play the game manually ///////////////////////////////////////////////////////

    // public static void main(String[] args) {
    //     try {
    //         // Lookup the RMI server
    //         TreasureGame server = (TreasureGame) Naming.lookup("rmi://127.0.0.1/TreasureGame");
            
    //         // Register client callback
    //         GameStateCallback callback = new GameStateCallbackImpl();
    //         String myId = server.registerPlayer(callback);

    //         try (Scanner scanner = new Scanner(System.in)) {
    //             while (true) {
    //                 String input = scanner.nextLine();

    //                 if ("exit".equalsIgnoreCase(input)) {
    //                     // Disconnect the player and exit the loop
    //                     server.disconnectPlayer(myId);
    //                     System.out.println("You have been disconnected.");
    //                     break;
    //                 }

    //                 if ("X".equalsIgnoreCase(input)) {
    //                     // Call the exploreCell method
    //                     String result = server.exploreCell(myId);
    //                     System.out.println(result);
    //                 } else {
    //                     //Move the player
    //                     boolean move = server.movePlayer(myId, input);
    //                     if (!move) {
    //                         System.out.println("Invalid or occupied move !");
    //                     }
    //                 }
    //             }
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
   
}
