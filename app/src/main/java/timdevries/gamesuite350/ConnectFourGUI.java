package timdevries.gamesuite350;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import madsen.connectfour.ConnectFourGame;

/**
 * Creates and manages the view of a ConnectFourGame.
 */
public class ConnectFourGUI
        extends AppCompatActivity
        implements View.OnClickListener, View.OnLongClickListener {

    /**
     * The game itself.
     */
    private ConnectFourGame game;

    /**
     * The game board.
     */
    private ImageButton[][] board;

    /**
     * Pixel dimension of buttons.
     */
    private int buttonSize;

    /**
     * Column selected to display piece over top of.
     */
    private int selected;

    /**
     * Handles the creation of a ConnectFourGUI object.
     *
     * @param savedInstanceState The state this view is creted within.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectfour);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Creating a new game of ConnectFourGUI
        game = new ConnectFourGame();

        // Initializing board of buttons
        board = new ImageButton[ConnectFourGame.HEIGHT + 1][ConnectFourGame.WIDTH];

        // Give selected a default value
        selected = 0;

        // Grid layout
        final GridLayout myLayout
                = (GridLayout) findViewById(R.id.connectfourGrid);
        myLayout.setRowCount(board.length);
        myLayout.setColumnCount(board[0].length);
        myLayout.setPadding(0, 0, 0, 0);

        // This sets the size of game board buttons after rendering
        findViewById(R.id.content_connectfour).post(new Runnable() {
            @Override
            public void run() {
                // Sizing buttons and icons
                int w = findViewById(R.id.content_connectfour).getWidth();
                int xSize = w / board[0].length;
                int h = findViewById(R.id.content_connectfour).getHeight();
                int ySize = h / board.length;

                if (xSize * board[0].length <= h) {
                    buttonSize = xSize;
                } else {
                    buttonSize = ySize;
                }

                ViewGroup.LayoutParams params;
                for (ImageButton[] aBoard : board) {
                    for (int x = 0; x < board[0].length; x++) {
                        params = aBoard[x].getLayoutParams();
                        params.height = buttonSize;
                        params.width = buttonSize;
                        aBoard[x].setLayoutParams(params);
                    }
                }

                // Centering game board
                myLayout.setX((float) ((w - (buttonSize * board[0].length)) / 2.0));
                myLayout.setY((float) ((h - (buttonSize * board.length)) / 2.0));
            }
        });

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                board[y][x] = new ImageButton(this);
                board[y][x].setOnClickListener(this);
                board[y][x].setOnLongClickListener(this);
                myLayout.addView(board[y][x]);
            }
        }

        for (ImageButton[] aBoard : board) {
            for (int x = 0; x < board[0].length; x++) {
                aBoard[x].setScaleType(ImageView.ScaleType.FIT_CENTER);
                aBoard[x].setPadding(0, 0, 0, 0);
            }
        }

        // Draw the game board
        drawBoard();

        try {
            //noinspection ConstantConditions
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Iterates across the board and draws each icons.
     */
    public void drawBoard() {
        for (int x = 0; x < board[0].length; x++) {
            if (x == selected && game.getPlayer() == 1) {
                board[0][x].setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(),
                                R.drawable.red_chip));
            } else if (x == selected) {
                board[0][x].setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(),
                                R.drawable.yellow_chip));
            } else {
                board[0][x].setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(),
                                R.drawable.empty_chip));
            }
        }

        int[][] b = game.getBoard();
        for (int y = 1; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (b[y][x] == 1) {
                    board[y][x].setImageDrawable(
                            ContextCompat.getDrawable(
                                    getApplicationContext(),
                                    R.drawable.red_chip));
                } else if (b[y][x] == 2) {
                    board[y][x].setImageDrawable(
                            ContextCompat.getDrawable(
                                    getApplicationContext(),
                                    R.drawable.yellow_chip));
                } else {
                    board[y][x].setImageDrawable(
                            ContextCompat.getDrawable(
                                    getApplicationContext(),
                                    R.drawable.empty_chip));
                }
            }
        }
    }

    /**
     * A helper method to display win and lose message.
     *
     * @param view The view this is in
     */
    private void announceGameOver(final View view) {
        Snackbar.make(view,
                "Player " + game.getPlayer() + " wins!",
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    /**
     * Implementation of the onClick method for View. Determines which button
     * was clicked and changes the icon.
     *
     * @param view The current view of the game
     */
    @Override
    public void onClick(final View view) {
        for (ImageButton[] aBoard : board) {
            for (int x = 0; x < aBoard.length; x++) {
                if (aBoard[x] == view) {
                    selected = x;
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
        for (ImageButton[] aBoard : board) {
            for (int x = 0; x < aBoard.length; x++) {
                if (aBoard[x] == view) {
                    if (!game.placeTile(x)) {
                        Snackbar.make(view,
                                "Column full, try again",
                                Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        }

        drawBoard();

        if (game.isWin()) {
            announceGameOver(view);
        }

        // Always consume click
        return true;
    }
}
