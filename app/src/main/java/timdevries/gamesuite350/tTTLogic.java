package timdevries.gamesuite350;

/**
 * Created by Matt on 1/20/2017.
 */

public class TTTLogic {

    /**
     * The player character, either an 'X' or 'O'.
     */
    private char playerChar;
    /**
     * The board size for a normal game of tic tac toe.
     *  A tic tac toe board is a 3 by 3 grid.
     */
    private final int boardSize = 3;
    /**
     * A boolean that shows if the board is full (true) or not full (false).
     */
    private boolean boardFull;
    /**
     * A boolean that shows if the player has won the game.
     * This is only true if the player has three of their chars in a row.
     */
    private boolean haveWon;
    /**
     * This is the board and since board size never changes it can be
     * initialized here.
     */
    private char[][] board = new char[boardSize][boardSize];


    /**
     * The constructor for the tictactoe game.
     * The player's chosen character is set and the
     * gameover checks are set to false.
     * The board is filled with spaces to signal places that can be filled.
     * pC is the players chosen character @param pC
     */
    public TTTLogic(final char pC) {
        playerChar = pC;
        boardFull = false;
        haveWon = false;

        for (int i = 0; i < boardSize; i++) {
            for (int k = 0; k < boardSize; k++) {
                board[i][k] = ' ';
            }
        }
    }

    /**
     * This method is for the placing of a piece on tic tac toe board.
     * It checks if the selected space is open for a piece and
     * then places it at that place if it is open.
     * x is the horizontal coordinate of the space
     * where the player wants to place their piece @param x
     * y is the vertical coordinate of the space
     * where the player want to place their piece @param y
     * returns whether or not the piece was placed @return
     */
    public boolean placePiece(final int x, final int y) {

        if (board[x][y] == ' ') {
            board[x][y] = playerChar;
            isGameOver();
            return true;
        }

        return false;
    }

    /**
     * A method that check if the game is over.
     * It calls a helper method to determine if there
     * are three of the important chars in a row
     * return true if game is over otherwise return false @return
     */
    private boolean isGameOver() {

        for (char[] c: board) {
            for (char k: c) {
                if (k == ' ') {
                    boardFull = false;
                }
            }
        }

        if (threeInRow() || boardFull) {
            return true;
        }

        return false;
    }

    /**
     *
     * @return
     */
    private boolean threeInRow() {

        return false;
    }

}
