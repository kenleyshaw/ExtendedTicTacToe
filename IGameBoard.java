package cpsc2150.extendedTicTacToe;

import java.util.*;

/**
 * defines:
 * rowLength: Z, the maximum number of rows on the game board
 * colLength: Z, the maximum number of columns on the game board
 * winningNum: Z, the number of tokens that have to be in a row in order to win
 *
 * constraints:
 * MINROWCOL <= rowLength <= MAXROWCOL
 * MINROWCOL <= colLength <= MAXROWCOL
 * MINWINNUM <= winNum <= (rowLength || colLength)
 *
 * initialization ensures:
 * gameboard will be a 2D array of rowLength by colLength filled with blank characters
 */
public interface IGameBoard {

    int MAXROWCOL = 100;
    int MINROWCOL = 3;
    int MAXWINNUM = 25;
    int MINWINNUM = 3;

    /**
     * This function gets the number of rows in the game board.
     * @return the integer number of rows in the game board
     * @post getNumRows = rowLength
     */
    int getNumRows();

    /**
     * This function gets the number of columns in the game board.
     * @return the integer number of columns in the game board
     * @post getNumColumns = colLength
     */
    int getNumColumns();

    /**
     * This function gets the number of tokens in a row needed to win the game.
     * @return the integer number of tokens in a row needed to win the game
     * @post getNumToWin = WINNUM
     */
    int getNumToWin();

    /**
     * This function checks if the space is within bounds of the board, between 0 and
     * the actual dimensions of the rows and columns.
     * @param pos: BoardPosition that will be checked
     * @return true if pos is empty and within the bounds of the board
     * @pre pos != null
     * @post checkSpace iff (pos.getRow() >= 0 && pos.getColumn() >= 0) and
     * pos.getRow() < getNumRows() && pos.getColumn() < getNumColumns() and
     * whatsAtPos(pos) == ' '
     */
    default boolean checkSpace(BoardPosition pos) {
        // if the position is not less than 0
        if (pos.getRow() >= 0 && pos.getColumn() >= 0) {
            // if the position is not greater than the rows and columns in the board
            if (pos.getRow() < getNumRows() && pos.getColumn() < getNumColumns()) {
                // return if it is true that the position contains ' '
                return whatsAtPos(pos) == ' ';
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * This function places a character marker in a board position.
     * @param marker: BoardPosition that a marker will be placed in
     * @param player: the character that represents what player is taking their turn
     * @pre checkSpace = true
     * @post char player is placed at BoardPosition marker
     */
    void placeMarker(BoardPosition marker, char player);

    /**
     * This function calls all winner functions to check if any are true to see if there
     * is a winning sequence.
     * @param lastPos: BoardPosition that the marker was placed in
     * @return true if player has won any direction, false if there is no win
     * @pre 0 <= lastPos.getRow() < rowLength and 0 <= lastPos.getColumn() < colLength
     * @post checkForWinner iff (checkHorizontalWin || checkVerticalWin || checkDiagonalWin)
     */
    default boolean checkForWinner(BoardPosition lastPos) {
        char player = whatsAtPos(lastPos);
        // see what char is at the last position and if there is any win, then it is true
        return checkHorizontalWin(lastPos, player) || checkVerticalWin(lastPos, player) ||
                checkDiagonalWin(lastPos, player);
    }

    /**
     * This function loops through each position to check if the position is empty, and if there are no
     * empty positions and no winners, there is a draw.
     * @return true if GameBoard is full with no winner, false if GameBoard has a blank position
     * @pre checkForWinner = false
     * @post checkForDraw iff (whatsAtPos == ' ' for all positions)
     */
    default boolean checkForDraw() {
        BoardPosition myPos;
        // loops through both rows and columns
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                // assigns a new board position for the respectful row and column
                myPos = new BoardPosition(i, j);
                // if the position is not empty then it returns true
                if (whatsAtPos(myPos) == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This function goes through each row to check if there is a winning sequence.
     * @param lastPos: BoardPosition that the marker was placed in
     * @param player: the character that represents what player is taking their turn
     * @return true if there is winningNum in a row horizontally, false if there is not
     * @pre 0 <= lastPos.getRow() < rowLength and 0 <= lastPos.getColumn() < colLength
     * @post checkHorizontalWin iff (board contains winNum places where the char is the same horizontally)
     */
    default boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        // declaring the count to be 1 because the current position already has the character
        int charCount = 1;

        // moving right
        int moveColumn = lastPos.getColumn() + 1;
        BoardPosition pos = new BoardPosition(lastPos.getRow(), moveColumn);
        while (moveColumn < getNumColumns() && charCount != getNumToWin() && isPlayerAtPos(pos, player)) {
            charCount++;
            moveColumn++;
            pos = new BoardPosition(lastPos.getRow(), moveColumn);
        }

        // moving left
        moveColumn = lastPos.getColumn() - 1;
        pos = new BoardPosition(lastPos.getRow(), moveColumn);
        while (moveColumn >= 0 && charCount != getNumToWin() && isPlayerAtPos(pos, player)) {
            charCount++;
            moveColumn--;
            pos = new BoardPosition(lastPos.getRow(), moveColumn);
        }

        return charCount == getNumToWin();
    }

    /**
     * This function goes through each column to check if there is a winning sequence.
     * @param lastPos: BoardPosition that the marker was placed in
     * @param player: the character that represents what player is taking their turn
     * @return true if there is five in a row vertically, false if there is not
     * @pre 0 <= lastPos.getRow() < rowLength and 0 <= lastPos.getColumn() < colLength
     * @post checkVerticalWin iff (board contains winNum places where the char is the same vertically)
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player) {
        // declaring the count to be 1 because the current position already has the character
        int charCount = 1;

        // moving down
        int moveRow = lastPos.getRow() + 1;
        BoardPosition pos = new BoardPosition(moveRow, lastPos.getColumn());
        while (moveRow < getNumRows() && charCount != getNumToWin() && isPlayerAtPos(pos, player)) {
            charCount++;
            moveRow++;
            pos = new BoardPosition(moveRow, lastPos.getColumn());
        }

        // moving up
        moveRow = lastPos.getRow() - 1;
        pos = new BoardPosition(moveRow, lastPos.getColumn());
        while (moveRow >= 0 && charCount != getNumToWin() && isPlayerAtPos(pos, player)) {
            charCount++;
            moveRow--;
            pos = new BoardPosition(moveRow, lastPos.getColumn());
        }

        return charCount == getNumToWin();
    }

    /**
     * This function goes through all the combinations of diagonal wins in different rows and columns to
     * check if there is a winning sequence.
     * @param lastPos: BoardPosition that the marker was placed in
     * @param player: the character that represents what player is taking their turn
     * @return true if there is five in a row diagonally, false if there is not
     * @pre 0 <= lastPos.getRow() < rowLength and 0 <= lastPos.getColumn() < colLength
     * @post checkDiagonalWin iff (board contains winNum places where the char is the same diagonally)
     */
    default boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        // declaring the count to be 1 because the current position already has the character
        int charCount = 1;

        // moving left and down
        int moveRow = lastPos.getRow() + 1;
        int moveColumn = lastPos.getColumn() - 1;
        BoardPosition pos = new BoardPosition(moveRow, moveColumn);
        while (moveRow < getNumRows() && moveColumn >= 0 &&
                charCount != getNumToWin() && isPlayerAtPos(pos, player)) {
            charCount++;
            moveRow++;
            moveColumn--;
            pos = new BoardPosition(moveRow, moveColumn);
        }

        // moving right and up
        moveRow = lastPos.getRow() - 1;
        moveColumn = lastPos.getColumn() + 1;
        pos = new BoardPosition(moveRow, moveColumn);
        while (moveRow >= 0 && moveColumn < getNumColumns() &&
                charCount != getNumToWin() && isPlayerAtPos(pos, player)) {
            charCount++;
            moveRow--;
            moveColumn++;
            pos = new BoardPosition(moveRow, moveColumn);
        }

        // moving right and down
        moveRow = lastPos.getRow() + 1;
        moveColumn = lastPos.getColumn() + 1;
        pos = new BoardPosition(moveRow, moveColumn);
        while (moveRow < getNumRows() && moveColumn < getNumColumns() &&
                charCount != getNumToWin() && isPlayerAtPos(pos, player)) {
            charCount++;
            moveRow++;
            moveColumn++;
            pos = new BoardPosition(moveRow, moveColumn);
        }

        // moving left and up
        moveRow = lastPos.getRow() - 1;
        moveColumn = lastPos.getColumn() - 1;
        pos = new BoardPosition(moveRow, moveColumn);
        while (moveRow >= 0 && moveColumn >= 0 &&
                charCount != getNumToWin() && isPlayerAtPos(pos, player)) {
            charCount++;
            moveRow--;
            moveColumn--;
            pos = new BoardPosition(moveRow, moveColumn);
        }

        return charCount == getNumToWin();
    }

    /**
     * This function checks a board position to see what is contained in it.
     * @param pos: BoardPosition that will be examined
     * @return the character that pos contains, either a character or ' '
     * @pre 0 <= pos.getRow() < rowLength and 0 <= pos.getColumn() < colLength
     * @post char in position pos is returned
     */
    char whatsAtPos(BoardPosition pos);

    /**
     * This function checks to see if a certain character is contained at a certain position.
     * @param pos: BoardPosition that will be examined
     * @param player: the character that represents what player is taking their turn
     * @return true if the pos contains the char that the player contains, false if it doesn't
     * @pre 0 <= pos.getRow() < rowLength and 0 <= p.getColumn() < colLength
     * @post isPlayerAtPos iff (whatsAtPos(pos) == player)
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player) {
        // compared the player to the char at the position
        char atPos = whatsAtPos(pos);
        return player == atPos;
    }
}
