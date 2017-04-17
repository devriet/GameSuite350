package timdevries.gamesuite350;

/**
 * A helper class for checkers logic. It depicts a square on the
 *  checkers board holding a color and a piece
 * Created by Matt Johnson on 4/16/2017.
 */

class BoardSquare {

    /**
     * The board square color.
     */
    private String color;

    /**
     * The board square piece.
     */
    private CheckPiece piece;

    /**
     * Initializes a board square with the passed color and piece.
     * @param c the color of the board square
     * @param checkPiece the piece object on this square
     */
    BoardSquare(final String c, final CheckPiece checkPiece) {
        setColor(c);
        piece = checkPiece;
    }

    /**
     * Initializes the board square with just a color.
     * @param c The board square color.
     */
    BoardSquare(final String c) {
        setColor(c);
        piece = null;
    }

    /**
     * Gets the color of the board square.
     * @return The string value for the color of the board Square.
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the piece on the board square.
     * @return The piece that this board square holds
     */
    public CheckPiece getPiece() {
        return piece;
    }

    /**
     * Sets the color of the board square.
     * @param c The color of the board square
     */
    public void setColor(final String c) {
        color = c;
    }

    /**
     * Sets the piece that is on this board square.
     * @param p the piece that is in this board square
     */
    public void setPiece(final CheckPiece p) { piece = p; }
}
