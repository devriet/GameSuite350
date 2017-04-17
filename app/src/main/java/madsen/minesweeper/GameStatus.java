package madsen.minesweeper;

/**
 * An enumeration of the multiple states that the game may be in.
 *
 * Created by Troy Madsen on 1/26/2017.
 */

enum GameStatus {
    /**
     * Indicates that the game has been won.
     */
    WIN,

    /**
     * Indicates that the game has been lost.
     */
    LOSE,

    /**
     * Indicates that the game is currently running.
     */
    RUNNING
}
