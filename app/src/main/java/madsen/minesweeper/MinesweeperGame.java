package madsen.minesweeper;

/**
 * A Minesweeper game that functions similarly to the original game with a few
 * variations where appropriate.
 *
 * Created by Troy Madsen on 1/25/2017.
 * @version 1.0
 */

public class MinesweeperGame {
    /**
     * Minimum allowable board height.
     */
    private static final int MIN_BOARD_HEIGHT = 3;

    /**
     * Maximum allowable board height.
     */
    private static final int MAX_BOARD_HEIGHT = 10;

    /**
     * Default board height.
     */
    private static final int DEFAULT_BOARD_HEIGHT = 5;

    /**
     * Minimum allowable board width.
     */
    private static final int MIN_BOARD_WIDTH = 3;

    /**
     * Maximum allowable board width.
     */
    private static final int MAX_BOARD_WIDTH = 10;

    /**
     * Default board width.
     */
    private static final int DEFAULT_BOARD_WIDTH = 5;

    /**
     * Minimum number of bombs.
     */
    private static final int MIN_BOMB_COUNT = 1;

    /**
     * Default number of bombs.
     */
    private static final int DEFAULT_BOMB_COUNT = 12;

    /**
     * Allows for the modification of the max number of bombs.
     */
    private static final double MAX_BOMB_MULTIPLIER = (2.0 / 3.0);

    /**
     * The height of this MinesweeperGUI board.
     */
    private int boardHeight;

    /**
     * The width of this MinesweeperGUI board.
     */
    private int boardWidth;

    /**
     * An array of cells that make up the game board.
     */
    private Cell[][] board;

    /**
     * The status of the game.
     */
    private GameStatus status;

    /**
     * The number of bombs located on the game board.
     */
    private int numBombs;

    /**
     * The number of cells flagged on the game board.
     */
    private int numFlagged;

    /**
     * The default constructor for a MinesweeperGame game. Create a board with
     * board dimensions of 5.
     */
    public MinesweeperGame() {
        this(DEFAULT_BOARD_HEIGHT, DEFAULT_BOARD_WIDTH, DEFAULT_BOMB_COUNT);
    }

    /**
     * Creates a MinesweeperGame game with provided board dimensions.
     *
     * @param pBoardHeight Height of the board between 3 and 10 inclusive.
     * @param pBoardWidth Width of the board between 3 and 10 inclusive.
     * @param pNumBombs Number of bombs to place on game board.
     */
    public MinesweeperGame(final int pBoardHeight, final int pBoardWidth,
                           final int pNumBombs) {
        //Checking for valid board height
        if (MIN_BOARD_HEIGHT <= pBoardHeight
                && pBoardHeight <= MAX_BOARD_HEIGHT) {
            this.boardHeight = pBoardHeight;
        } else {
            this.boardHeight = DEFAULT_BOARD_HEIGHT;
        }

        //Checking for valid board width
        if (MIN_BOARD_WIDTH <= pBoardWidth && pBoardWidth <= MAX_BOARD_WIDTH) {
            this.boardWidth = pBoardWidth;
        } else {
            this.boardWidth = DEFAULT_BOARD_WIDTH;
        }

        if (MIN_BOMB_COUNT <= pNumBombs && pNumBombs <= calcMaxBombs()) {
            this.numBombs = pNumBombs;
        } else {
            this.numBombs = calcNumBombs();
        }

        initializeBoard(boardHeight, boardWidth);
        placeBombs(numBombs);
        numFlagged = 0;

        status = GameStatus.RUNNING;
    }

    /**
     * A helper method to determine max number of bombs on the current board.
     *
     * @return The maximum number of bombs on the board.
     */
    private int calcMaxBombs() {
        return (int) Math.floor(boardHeight * boardWidth * MAX_BOMB_MULTIPLIER);
    }

    /**
     * A helper method to determine default number of bombs on the current
     * board.
     *
     * @return Number of allowed bombs
     */
    private int calcNumBombs() {
        return (int) Math.sqrt(boardHeight * boardWidth);
    }

    /**
     * Provides the current height of the game board.
     *
     * @return Height of the board
     */
    public int getBoardHeight() {
        return boardHeight;
    }

    /**
     * Provides the current width of the game board.
     *
     * @return Width of the board
     */
    public int getBoardWidth() {
        return boardWidth;
    }

    /**
     * Provides the number of bombs on the board.
     *
     * @return Number of bombs on the board.
     */
    public int getNumBombs() {
        return numBombs;
    }

    /**
     * Provides the number of cells on the board that are flagged.
     *
     * @return Number of flagged cells.
     */
    public int getNumFlagged() {
        return numFlagged;
    }

    /**
     * An accessor method that allows the caller to get a cell from
     * the game board.
     *
     * @param y The row of the cell requested.
     * @param x The column of the cell requested.
     * @return The cell requested or null if improper cell accessed.
     */
    public Cell getCell(final int y, final int x) {
        Cell c = null;

        if (0 <= x && x < boardWidth && 0 <= y && y < boardHeight) {
            c = board[y][x];
        }

        return c;
    }

    /**
     * A helper method to initialize the gam board with empty cells.
     *
     * @param height Height of the game board.
     * @param width Width of the game board.
     */
    private void initializeBoard(final int height, final int width) {
        board = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y][x] = new Cell();
            }
        }
    }

    /**
     * A helper method to place bombs on the board.
     *
     * @param bombs The number of bombs to place.
     */
    private void placeBombs(final int bombs) {
        java.util.Random r = new java.util.Random();

        int numPlaced = 0;
        int y;
        int x;
        while (numPlaced < bombs) {
            y = r.nextInt(boardHeight);
            x = r.nextInt(boardWidth);

            if (!getCell(y, x).isBomb()) {
                getCell(y, x).setBomb(true);
                numPlaced++;
            }
        }

        setBombCounts();
    }

    /**
     * Iterates across all cells in the game board and sets the surrounding
     * mine count of each cell to the appropriate value.
     */
    private void setBombCounts() {
        int neighborCount;

        // Iterating through all board cells
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                neighborCount = 0;

                // Iterating through surrounding cells and counting number
                // that are bombs.
                for (int dY = y - 1; dY <= y + 1; dY++) {
                    for (int dX = x - 1; dX <= x + 1; dX++) {
                        // Skip if accessing outside the board.
                        if (0 > dX || dX >= boardWidth
                                || 0 > dY || dY >= boardHeight) {
                            continue;
                        }

                        // Incrementing neighbor count of bombs
                        if (getCell(dY, dX).isBomb()) {
                            neighborCount++;
                        }
                    }
                }

                // Setting surrounding bombs of the cell
                getCell(y, x).setSurroundingBombs(neighborCount);
            }
        }
    }

    /**
     * Sets the state of the requested cell to the provided flag state.
     *
     * @param y The row of the cell to toggle.
     * @param x The column of the cell to toggle.
     * @param flagged Whether the cell is flagged.
     * @return Whether the cell was successfully updated.
     */
    public boolean setFlagged(final int y, final int x, final boolean flagged) {
        Cell c = getCell(y, x);
        boolean updated = false;

        if (c != null && !c.isRevealed() && !isGameOver()) {
            c.setFlagged(flagged);

            if (flagged) {
                numFlagged++;
            } else {
                numFlagged--;
            }

            updated = true;
        }

        setStatus();

        return updated;
    }

    /**
     * Reveals the cell indicated by its x and y terms. Will only reveal cells
     * that are not flagged and not already revealed.
     *
     * @param y The row of the cell to reveal.
     * @param x The column of the cell to reveal.
     * @return If a cell was successfully revealed.
     */
    public boolean revealCell(final int y, final int x) {
        // Checks that cell being accessed exists on the board and isn't flagged
        // or already revealed
        Cell c = getCell(y, x);

        if (c == null || c.isFlagged() || c.isRevealed()
                || !(status == GameStatus.RUNNING)) {
            return false;
        } else {
            c.setRevealed(true);
            setFlagged(y, x, false);

            if (getCell(y, x).getSurroundingBombs() == 0) {
                revealSurroundingCells(x, y);
            }

            //Check for an update to the game status
            if (c.isBomb()) {
                //Checking if the game was lost
                status = GameStatus.LOSE;
                //Reveal the game board
                revealBoard();
            } else {
                setStatus();
            }

            return true;
        }
    }

    /**
     * Starting from a central value, it iterates through surrounding cells and
     * reveals them as well until there is no adjacent empty cells remaining.
     *
     * @param x The column of the starting cell.
     * @param y The row of the starting cell.
     */
    private void revealSurroundingCells(final int x, final int y) {
        for (int dY = y - 1; dY <= y + 1; dY++) {
            for (int dX = x - 1; dX <= x + 1; dX++) {
                // Checking that the empty cell expansion does not access
                // outside of board or already revealed cells.
                if (getCell(dY, dX) == null
                        || getCell(dY, dX).isRevealed()) {
                    continue;
                }

                revealCell(dY, dX);
            }
        }
    }

    /**
     * A helper method to reveal the entire game board.
     */
    private void revealBoard() {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                getCell(y, x).setRevealed(true);
            }
        }
    }

    /**
     * A helper method to set the status of the game after a move.
     */
    private void setStatus() {
        //Checking if the game was won
        if (numFlagged == numBombs) {
            status = GameStatus.WIN;

            Cell c;
            for (int pY = 0; pY < boardHeight; pY++) {
                for (int pX = 0; pX < boardWidth; pX++) {
                    c = getCell(pY, pX);
                    if (!c.isRevealed() && !c.isFlagged()) {
                        //Not all cells are revealed and game should
                        //continue.
                        status = GameStatus.RUNNING;
                        break;
                    }
                }

                if (status == GameStatus.RUNNING) {
                    break;
                }
            }
        }
    }

    /**
     * Determines if the game is over.
     *
     * @return Whether the game is over
     */
    public boolean isGameOver() {
        return !(status == GameStatus.RUNNING);
    }

    /**
     * Determines if the game was won or lost.
     *
     * @return True if won, false if lost
     */
    public boolean isWin() {
        return status == GameStatus.WIN;
    }
}
