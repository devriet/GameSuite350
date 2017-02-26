package madsen.GUI;


import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import madsen.Logic.MinesweeperGame;

/**
 * The canvas object that draws the MinesweeperGame.
 */

public class MinesweeperCanvas extends View {
    /**
     * The game this is responsible for drawing.
     */
    private MinesweeperGame game;

    /**
     * The board of cells.
     */
    private ViewCell[][] board;

    /**
     * Instantiates a MinesweeperCanvas object to be drawn on.
     *
     * @param pGame The MinesweeperGame this draws
     * @param context The context this canvas exists in
     */
    public MinesweeperCanvas(final MinesweeperGame pGame,
                             final Context context) {
        super(context);

        game = pGame;

        board = new ViewCell[game.getBoardHeight()][game.getBoardWidth()];

        for (int y = 0; y < game.getBoardHeight(); y++) {
            for (int x = 0; x < game.getBoardWidth(); x++) {
                board[y][x] = new ViewCell(game.getCell(x, y), x, y,
                        this.getContext());
            }
        }
    }

    /**
     * Overrides the onDraw method of the View class.
     *
     * @param canvas The View Canvas this should draw on
     */
    @Override
    public void onDraw(final Canvas canvas) {
        for (int y = 0; y < game.getBoardHeight(); y++) {
            for (int x = 0; x < game.getBoardWidth(); x++) {

            }
        }
    }
}
