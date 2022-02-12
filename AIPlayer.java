import java.util.ArrayList; // Imports ArrayList for storing information about the board
import java.lang.Math;      // Imports Math for selecting random numbers

public class AIPlayer extends Player {
  public AIPlayer(int number) {                           // Creates a new AI player with a certain number
    super("Bot" + number);                                // Populates all of the attributes that Player has with the proper constructor
    System.out.println("What behavior would you like? "); // Prompts the Player for their preferred behaviour
    System.out.println("Options:");                       // ⎫
    System.out.println(" • RANDOM");                      // ⎪
    System.out.println(" • EASY");                         // ⎪ Displays the list of inputs
    System.out.println(" • HARD");                        // ⎭
    String input = sc.nextLine();                         // Get input
    if(input.equalsIgnoreCase("RANDOM")) {                // If they requested an AI with random behaviour:
      behavior = Behavior.RANDOM;                         //  - Set the behaviour to Random
      changeName("Bot" + number + " RANDOM");             //  - Append "RANDOM" to the name for clarification
    } else if(input.equalsIgnoreCase("EASY")) {           // If they requested an AI with easy behaviour:
      behavior = Behavior.EASY;                           //  - Set the behaviour to Easy
      changeName("Bot" + number + " EASY");               //  - Append "EASY" to the name for clarification
    } else if(input.equalsIgnoreCase("HARD")) {           // If they requested an AI with hard behaviour:
      behavior = Behavior.HARD;                           //  - Set the behaviour to Hard
      changeName("Bot" + number + " HARD");               //  - Append "HARD" to the name for clarification
    } else {                                              // Otherwise:
      new AIPlayer(number);                               //  - Ask them for input again, repeat until a valid
    }                                                     //    input is entered
  }                                                       //

  private Behavior behavior; // variable to store an enum that determines how the AI behaves.
  
  @Override                                                          // Overrides the already existing getMove method in player
  public int getMove(int piecesLeft, ArrayList<Player> allPlayers) { // Gets the move from the AI, inputting information about the game
    int val;                                                         // A value that stores what the AI will return
    switch(behavior) {                                               // Creates a switch statement for the enum
      case RANDOM:                                                   // If the AI behaves randomly:
        val = getRandom(piecesLeft);                                 //  - Refer to the getRandom() method for piece selection
        if(val <= 0) {
          val = 1;
        }
        System.out.println(name + " took " + val + " pieces.");      //  - Print out the number of pieces taken
        return val;                                                  //  - Return the number of pieces taken
      case EASY:                                                     // If the AI is set to "easy":
        val = getEasy(piecesLeft, allPlayers);                       //  - Refer to the getEasy() method for piece selection
        if(val <= 0) {
          val = 1;
        }
        System.out.println(name + " took " + val + " pieces.");      //  - Print out the numbe of pieces taken
        return val;                                                  //  - Return the number of pieces taken
      case HARD:                                                     // If the AI is set to "hard":
        val = getHard(piecesLeft, allPlayers);                       //  - Refer to the getHard() method for piece selection
        if(val <= 0) {
          val = 1;
        }
        System.out.println(name + " took " + val + " pieces.");      //  - Print out the number of pieces taken
        return val;                                                  //  - Return the number of pieces taken
      default:                                                       // If the AI is not set to any of the above (which cannot happen, but
                                                                     // java throws a compiler error if I don't do this):
        return -1;                                                   //  - Return -1
    }                                                                //
  }                                                                  //

  private int getRandom(int piecesLeft) {             // Get a random number of pieces
    int max = piecesLeft / 2;                         // Defines the maximum number of pieces taken to half of the ones available
    int randomReturn = (int)(Math.random() * max) +1; // Selects a random number between 1 and the maximum value
    return randomReturn;                              // Returns that value
  }                                                   //

  private int getEasy(int piecesLeft, ArrayList<Player> allPlayers) { // AI performs a strategy which we evaluated to be relatively easier
    int max = piecesLeft / 2;                                         // Defines the maximum number of pieces taken to half of the ones
                                                                      // available
    if(piecesLeft < 6) {                                              // ⎫
      return 1;                                                       // ⎪ If there are very few pieces left, take as little as possible
    }                                                                 // ⎭
    return max - (int)(Math.random() * (max/2));                      // Otherwise, take a random (but large) number of pieces
  }

  // The hard AI takes one piece when there are less than 8 pieces left and there are an odd number of players. If there are from 4-8 pieces and there is an even number of players it takes 2, and else it just takes a random number from 1 to half of the pieces on the field.
  private int getHard(int piecesLeft, ArrayList<Player> allPlayers) {  // AI performs a strategy which we evaluated to be relatively harder
    int max = piecesLeft / 2;                                          // Defines the maximum number of pieces taken to half of the ones 
                                                                       // available
    boolean oddNumPlayers = (allPlayers.size() % 2) == 1;              // boolean that stores if there is an odd number of players
    if(piecesLeft < 8 && oddNumPlayers) {                              // If there are few pieces and an odd number of players:
      return 1;                                                        //  - Take only one piece
    } else if(piecesLeft < 8 && !oddNumPlayers && !(piecesLeft < 4)) { // If there are few pieces and an even number of players and taking 
                                                                       // two pieces is legal in the current game state:
      return 2;                                                        //  - Take 2 pieces
    }                                                                  //
      return max - (int)((Math.random() * (max/2)) + 1);               // // Otherwise, take a random (but large) number of pieces 
  }
}