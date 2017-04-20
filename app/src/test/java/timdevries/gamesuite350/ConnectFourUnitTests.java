package timdevries.gamesuite350;

import org.junit.Test;

import madsen.connectfour.ConnectFourGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * A suite of tests designed to ensure functionality of the ConnectFourGame
 * class.
 *
 * @author Troy Madsen
 */
public class ConnectFourUnitTests {

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
}
