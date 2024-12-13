


package server;

import utils.Position;

public class Player {
    private final String id;
    private Position position;

    public Player(String id) {
        this.id = id;
        this.position = new Position(0, 0); // Default starting position
    }

    public String getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}


