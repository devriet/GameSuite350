package madsen.GUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.RelativeLayout;

import madsen.Logic.MinesweeperGame;
import timdevries.gamesuite350.R;

/**
 *
 */
public class MinesweeperGUI extends AppCompatActivity {

    /**
     * The game itself.
     */
    private MinesweeperGame game;

    /**
     * The game board.
     */
    private Button[][] board;

    /**
     * The width of the game board.
     * //FIXME make these variables
     */
    private final int width = 5;

    /**
     * The height of the game board.
     * //FIXME make these variables
     */
    private final int height = 5;

    /**
     * Board size.
     * //FIXME Change this to be variable
     */
    private final int size = 5;

    /**
     * The text size to display on buttons.
     */
    private final float textSize = 10.0f;

    /**
     * The canvas for drawing the game on.
     */
    private MinesweeperCanvas canvas;

    /**
     * The layout of the main app.
     */
    private RelativeLayout myLayout  =
            (RelativeLayout) findViewById(R.id.content_minesweeper);

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Creating a new game of MinesweeperGUI
        game = new MinesweeperGame();

        // Initialize view gameboard
        canvas = new MinesweeperCanvas(game, this);

        myLayout.addView(canvas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
