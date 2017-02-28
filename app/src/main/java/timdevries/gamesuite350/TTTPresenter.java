package timdevries.gamesuite350;

/**
 * The go between class for the view and logic.
 * Created by Matt on 2/26/2017.
 */

class TTTPresenter {

    /**
     * The game logic object.
     */
    private TTTLogic tl;


    /**
     * Constructor fot a new game of TicTacToe.
     */
    TTTPresenter(final char piece) {
        tl = new TTTLogic(piece);
    }

    /**
     * Accesses the game logic to play the game.
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

    boolean isGameOver() {
        return tl.isGameOver();
    }

    boolean hasPlayerWon() {
        return tl.hasWon();
    }

    /**
     * @return returns the game board
     */
    public char[][] getBoard() {
        return tl.getBoard();
    }
}
