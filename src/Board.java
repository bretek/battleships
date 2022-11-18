public class Board {
    private int[][] board; //0 = water, 1 = missed, 2 = ship, 3 = hit ship
    public Board(int width, int height) {
        this.board = new int[width][height];
    }

    public boolean placeShip(int x, int y, boolean rotation, int length) { //true if valid position
        boolean valid = true;
        if (rotation) {
            for (int vert = x; vert < x + length; vert++) {
                if (board[vert][y] != 0 || vert > board.length) {
                    valid = false;
                }
            }
        }
        else {
            for (int horz = y; horz < y + length; horz++) {
                if (board[x][horz] != 0 || horz > board[0].length) {
                    valid = false;
                }
            }
        }
        if (valid) {
            if (rotation) {
                for (int offset = 0; offset < length; offset++) {
                    board[x+offset][y] = 2;
                }
            }
            else {
                for (int offset = 0; offset < length; offset++) {
                    board[x][y+offset] = 2;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    public boolean makeTurn(int x, int y) { //true if move is valid, false otherwise
        if (board[x][y] != 0 && board[x][y] != 2) {
            System.out.println("Space is not empty!");
            return false;
        }
        else if(board[x][y] == 2) {
            board[x][y] = 3;
        }
        else {
            board[x][y] = 1;
        }
        return true;
    }

    public boolean checkWin() { //returns true if won
        boolean won = true;
        for (int[] row : board) {
            for (int square : row) {
                if (square == 2) {
                    won = false;
                }
            }
        }
        return won;
    }

    public void printBoard(boolean showShips) {
        System.out.print(' ');
        for (char c = 'A'; c < board.length+65; ++c) {
            System.out.print(c);
        }
        System.out.print('\n');
        for (int row = 0; row < board.length; row++) {
            System.out.print(row);
            for (int col = 0; col < board[0].length; col++) {
                char character = ' ';
                int square = board[row][col];
                switch (square) {
                    case 1:
                        character = 'O';
                        break;
                    case 2:
                        if (showShips) {
                            character = 'O';
                        }
                        break;
                    case 3:
                        character = 'X';
                        break;
                }
                System.out.print(character);
            }
            System.out.print('\n');
        }
    }

}
