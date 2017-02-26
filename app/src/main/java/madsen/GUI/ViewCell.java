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
     * The column of this cell.
     */
    private int column;

    /**
     * The row of this cell.
     */
    private int row;

    /**
     * This is the default constructor for a minesweeper cell.
     *
     * @param pCell The game cell this object is responsible for
     * @param pColumn The column coordinate of this cell
     * @param pRow The row coordinate of this cell
     * @param context The context this cell exists in
     */
    public ViewCell(final Cell pCell, final int pColumn, final int pRow,
                    final Context context) {
        super(context);
        cell = pCell;
        column = pColumn;
        row = pRow;
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
     * Provides the column of this cell.
     *
     * @return The column of this cell
     */
    public int getColumn() {
        return column;
    }

    /**
     * Provides the row of this cell.
     *
     * @return The row of this cell
     */
    public int getRow() {
        return row;
    }

    /**
     * Draws the cell at its column and row coordinates.
     *
     * @param canvas The canvas this cell should be drawn on
     */
    @Override
    public void onDraw(final Canvas canvas) {
        Drawable d;

        if (cell.isRevealed()) {
            if (cell.isBomb()) {
                d = ContextCompat.getDrawable(getContext(),
                        R.drawable.minesweeper_bomb);
            } else {
                switch (cell.getSurroundingBombs()) {
                    case 1:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.minesweeper_1);
                        break;
                    case 2:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.minesweeper_2);
                        break;
                    case 3:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.minesweeper_3);
                        break;
                    case 4:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.minesweeper_4);
                        break;
                    case 5:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.minesweeper_5);
                        break;
                    case 6:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.minesweeper_6);
                        break;
                    case 7:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.minesweeper_7);
                        break;
                    case 8:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.minesweeper_8);
                        break;
                    default:
                        d = ContextCompat.getDrawable(getContext(),
                                R.drawable.minesweeper_empty);
                }
            }
        } else {
            if (cell.isFlagged()) {
                d = ContextCompat.getDrawable(getContext(),
                        R.drawable.minesweeper_flag);
            } else {
                d = ContextCompat.getDrawable(getContext(),
                        R.drawable.minesweeper_blank);
            }
        }

        d.setBounds(0, 0, getWidth(), getHeight());
        d.draw(canvas);
    }
}
