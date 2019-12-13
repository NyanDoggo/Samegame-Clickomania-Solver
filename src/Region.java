import java.util.ArrayList;

public class Region {
    /**
    A single cell in a 2d grid is a block (1x1 Region is a block)
    I.e:  Region of blocks, pentominoes, Tetris-like shapes... etc
    Block consists of coordinate
    since this problems "blocks" consists only of coordinates and a color
    (color is abstracted, i.e doesn't matter which color is it, only that its consists of different colors, therefore different regions)
    Note: As of current implementation, a "Region" maybe disjoint, that means a region of blocks could consists of different blocks
    that are not adjacent. (I.E not strongly connected)
    TODO: Implement checkAdjacency.
    */

    ArrayList<Pair> blocks;

    public Region (ArrayList<Pair> region){
        this.blocks = region;
    }

    Region(){
        this.blocks = new ArrayList<Pair>();
    }

    void addBlock(int verticalCoordinate, int horizontalCoordinate){
        this.blocks.add(new Pair(verticalCoordinate,horizontalCoordinate));
    }

    void printRegion(){
        System.out.println(this.blocks);
    }

    int getSize(){
        return this.blocks.size();
    }

}
