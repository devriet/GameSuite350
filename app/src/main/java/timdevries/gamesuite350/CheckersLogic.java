package timdevries.gamesuite350;

/**
 * Created by Matt Johnson on 4/14/2017.
 */

public class CheckersLogic {

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
     */
    CheckersLogic() {
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
                    board[i][k] = new BoardSquare();
                    isWhite = false;
                } else {
                    board[i][k] = new BoardSquare();
                    isWhite = true;
                }
            }
        }

        //add the red pieces to the board top
        int c = 3;
        for (int p = 0; p < c; p++) {
            for (int l = 0; l < boardSize; l++) {
                if ((p + l) % 2 == 0) {
                    board[p][l].setPiece(new CheckPiece(PieceColor.RED));
                }
            }
        }

        //add the black pieces to the board bottom
        int a = 7, b = 4;
        for (int p = a; p > b; p--) {
            for (int l = 0; l < boardSize; l++) {
                if ((p + l) % 2 == 0) {
                    board[p][l].setPiece(new CheckPiece(PieceColor.BLACK));
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

        if (checkDiagonalMove(x, y, cx, cy)) {

            if (board[cx][cy].getPiece().isKing()) {
                //can move any direction
                BoardSquare b;
                b = board[cx][cy];
                board[cx][cy] = new BoardSquare();
                board[x][y] = b;
                return true;
            } else {
                if (board[cx][cy].getPiece().getColor() == PieceColor.RED) {
                    //can only move down the board unless it is a king
                    if (cx > x) {
                        BoardSquare b;
                        b = board[cx][cy];
                        board[cx][cy] = new BoardSquare();
                        board[x][y] = b;
                        return true;
                    } else {
                        return false;
                    }
                } else if (board[cx][cy].getPiece().getColor()
                        == PieceColor.BLACK) {
                    //can only move up the board unless it is a king
                    if (cx < x) {
                        BoardSquare b;
                        b = board[cx][cy];
                        board[cx][cy] = new BoardSquare();
                        board[x][y] = b;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }

        return false;
    }

    /**
     * Checks if the space is diagonal.
     * @param x x val for where the piece is being moved
     * @param y y val for where the piece is being moved
     * @param cx x val for where the piece is currently
     * @param cy y val for where the piece is currently
     *
     * @return boolean if it is diagonal or not
     */
    private boolean checkDiagonalMove(final int x, final int y,
                                      final int cx, final int cy) {
            return ((x + y) % 2 == (cx + cy) % 2);
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
