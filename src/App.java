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
        placeShips(scanner, player1, "Player 1");
        placeShips(scanner, player2, "Player 2");

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

    private static void placeShips(Scanner scanner, Board board, String playerName) {
        int shipLengths[] = {2,3,3,4,5};
        board.printBoard(true);
        System.out.println(playerName + ", place your ships (Enter coordinate then 0 or 1 for horizontal or vertical respectively, no spaces e.g 'A10')");
        for (int length : shipLengths) {
            int placement[];
            System.out.printf("Ship length %d\n", length);
            placement = getShipPlaceInput(scanner);
            board.placeShip(placement[1], placement[0], intToBool(placement[2]), length);
            board.printBoard(true);
        }
    }

    private static int[] getShipPlaceInput(Scanner scanner) {
        System.out.println("Input ship placement:");
        String input = scanner.next();
        char x = input.charAt(0);
        x = Character.toUpperCase(x);
        x -= 65;
        int y = input.charAt(1) - 48;
        int rotation = input.charAt(2) - 48;

        //validate
        int output[] = {x, y, rotation};
        if (x < 0 || y < 0 || x > 9 || y > 9 || (rotation != 0 && rotation != 1)) {
            System.out.println("Invalid move. Try again.");
            output = getShipPlaceInput(scanner);
        }
        return output;
    }

    private static boolean intToBool(int value) {
        if (value == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}