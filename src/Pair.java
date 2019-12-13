class Pair<X,Y> {
    X first;
    Y second;

    Pair(X first, Y second){
        this.first = first;
        this.second = second;
    }

    boolean equals(Pair pair){
        return ((this.first == pair.first) && (this.second == pair.second));
    }

    @Override
    public String toString(){
        return "(" + first + " , " + second + ")";
    }
}
