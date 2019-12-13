import java.util.ArrayList;
import java.util.Collections;

class Solver {
    //verticalCoordinate = i index, horizontalCoordinate = j index
    // Each region contains a list of coordinate(pair) -> ArrayList<Pair> => listOfRegions = ArrayList<ArrayList<Pair>>
    private static ArrayList<Region> regionList = new ArrayList<>();
    private static Region region = new Region();
    private static ArrayList<GameState> leafStates = new ArrayList<>();

    private static void getRegionTmp(char[][] board, int verticalCoordinate, int horizontalCoordinate, char old){
        if (verticalCoordinate < 0 || verticalCoordinate >= board.length || horizontalCoordinate < 0 || horizontalCoordinate >= board[0].length){
            return;
        }

        if (board[verticalCoordinate][horizontalCoordinate] != old){
            return;
        }

        if (old == '-'){
            return;
        }

        board[verticalCoordinate][horizontalCoordinate] = '-';

        region.addBlock(verticalCoordinate, horizontalCoordinate);
        getRegionTmp(board, verticalCoordinate, horizontalCoordinate + 1, old);
        getRegionTmp(board, verticalCoordinate - 1, horizontalCoordinate, old);
        getRegionTmp(board, verticalCoordinate, horizontalCoordinate - 1, old);
        getRegionTmp(board, verticalCoordinate + 1, horizontalCoordinate, old);
    }

    private static ArrayList<Pair> initAllRegionCoordinate(char[][] board){
        ArrayList<Pair> tmp = new ArrayList<>();
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] != '-'){ //if empty space then do not init coordinate, as it will cause infinite recursion
                    tmp.add(new Pair(i,j));
                }
            }
        }
        return tmp;
    }

    private static void removeSecondFromFirst(ArrayList<Pair> first, ArrayList<Pair> second){
        for (Pair pair : second) {
            for (int j = 0; j < first.size(); j++) {
                if (pair.equals(first.get(j))) {
                    first.remove(j);
                    break;
                }
            }
        }
    }

    private static void getAllRegions(char[][] board){
        ArrayList<Pair> tmp = initAllRegionCoordinate(board);
        regionList = new ArrayList<>();
        while(!tmp.isEmpty()){
            int first = (int)tmp.get(0).first;
            int second = (int)tmp.get(0).second;
            getRegionTmp(board, first, second, board[first][second]);
            regionList.add(region);
            removeSecondFromFirst(tmp, region.blocks);
            region = new Region();
        }
    }

    private static ArrayList<Pair> getAllPossibleMoves(char[][] board){
        ArrayList<Pair> allPossibleMoves = new ArrayList<>();
        getAllRegions(board);
        for (Region region : regionList) {
            if (region.getSize() != 1){
                Pair move = region.blocks.get(0); //get a single Pair in a region
                allPossibleMoves.add(move);
            }
        }
        return allPossibleMoves;
    }

    //Note: Only root does not have an "origin"
    private static ArrayList<GameState> makeAllPossibleMoves(GameState state){
        char[][] board = state.board;
        char[][] newBoard = Main.copy(board);
        ArrayList<Pair> allPossibleMoves = getAllPossibleMoves(newBoard);
        ArrayList<GameState> nextStates = new ArrayList<>();
        for (Pair move : allPossibleMoves) {
            char[][] resetBoard = Main.copy(board);
            int vertical = (int)move.first;
            int horizontal = (int)move.second;
            GameLogic.onclick(vertical, horizontal, board);
            GameState childGameState = new GameState(board);
            childGameState.possibleMoves = allPossibleMoves;
            childGameState.setOrigin(state, move);
            nextStates.add(childGameState);
            board = resetBoard;
        }
        return nextStates;
    }

    private static void solve(GameState root){
        root.setChildren(makeAllPossibleMoves(root));
        if (root.children.size() == 0){
            leafStates.add(root);
            return;
        }
        for (int i = 0; i < root.children.size(); i++){
            solve(root.children.get(i));
        }
    }

    private static GameState getEndState(GameState root){
        solve(root);
        return findMinimumRemainingBlock(root.boardState);
    }

    private static GameState findMinimumRemainingBlock(char[][] board){
        int min = Integer.MAX_VALUE;
        GameState gameState = new GameState(board);
        for (GameState leafState : leafStates) {
            char[][] tmpBoard = Main.copy(leafState.boardState);
            regionList = new ArrayList<>();
            getAllRegions(tmpBoard);

            if (regionList.size() < min) {
                min = regionList.size();
                gameState = leafState;
            }
        }
        return gameState;
    }

    static void getSolution(GameState root){
        System.out.println("Initial State: ");
        root.printBoardState();
        GameState endState = getEndState(root);
        Backtracking.backtrack(endState, root);
        System.out.println("Game Result: ");
        endState.printBoardState();
        System.out.println("Moves made: ");
        Collections.reverse(Backtracking.moveList);
        System.out.println(Backtracking.moveList);
    }
}

