package timdevries.gamesuite350;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

/**
 * This is a frickin javadoc comment.
 */
public class Home extends AppCompatActivity {
    /**
     * This is a frickin javadoc comment.
     */
    private Button tictac;
    /**
     * This is a frickin javadoc comment.
     */
    private Button mines;
    /**
     * This is a frickin javadoc comment.
     */
    private Button connect;
    /**
     * This is a frickin javadoc comment.
     */
    private Button checkers;
    /**
     * This is a frickin javadoc comment.
     */
    private Activity me;
    /**
     *
     * final @param savedInstanceState
     * This is a frickin javadoc comment.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =
                    (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,
                        "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        me = this;
        tictac = (Button) findViewById(R.id.button);
        tictac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent start = new Intent(me, Tictactoe.class);
                startActivity(start);
            }
        });
        mines = (Button) findViewById(R.id.button2);
        mines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent start = new Intent(me, minesweeper.class);
                startActivity(start);
            }
        });
        checkers = (Button) findViewById(R.id.button3);
        checkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

            }
        });
        connect = (Button) findViewById(R.id.button4);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

            }
        });

    }

    /**
     *
     * @param menu This is a frickin javadoc comment.
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    /**
     *
     * @param item This is a frickin javadoc comment.
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
