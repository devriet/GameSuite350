package timdevries.gamesuite350;

import org.junit.Test;

import madsen.minesweeper.MinesweeperGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A suite of tests designed to ensure proper functionality for the
 * MinesweeperGame class.
 *
 * @author Troy Madsen
 */
public class MinesweeperUnitTests {
    private final static int BOARD_HEIGHT = 4;
    private final static int BOARD_WIDTH = 3;
    private final static int BOMB_COUNT = 8;

    @Test
    public void checkBoardHeight() {
        MinesweeperGame game
                = new MinesweeperGame(BOARD_HEIGHT, BOARD_WIDTH, BOMB_COUNT);
        assertEquals(BOARD_HEIGHT, game.getBoardHeight());
    }

    @Test
    public void checkBoardWidth() {
        MinesweeperGame game
                = new MinesweeperGame(BOARD_HEIGHT, BOARD_WIDTH, BOMB_COUNT);
        assertEquals(BOARD_WIDTH, game.getBoardWidth());
    }

    @Test (expected = NullPointerException.class)
    public void testOutsideBoard() {
        MinesweeperGame game
                = new MinesweeperGame(BOARD_HEIGHT, BOARD_WIDTH, BOMB_COUNT);
        game.getCell(-1, 0).isRevealed();
    }

    @Test
    public void checkBombCount() {
        MinesweeperGame game
                = new MinesweeperGame(BOARD_HEIGHT, BOARD_WIDTH, BOMB_COUNT);
        assertEquals(BOMB_COUNT, game.getNumBombs());
    }

    @Test
    public void checkBombLayout() {
        MinesweeperGame game
                = new MinesweeperGame(BOARD_HEIGHT, BOARD_WIDTH, BOMB_COUNT);
        int h = game.getBoardHeight();
        int w = game.getBoardWidth();
        int b = game.getNumBombs();

        int count = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (game.getCell(y, x).isBomb()) {
                    count++;
                }
            }
        }

        assertEquals(b, count);
    }

    @Test
    public void testDefaultConstructor() {
        MinesweeperGame game = new MinesweeperGame();

        boolean failed = (game.getBoardHeight() == 5)
                && (game.getBoardWidth() == 5) && (game.getNumBombs() == 12);

        assertTrue(failed);
    }

    @Test
    public void testFlagging() {
        MinesweeperGame game
                = new MinesweeperGame(BOARD_HEIGHT, BOARD_WIDTH, BOMB_COUNT);
        game.setFlagged(0, 0, true);
        assertTrue(game.getCell(0, 0).isFlagged());
    }

    @Test
    public void testFlagCount() {
        MinesweeperGame game
                = new MinesweeperGame(BOARD_HEIGHT, BOARD_WIDTH, BOMB_COUNT);
        game.setFlagged(0, 0, true);
        game.setFlagged(0, 1, true);
        game.setFlagged(0, 0, false);
        assertEquals(1, game.getNumFlagged());
    }

    @Test
    public void testRevealing() {
        MinesweeperGame game
                = new MinesweeperGame(BOARD_HEIGHT, BOARD_WIDTH, BOMB_COUNT);
        game.revealCell(0, 0);
        assertTrue(game.getCell(0, 0).isRevealed());
    }


}
