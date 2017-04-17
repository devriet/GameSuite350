package timdevries.gamesuite350;

/**
 * Created by Matt Johnson on 4/14/2017.
 */

public class CheckersLogic {

    /**
     * The piece the player is using for the game.
     */
    private String playerPiece;

    /**
     * The computer's game piece.
     */
    private String computerPiece;

    /**
     * The board for the game of Draughts better known as Checkers.
     */
    private BoardSquare[][] board;
    /**
     * The size of the board sides.
     */
    private final int boardSize = 8;

    /**
     * Constructor for the Checkers Logic class.
     * @param piece The players chosen piece. It can be either "R" or "B".
     */
    CheckersLogic(final String piece) {
        playerPiece = piece;
        if (piece.equals("R")) {
            computerPiece = "B";
        } else if (piece.equals("B")) {
            computerPiece = "R";
        } else {
            playerPiece = "R";
            computerPiece = "B";
        }
        board = new BoardSquare[boardSize][boardSize];
        populateBoard();
    }

    /**
     * Makes the board and puts pieces in the starting positions.
     */
    private void populateBoard() {
        boolean isWhite = true;
        for (int i = 0; i < boardSize; i++) {
            for (int k = 0; k < boardSize; k++) {
                if (isWhite) {
                    board[i][k] = new BoardSquare("White");
                    isWhite = false;
                } else {
                    board[i][k] = new BoardSquare("Black");
                    isWhite = true;
                }
            }
        }

        //add the red pieces to the board
        int c = 3;
        for (int p = 0; p < c; p++) {
            for (int l = 0; l < boardSize; l++) {
                if (board[p][l].getColor().equals("Black")) {
                    board[p][l].setPiece("R");
                }
            }
        }

        //add the black pieces
        int a = 7, b = 4;
        for (int p = a; p > b; p--) {
            for (int l = 0; l < boardSize; l++) {
                if (board[p][l].getColor().equals("Black")) {
                    board[p][l].setPiece("B");
                }
            }
        }
    }

    /**
     * This method take a place on the board and moves
     *  the given piece type there.
     *
     * @param cx the current x value for the piece being moved
     * @param cy the current y value for the piece being moved
     * @param x the x value for where the piece is being moved to
     * @param y the y value for where the piece is being moved to
     *
     * @return The boolean value for if the piece moved or not.
     */
    private boolean move(final int x, final int y,
                      final int cx, final int cy) {

        if ((cx == x) && (cy == y)) {
            return false;
        }



        return false;
    }

    /**
     * Prints the board based on piece.
     */
    private void printBoard() {
        for (BoardSquare[] b : board) {
            for (BoardSquare bs : b) {
                System.out.print(bs.getPiece());
            }
            System.out.print("\n");
        }
    }

    /**
     * Gets the Board.
     *
     * @return the board.
     */
    public BoardSquare[][] getBoard() {
        return board;
    }
}
