import java.util.Scanner;

public class TicTacToe {
    // 3x3 Tic Tac Toe board initialized with empty spaces
    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char currentPlayer = 'X';    // X always starts first
        boolean gameEnded = false;   // Flag to check if game is over

        // Game loop runs until game ends (win or draw)
        while (!gameEnded) {
            printBoard();   // Show the board before each move
            System.out.println("Player " + currentPlayer + 
                               ", enter your row and column (0, 1, or 2): ");
            
            // Read player's move (row and column)
            int row = sc.nextInt();
            int col = sc.nextInt();

            // Check if the chosen cell is empty
            if (board[row][col] == ' ') {
                // Place player's mark on the board
                board[row][col] = currentPlayer;

                // Check if current player has won
                if (hasWon(currentPlayer)) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameEnded = true;   // End game
                } 
                // Check for draw (if board is full)
                else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    gameEnded = true;   // End game
                } 
                // Otherwise, switch player and continue
                else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                // If cell is already taken
                System.out.println("That spot is already taken. Try again.");
            }
        }

        sc.close(); // Close scanner to prevent memory leak
    }

    // Method to print the current board
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | "); // Print each cell
            }
            System.out.println("\n-------------");
        }
    }

    // Method to check if a player has won
    public static boolean hasWon(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (
                (board[i][0] == player && board[i][1] == player && board[i][2] == player) || // row
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)    // column
            ) return true;
        }

        // Check diagonals
        if (
            (board[0][0] == player && board[1][1] == player && board[2][2] == player) || // left-to-right diagonal
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)    // right-to-left diagonal
        ) return true;

        return false; // If no win condition met
    }

    // Method to check if the board is full (draw condition)
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') // If any empty space exists
                    return false;
        return true; // No empty spaces → board full
    }
}
