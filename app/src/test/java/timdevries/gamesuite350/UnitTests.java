package timdevries.gamesuite350;

import org.junit.Test;

import madsen.connectfour.ConnectFourGame;
import madsen.minesweeper.MinesweeperGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * A suite of tests designed to ensure proper functionality.
 *
 * @author Troy Madsen
 */
public class UnitTests {
    private final static int BOARD_HEIGHT = 4;
    private final static int BOARD_WIDTH = 3;
    private final static int BOMB_COUNT = 8;

    @Test
    public void defaultParams() {
        new MinesweeperGame();
    }

    @Test
    public void underParams() {
        new MinesweeperGame(-12, -12, -12);
    }

    @Test
    public void overParams() {
        new MinesweeperGame(100, 100, 100000);
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

    @Test
    public void createGameTest() {
        new ConnectFourGame();
    }

    @Test
    public void placeTileTest() {
        ConnectFourGame game = new ConnectFourGame();
        assertTrue(game.placeTile(0));
    }

    @Test
    public void placeFilledColumnTest() {
        ConnectFourGame game = new ConnectFourGame();
        for (int i = 0; i < 6; i++) {
            game.placeTile(0);
        }
        assertFalse(game.placeTile(0));
    }

    @Test
    public void getPlayerTest() {
        ConnectFourGame game = new ConnectFourGame();
        assertEquals(1, game.getPlayer());
    }

    @Test
    public void playerSwitchTest() {
        ConnectFourGame game = new ConnectFourGame();
        int p1 = game.getPlayer();
        game.placeTile(0);
        assertNotEquals(p1, game.getPlayer());
    }

    @Test
    public void noPlayerSwitchTest() {
        ConnectFourGame game = new ConnectFourGame();
        for (int i = 0; i < 6; i++) {
            game.placeTile(0);
        }
        int p1 = game.getPlayer();
        game.placeTile(0);
        assertEquals(p1, game.getPlayer());
    }

    @Test
    public void getBoardTest() {
        ConnectFourGame game = new ConnectFourGame();
        int[][] b = new int[ConnectFourGame.HEIGHT][ConnectFourGame.WIDTH];
        b[5][0] = 1;
        b[4][0] = 2;
        game.placeTile(0);
        game.placeTile(0);
        boolean isSame = true;
        int[][] gb = game.getBoard();
        for (int y = 0; y < ConnectFourGame.HEIGHT; y++) {
            for (int x = 0; x < ConnectFourGame.WIDTH; x++) {
                if (b[y][x] != gb[y][x]) {
                    isSame = false;
                }
            }
        }
        assertTrue(isSame);
    }

    @Test
    public void player1WinTest() {
        ConnectFourGame game = new ConnectFourGame();
        for (int i = 0; i < 4; i++) {
            game.placeTile(i);
            game.placeTile(i);
        }
        assertTrue(game.getPlayer() == 1 && game.isWin());
    }

    @Test
    public void player2WinTest() {
        ConnectFourGame game = new ConnectFourGame();
        for (int i = 0; i < 3; i++) {
            game.placeTile(i);
            game.placeTile(i);
        }
        game.placeTile(4);
        game.placeTile(3);
        game.placeTile(4);
        game.placeTile(3);
        assertTrue(game.getPlayer() == 2 && game.isWin());
    }

    @Test
    public void boardFullTest() {
        ConnectFourGame game = new ConnectFourGame();
        for (int i = 0; i < 3; i++) {
            game.placeTile(i);
            game.placeTile(i);
            game.placeTile(i);
            game.placeTile(i);
            game.placeTile(i);
            game.placeTile(i);
        }
        game.placeTile(4);
        game.placeTile(3);
        game.placeTile(3);
        game.placeTile(3);
        game.placeTile(3);
        game.placeTile(3);
        game.placeTile(3);
        for (int i = 4; i < 7; i++) {
            game.placeTile(i);
            game.placeTile(i);
            game.placeTile(i);
            game.placeTile(i);
            game.placeTile(i);
            game.placeTile(i);
        }
        assertTrue(!game.isRunning() && !game.isWin());
    }

    @Test
    public void test_move() {

        CheckersLogic cl = new CheckersLogic();
        // move happens
        assertTrue(cl.move(3, 1, 2, 0));
    }

    @Test
    public void test_jump_move() {
        CheckersLogic cl = new CheckersLogic();
        // move several times to set up a jump
        cl.move(3, 1, 2, 0);
        cl.move(4, 0, 3, 1);
        cl.move(4, 2, 5, 1);
        cl.move(5, 1, 6, 2);
        //the jump
        assertTrue(cl.move(6, 2, 4, 0));
    }

    @Test
    public void move_to_current_space() {
        CheckersLogic cl = new CheckersLogic();
        assertFalse(cl.move(2, 0, 2, 0));
    }

    @Test
    public void move_not_diagonal() {
        CheckersLogic cl = new CheckersLogic();
        assertFalse(cl.move(2, 1, 2, 0));
    }
}
