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
     *
     * @param piece the player's chosen piece
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
    public boolean playGame(final int x, final int y) {

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
     * Return if the game over.
     *
     * @return true if the game is over
     */
    boolean isGameOver() {
        return tl.isGameOver();
    }

    /**
     * Returns if the player has won or not.
     * @return true for player win
     */
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
