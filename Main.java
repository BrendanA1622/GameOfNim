/* AP CSA 1 Game of Nim Project
 * - By Brendan Aeria, Reza Bagheri, and Marshall Hamon
 * - 2/7/22
 * 
 * Description: The Game of Nim is a classic game in which two (or in this case more) players take pieces off the board and try to not be the last person to take a piece off the board.
 * Preconditions: The board is set and the amount of pieces is determined. The artificial intelligence algorithms are ready to start taking away game pieces.
 * Postconditions: Players strategically take away pieces and try to line up the game so that either the player or the ai they are playing takes the last piece. The score and winners are also printed to the screen.
*/

class Main {
  public static void main(String[] args) {
    Game game = new Game();
    game.play();
  }
}