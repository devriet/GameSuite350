package timdevries.gamesuite350;

/**
 * The checkers piece.
 * Created by robot on 4/17/2017.
 */

public class CheckPiece {
    /**
     * The color of the piece.
     */
    private Enum pieceColor;

    /**
     * Boolean for king or not.
     */
    private boolean isKing;

    /**
     * The constructor for the piece.
     * @param p the piece color
     */
    CheckPiece(final Enum p) {
        this.setPieceColor(p);
    }

    /**
     * Gets the color of the piece.
     * @return string pieceColor
     */
    public Enum getColor() {
        return pieceColor;
    }

    /**
     * Gets whether or not a piece is a king.
     * @return isKing
     */
    public boolean isKing() {
        return isKing;
    }

    /**
     * Sets the color of the piece.
     * @param p string of the color wanted
     */
    public void setPieceColor(final Enum p) {
        pieceColor = p;
    }

    /**
     * Sets whether the piece is a king or not.
     * @param b boolean if piece is king.
     */
    public void setKing(final boolean b) {
        isKing = b;
    }
}
