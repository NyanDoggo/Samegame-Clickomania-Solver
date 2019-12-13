class Direction {
    //Check whether current direction is horizontal direction or vertical direction
    private boolean horizontalDirection;
    private boolean verticalDirection;
    //i.e direction to move in 2d array
    int indexHorizontalDirection = 0;
    int indexVerticalDirection = 0;

    Direction(String direction){
        if (direction.equalsIgnoreCase("right")){ //indexVerticalDirection = 0
            this.horizontalDirection = true;
            this.indexHorizontalDirection = 1;
        }

        if (direction.equalsIgnoreCase("left")){
            this.horizontalDirection = true;
            this.indexHorizontalDirection = -1;
        }

        if (direction.equalsIgnoreCase("down")){
            this.verticalDirection = true;
            this.indexVerticalDirection = 1;
        }

        if (direction.equalsIgnoreCase("up")){
            this.verticalDirection = true;
            this.indexVerticalDirection = -1;
        }
    }

    private boolean isInBoundVertical(int verticalCoordinate, int maxLengthVertical){
        return ((verticalCoordinate + indexVerticalDirection < maxLengthVertical) && (verticalCoordinate + indexVerticalDirection >= 0));

    }

    private boolean isInBoundHorizontal(int horizontalCoordinate, int maxLengthHorizontal){
        return ((horizontalCoordinate + indexHorizontalDirection < maxLengthHorizontal) && (horizontalCoordinate + indexHorizontalDirection >= 0));
    }

    boolean checkInBound(int currentVerticalCoordinate, int currentHorizontalCoordinate, int maxLengthVertical, int maxLengthHorizontal){
        if (this.horizontalDirection) return isInBoundHorizontal(currentHorizontalCoordinate, maxLengthHorizontal);
        if (this.verticalDirection) return isInBoundVertical(currentVerticalCoordinate, maxLengthVertical);
        else{
            System.out.println("ERROR DIRECTION");
            return false;
        }
    }
}
