package timdevries.gamesuite350;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


/**
 * This is a frickin javadoc comment.
 */
public class TTTView extends AppCompatActivity implements View.OnClickListener {

    TTTPresenter tp;

    View topLeft;
    View topMiddle;
    View topRight;
    View centerLeft;
    View centerMiddle;
    View centerRight;
    View bottomLeft;
    View bottomMiddle;
    View bottomRight;

    /**
     * Sets up the view for the android screen
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //on start of game make a new game presenter
        tp = new TTTPresenter('x');

        //Connect the EditTexts to this class from the android
        topLeft = findViewById(R.id.topLText);
        topMiddle = findViewById(R.id.topMText);
        topRight = findViewById(R.id.topRText);
        centerLeft = findViewById(R.id.centerLText);
        centerMiddle = findViewById(R.id.centerMText);
        centerRight = findViewById(R.id.centerRText);
        bottomLeft = findViewById(R.id.bottomLText);
        bottomMiddle = findViewById(R.id.bottomMText);
        bottomRight = findViewById(R.id.bottomRText);

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

    }

    /**
     * Find out which box is clicked and place
     * the player's piece there
     *
     * @param view the box clicked
     */
    @Override
    public void onClick(View view) {

        if (view == topLeft) {

        } else if (view == topMiddle) {


        } else if (view == topRight) {


        } else if (view == centerLeft) {


        } else if (view == centerMiddle) {


        } else if (view == centerRight) {


        } else if (view == bottomLeft) {


        } else if (view == bottomMiddle) {


        } else if (view == bottomRight) {


        }

    }

    /**
     * Update game board views
     *
     * @param board the updated game board
     */
    public void updateBoard(char[][] board) {
        for (char[] c : board) {
            for (char k : c) {
                //// TODO: 2/26/2017
            }
        }
    }

}
