package timdevries.gamesuite350;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import madsen.MinesweeperGame;

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
     * Board size.
     */
    private final int size = 5;

    /**
     * son of a beach tree.
     */
    private RelativeLayout myLayout;

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

        // Layout of buttons
        myLayout = (RelativeLayout) findViewById(R.id.content_minesweeper);

        // Creating the grid of buttons
        for (int y = 0; y < game.getBoardHeight(); y++) {
            for (int x = 0; x < game.getBoardWidth(); x++) {
                board[y][x] = new Button(this);
                myLayout.addView(board[y][x]);
                board[y][x].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                    }
                });
            }
        }

        this.

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
