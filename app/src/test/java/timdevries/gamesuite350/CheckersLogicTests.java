package timdevries.gamesuite350;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the test file for the CheckersLogic class.
 * Created by Matthew Johnson on 4/19/2017.
 */

public class CheckersLogicTests {

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
}
