package battleship;

public class CalculatingField {
    int startRow;
    int startCol;
    int endRow;
    int endCol;

    CalculatingField(int startRow, int startCol, int endRow, int endCol){
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
    }

    public int getStartRow(){
        return startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndCol() {
        return endCol;
    }

    protected int lengthOfTheShip(){

        return Math.abs(getEndCol() - getStartCol()) + Math.abs (getStartRow()- getEndRow())  + 1;
    }


    // rest after using them
    protected void resetCalculatingField() {
        this.startRow = 0;
        this.startCol = 0;
        this.endRow = 0;
        this.endCol = 0;
    }


}
