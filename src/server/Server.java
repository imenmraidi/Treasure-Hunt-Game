

package server;

import shared.TreasureGame;

public class Server {
   public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname","127.0.0.1");
            TreasureGame server = new TreasureGameServer(5,5);
            java.rmi.Naming.rebind("TreasureGame", server);
            System.out.println("Game server started...");
            System.out.println("Treasure at "+GameMap.getTreasure());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");

        }
    }
}



