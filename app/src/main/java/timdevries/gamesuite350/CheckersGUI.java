package timdevries.gamesuite350;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Creates and manages the view of a Checkers Game.
 */
public class CheckersGUI extends AppCompatActivity
        implements View.OnClickListener {

    /**
     * The game board.
     */
    private ImageButton[][] board;

    /**
     * The currently selected tile.
     */
    private ImageButton selected;

    /**
     * Pixel dimension of buttons.
     */
    private int buttonSize;

    /**
     * The game itself.
     */
    private CheckersLogic game;

    /**
     *
     * @param savedInstanceState dasji.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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

    /**
     * Renders the board.
     */
    private void drawBoard() {
        BoardSquare[][] boardSquares = game.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i + j) % 2 == 0) {
                    if (boardSquares[i][j].getPiece()
                            .getColor() == PieceColor.BLANK) {
                        board[i][j].setImageDrawable(
                                ContextCompat.getDrawable(
                                        getApplicationContext(),
                                        R.drawable.checkers_empty_dark)
                        );
                    } else if (boardSquares[i][j].getPiece()
                            .getColor() == PieceColor.BLACK) {
                        if (boardSquares[i][j].getPiece().isKing()) {
                            board[i][j].setImageDrawable(
                                    ContextCompat.getDrawable(
                                            getApplicationContext(),
                                            R.drawable.checkers_black_dark_king)
                            );
                        } else {
                            board[i][j].setImageDrawable(
                                    ContextCompat.getDrawable(
                                            getApplicationContext(),
                                            R.drawable.checkers_black_dark)
                            );
                        }
                    } else {
                        if (boardSquares[i][j].getPiece().isKing()) {
                            board[i][j].setImageDrawable(
                                    ContextCompat.getDrawable(
                                            getApplicationContext(),
                                            R.drawable.checkers_red_dark_king)
                            );
                        } else {
                            board[i][j].setImageDrawable(
                                    ContextCompat.getDrawable(
                                            getApplicationContext(),
                                            R.drawable.checkers_red_dark)
                            );
                        }
                    }
                } else {
                    if (boardSquares[i][j].getPiece()
                            .getColor() == PieceColor.BLANK) {
                        board[i][j].setImageDrawable(
                                ContextCompat.getDrawable(
                                        getApplicationContext(),
                                        R.drawable.checkers_empty_light)
                        );
                    } else if (boardSquares[i][j].getPiece()
                            .getColor() == PieceColor.BLACK) {
                        if (boardSquares[i][j].getPiece().isKing()) {
                            board[i][j].setImageDrawable(
                                    ContextCompat.getDrawable(
                                            getApplicationContext(),
                                            R.drawable
                                                    .checkers_black_light_king)
                            );
                        } else {
                            board[i][j].setImageDrawable(
                                    ContextCompat.getDrawable(
                                            getApplicationContext(),
                                            R.drawable.checkers_black_light)
                            );
                        }
                    } else {
                        if (boardSquares[i][j].getPiece().isKing()) {
                            board[i][j].setImageDrawable(
                                    ContextCompat.getDrawable(
                                            getApplicationContext(),
                                            R.drawable.checkers_red_light_king)
                            );
                        } else {
                            board[i][j].setImageDrawable(
                                    ContextCompat.getDrawable(
                                            getApplicationContext(),
                                            R.drawable.checkers_red_light)
                            );
                        }
                    }
                }
            }
        }

        /*
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                board[y][x].setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(),
                                R.drawable.red_chip));
            }
        }*/

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

    /**
     * Returns the column of a button in int[0] and the row in int[1].
     * @param button The button to find.
     * @return int[] of size 2.
     */
    public int[] getCR(final ImageButton button) {
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

    /**
     * Performs an action based on a click.
     * @param view dcskan.
     */
    @Override
    public void onClick(final View view) {
        int r = -1, c = -1;
        if (view instanceof ImageButton) {
            int[] out = getCR((ImageButton) view);
            c = out[0];
            r = out[1];
        }
        if (!(r == -1 || c == -1)) {
            if (selected == null) {
                selected = (ImageButton) view;
                selected.setColorFilter(0xffff0000, PorterDuff.Mode.MULTIPLY);
//                selected.setColorFilter(100);
            } else if (selected == view) {
                selected.setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
                drawBoard();
                selected = null;
            } else {
                int[] cr1 = getCR(selected);
                int[] cr2 = getCR((ImageButton) view);
                int c1 = cr1[0], r1 = cr1[1];
                int c2 = cr2[0], r2 = cr2[1];
                game.move(c2, r2, c1, r1);
                drawBoard();
                selected = null;
            }
        }
    }
}
