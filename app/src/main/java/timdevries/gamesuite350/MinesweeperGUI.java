package timdevries.gamesuite350;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import madsen.MinesweeperGame;

/**
 * Creates and manages the view of a MinesweeperGame.
 */
public class MinesweeperGUI
        extends AppCompatActivity
        implements View.OnClickListener {

    /**
     * The game itself.
     */
    private MinesweeperGame game;

    /**
     * The game board.
     */
    private ImageButton[][] board;

    /**
     * Width of game board.
     */
    private static final int WIDTH = 3;

    /**
     * Height of game board.
     */
    private static final int HEIGHT = 5;

    /**
     * Pixel dimension of buttons.
     */
    private static final int BUTTON_SIZE = 100;

    /**
     * Array of linear layouts holding rows of buttons.
     */
    private LinearLayout[] rows;

    /**
     * This is the main view holder.
     */
    private LinearLayout myLayout;

    /**
     * Handles the creation of a MinesweeperGUI object.
     *
     * @param savedInstanceState The state this view is creted within.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Creating a new game of MinesweeperGUI
        game = new MinesweeperGame();

        // Initializing board of buttons
        board = new ImageButton[WIDTH][HEIGHT];

        // Linear layout
        myLayout = (LinearLayout) findViewById(R.id.linLay);
        myLayout.setPadding(0, 0, 0, 0);

        // Array of linear layouts (rows)
        rows = new LinearLayout[HEIGHT];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 1
        );

        // Creating the grid of buttons
        for (int i = 0; i < HEIGHT; i++) {
            rows[i] = new LinearLayout(this);
            rows[i].setOrientation(LinearLayout.VERTICAL);
            myLayout.addView(rows[i]);
        }

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                board[x][y] = new ImageButton(this);
                board[x][y].setOnClickListener(this);
                rows[y].addView(board[x][y]);
            }
        }
        for (LinearLayout l : rows) {
            l.setLayoutParams(params);
            l.setPadding(0, 0, 0, 0);
        }
        for (ImageButton[] a : board) {
            for (ImageButton b : a) {
                b.setLayoutParams(params);
                b.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.minesweeper_3));
                b.setPadding(0, 0, 0, 0);
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Implementation of the onClick method for View. Determines which button
     * was clicked and functions appropriately.
     *
     * @param view The current view of the game
     */
    @Override
    public void onClick(final View view) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == view) {
                            Snackbar.make(view,
                                    "i = " + i + " j = " + j,
                                    Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                }
            }
        }
    }
}
