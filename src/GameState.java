import java.util.ArrayList;

class GameState {
    char[][] board;
    char[][] boardState;
    ArrayList<GameState> children;
    ArrayList<Pair> possibleMoves;
    private Pair origin; //consists of parent and which move from parent leads to this state. Pair<State, Move>

    GameState(char[][] board){
        this.board = board;
        boardState = Main.copy(board);
    }

    void setChildren(ArrayList<GameState> children) {
        this.children = children;
    }

    //put printBoardState method into another "board util class"
    void printBoardState(){
        Main.printGrid(this.boardState);
    }

    void printChildren(){
        for (GameState child : this.children) {
            child.printBoardState();
            System.out.println();
        }
    }

    void setOrigin(GameState parent, Pair move){
        this.origin = new Pair(parent, move);
    }

    Pair getOrigin(){
        return this.origin;
    }

}
