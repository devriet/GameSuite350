package timdevries.gamesuite350;

/**
 * The logic for the checkers game.
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
    private static final int BOARD_SIZE = 8;

    /**
     * Boolean for if the game is over or not.
     */
    private boolean isGameOver;

    /**
     * The piece that won the game.
     */
    private PieceColor pieceThatWon;

    /**
     * Constructor for the Checkers Logic class.
     * Makes a new game;
     */
    CheckersLogic() {
        board = new BoardSquare[BOARD_SIZE][BOARD_SIZE];
        isGameOver = false;
        populateBoard();
    }

    /**
     * Populates the board with BoardSquares
     *  and puts pieces in the starting positions.
     */
    private void populateBoard() {
        boolean isWhite = true;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int k = 0; k < BOARD_SIZE; k++) {
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
        //3 is the number of rows that get pieces
        int c = 3;
        for (int p = 0; p < c; p++) {
            for (int l = 0; l < BOARD_SIZE; l++) {
                if ((p + l) % 2 == 0) {
                    board[p][l].setPiece(new CheckPiece(PieceColor.RED));
                }
            }
        }

        //add the black pieces to the board bottom
        //7 and 4 are the limits to the rows that get pieces
        int a = 7, b = 4;
        for (int p = a; p > b; p--) {
            for (int l = 0; l < BOARD_SIZE; l++) {
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
    public boolean move(final int x, final int y,
                      final int cx, final int cy) {

        boolean isJump;

        //no piece to move
        if (board[cx][cy].getPiece().getColor() == PieceColor.BLANK) {
            checkGameStatus();
            return false;
        }

        //no movement of piece
        if ((cx == x) && (cy == y)) {
            checkGameStatus();
            return false;
        }

        //move to an occupied space
        if (board[x][y].getPiece().getColor() != PieceColor.BLANK) {
            checkGameStatus();
            return false;
        }

        //place is taken

        //checks to make sure the move is diagonal
        if (checkDiagonalMove(x, y, cx, cy)) {
            isJump = ((cx - x) == 2 || (cx - x) == -2);
            if (isJump) {
                return moveJump(x, y, cx, cy);
            }

            if (board[cx][cy].getPiece().isKing()) {
                //can move any direction
                BoardSquare b;
                b = board[cx][cy];
                board[cx][cy] = new BoardSquare(
                        new CheckPiece(PieceColor.BLANK));
                board[x][y] = b;
                checkGameStatus();
                makeKings(x, y);
                return true;
            } else {
                if (board[cx][cy].getPiece().getColor() == PieceColor.RED) {
                    //can only move down the board unless it is a king
                    if (cx < x) {
                        BoardSquare b;
                        b = board[cx][cy];
                        board[cx][cy] = new BoardSquare(
                                new CheckPiece(PieceColor.BLANK));
                        board[x][y] = b;
                        checkGameStatus();
                        makeKings(x, y);
                        return true;
                    } else {
                        return false;
                    }
                } else if (board[cx][cy].getPiece().getColor()
                        == PieceColor.BLACK) {
                    //can only move up the board unless it is a king
                    if (cx > x) {
                        BoardSquare b;
                        b = board[cx][cy];
                        board[cx][cy] = new BoardSquare(
                                new CheckPiece(PieceColor.BLANK));
                        board[x][y] = b;
                        checkGameStatus();
                        makeKings(x, y);
                        return true;
                    } else {
                        checkGameStatus();
                        return false;
                    }
                }
            }
        } else {
            checkGameStatus();
            return false;
        }
        checkGameStatus();
        return false;
    }

    /**
     * For a jump.
     *
     * @param x row of the piece being moved
     * @param y col of the piece being moved
     * @param cx row of where the piece is going
     * @param cy col of where the piece is going
     *
     * @return boolean for if the move was completed.
     */
    private boolean moveJump(final int x, final int y,
                             final int cx, final int cy) {

        int jumpedRow = (cx + x) / 2;
        int jumpedCol = (cy + y) / 2;

        if (board[jumpedRow][jumpedCol] != null) {
            board[jumpedRow][jumpedCol] = new BoardSquare();

            BoardSquare b;
            b = board[cx][cy];
            board[cx][cy] = new BoardSquare(new CheckPiece(PieceColor.BLANK));
            board[x][y] = b;
            checkGameStatus();
            makeKings(x, y);
            return true;
        }
        checkGameStatus();
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
     * Checks the game status.
     * Assigns a value to isGameOver and Piece that won.
     */
    private void checkGameStatus() {
        int redsOnBoard = 0;
        int blacksOnBoard = 0;
        for (BoardSquare[] boardSquares: board) {
            for (BoardSquare b: boardSquares) {
                if (b.getPiece().getColor() == PieceColor.BLACK) {
                    blacksOnBoard++;
                } else if (b.getPiece().getColor() == PieceColor.RED) {
                    redsOnBoard++;
                }
            }
        }
        if (redsOnBoard > 0 && blacksOnBoard == 0) {
            isGameOver = true;
            pieceThatWon = PieceColor.RED;
        } else if (blacksOnBoard > 0 && redsOnBoard == 0) {
            isGameOver = true;
            pieceThatWon = PieceColor.BLACK;
        } else {
            isGameOver = false;
        }
    }


    /**
     * Prints the board based on piece.
     * Not useful...
     */
    protected void printBoard() {
        for (BoardSquare[] b : board) {
            for (BoardSquare bs : b) {
                System.out.print(bs.getPiece().getColor().toString() + " ");
            }
            System.out.print("\n");
        }
    }

    /**
     * Checks for and makes the moved piece a king if it made
     * it across the board.
     * @param x row of the piece
     * @param y col of the piece
     */
    private void makeKings(final int x, final int y) {
        if (x == 0) {
            if (!board[x][y].getPiece().isKing()
                    && board[x][y].getPiece().getColor() == PieceColor.BLACK) {
                board[x][y].getPiece().setKing(true);
            }
        } else if (x == (BOARD_SIZE - 1)) {
            if (!board[x][y].getPiece().isKing()
                    && board[x][y].getPiece().getColor() == PieceColor.RED) {
                board[x][y].getPiece().setKing(true);
            }

        }
    }

    /**
     * Gets the Board.
     *
     * @return BoardSquare[][] board.
     */
    public BoardSquare[][] getBoard() {
        return board.clone();
    }

    /**
     * Gets if the game is over.
     *
     * @return boolean isGameOver;
     */
    public boolean getGameOver() {
        return isGameOver;
    }

    /**
     * Gets the piece that won.
     * @return the color of the piece that won
     */
    public PieceColor getPieceThatWon() {
        return pieceThatWon;
    }

    /**
     * the main method.
     * @param args the arguments
     */
    public static void main(final String[] args) {
        CheckersLogic cl = new CheckersLogic();
        cl.printBoard();
    }
}
