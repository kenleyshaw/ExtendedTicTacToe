package cpsc2150.extendedTicTacToe;
import java.util.*;

public abstract class AbsGameBoard implements IGameBoard {
    /**
     * This function overrides the toString() function to present the formatted game board where there
     * is space to enter a character, the rows and columns are labeled, and there are slashes in between each space.
     * @return the formatted GameBoard in its current state
     * @post GameBoard formatted as a string
     */
    @Override
    public String toString() {
        // creates new board position
        BoardPosition place;
        // start of the String is a space
        StringBuilder gameString = new StringBuilder("    ");

        // creates this variable to be able to format the game board to allow for equal spacing between
        // one digit and two digit numbers that label each row and column
        int twoDigits = 10;

        // loops through columns and adds a | after each
        for (int i = 0; i < getNumColumns(); i++) {
            gameString.append(i).append("|");
            // if the column number is less than two digits, a space is added to compensate for the extra digit
            if (i < twoDigits - 1) {
                gameString.append(" ");
            }
        }
        gameString.append("\n");

        // loops through rows and adds a | after each
        for (int j = 0; j < getNumRows(); j++) {
            // if the row number is less than two digits, a space is added to compensate for the extra digit
            if (j < twoDigits) {
                gameString.append(" ").append(j).append("| ");
            }
            else {
                gameString.append(j).append("| ");
            }
            // loops through columns again and adds a | after each board position
            for (int k = 0; k < getNumColumns(); k++) {
                place = new BoardPosition(j, k);
                gameString.append(whatsAtPos(place)).append("|");
                gameString.append(" ");
            }
            gameString.append("\n");
        }

        return gameString.toString();
    }
}
