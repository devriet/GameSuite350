package timdevries.gamesuite350;



import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 * Created by Matt on 2/1/2017.
 */

class TTTLogicTest {

    /**
     * Constructor for test file
     */
    private TTTLogicTest() {

    }

    /**
     *
     */
    @Test
    private void constructorTest() {
        TTTLogic t = new TTTLogic('x');
        assertEquals(t.getPlayerChar(), 'X');

    }

}
