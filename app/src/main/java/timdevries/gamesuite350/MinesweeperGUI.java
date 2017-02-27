package timdevries.gamesuite350;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;

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
     * Bomb count for game.
     */
    private static final int BOMBS = 8;

    /**
     * Pixel dimension of buttons.
     */
    private int buttonSize;

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

        // Grid layout
        GridLayout myLayout = (GridLayout) findViewById(R.id.minesweeperGrid);
        myLayout.setRowCount(HEIGHT);
        myLayout.setColumnCount(WIDTH);
        myLayout.setPadding(0, 0, 0, 0);

        // This sets the size of game board buttons after rendering
        findViewById(R.id.content_minesweeper).post(new Runnable() {
            @Override
            public void run() {
                Point size = new Point();
                getWindowManager().getDefaultDisplay().getSize(size);

                int xSize = size.x / WIDTH;
                int h = findViewById(R.id.content_minesweeper).getHeight();
                int ySize = h / HEIGHT;

                if (size.x * HEIGHT <= h) {
                    buttonSize = xSize;
                } else {
                    buttonSize = ySize;
                }

                ViewGroup.LayoutParams params;
                for (int y = 0; y < HEIGHT; y++) {
                    for (int x = 0; x < WIDTH; x++) {
                        params = board[y][x].getLayoutParams();
                        params.height = buttonSize;
                        params.width = buttonSize;
                        board[y][x].setLayoutParams(params);
                    }
                }
            }
        });

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                board[y][x] = new ImageButton(this);
                board[y][x].setOnClickListener(this);
                myLayout.addView(board[y][x]);
            }
        }

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                board[y][x].setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.minesweeper_blank));
                board[y][x].setPadding(0, 0, 0, 0);
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
