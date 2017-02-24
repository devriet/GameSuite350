package timdevries.gamesuite350;

import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 * A test class for the TTTLogic class
 * Created by Matt on 2/1/2017.
 */

class TTTLogicTest {
    @Test
    public void placePiece() throws Exception {
        TTTLogic t = new TTTLogic('x');

        //top left spot
        t.placePiece(0, 0);
        assertEquals(t.getBoard()[0][0], 'x');

        //bottom right spot
        t.placePiece(2,2);
        assertEquals(t.getBoard()[2][2], 'x');

        //middle spot
        t.placePiece(1,1);
        assertEquals(t.getBoard()[1][1], 'x');
    }

    @Test
    public void getPlayerChar() throws Exception {

    }

    /**
     * Constructor for test file
     */
    private TTTLogicTest() {

    }

    /**
     * Test of the TTTLogic constructor
     */
    @Test
    private void constructorTest() {
        TTTLogic t = new TTTLogic('x');
        assertEquals(t.getPlayerChar(), 'X');

    }

}
