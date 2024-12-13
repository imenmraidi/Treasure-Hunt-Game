package utils;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Position move(String direction, int maxRows, int maxCols) {
        int newX = x, newY = y;

        switch (direction.toUpperCase()) {
            case "UP":
                newX = Math.max(0, x - 1);
                break;
            case "DOWN":
                newX = Math.min(maxRows - 1, x + 1);
                break;
            case "LEFT":
                newY = Math.max(0, y - 1);
                break;
            case "RIGHT":
                newY = Math.min(maxCols - 1, y + 1);
                break;
        }
        if (newX == x && newY == y) return null; // No move
        return new Position(newX, newY);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if both references point to the same object
        if (obj == null || getClass() != obj.getClass()) return false; // Ensure obj is a Position
        Position position = (Position) obj; // Cast obj to Position
        return x == position.x && y == position.y; // Compare x and y values
    }
}


