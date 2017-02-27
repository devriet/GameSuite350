package timdevries.gamesuite350;

import java.util.Random;

/**
 * Created by Matt Johnson on 1/20/2017.
 * @author Matt Johnson
 */

public class TTTLogic {

    /**
     * The player character, either an 'X' or 'O'.
     */
    private char playerChar;
    /**
     * The computers character.
     */
    private char compChar;
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
     * The constructor for the Tictactoe game.
     * The player's chosen character is set and the
     * gameover checks are set to false.
     * The board is filled with spaces to signal places that can be filled.
     * @param pc is the players chosen character
     */
    public TTTLogic(final char pc) {
        playerChar = pc;
        boardFull = false;
        haveWon = false;

        if (playerChar == 'X' || playerChar == 'x') {
            compChar = 'O';
            playerChar = 'X';
        } else {
            compChar = 'X';
            playerChar = 'O';
        }

        for (int i = 0; i < boardSize; i++) {
            for (int k = 0; k < boardSize; k++) {
                board[i][k] = '*';
            }
        }
    }

    /**
     * This method is for the placing of a piece on tic tac toe board.
     * It checks if the selected space is open for a piece and
     * then places it at that place if it is open.
     * @param x is the horizontal coordinate of the space
     * where the player wants to place their piece
     * @param y is the vertical coordinate of the space
     * where the player want to place their piece
     * @return if the game is over
     */
    public boolean placePiece(final int x, final int y) {

        if (board[x][y] == '*') {
            board[x][y] = playerChar;
        }

        placeCompPiece();

        return isGameOver();
    }

    /**
     * The computer's turn.
     */
    private void placeCompPiece() {
        Random r = new Random();
        Random a = new Random();

        int size = 3;

        int x = r.nextInt(size);
        int y = a.nextInt(size);
        if (board[x][y] == '*') {
            board[x][y] = playerChar;
        }
    }

    /**
     * A method that check if the game is over.
     * It calls a helper method to determine if there
     * are three of the important chars in a row
     * @return true if game is over otherwise return false
     */
    private boolean isGameOver() {

        boardFull = true;
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
     * This method returns false if there aren't three in a row.
     * If there are it returns true
     * @return if there are three in a row
     */
    private boolean threeInRow() {

        //Player wins with three in a row:
        // for every row see if the chars are the same all the
        // way across
        for (int i = 0; i < boardSize; i++) {
                if (board[i][0] == playerChar && board[i][1] == playerChar
                        && board[i][2] == playerChar) {
                    haveWon = true;
                    return true;
                }
        }

        //vertical three in a row/column
        for (int i = 0; i < boardSize; i++) {
            if (board[0][i] == playerChar && board[1][i] == playerChar
                    && board[2][i] == playerChar) {
                haveWon = true;
                return true;
            }
        }

        //Computer wins with three in a row:
        // for every row see if the chars are the same all the
        // way across
        for (int i = 0; i < boardSize; i++) {
            if (board[i][0] == playerChar && board[i][1] == playerChar
                    && board[i][2] == playerChar) {
                haveWon = true;
                return true;
            }
        }

        //vertical three in a row/column
        for (int i = 0; i < boardSize; i++) {
            if (board[0][i] == playerChar && board[1][i] == playerChar
                    && board[2][i] == playerChar) {
                haveWon = true;
                return true;
            }
        }
        // no three in a row
        return false;
    }

    /**
     * @return the player's chosen piece.
     */
    public char getPlayerChar() {
        return playerChar;
    }

    /**
     *  @return the board.
     */
    public char[][] getBoard()   {
        char[][] cboard;
        cboard = board;
        return cboard;
    }

    /**
     * @return the player win or not
     */
    protected boolean hasWon() {
        return haveWon;
    }
}
