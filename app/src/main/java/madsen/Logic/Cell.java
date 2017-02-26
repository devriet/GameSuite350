package madsen.Logic;

/**
 * A representation of a cell located on the board of a MinesweeperGUI field.
 *
 * Created by Troy Madsen on 1/25/2017.
 */

public class Cell {
    /**
     * Minimum number of bombs that may surround this cell in the 8 cells around
     * this minesweeper1 and this cell.
     */
    private static final int MIN_SURROUNDING_BOMBS = 0;

    /**
     * Maximum number of bombs that may surround this cell in the 8 cells around
     * this minesweeper1 and this cell.
     */
    private static final int MAX_SURROUNDING_BOMBS = 9;

    /**
     * The number of bombs that surround this cell on the board.
     */
    private int surroundingBombs;

    /**
     * Indicates whether this cell is a bomb.
     */
    private boolean isBomb;

    /**
     * Indicates whether this cell has been flagged on the board.
     */
    private boolean isFlagged;

    /**
     * Indicates whether this cell has been exposed on the board.
     */
    private boolean isRevealed;

    /**
     * The default constructor of a cell object. It initializes all variables
     * to base values.
     */
    public Cell() {
        surroundingBombs = 0;
        isBomb = false;
        isFlagged = false;
        isRevealed = false;
    }

    /**
     * Provides the number of bombs located in the surrounding cells.
     *
     * @return Number of surrounding bombs.
     */
    public int getSurroundingBombs() {
        return surroundingBombs;
    }

    /**
     * Allows the number of surrounding bombs to be set to an appropriate value.
     *
     * @param pSurroundingBombs The number of bombs that are surrounding this
     *                          cell.
     * @return Whether surroundingBombs was set the the specified value.
     */
    public boolean setSurroundingBombs(final int pSurroundingBombs) {
        if (MIN_SURROUNDING_BOMBS <= pSurroundingBombs
                && pSurroundingBombs <= MAX_SURROUNDING_BOMBS) {
            this.surroundingBombs = pSurroundingBombs;
            return true;
        } else {
            this.surroundingBombs = MIN_SURROUNDING_BOMBS;
            return false;
        }
    }

    /**
     * Provides whether this cell is a bomb or not.
     *
     * @return Whether this cell is a bomb.
     */
    public boolean isBomb() {
        return isBomb;
    }

    /**
     * Attempts to set this cell as a bomb. May already be a bomb and will fail
     * if this is the case.
     *
     * @param pIsBomb The new bomb state of the cell.
     * @return Updated state of cell.
     */
    public boolean setBomb(final boolean pIsBomb) {
        isBomb = pIsBomb;
        return isBomb;
    }

    /**
     * Provides whether this cell has been flagged or not.
     *
     * @return Whether this cell is flagged.
     */
    public boolean isFlagged() {
        return isFlagged;
    }

    /**
     * Toggles the state of the isFlagged variable, providing the updated state.
     *
     * @param pIsFlagged The new flagged state of the cell.
     * @return Updated state of the cell.
     */
    public boolean setFlagged(final boolean pIsFlagged) {
        isFlagged = pIsFlagged;
        return isFlagged;
    }

    /**
     * Provides the current visibility state of the cell.
     *
     * @return Whether the cell is revealed or not.
     */
    public boolean isRevealed() {
        return isRevealed;
    }

    /**
     * Sets the revealed status of the cell to the specified status.
     *
     * @param pIsRevealed The new revealed state of the cell.
     * @return Updated state of the cell.
     */
    public boolean setRevealed(final boolean pIsRevealed) {
        isRevealed = pIsRevealed;
        return isRevealed;
    }
}
