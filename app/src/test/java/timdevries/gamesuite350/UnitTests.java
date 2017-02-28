package timdevries.gamesuite350;

import org.junit.Test;
import static org.junit.Assert.*;
import madsen.MinesweeperGame;

/**
 * A suite of tests designed to ensure proper functionality for the
 * MinesweperGame class.
 *
 * @author Troy Madsen
 */
public class UnitTests {
    private final static int BOARD_HEIGHT = 4;
    private final static int BOARD_WIDTH = 3;
    private final static int BOMB_COUNT = 8;

    @Test
    public void defaultParams() {
        MinesweeperGame game = new MinesweeperGame();
    }

    @Test
    public void underParams() {
        MinesweeperGame game = new MinesweeperGame(-12, -12, -12);
    }

    @Test
    public void overParams() {
        MinesweeperGame game = new MinesweeperGame(100, 100, 100000);
    }

    @Test
    public void revealSurroundingCells() {
        MinesweeperGame game = new MinesweeperGame();

        for (int y = 0; y < game.getBoardHeight(); y++) {
            for (int x = 0; x < game.getBoardWidth(); x++) {
                if (!game.getCell(y, x).isBomb()) {
                    game.revealCell(y, x);
                }
            }
        }
    }

    @Test
    public void setStatus() {
        MinesweeperGame game = new MinesweeperGame();

        for (int y = 0; y < game.getBoardHeight(); y++) {
            for (int x = 0; x < game.getBoardWidth(); x++) {
                if (!game.getCell(y, x).isBomb()) {
                    game.setFlagged(y, x, true);
                    game.setFlagged(y, x, false);
                }
            }
        }
    }

    @Test
    public void isGameOver() {
        MinesweeperGame game = new MinesweeperGame();
        assertEquals(game.isGameOver(), false);
    }

    @Test
    public void isWin() {
        MinesweeperGame game = new MinesweeperGame();
        assertEquals(game.isWin(), false);
    }

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



    //TicTacToe Testing
    @Test
    public void placePiece() throws Exception {
        TTTLogic t = new TTTLogic('X');

        //top left spot
        t.placePiece(0, 0);
        assertEquals(t.getBoard()[0][0], 'X');
    }

    /**
     * Test of the TTTLogic constructor
     */
    @Test
    public void constructorTest() {
        TTTLogic t = new TTTLogic('x');
        assertEquals(t.getPlayerChar(), 'X');

    }
}
