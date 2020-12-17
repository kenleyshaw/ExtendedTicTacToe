package cpsc2150.extendedTicTacToe;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoardMem {
    // private method to use factory method design pattern so that this gameboard
    // can be compared with the string representation
    private IGameBoard testGB(int row, int column, int winNum) {
        return new GameBoard(row, column, winNum);
    }

    // string representation of the gameboard using the same method from absGameBoard
    private String stringBoard(char[][] myBoard) {
        StringBuilder gameString = new StringBuilder("    ");
        int twoDigits = 10;
        for (int i = 0; i < myBoard[0].length; i++) {
            gameString.append(i).append("|");
            if (i < twoDigits - 1) {
                gameString.append(" ");
            }
        }
        gameString.append("\n");
        for (int j = 0; j < myBoard.length; j++) {
            if (j < twoDigits) {
                gameString.append(" ").append(j).append("| ");
            }
            else {
                gameString.append(j).append("| ");
            }
            for (int k = 0; k < myBoard[0].length; k++) {
                gameString.append(myBoard[j][k]).append("|");
                gameString.append(" ");
            }
            gameString.append("\n");
        }
        return gameString.toString();
    }

    // testing a gameboard with the minimum rows, columns, and winning number
    @Test
    public void testConstructorMin() {
        char[][] testBoard = new char[3][3];
        for (int rows = 0; rows < 3; rows++) {
            for (int cols = 0; cols < 3; cols++) {
                testBoard[rows][cols] = ' ';
            }
        }
        IGameBoard gb = testGB(3,3,3);
        assertTrue(stringBoard(testBoard).equals(gb.toString())
                && gb.getNumRows() == 3 && gb.getNumColumns() == 3 && gb.getNumToWin() == 3);
    }

    // testing a gameboard with the maximum rows, columns, and winning number
    @Test
    public void testConstructorMax() {
        char[][] testBoard = new char[100][100];
        for (int rows = 0; rows < 100; rows++) {
            for (int cols = 0; cols < 100; cols++) {
                testBoard[rows][cols] = ' ';
            }
        }
        IGameBoard gb = testGB(100,100,25);
        assertTrue(stringBoard(testBoard).equals(gb.toString())
                && gb.getNumRows() == 100 && gb.getNumColumns() == 100 && gb.getNumToWin() == 25);
    }

    // testing a gameboard with random rows, columns, and winning number
    @Test
    public void testConstructorMiddle() {
        char[][] testBoard = new char[30][40];
        for (int rows = 0; rows < 30; rows++) {
            for (int cols = 0; cols < 40; cols++) {
                testBoard[rows][cols] = ' ';
            }
        }
        IGameBoard gb = testGB(30,40,10);
        assertTrue(stringBoard(testBoard).equals(gb.toString())
                && gb.getNumRows() == 30 && gb.getNumColumns() == 40 && gb.getNumToWin() == 10);
    }

    // testing that a space taken is not available
    @Test
    public void testCheckSpaceTaken() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        assertFalse(gb.checkSpace(new BoardPosition(0,0)));
    }

    // testing that a space in an empty row is available
    @Test
    public void testCheckSpaceEmptyRow() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        assertTrue(gb.checkSpace(new BoardPosition(2,0)));
    }

    // testing that a space in an empty column is available
    @Test
    public void testCheckSpaceEmptyCol() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        assertTrue(gb.checkSpace(new BoardPosition(0,2)));
    }

    // testing that there is a horizontal win starting from the left
    @Test
    public void testCheckHorizontalWinLeft() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        assertTrue(gb.checkHorizontalWin(new BoardPosition(0,0), 'X'));
    }

    // testing that there is a horizontal win starting from the right
    @Test
    public void testCheckHorizontalWinRight() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        assertTrue(gb.checkHorizontalWin(new BoardPosition(0,2), 'X'));
    }

    // testing that there is a horizontal win when the last character is placed in the middle
    @Test
    public void testCheckHorizontalWinMiddle() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        assertTrue(gb.checkHorizontalWin(new BoardPosition(0,1), 'X'));
    }

    // testing that there is not a horizontal win anywhere on the board
    @Test
    public void testCheckHorizontalWinFalse() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        assertFalse(gb.checkHorizontalWin(new BoardPosition(0, 0), 'X'));
    }

    // testing that there is a vertical win starting from the top
    @Test
    public void testCheckVerticalWinDown() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        assertTrue(gb.checkVerticalWin(new BoardPosition(0,0), 'X'));
    }

    // testing that there is a vertical win starting from the bottom
    @Test
    public void testCheckVerticalWinUp() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        assertTrue(gb.checkVerticalWin(new BoardPosition(2,0), 'X'));
    }

    // testing that there is a vertical win when the last character is placed in the middle
    @Test
    public void testCheckVerticalWinMiddle() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        assertTrue(gb.checkVerticalWin(new BoardPosition(1,0), 'X'));
    }

    // testing that there is not a vertical win anywhere on the board
    @Test
    public void testCheckVerticalWinFalse() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        assertFalse(gb.checkVerticalWin(new BoardPosition(0, 0), 'X'));
    }

    // testing that there is a diagonal win starting from the top left
    @Test
    public void testCheckDiagonalWinLeftTopLeft() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        assertTrue(gb.checkDiagonalWin(new BoardPosition(0,0), 'X'));
    }

    // testing that there is a diagonal win starting from the bottom right
    @Test
    public void testCheckDiagonalWinLeftBottomRight() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        assertTrue(gb.checkDiagonalWin(new BoardPosition(2,2), 'X'));
    }

    // testing that there is a diagonal win on the left diagonal starting from the middle
    @Test
    public void testCheckDiagonalWinLeftMiddle() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        assertTrue(gb.checkDiagonalWin(new BoardPosition(1,1), 'X'));
    }

    // testing that there is a diagonal win starting from the top right
    @Test
    public void testCheckDiagonalWinRightTopRight() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(0,0), 'O');
        assertTrue(gb.checkDiagonalWin(new BoardPosition(0,2), 'X'));
    }

    // testing that there is a diagonal win starting from the bottom left
    @Test
    public void testCheckDiagonalWinRightBottomLeft() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(0,0), 'O');
        assertTrue(gb.checkDiagonalWin(new BoardPosition(2,0), 'X'));
    }

    // testing that there is a diagonal win on the right diagonal starting from the middle
    @Test
    public void testCheckDiagonalWinRightMiddle() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(0,0), 'O');
        assertTrue(gb.checkDiagonalWin(new BoardPosition(1,1), 'X'));
    }

    // testing that there is not a diagonal win anywhere on the board
    @Test
    public void testCheckDiagonalWinFalse() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        assertFalse(gb.checkDiagonalWin(new BoardPosition(0, 0), 'X'));
    }

    // testing that there is not a draw when the board is empty
    @Test
    public void testCheckForDrawEmpty() {
        IGameBoard gb = testGB(3,3,3);
        assertFalse(gb.checkForDraw());
    }

    // testing that there is a draw when the board is full and theres no winner
    @Test
    public void testCheckForDrawFull() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertTrue(gb.checkForDraw());
    }

    // testing that there is not a draw when the maximum row is empty
    @Test
    public void testCheckForDrawEmptyMaxRow() {
        IGameBoard gb = testGB(4,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertFalse(gb.checkForDraw());
    }

    // testing that there is not a draw when the maximum column is empty
    @Test
    public void testCheckForDrawEmptyMaxCol() {
        IGameBoard gb = testGB(3,4,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertFalse(gb.checkForDraw());
    }

    // testing that a position contains a space if a marker is not placed in it
    @Test
    public void testWhatsAtPosEmpty() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertEquals(' ', gb.whatsAtPos(new BoardPosition(0, 0)));
    }

    // testing that a position in the maximum row can be correlated to a marker
    @Test
    public void testWhatsAtPosMaxRow() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertEquals('O', gb.whatsAtPos(new BoardPosition(2, 0)));
    }

    // testing that a position in the maximum column can be correlated to a marker
    @Test
    public void testWhatsAtPosMaxCol() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertEquals('X', gb.whatsAtPos(new BoardPosition(0, 2)));
    }

    // testing that a position in the middle of the board can be correlated to a marker
    @Test
    public void testWhatsAtPosMiddle() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertEquals('X', gb.whatsAtPos(new BoardPosition(1, 1)));
    }

    // testing that the last entered position can be correlated to a value, similar to how maps store keys
    @Test
    public void testWhatsAtPosMap() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertEquals('O', gb.whatsAtPos(new BoardPosition(2, 2)));
    }

    // testing to see if there is a player at a random position if there is a marker
    // correlating to the player at that position
    @Test
    public void testIsPlayerAtPosTrue() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertTrue(gb.isPlayerAtPos(new BoardPosition(1,1), 'X'));
    }

    // testing to see that there is not a player at the position if there is no marker correlating to
    // the player at the position
    @Test
    public void testIsPlayerAtPosFalse() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertFalse(gb.isPlayerAtPos(new BoardPosition(2, 2), 'X'));
    }

    // testing to see if there is not a player at the position if the position contains an empty space
    @Test
    public void testIsPlayerAtPosEmptyFalse() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertFalse(gb.isPlayerAtPos(new BoardPosition(0, 0), 'X'));
    }

    // testing to see if there is a player at a position in the maximum row if that position
    // contains the marker that correlates to the player
    @Test
    public void testIsPlayerAtPosMaxRow() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertTrue(gb.isPlayerAtPos(new BoardPosition(2,0), 'O'));
    }

    // testing to see if there is a player at a position in the maximum column if that position
    // contains the marker that correlates to the player
    @Test
    public void testIsPlayerAtPosMaxCol() {
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertTrue(gb.isPlayerAtPos(new BoardPosition(0,2), 'X'));
    }

    // testing to see if a marker can be placed in the maximum row
    @Test
    public void testPlaceMarkerMaxRow() {
        char[][] testBoard = new char[3][3];
        for (int rows = 0; rows < 3; rows++) {
            for (int cols = 0; cols < 3; cols++) {
                testBoard[rows][cols] = ' ';
            }
        }
        testBoard[2][0] = 'X';
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(2,0), 'X');
        assertEquals(stringBoard(testBoard), gb.toString());
    }

    // testing to see if a marker can be placed in the maximum column
    @Test
    public void testPlaceMarkerMaxCol() {
        char[][] testBoard = new char[3][3];
        for (int rows = 0; rows < 3; rows++) {
            for (int cols = 0; cols < 3; cols++) {
                testBoard[rows][cols] = ' ';
            }
        }
        testBoard[0][2] = 'X';
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,2), 'X');
        assertEquals(stringBoard(testBoard), gb.toString());
    }

    // testing to see if a marker can be placed in a position in the middle of the board
    @Test
    public void testPlaceMarkerMiddle() {
        char[][] testBoard = new char[3][3];
        for (int rows = 0; rows < 3; rows++) {
            for (int cols = 0; cols < 3; cols++) {
                testBoard[rows][cols] = ' ';
            }
        }
        testBoard[1][1] = 'X';
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(1,1), 'X');
        assertEquals(stringBoard(testBoard), gb.toString());
    }

    // testing to see that multiple markers can be placed in the board at once
    @Test
    public void testPlaceMarkerMultiPlayer() {
        char[][] testBoard = new char[3][3];
        for (int rows = 0; rows < 3; rows++) {
            for (int cols = 0; cols < 3; cols++) {
                testBoard[rows][cols] = ' ';
            }
        }
        testBoard[1][1] = 'X';
        testBoard[2][2] = 'O';
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        assertEquals(stringBoard(testBoard), gb.toString());
    }

    // testing to see that a full board of multiple markers can exist as long as there
    // are not over 25 players
    @Test
    public void testPlaceMarkerFullPlayers() {
        char[][] testBoard = new char[3][3];
        for (int rows = 0; rows < 3; rows++) {
            for (int cols = 0; cols < 3; cols++) {
                testBoard[rows][cols] = ' ';
            }
        }
        testBoard[0][0] = 'A';
        testBoard[1][1] = 'B';
        testBoard[2][1] = 'C';
        testBoard[0][2] = 'D';
        testBoard[1][2] = 'E';
        testBoard[0][1] = 'F';
        testBoard[1][0] = 'G';
        testBoard[2][0] = 'H';
        testBoard[2][2] = 'I';
        IGameBoard gb = testGB(3,3,3);
        gb.placeMarker(new BoardPosition(0,0), 'A');
        gb.placeMarker(new BoardPosition(1,1), 'B');
        gb.placeMarker(new BoardPosition(2,1), 'C');
        gb.placeMarker(new BoardPosition(0,2), 'D');
        gb.placeMarker(new BoardPosition(1,2), 'E');
        gb.placeMarker(new BoardPosition(0,1), 'F');
        gb.placeMarker(new BoardPosition(1,0), 'G');
        gb.placeMarker(new BoardPosition(2,0), 'H');
        gb.placeMarker(new BoardPosition(2,2), 'I');
        assertEquals(stringBoard(testBoard), gb.toString());
    }
}

