package timdevries.gamesuite350;

/**
 * Created by robot on 4/17/2017.
 */

public class CheckPiece {
    /**
     * The color of the piece.
     */
    private String pieceColor;

    /**
     * Boolean for king or not.
     */
    private boolean isKing;

    /**
     * Gets the color of the piece.
     * @return string pieceColor
     */
    public String getColor() {
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
    public void setPieceColor(final String p) {
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
