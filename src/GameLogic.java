import java.util.Arrays;

class GameLogic {
    private static void floodFill(char[][] board, int verticalCoordinate, int horizontalCoordinate, char old, char replace){
        if (verticalCoordinate < 0 || verticalCoordinate >= board.length || horizontalCoordinate < 0 || horizontalCoordinate >= board[0].length){
            return;
        }
        if (board[verticalCoordinate][horizontalCoordinate] != old){
            return;
        }
        if (old == replace){
            return;
        }

        board[verticalCoordinate][horizontalCoordinate] = replace;

        floodFill(board, verticalCoordinate, horizontalCoordinate + 1, old, replace);
        floodFill(board, verticalCoordinate - 1, horizontalCoordinate, old, replace);
        floodFill(board, verticalCoordinate, horizontalCoordinate - 1, old, replace);
        floodFill(board, verticalCoordinate + 1, horizontalCoordinate, old, replace);
    }

     static void onclick(int i, int j, char[][] board){
            floodFill(board, i, j, board[i][j], '-'); //'-' denotes empty space
            nextBoardState(board, "down"); //SameGame only allows for down and left direction
            nextBoardState(board, "left");
    }

    private static void swapElement(char[][] board, int firstI, int firstJ, int secondI, int secondJ){
        char tmp = board[firstI][firstJ];
        board[firstI][firstJ] = board[secondI][secondJ];
        board[secondI][secondJ] = tmp;
    }

    private static void shiftBlocksOneRound(char[][] board, String direction){
        Direction tmp = new Direction(direction);
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                //Find empty space under a block and swap them i.e apply gravity
                /*checkInBound according to current shift direction (checkInBound all 4 direction causes OutOfBoundException)
                * -> Resolved: Created new class Direction that handles direction according to this method "direction" parameter
                */
                if (board[i][j] != '-' && tmp.checkInBound(i,j,board.length,board[i].length)){
                    if (board[i + tmp.indexVerticalDirection][j + tmp.indexHorizontalDirection] == '-'){
                        swapElement(board, i,j, i + tmp.indexVerticalDirection, j + tmp.indexHorizontalDirection);
                    }
                }
            }
        }
    }

    private static void nextBoardState(char[][] board, String direction){
        int currentHash = Arrays.deepHashCode(board);
        int previousHash = 0;
        while (currentHash != previousHash){
            shiftBlocksOneRound(board, direction);
            previousHash = currentHash;
            currentHash = Arrays.deepHashCode(board);
        }
    }
}
