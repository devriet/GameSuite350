package madsen.GUI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import madsen.Logic.Cell;
import timdevries.gamesuite350.R;

/**
 * This class manages the responsibility of drawing the game cells.
 */
public class ViewCell extends View {
    /**
     * The cell this is responsible for drawing.
     */
    private Cell cell;

    /**
     * The x coordinate of this cell.
     */
    private int x;

    /**
     * The y coordinate of this cell.
     */
    private int y;

    /**
     * This is the default constructor for a minesweeper cell.
     *
     * @param pCell The game cell this object is responsible for
     * @param pX The x coordinate of this cell
     * @param pY The y coordinate of this cell
     * @param context The canvas this cell exists in
     */
    public ViewCell(final Cell pCell, final int pX, final int pY,
                    final Context context) {
        super(context);
        cell = pCell;
    }

    /**
     * Provides if this objects cell is a bomb.
     *
     * @return Whether this cell is a bomb
     */
    public boolean isBomb() {
        return cell.isBomb();
    }

    /**
     * Provides if this objects cell is flagged.
     *
     * @return Whether this cell is flagged
     */
    public boolean isFlagged() {
        return cell.isFlagged();
    }

    /**
     * Provides if this object is revealed.
     *
     * @return Whether this cell is revealed
     */
    public boolean isRevealed() {
        return cell.isRevealed();
    }

    /**
     * Draws the cell at its x and y coordinates.
     *
     * @param canvas The canvas this cell should be drawn on
     */
    //TODO checkstyle ignore magic numbers
    // @cs-: MagicNumber
    public void draw(final Canvas canvas) {
        Drawable d;

        if (cell.isRevealed()) {
            if (cell.isBomb()) {
                d = ContextCompat.getDrawable(getContext(), R.drawable.bomb);
            } else {
                switch (cell.getSurroundingBombs()) {
                    case 1:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.one);
                        break;
                    case 2:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.two);
                        break;
                    case 3:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.three);
                        break;
                    case 4:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.four);
                        break;
                    case 5:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.five);
                        break;
                    case 6:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.six);
                        break;
                    case 7:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.seven);
                        break;
                    case 8:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.eight);
                        break;
                    default:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.empty);
                }
            }
        } else {
            if (cell.isFlagged()) {
                d = ContextCompat.getDrawable(getContext(),
                        R.drawable.flag);
            } else {
                d = ContextCompat.getDrawable(getContext(),
                        R.drawable.blank);
            }
        }

        d.setBounds(0, 0, getWidth(), getHeight());
        d.draw(canvas);
    }
}
