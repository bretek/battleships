import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Board player1 = new Board(10, 10);
        Board player2 = new Board(10, 10);
        Board currentPlayerBoard = player2;
        boolean gameOver = false;
        boolean player = false;

        Scanner scanner = new Scanner(System.in);

        //place ships
        player2.placeShip(3, 3, false, 4);
        player1.placeShip(6, 7, true, 4);

        while (!gameOver) {
            if (player) {
                System.out.println("Player 1s ships:");
            }
            else {
                System.out.println("Player 2s ships:");
            }
            currentPlayerBoard.printBoard(false);
            int[] input;
            do {
                input = getInput(scanner);
            }
            while (!currentPlayerBoard.makeTurn(input[1], input[0]));
            if (currentPlayerBoard.checkWin()) {
                gameOver = true;
                System.out.println("You won!");
            }

            currentPlayerBoard.printBoard(false);

            if (player) {
                player1 = currentPlayerBoard;
                currentPlayerBoard = player2;
            }
            else {
                player2 = currentPlayerBoard;
                currentPlayerBoard = player1;
            }
            player = !player;
        }

        scanner.close();
    }

    private static int[] getInput(Scanner scanner) {
        //input turn
        System.out.println("Input your move:");
        String input = scanner.next();
        char x = input.charAt(0);
        x = Character.toUpperCase(x);
        x -= 65;
        int y = input.charAt(1) - 48;

        //validate
        int output[] = {x, y};
        if (x < 0 || y < 0 || x > 9 || y > 9) {
            System.out.println("Invalid move. Try again.");
            output = getInput(scanner);
        }
        return output;
    }
}