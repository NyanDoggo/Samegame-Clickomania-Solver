import java.util.ArrayList;

public class Main {
    //verticalCoordinate = i index, horizontalCoordinate = j index
    private static ArrayList<String> boardTmp = new ArrayList<>();

    static void printGrid(char[][] grid){
        for (char[] chars : grid) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    private static char[][] convertStringArrayToCharGrid(ArrayList<String> stringArray){
        char[][] result = new char[stringArray.size()][stringArray.get(0).length()];
        for (int i = 0; i < stringArray.size(); i++){
            char[] tmp = stringArray.get(i).toCharArray();
            result[i] = tmp;
        }
        return result;
    }

    static char[][] copy(char[][] board){
        char[][] newBoard = new char[board.length][board[0].length];
        for (int i = 0; i < board.length; i++){
            System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
        }
        return newBoard;
    }

    //TODO GameLogic floodfill and Solver.getregiontmp is the same

    private static void printAllNode(GameState root){
        root.printBoardState();
        System.out.println();
        if (root.children.size() == 0){
            return;
        }
        for (int i = 0; i < root.children.size(); i++){
            printAllNode(root.children.get(i));
        }
    }

    public static void main(String[] args) {

//        boardTmp.add("AABB");
//        boardTmp.add("AABB");

//        boardTmp.add("BAA");
//        boardTmp.add("BDD");

//        boardTmp.add("AA-");
//        boardTmp.add("DD-");

//        boardTmp.add("BAA");
//        boardTmp.add("BDC");

        boardTmp.add("BRBB");
        boardTmp.add("RRYY");
        boardTmp.add("YRBY");
        boardTmp.add("BRRY");
        boardTmp.add("YRRB");

        char[][] board = Main.convertStringArrayToCharGrid(Main.boardTmp);

        GameState root = new GameState(board);

        Solver.getSolution(root);


//        int maxVal = Integer.MAX_VALUE/3 - 13281970;
    }
}
