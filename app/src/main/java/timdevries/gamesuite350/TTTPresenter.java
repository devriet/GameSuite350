package timdevries.gamesuite350;

/**
 * The go between class for the view and logic.
 * Created by Matt on 2/26/2017.
 */

class TTTPresenter {


    TTTLogic tl;


    /**
     * Constructor fot a new game of TicTacToe
     */
    public TTTPresenter(char piece) {
        tl = new TTTLogic(piece);
    }

    /**
     * Accesses the game logic to play the game
     *
     * @param x the horizontal location of the desired placement spot
     * @param y the vertical location of the desired placement spot
     * @return the updated game board
     */
    public boolean playGame(int x, int y) {

        if (!(tl.placePiece(x, y))) {
            if (tl.hasWon()) {
                return true;
            }
        } else {
            return false;
        }

        return false;
    }

    /**
     * @return returns the game board
     */
    public char[][] getBoard() {
        return tl.getBoard();
    }
}
