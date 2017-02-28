package timdevries.gamesuite350;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import madsen.Cell;
import madsen.MinesweeperGame;

/**
 * Creates and manages the view of a MinesweeperGame.
 */
public class MinesweeperGUI
        extends AppCompatActivity
        implements View.OnClickListener, View.OnLongClickListener {

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
    private static final int WIDTH = 6;

    /**
     * Height of game board.
     */
    private static final int HEIGHT = 9;

    /**
     * Bomb count for game.
     */
    private static final int BOMBS = 10;

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
        final GridLayout myLayout
                = (GridLayout) findViewById(R.id.minesweeperGrid);
        myLayout.setRowCount(HEIGHT);
        myLayout.setColumnCount(WIDTH);
        myLayout.setPadding(0, 0, 0, 0);

        // This sets the size of game board buttons after rendering
        findViewById(R.id.content_minesweeper).post(new Runnable() {
            @Override
            public void run() {
                // Sizing buttons and icons
                int w = findViewById(R.id.content_minesweeper).getWidth();
                int xSize = w / WIDTH;
                int h = findViewById(R.id.content_minesweeper).getHeight();
                int ySize = h / HEIGHT;

                if (xSize * HEIGHT <= h) {
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

                // Centering game board
                myLayout.setX((w - (buttonSize * WIDTH)) / 2);
                myLayout.setY((h - (buttonSize * HEIGHT)) / 2);
            }
        });

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                board[y][x] = new ImageButton(this);
                board[y][x].setOnClickListener(this);
                board[y][x].setOnLongClickListener(this);
                myLayout.addView(board[y][x]);
            }
        }

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                board[y][x].setScaleType(ImageView.ScaleType.FIT_CENTER);
                board[y][x].setPadding(0, 0, 0, 0);
            }
        }

        // Draw the game board
        drawBoard();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Iterates across the board and draws each icons.
     */
    public void drawBoard() {
        Cell c;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                c = game.getCell(y, x);
                if (c.isFlagged()) {
                    board[y][x].setImageDrawable(
                            ContextCompat.getDrawable(
                                    getApplicationContext(),
                                    R.drawable.minesweeper_flag));
                } else if (!c.isRevealed()) {
                    board[y][x].setImageDrawable(
                            ContextCompat.getDrawable(
                                    getApplicationContext(),
                                    R.drawable.minesweeper_blank));
                } else if (c.isBomb()) {
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

    /**
     * Implementation of the onClick method for View. Determines which button
     * was clicked and changes the icon.
     *
     * @param view The current view of the game
     */
    @Override
    public void onClick(final View view) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == view) {
//                    Snackbar.make(view,
//                            "Click",
//                            Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                    if (game.getCell(y, x).isFlagged()) {
                        game.setFlagged(y, x, false);
                    } else {
                        game.setFlagged(y, x, true);
                    }
                }
            }
        }

        drawBoard();
    }

    /**
     * Implementation of the onLongClick method for View. Determines which
     * button was clicked and reveals the cell.
     *
     * @param view The current view of the game
     * @return Always true as the click is always consumed
     */
    @Override
    public boolean onLongClick(final View view) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == view) {
//                            Snackbar.make(view,
//                                    "Long click",
//                                    Snackbar.LENGTH_LONG)
//                                    .setAction("Action", null).show();
                    if (game.getCell(y, x).isFlagged()) {
                        // Do nothing
                        break;
                    } else {
                        game.revealCell(y, x);
                    }
                }
            }
        }

        drawBoard();

        // Always consume click
        return true;
    }
}
