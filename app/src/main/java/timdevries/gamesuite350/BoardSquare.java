package timdevries.gamesuite350;

/**
 * A helper class for checkers logic. It depicts a square on the
 *  checkers board holding a piece or no piece.
 * Created by Matt Johnson on 4/16/2017.
 */

class BoardSquare {

    /**
     * The board square piece.
     */
    private CheckPiece piece;

    /**
     * Initializes a board square with the passed color and piece.
     * @param checkPiece the piece object on this square
     */
    BoardSquare(final CheckPiece checkPiece) {
        piece = checkPiece;
    }

    /**
     * Initializes the board square with just a color.
     */
    BoardSquare() {
        piece = new CheckPiece(PieceColor.BLANK);
    }

    /**
     * Gets the piece on the board square.
     * @return The piece that this board square holds
     */
    public CheckPiece getPiece() {
        return piece;
    }

    /**
     * Sets the piece that is on this board square.
     * @param p the piece that is in this board square
     */
    public void setPiece(final CheckPiece p) {
        piece = p;
    }
}
