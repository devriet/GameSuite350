package madsen.GUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

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
     * son of a beach tree.
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

        // Initializing board of buttons
        board = new Button[size][size];

        // Button layout manager
        GridLayout buttonLayout = new GridLayout(this);
        buttonLayout.setLayoutParams(
                new TableLayout.LayoutParams(width, height));

        // Creating the grid of buttons
        for (int y = 0; y < game.getBoardHeight(); y++) {
            for (int x = 0; x < game.getBoardWidth(); x++) {
                board[y][x] = new Button(this);
                board[y][x].setText("B");
                board[y][x].setTextSize(textSize);
                board[y][x].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                    }
                });
            }
        }

        myLayout.addView(buttonLayout);
        myLayout.requestFocus();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
