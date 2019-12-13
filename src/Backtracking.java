import java.util.ArrayList;

class Backtracking {
    static ArrayList<Pair> moveList = new ArrayList<>();

    static void backtrack(GameState endState, GameState root){
        if (endState.hashCode() != root.hashCode()){
            moveList.add((Pair) endState.getOrigin().second);
            Pair origin = endState.getOrigin();
            backtrack((GameState) origin.first, root);
        }
    }
}
