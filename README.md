# Treasure Hunt Game

## Project Overview
This is a real-time multiplayer game where players explore a grid map in search of a hidden treasure. The game is implemented using Java RMI (Remote Method Invocation) for client-server communication. Players can move on the grid, interact with other players, and explore cells to find the treasure.

## Game Description
- The game involves multiple players connected to a server.
- Each player is assigned a unique ID and can move in different directions on a grid.
- The goal is to find the hidden treasure by exploring the grid.
- Players can move to adjacent cells and check if the treasure is present in their current position.
- The first player to find the treasure wins the game.

## Rules
- Players can move in four directions: up, down, left, right.
- When a player finds the treasure, the game ends, and all players are notified.
- Players can disconnect from the game at any time.
  
## Features
- Real-time multiplayer functionality using Java RMI.
- Players can register, move, and explore the grid.
- Synchronization ensures that player actions are handled concurrently.
- Winner announcement when the treasure is found.

## Technologies Used
- Java RMI (Remote Method Invocation)
- Threading for concurrent player actions

## How to Run
1. Clone the repository.
2. Compile the server and client files.
3. Start the server using `java server.Server`.
4. Start the client using `java client.Client`.
