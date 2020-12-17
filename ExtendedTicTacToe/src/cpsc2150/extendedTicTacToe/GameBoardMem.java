package cpsc2150.extendedTicTacToe;
import java.util.*;

/**
 * @invariant MINROWCOL <= rows <= MAXROWCOL
 * @invariant MINROWCOL <= columns <= MAXROWCOL
 * @invariant MINWINNUM <= winNum <= MAXWINNUM
 *
 * Correspondences:
 * rowLength = rows
 * colLength = columns
 * winningNum = winNum
 * [positions in the list are in the bounds of the board]
 */

public class GameBoardMem extends AbsGameBoard {

    private int rows;
    private int columns;
    private int winNum;
    private Map<Character, List<BoardPosition>> memBoard;

    GameBoardMem(int rowLength, int colLength, int winnerNum) {
        rows = rowLength;
        columns = colLength;
        winNum = winnerNum;
        // creation of hash map to hold board position and player data
        memBoard = new HashMap<>();
    }

    public void placeMarker(BoardPosition marker, char player) {
        // if the hash map contains the key that the board position will be marked by
        if (memBoard.containsKey(marker)) {
            // the marker is added to the player's list of positions
            memBoard.get(player).add(marker);
            return;
        }
        // if it doesn't contain the key
        else if (!(memBoard.containsKey(marker))) {
            // a new board position list is made for that key
            List<BoardPosition> posList = new ArrayList<>();
            // the marker is to the player's list
            posList.add(marker);
            memBoard.put(player, posList);
            return;
        }
    }

    public char whatsAtPos(BoardPosition pos) {
        // loops through each entry in the map
        for (Map.Entry<Character, List<BoardPosition>> charMap: memBoard.entrySet()) {
            // loops through each key
            for (int i = 0; i < charMap.getValue().size(); i++) {
                // if the key is equal to the key in the board position, then the key is returned
                if (charMap.getValue().get(i).equals(pos)) {
                    return charMap.getKey();
                }
            }
        }
        // else an empty string is returned
        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        // loops through each position in the hash map and gets the player
        for (BoardPosition myPos: memBoard.get(player)) {
            // if the position where the player is is equal to the entered position, return true
            if (myPos.equals(pos)) {
                return true;
            }
        }
        // else return false
        return false;
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
}
