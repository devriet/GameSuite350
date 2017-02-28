package timdevries.gamesuite350;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This sets the display screen for the ttt game.
 */
public class TTTView extends AppCompatActivity
        implements View.OnClickListener {
    /**
     * The presenter object.
     */
    private TTTPresenter tp;

    /**
     * The top left box on the ttt board.
     */
    private TextView topLeft;

    /**
     * The top middle box on the ttt board.
     */
    private TextView topMiddle;

    /**
     * The top right box on the ttt board.
     */
    private TextView topRight;

    /**
     * The center left box on the ttt board.
     */
    private TextView centerLeft;

    /**
     * The center middle box on the ttt board.
     */
    private TextView centerMiddle;

    /**
     * The center right box on the ttt board.
     */
    private TextView centerRight;

    /**
     * The bottom left box on the ttt board.
     */
    private TextView bottomLeft;

    /**
     * The bottom middle box on the ttt board.
     */
    private TextView bottomMiddle;

    /**
     * The bottom right box on the ttt board.
     */
    private TextView bottomRight;

    /**
     * The array of views for easy board update.
     */
    private TextView[] board;

    /**
     * Sets up the view for the android screen.
     *
     * @param savedInstanceState ?
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Build the alert dialog box and make a new presenter
        //need this context line; may break app
        //help for this was found at:
        //https://developer.android.com/guide/topics/ui/dialogs.html
        Context c = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(R.string.ttt_dialog_title)
                .setItems(R.array.ttt_dialog_elements,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(
                                    final DialogInterface dialogInterface,
                                                final int i) {
                                if (i == 0) {
                                    tp = new TTTPresenter('X');
                                } else {
                                    tp = new TTTPresenter('O');
                                }
                            }
                        });
        builder.create();
        builder.show();

        //this happens before the alert dialog
        //which then overwrites this value.
        tp = new TTTPresenter('X');

        //Connect the TextViews to this class from the android
        topLeft = (TextView) findViewById(R.id.topLText);
        topMiddle = (TextView) findViewById(R.id.topMText);
        topRight = (TextView) findViewById(R.id.topRText);
        centerLeft = (TextView) findViewById(R.id.centerLText);
        centerMiddle = (TextView) findViewById(R.id.centerMText);
        centerRight = (TextView) findViewById(R.id.centerRText);
        bottomLeft = (TextView) findViewById(R.id.bottomLText);
        bottomMiddle = (TextView) findViewById(R.id.bottomMText);
        bottomRight = (TextView) findViewById(R.id.bottomRText);

        //set the onClick listeners for the game spaces
        topLeft.setOnClickListener(this);
        topMiddle.setOnClickListener(this);
        topRight.setOnClickListener(this);
        centerLeft.setOnClickListener(this);
        centerMiddle.setOnClickListener(this);
        centerRight.setOnClickListener(this);
        bottomLeft.setOnClickListener(this);
        bottomMiddle.setOnClickListener(this);
        bottomRight.setOnClickListener(this);

        //set the board
        board = new TextView[]{topLeft, topMiddle,
                topRight, centerLeft,
                centerMiddle, centerRight, bottomLeft,
                bottomMiddle, bottomRight};

        setGame();

    }

    /**
     * sets up the game.
     */
    private void setGame() {
        updateBoard(tp.getBoard());
    }

    /**
     * Find out which box is clicked and place
     * the player's piece there.
     *
     * @param view the box clicked
     */
    @Override
    public void onClick(final View view) {
        //the game board
        char[][] b;

        boolean gameOver = false;
        boolean spotfull = false;

        //Dialog for if spot has a piece already
        Context c = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(R.string.ttt_dialog_full_title)
                .setMessage(R.string.ttt_dialog_full_msg);
        builder.create();

        if (view == topLeft) {

            spotfull = tp.playGame(0, 0);
            b = tp.getBoard();
            gameOver = tp.isGameOver();
            updateBoard(b);

        } else if (view == topMiddle) {

            spotfull = tp.playGame(0, 1);
            b = tp.getBoard();
            gameOver = tp.isGameOver();
            updateBoard(b);

        } else if (view == topRight) {

            spotfull = tp.playGame(0, 2);
            b = tp.getBoard();
            gameOver = tp.isGameOver();
            updateBoard(b);

        } else if (view == centerLeft) {

            spotfull = tp.playGame(1, 0);
            b = tp.getBoard();
            gameOver = tp.isGameOver();
            updateBoard(b);

        } else if (view == centerMiddle) {

            spotfull = tp.playGame(1, 1);
            b = tp.getBoard();
            gameOver = tp.isGameOver();
            updateBoard(b);

        } else if (view == centerRight) {

            spotfull = tp.playGame(1, 2);
            b = tp.getBoard();
            gameOver = tp.isGameOver();
            updateBoard(b);

        } else if (view == bottomLeft) {

            spotfull = tp.playGame(2, 0);
            b = tp.getBoard();
            gameOver = tp.isGameOver();
            updateBoard(b);

        } else if (view == bottomMiddle) {

            spotfull = tp.playGame(2, 1);
            b = tp.getBoard();
            gameOver = tp.isGameOver();
            updateBoard(b);

        } else if (view == bottomRight) {

            spotfull = tp.playGame(2, 2);
            b = tp.getBoard();
            gameOver = tp.isGameOver();
            updateBoard(b);
        }

        if (spotfull) {
            builder.show();
        }

        if (gameOver) {
            CharSequence text;
            if (tp.hasPlayerWon()) {
                text = "You WIN! :)";
            } else {
                text = "You Lose. :(";
            }
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(c, text, duration);
            toast.show();
        }

    }

    /**
     * Update game board views.
     *
     * @param b the updated game board
     */
    public void updateBoard(final char[][] b) {

        //board counter
        int x = 0;

        for (char[] c : b) {
            for (char k : c) {
                String s = Character.toString(k);
                board[x].setText(s);
                x++;
            }
        }
    }


}
