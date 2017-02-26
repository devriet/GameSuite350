package timdevries.gamesuite350;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import madsen.MinesweeperGame;

/**
 *
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
    private Button[][] board;

    /**
     * Board size.
     */
    private final int size = 10;


    /**
     * array of linear layouts holding rows of buttons.
     */
    private LinearLayout[] rows;

    /**
     * This is the main view holder.
     */
    private LinearLayout myLayout;

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

        // Initializing board of buttons
        board = new Button[size][size];

        // Linear layout
        myLayout = (LinearLayout) findViewById(R.id.linLay);
        myLayout.setPadding(0, 0, 0, 0);

        // Array of linear layouts (rows)
        rows = new LinearLayout[size];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 1
        );

        // Creating the grid of buttons
        for (int i = 0; i < size; i++) {
            rows[i] = new LinearLayout(this);
            rows[i].setOrientation(LinearLayout.VERTICAL);
            myLayout.addView(rows[i]);
        }

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = new Button(this);
                board[y][x].setOnClickListener(this);
                rows[y].addView(board[y][x]);
            }
        }
        for (LinearLayout l : rows) {
            l.setLayoutParams(params);
            l.setPadding(0, 0, 0, 0);
        }
        for (Button[] a : board) {
            for (Button b : a) {
                b.setLayoutParams(params);
                b.setPadding(0, 0, 0, 0);
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     *
     * @param view
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
