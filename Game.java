import java.util.ArrayList;   // Imports the ArrayList for the list of players
import java.util.Collections; // Imports the Collections so the ArrayList can be shuffled
import java.lang.Math;        // Imports Math for randomness
import java.util.Scanner;     // Imports Scanner for Input

public class Game {
  private int piecesLeft;    // The number of pieces that the current game of nin has left
  private int playerCount;   // The number of players in the game
  private int botCount;      // The number of AI Players in the game
  private int currentTurn;   // Which player's turn it is
  private boolean isPlaying; // A boolean that stores whether or not the game is ongoing
  
  private String input;                        // A String that stores user input
  private Scanner sc = new Scanner(System.in); // A scanner used to collect input
  private ArrayList<Player> allPlayers;        // An ArrayList of every player
  
  public Game() {                                   // Constructs a Game. Default constructor.
    allPlayers = new ArrayList<Player>();           // Sets all of the players to a blank ArrayList
    piecesLeft = ((int) (Math.random() * 41)) + 10; // Select a random amount of pieces
    playerCount = 0;                                // Set the player count to zero
    botCount = 0;                                   // Set the AI player count to zero
    currentTurn = 0;                                // Set the current turn to zero
    getPlayers();                                   // Ask for players
    isPlaying = true;                               // Make it so the game is playing
  }                                                 //

  public void play() {                                              //
    while(isPlaying) {                                              // While the game is playing:
      piecesLeft -= allPlayers.get(currentTurn % allPlayers.size()) //  - Remove a number of pieces equal to the amount that the 
      .getMove(piecesLeft, allPlayers);                             //    current player took
      if(piecesLeft <= 0) {                                         //  - If there are no pieces left to take:
        isPlaying = false;                                          //    - Stop the game
      }                                                             //  - 
      currentTurn++;                                                //  - Increment the score while the game is playing
    }                                                               //
    endGame();                                                      // End the game once you're no longer playing
  }                                                                 //

  public void endGame() {                                                 // The method that ends the game (and asks for a new one)
    currentTurn--;                                                        // decrement the current turn (accounts for line 34)
    for(int i = 0; i < allPlayers.size(); i++) {                          // For every player in the list:
      if(currentTurn % allPlayers.size() != i) {                          //  - If that player didn't lose:
        allPlayers.get(i).incrementScore();                               //    - Increment that player's score by one
      }                                                                   //
    }                                                                     //
    printScore();                                                         // Print the score out
    System.out.println("The game has ended, do you want to play again?"); // Prompt the player to play again
    String input = sc.next();                                             // Get the player's input if they want to play again
    if (input.equalsIgnoreCase("y")) {                                    // If the player wants to play again:
      piecesLeft = ((int) (Math.random() * 41)) + 10;                     //  - Reset the number of pieces
      Collections.shuffle(allPlayers);                                    //  - Shuffle the number of players
      play();                                                             //  - Play the game again
    } if(currentTurn > 1000) {                                            // ⎫ 
      isPlaying = false;                                                  // ⎭ Stops the game before a StackOverflow occurs
    }                                                                     //
  }                                                                       //

  public void printScore() {                           // Print the score
    System.out.println("Scores:");                     // Header for player data
    for(int i = 0; i < allPlayers.size(); i++) {       // Print, for every player in the ArrayList:
      System.out.println(allPlayers.get(i).getName() + //  - Their name
      ":\t" + allPlayers.get(i).getScore());           //  - Their score
    }                                                  //
  }                                                    //

  public void getPlayers() {                                               // Get the list of players
    System.out.print("Would you like to add another player/bot? (y/n) ");  // Prompts if the user wants another player
    input = sc.next();                                                     // Receives input from the user
    if(input.equalsIgnoreCase("y")) {                                      // If they want another player
      System.out.print("Would you like to add a player, or a bot (p/b) "); //  - Prompt the user if they want to add a player or a bot
      input = sc.next();                                                   //  - Receives input from user
      if(input.equals("p")) {                                              //  - If the user wants a player
        addPlayer();                                                       //    - Add a player
      } else if (input.equals("b")) {                                      //  - If the user wants a bot
        addBot();                                                          //    - Add a bot
      } else {                                                             //  - If the player does neither
        System.out.println("Invalid input, no player/bot was added.");     //    - Inform them of their error and do nothing
      }                                                                    //
      getPlayers();                                                        //  - Ask for players again
    } else if (!input.equalsIgnoreCase("n")) {                             // If the user made a typo:
      getPlayers();                                                        //  - Get more players
    } else if ((playerCount + botCount) < 2) {                             // If there are less than two players:
      getPlayers();                                                        //  - Get more players
    }                                                                      //
  }                                                                        //

  private void addPlayer() {                     // Adds a player to the list of players
    allPlayers.add(new Player(playerCount + 1)); // Adds a player to the list of players
    playerCount++;                               // Increments the player count
  }                                              //

  private void addBot() {                       // Adds a bot to the list of players
    allPlayers.add(new AIPlayer(botCount + 1)); // Adds a bot to the list of players
    botCount++;                                 // Increments the bot count
  }                                             //
}