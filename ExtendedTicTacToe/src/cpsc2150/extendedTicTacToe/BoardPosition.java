package cpsc2150.extendedTicTacToe;
import java.util.*;

public class BoardPosition {

    /**
     * @invariant 0 <= row <= MAXROWCOL
     * @invariant 0 <= column <= MAXROWCOL
     */

    private int myRow;
    private int myColumn;

    /**
     * This constructor creates a new board position with an assigned row and column.
     * @param row: row integer to be entered in the GameBoard array
     * @param column: column integer to be entered in the GameBoard array
     * @post
     * myRow = row
     * myColumn = column
     */
    public BoardPosition(int row, int column) {
        myRow = row;
        myColumn = column;
    }

    /**
     * This function gets the row position that the board position is in.
     * @return row position in GameBoard array
     * @post getRow = row
     */
    public int getRow() {
        return myRow;
    }

    /**
     * This function gets the column position that the board position is in.
     * @return column position in Gameboard array
     * @post getColumn = column
     */
    public int getColumn() {
        return myColumn;
    }

    /**
     * This function overrides the equals() function to compare rows and columns using the == sign.
     * @param pos: BoardPosition being compared in the equal statement
     * @return true if row and column of the BoardPosition contain the same char,
     *          false if they are different
     * @post equals = (row == pos.getRow() && column == pos.getColumn())
     */
    public boolean equals(BoardPosition pos) {
        return myRow == pos.myRow && myColumn == pos.myColumn;
    }

    /**
     * This function overrides the toString() function to format the board position in a row, column format.
     * @return  the formatted string of rows and columns
     * @post toString = "row" + ", " + "column"
     */
    @Override
    public String toString() {
        return myRow + ", " + myColumn;
    }
}
