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
            String input;
            char x;
            int y;
            do {
                //input turn
                System.out.println("Input your move:");
                input = scanner.next();
                x = input.charAt(0);
                System.out.println(x);
                if (65 <= x && x <= 90) {
                    x -= 65;
                }
                else {
                    x -= 97;
                }
                y = input.charAt(1)-48;
                System.out.println(y);
            }
            while (!currentPlayerBoard.makeTurn(y, x));
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
}