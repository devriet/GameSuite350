package timdevries.gamesuite350;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
    private static final int HEIGHT = 3;

    /**
     * Bomb count for game.
     */
    private static final int BOMBS = 8;

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
        game = new MinesweeperGame(HEIGHT, WIDTH, BOMBS);

        // Initializing board of buttons
        board = new ImageButton[HEIGHT][WIDTH];

        // Linear layout
        myLayout = (LinearLayout) findViewById(R.id.linLay);
        myLayout.setPadding(0, 0, 0, 0);

        // Array of linear layouts (rows)
        rows = new LinearLayout[HEIGHT];
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT, 1
//        );

        // Creating the grid of buttons
        for (int y = 0; y < HEIGHT; y++) {
            rows[y] = new LinearLayout(this);
            rows[y].setOrientation(LinearLayout.HORIZONTAL);
            myLayout.addView(rows[y]);
        }

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                board[y][x] = new ImageButton(this);
                board[y][x].setOnClickListener(this);
                rows[y].addView(board[y][x]);
            }
        }
        for (LinearLayout l : rows) {
//            l.setLayoutParams(params);
            l.setPadding(0, 0, 0, 0);
        }
        for (ImageButton[] y : board) {
            for (ImageButton x : y) {
//                x.setLayoutParams(params);
                x.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.minesweeper_blank));
                x.setPadding(0, 0, 0, 0);
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
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == view) {
//                            Snackbar.make(view,
//                                    "y = " + y + " x = " + x,
//                                    Snackbar.LENGTH_LONG)
//                                    .setAction("Action", null).show();
                    if (game.getCell(y, x).isFlagged()) {
                        // Do nothing
                        break;
                    }

                    if (game.getCell(y, x).isBomb()) {
                        board[y][x].setImageDrawable(
                                ContextCompat.getDrawable(
                                        getApplicationContext(),
                                        R.drawable.minesweeper_bomb));
                    } else {
                        switch (game.getCell(y, x).getSurroundingBombs()) {
                            case 1:
                                board[y][x].setImageDrawable(
                                        ContextCompat.getDrawable(
                                                getApplicationContext(),
                                                R.drawable.minesweeper_1));
                                break;
                            case 2:
                                board[y][x].setImageDrawable(
                                        ContextCompat.getDrawable(
                                                getApplicationContext(),
                                                R.drawable.minesweeper_2));
                                break;
                            case 3:
                                board[y][x].setImageDrawable(
                                        ContextCompat.getDrawable(
                                                getApplicationContext(),
                                                R.drawable.minesweeper_3));
                                break;
                            case 4:
                                board[y][x].setImageDrawable(
                                        ContextCompat.getDrawable(
                                                getApplicationContext(),
                                                R.drawable.minesweeper_4));
                                break;
                            case 5:
                                board[y][x].setImageDrawable(
                                        ContextCompat.getDrawable(
                                                getApplicationContext(),
                                                R.drawable.minesweeper_5));
                                break;
                            case 6:
                                board[y][x].setImageDrawable(
                                        ContextCompat.getDrawable(
                                                getApplicationContext(),
                                                R.drawable.minesweeper_6));
                                break;
                            case 7:
                                board[y][x].setImageDrawable(
                                        ContextCompat.getDrawable(
                                                getApplicationContext(),
                                                R.drawable.minesweeper_7));
                                break;
                            case 8:
                                board[y][x].setImageDrawable(
                                        ContextCompat.getDrawable(
                                                getApplicationContext(),
                                                R.drawable.minesweeper_8));
                                break;
                            default:
                                board[y][x].setImageDrawable(
                                        ContextCompat.getDrawable(
                                                getApplicationContext(),
                                                R.drawable.minesweeper_empty));
                        }
                    }
                }
            }
        }
    }
}
