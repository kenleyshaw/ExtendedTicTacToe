package cpsc2150.extendedTicTacToe;
import java.util.*;

/**
 * @invariant rowLength = rows
 * @invariant colLength = columns
 * @invariant MINROWCOL <= rows <= MAXROWCOL
 * @invariant MINROWCOL <= columns <= MAXROWCOL
 * @invariant MINWINNUM <= winNum <= MAXWINNUM
 *
 * Correspondences:
 * rowLength = rows
 * colLength = columns
 * winningNum = winNum
 * [positions in the character array are in the bounds of the board]
 */
public class GameBoard extends AbsGameBoard {

    private int rows;
    private int columns;
    private int winNum;
    private char gameBoard[][];

    /**
     * This constructor creates a gameBoard that is a character array of rowLength by colLength dimensions
     * with a certain number of tokens in a row needed to win, winningNum.
     * @param rowLength: the length of the rows of the board
     * @param colLength: the length of the columns of the board
     * @param winnerNum: the amount of tokens in a row to win the game
     * @pre rowLength <= MAXROWCOL and columns <= MAXROWCOL and winNum <= MAXWINNUM,
     *      rowLength >= MINROWCOL and columns >= MINROWCOL and winNum >= MINWINNUM
     * @post
     * rows = rowLength and columns = colLength and winNum = winnerNum
     * and gameBoard.length = rowLength and gameBoard[].length = colLength
     * all spaces in GameBoard are set to empty
     */
    public GameBoard(int rowLength, int colLength, int winnerNum) {
        // assigning the length of the rows and columns
        rows = rowLength;
        columns = colLength;
        winNum = winnerNum;
        gameBoard = new char[rows][columns];
        // loops through row and column lengths and assigns each spot to be empty
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gameBoard[i][j] = ' ';
            }
        }
    }

    public int getNumRows() {
        return rows;
    }

    public int getNumColumns() {
        return columns;
    }

    public int getNumToWin() {
        return winNum;
    }

    public void placeMarker(BoardPosition marker, char player) {
        gameBoard[marker.getRow()][marker.getColumn()] = player;
    }

    public char whatsAtPos(BoardPosition pos){
        return gameBoard[pos.getRow()][pos.getColumn()];
    }
}
