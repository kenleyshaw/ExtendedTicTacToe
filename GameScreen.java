package cpsc2150.extendedTicTacToe;

import java.util.*;

public class GameScreen {

    public static void main(String[] args) {
        // declaring all initial variables
        IGameBoard myBoard;
        BoardPosition boardPos;
        Scanner scanner = new Scanner(System.in);
        int row, rowLength;
        int column, colLength;
        int winNum;
        boolean done;
        boolean validInput = false;
        boolean play = true;
        final int MAXPLAYERS = 10;
        final int MINPLAYERS = 2;
        List<Character> players;
        int numPlayers;
        int nextPlayer;
        String choice;

        // while the user is playing again
        while (play) {
            // set all variables included in the statement to be their initial value
            boardPos = null;
            done = false;
            rowLength = 0;
            colLength = 0;
            winNum = 0;
            numPlayers = 0;
            nextPlayer = 0;
            choice = " ";
            // creates new game board
            myBoard = null;
            // creates new list of players
            players = new ArrayList<Character>();

            // while the number of players is not in bounds
            while (numPlayers > MAXPLAYERS || numPlayers < MINPLAYERS) {
                System.out.println("How many players?");
                numPlayers = scanner.nextInt();
                // if the input is greater than the max, a message is printed
                if (numPlayers > MAXPLAYERS) {
                    System.out.println("Must be 10 players or fewer.");
                }
                // if the input is less than the min, a message is printed
                else if (numPlayers < MINPLAYERS) {
                    System.out.println("Must be at least 2 players.");
                }
            }

            // loops through the number of players, starts at 1 because there is no "player 0"
            for (int i = 1; i <= numPlayers; i++) {
                Character myPlayer = null;
                // variable that allows the code to loop if a token is already taken
                boolean redo = true;
                // while a token is already taken or player list is empty
                while (redo || players.isEmpty()) {
                    System.out.println("Enter the character to represent player " + i);
                    // gets player character
                    myPlayer = scanner.next().toUpperCase().charAt(0);
                    // if the list of players contains the new player character, then a message is printed
                    if (players.contains(myPlayer)) {
                        System.out.println(myPlayer + " is already taken as a player token!");
                    }
                    // if a character is available or the list isn't empty then a player is added and the loop ends
                    else {
                        players.add(myPlayer);
                        redo = false;
                    }
                }
            }

            // while the row length is less than the min or greater than the max
            while (rowLength < GameBoard.MINROWCOL || rowLength > GameBoard.MAXROWCOL) {
                System.out.println("How many rows?");
                rowLength = scanner.nextInt();
                // if it is less than the min or greater than the max, then a message is printed
                if (rowLength < GameBoard.MINROWCOL || rowLength > GameBoard.MAXROWCOL)
                    System.out.println("Rows must be between " + GameBoard.MINROWCOL + " and " + GameBoard.MAXROWCOL);
            }

            // while the column length is less than the min or greater than the max
            while (colLength < GameBoard.MINROWCOL || colLength > GameBoard.MAXROWCOL) {
                System.out.println("How many columns?");
                colLength = scanner.nextInt();
                // if it is less than the min or greater than the max, then a message is printed
                if (colLength < GameBoard.MINROWCOL || colLength > GameBoard.MAXROWCOL)
                    System.out.println("Columns must be between " + GameBoard.MINROWCOL + " and " + GameBoard.MAXROWCOL);
            }

            // while the winning number is less than the min or greater than the max or not within bounds of the dimensions of the board
            while (winNum < GameBoard.MINWINNUM || winNum > GameBoard.MAXWINNUM || winNum > rowLength || winNum > colLength) {
                System.out.println("How many in a row to win?");
                winNum = scanner.nextInt();
                // if it is less than the min or greater than the max or not within the bounds of the board, then a message is printed
                if (winNum < GameBoard.MINWINNUM || winNum > GameBoard.MAXWINNUM || winNum > rowLength || winNum > colLength)
                    System.out.println("The number in to win must be between " + GameBoard.MINWINNUM + " and " + GameBoard.MAXWINNUM +
                            " and within " + rowLength + " rows and " + colLength + " columns.");
            }

            // creates new board game taking it the parameters created from the last 3 functions
            // while choice doesn't equal fast or memory efficient, keep looping until the input is correct
            while (!choice.toUpperCase().equals("F") && !choice.toUpperCase().equals("M")) {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?)");
                choice = scanner.nextLine();
                // if choice is equal to fast, then a game board is initiated using the game board constructor
                if (choice.toUpperCase().equals("F")) {
                    myBoard = new GameBoard(rowLength, colLength, winNum);
                }
                // if choice is equal to memory efficient, then a game board is initiated using the game board mem constructor
                if (choice.toUpperCase().equals("M")) {
                    myBoard = new GameBoardMem(rowLength, colLength, winNum);
                }
                else {
                    System.out.println("Please enter F or M");
                }
            }

            // while the game is not done
            while (!done) {
                // calls print method to print the board
                printBoard(myBoard.toString());

                // while the input is not valid
                while (!validInput) {
                    System.out.println("Player " + players.get(nextPlayer) + " Please enter your ROW.");
                    row = scanner.nextInt();
                    System.out.println("Player " + players.get(nextPlayer) + " Please enter your COLUMN.");
                    column = scanner.nextInt();
                    // gets player's input and assigns to the coordinates of a board position
                    boardPos = new BoardPosition(row, column);
                    // if the board has that position available then a marker is placed and the input is valid
                    if (myBoard.checkSpace(boardPos)) {
                        myBoard.placeMarker(boardPos, players.get(nextPlayer));
                        validInput = true;
                    }
                    // if the position is not available, the while loop is looped again
                    else {
                        System.out.println("That space is unavailable, please pick again");
                        printBoard(myBoard.toString());
                    }
                }
                validInput = false;

                // if there is a winner, a message is printed and the game is done
                if (myBoard.checkForWinner(boardPos)) {
                    System.out.println("Player " + players.get(nextPlayer) + " wins!");
                    printBoard(myBoard.toString());
                    done = true;
                }

                // if there is a tie, a message is printed and the game is done
                if (myBoard.checkForDraw()) {
                    System.out.println("The game ended in a draw.");
                    printBoard(myBoard.toString());
                    done = true;
                }

                // if the next player iterator is less than the number of players minus 1 (subtracting 1 represents
                // a turn that has already been taken), then the players iterate to the next player
                if (nextPlayer < numPlayers - 1) {
                    nextPlayer++;
                }
                // else there is no next player
                else {
                    nextPlayer = 0;
                }
            }
            // asks the user to play again
            System.out.println("Would you like to play again? Y/N");
            String playChoice = scanner.next().toLowerCase();
            // if the user says no, the boolean to play again is set to false so the loop ends
            if (playChoice.equals("n")) {
                play = false;
            }
        }
    }

    /**
     * @post
     * GameScreen.length = rowLength
     * GameScreen[].length = colLength
     */
    public GameScreen() {
        GameBoard myGameBoard;
    }

    /**
     * @param gameBoard: string that is in the format of the game board
     * @post gameBoard printed on the command line
     */
    public static void printBoard(String gameBoard) {
        System.out.println(gameBoard);
    }
}
