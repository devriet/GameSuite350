package timdevries.gamesuite350;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CheckersGUI extends AppCompatActivity implements View.OnClickListener {
    //TODO Tim remove me

    ImageButton[][] board;
    ImageButton selected;

    /**
     * Pixel dimension of buttons.
     */
    private int buttonSize;

    /**
     * The game itself.
     */
    private CheckersLogic game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkers_gui);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        game = new CheckersLogic();
        board = new ImageButton[8][8];

/*        buttons = new Button[8][8];
        linearLayout = (LinearLayout) findViewById(R.id.CheckLinLay);
        rows = new LinearLayout[8];
        for (int i = 0; i < 8; i++) {
            rows[i] = new LinearLayout(this);
            rows[i].setOrientation(LinearLayout.VERTICAL);
            for (int j = 0; j < 8; j++) {
                buttons[i][j] = new Button(this);
                //TO//DO: add to row
                buttons[i][j].setOnClickListener(this);
            }
        }*/

        // Grid layout
        final GridLayout myLayout
                = (GridLayout) findViewById(R.id.checkersGrid);
        myLayout.setRowCount(board.length);
        myLayout.setColumnCount(board[0].length);
        myLayout.setPadding(0, 0, 0, 0);

        // This sets the size of game board buttons after rendering
        findViewById(R.id.content_checkers_gui).post(new Runnable() {
            @Override
            public void run() {
                // Sizing buttons and icons
                int w = findViewById(R.id.content_checkers_gui).getWidth();
                int xSize = w / board[0].length;
                int h = findViewById(R.id.content_checkers_gui).getHeight();
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
                myLayout.setX((float) (
                        (w - (buttonSize * board[0].length)) / 2.0));
                myLayout.setY((float) (
                        (h - (buttonSize * board.length)) / 2.0));
            }
        });

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                board[y][x] = new ImageButton(this);
                board[y][x].setOnClickListener(this);
                //board[y][x].setOnLongClickListener(this);
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

    private void drawBoard() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                board[y][x].setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(),
                                R.drawable.red_chip));
            }
        }

        /*for (int x = 0; x < board[0].length; x++) {
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
        }*/

/*        int[][] b = game.getBoard();
        for (int y = 0; y < b.length; y++) {
            for (int x = 0; x < b[0].length; x++) {
                if (b[y][x] == 1) {
                    board[y + 1][x].setImageDrawable(
                            ContextCompat.getDrawable(
                                    getApplicationContext(),
                                    R.drawable.red_board));
                } else if (b[y][x] == 2) {
                    board[y + 1][x].setImageDrawable(
                            ContextCompat.getDrawable(
                                    getApplicationContext(),
                                    R.drawable.yellow_board));
                } else {
                    board[y + 1][x].setImageDrawable(
                            ContextCompat.getDrawable(
                                    getApplicationContext(),
                                    R.drawable.empty_board));
                }
            }
        }*/
    }

    public int[] getCR(ImageButton button) {
        int r = -1, c = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == button) {
                    r = i;
                    c = j;
                }
            }
        }
        int[] out = new int[2];
        out[0] = c;
        out[1] = r;
        return out;
    }

    @Override
    public void onClick(View view) {
        int r = -1, c = -1;
        if (view instanceof Button) {
            int[] out = getCR((ImageButton) view);
            c = out[0];
            r = out[1];
        }
        if (!(r == -1 || c == -1)) {
            if (selected == null) selected = (ImageButton) view;
            else if (selected == view) selected = null;
            else {

            }
        }
    }
}
