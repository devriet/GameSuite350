package madsen.connectfour;

/**
 * A Connect Four game using the standard dimensions and win conditions.
 *
 * Created by Troy Madsen on 4/16/2017.
 * @version 1.0
 */

public class ConnectFourGame {
    /**
     * The height of the game board.
     */
    public static final int HEIGHT = 6;

    /**
     * The width of the game board.
     */
    public static final int WIDTH = 7;

    /**
     * The length of run needed to win the game.
     */
    public static final int LENGTH = 4;

    /**
     * The game board containing all Chips placed.
     */
    private int[][] board;

    /**
     * The current player.
     */
    private int player;

    /**
     * The running state of the game.
     */
    private boolean isRunning;

    /**
     * The win state of the game.
     */
    private boolean isWin;

    /**
     * Creates a ConnectFourGame object.
     */
    public ConnectFourGame() {
        board = new int[HEIGHT][WIDTH];
        player = 1;
        isRunning = true;
        isWin = false;
    }

    /**
     * Provides the current player.
     *
     * @return A String representation of the current player.
     */
    public int getPlayer() {
        return player;
    }

    /**
     * Provides a representation of the game board with the chips marked as
     * 1 and 2 for player number.
     *
     * @return A board representation with 1 for black and 2 for red.
     */
    public int[][] getBoard() {
        final int[][] b = board;
        return b;
    }

    /**
     * Provides the running state of the game.
     *
     * @return Whether the game is over.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Provides the win state of the game.
     *
     * @return Whether the game is won.
     */
    public boolean isWin() {
        return isWin;
    }

    /**
     * Attempt to place a tile in the specified column of the game board.
     * Will fail if the game is over or chosen column is full.
     *
     * @param column The column the tile is placed in.
     * @return Whether the tile was placed or not.
     */
    public boolean placeTile(final int column) {
        // If game isn't isRunning, column is out of bounds or column is full
        // reject play
        if (!isRunning || 0 > column || column >= WIDTH
                || board[0][column] != 0) {
            return false;
        }

        // Place tile and return true
        for (int i = HEIGHT - 1; i >= 0; i--) {
            if (board[i][column] == 0) {
                board[i][column] = player;

                // Check if there are no plays remaining
                isRunning = false;
                for (int x = 0; x < WIDTH; x++) {
                    if (board[0][x] == 0) {
                        isRunning = true;
                    }
                }

                // Check if the last play won the game
                checkWin(column, i);

                break;
            }
        }

        // Change player
        if (isRunning) {
            if (player == 1) {
                player = 2;
            } else {
                player = 1;
            }
        }

        return true;
    }

    /**
     * A helper method to determine if the game was won by the last play.
     *
     * @param col Column tile was placed in.
     * @param row Row tile landed in.
     */
    private void checkWin(final int col, final int row) {
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                // Skip check if outside board or on self
                if ((dy == 0 && dx == 0) || 0 > row + dy || row + dy >= HEIGHT
                        || 0 > col + dx || col + dx >= WIDTH) {
                    continue;
                }

                if (board[row + dy][col + dx] == player
                        && buildRun(col, row, dx, dy) >= LENGTH) {
                    System.out.println("OVER");
                    isRunning = false;
                    isWin = true;
                    break;
                }
            }
            if (!isRunning) {
                break;
            }
        }
    }

    /**
     * Constructs a run of tiles starting from the location of the tile just
     * placed on the game board.
     *
     * @param col Column tile was placed in.
     * @param row Row tile landed in.
     * @param dCol Change in the column.
     * @param dRow Change in the row.
     * @return The length of the run generated.
     */
    private int buildRun(final int col, final int row, final int dCol,
                         final int dRow) {
        int x = col;
        int y = row;
        int length = 1;

        // Trace to the end of a run first
        while (0 <= y + dRow && y + dRow < HEIGHT && 0 <= x + dCol
                && x + dCol < WIDTH && board[y + dRow][x + dCol] == player) {
            x += dCol;
            y += dRow;
        }

        // Trace back across the run to get full length
        while (0 <= y - dRow && y - dRow < HEIGHT && 0 <= x - dCol
                && x - dCol < WIDTH && board[y - dRow][x - dCol] == player) {
            x -= dCol;
            y -= dRow;
            length++;
        }

        System.out.println(length);
        return length;
    }

//    /**
//     * The main entry point for testing ConnectFourGame from the terminal.
//     *
//     * @param args Command line arguments
//     */
//    public static void main(final String[] args) {
//        ConnectFourGame c = new ConnectFourGame();
//
//        java.util.Scanner s = new java.util.Scanner(System.in);
//        while (c.isRunning()) {
//            int[][] b = c.getBoard();
//            for (int y = 0; y < ConnectFourGame.HEIGHT; y++) {
//                for (int x = 0; x < ConnectFourGame.WIDTH; x++) {
//                    System.out.print(b[y][x]);
//                }
//                System.out.println();
//            }
//            System.out.print("Player " + c.getPlayer() + " enter col: ");
//            if (!c.placeTile(s.nextInt())) {
//                System.out.println("Bad");
//            }
//        }
//
//        int[][] b = c.getBoard();
//        for (int y = 0; y < ConnectFourGame.HEIGHT; y++) {
//            for (int x = 0; x < ConnectFourGame.WIDTH; x++) {
//                System.out.print(b[y][x]);
//            }
//            System.out.println();
//        }
//
//        if (c.isWin()) {
//            System.out.println("Player " + c.getPlayer() + " wins!");
//        } else {
//            System.out.println("No moves remain!");
//        }
//
//    }
}
