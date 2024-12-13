package server;
import java.util.List;
import java.util.Random;
import utils.Position;

public class GameMap {
    private final int rows, cols;
    private static Position treasurePosition;
    public GameMap(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        placeTreasure();
    }
    private void placeTreasure() {
        Random random = new Random();
        int x = random.nextInt(rows - 1) + 1; 
        int y = random.nextInt(cols - 1) + 1; 
        treasurePosition = new Position(x, y);
    }
    public boolean movePlayer(Player player, String direction, List<Position> occupiedPositions) {

        Position newPosition = player.getPosition().move(direction, rows, cols);
        if (newPosition == null) return false;
        if(occupiedPositions.contains(newPosition)) return false;
        player.setPosition(newPosition);
        return true;
    }
    public String gameState( List<Player> players) {
        StringBuilder map = new StringBuilder();

        for (int x = 0; x < rows; x++) { 
            for (int y = 0; y < cols; y++) { 
                StringBuilder cell = new StringBuilder();
                for (Player player : players) {
                    if (player.getPosition().getX() == x && player.getPosition().getY() == y) {
                        cell.append(player.getId()); 
                    }
                }
                if (cell.length() == 0) {
                    cell.append(".");
                }
                map.append(String.format("%-6s", cell.toString())); 
            }
            map.append("\n\n"); 
        }
        return map.toString();
    }

    public boolean hasTreasure(Player player) {
        Position position = player.getPosition();
        if (position.getX()== treasurePosition.getX() && position.getY()== treasurePosition.getY() ) {
            return true;
        }
        return false;
    }
    public static  Position getTreasure(){
        return treasurePosition;
    }
}
