package timdevries.gamesuite350;

/**
 * TODO
 * Created by Matt on 2/26/2017.
 */

class TTTPresenter {


    TTTLogic tl;


    /**
     * Constructor fot a new game of TicTacToe
     */
    public TTTPresenter(char piece) {
        TTTLogic tl = new TTTLogic(piece);


    }

    /**
     * Accesses the game logic to play the game
     *
     * @param x the horizontal location of the desired placement spot
     * @param y the vertical location of the desired placement spot
     * @return the updated game board
     */
    public char[][] playGame(int x, int y) {

        if (tl.placePiece(x, y)) {
            return tl.getBoard();
        } else {
            char[][] full = null;
            return full;
        }


    }
}
