package timdevries.gamesuite350;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

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

        // Creating the grid of buttons
        for (int y = 0; y < game.getBoardHeight(); y++) {
            for (int x = 0; x < game.getBoardWidth(); x++) {
                board[y][x] = new Button(this);
                board[y][x].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                    }
                });
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
