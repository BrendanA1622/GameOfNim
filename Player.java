import java.util.ArrayList; // Imports the Arraylist so that the getMove() method can work effectively
import java.util.Scanner;   // Imports the scanner to get input from the player

public class Player {
  protected String name;                          // Name attribute, only accessable by classes that extend Player
  protected int score;                            // Score attribute, only accessable by classes that extend Player
  protected Scanner sc = new Scanner(System.in);  // Scanner used for retrieving input, only accessable by classes that extend Player

  public Player(int number) {                                         // Construct a player off of what numbered player they are
    System.out.println("What is your name, player " + number + "? "); // Prompt the player for their name
    name = sc.nextLine();                                             // Set their name to whatever they respond
    this.score = 0;                                                   // Set their score to zero
  }                                                                   //

  public Player(String name) { // Construct a player off of what name they want
    this.name = name;          // Set the name accordingly
    this.score = 0;            // Set their score to zero
  }                            //

  public void changeName(String name) { // Change the name to the proper value
    this.name = name;                   // Sets the name to the correct value
  }                                     //

  public void incrementScore() { // Increments the Player's score by one
    score++;                     // Increase the score by one
  }                              //

  public int getScore() { // Returns the Player's score
    return score;         // Returns the score
  }                       //

  public String getName() { // Return's the Player's name
    return name;            // Returns the name
  }                         //

  public int getMove(int numPieces, ArrayList<Player> allPlayers) { // Ask the player for how many pieces they want to give
    System.out.print("There are " + numPieces + " left. " + name +  // ⎫ 
    ", how many pieces do you take? (no more than "                 // ⎪ Tell the player how many pieces they can take
    + Math.max((numPieces / 2),1) + ") ");                          // ⎭
    int result = sc.nextInt();                                      // Ask them for the amount of pieces they want
    if(result > Math.max(numPieces / 2,1) || result <= 0) {         // If the player took an illegal number of pieces
      System.out.println("You took the wrong amount of pieces.");   //  - Tell the player that they took a wrong amount of pieces
      return getMove(numPieces, allPlayers);                        //  - Ask them for it again
    }                                                               //
      return result;                                                // Otherwise, return the correct result                             
  }
}