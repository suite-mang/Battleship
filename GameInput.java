package battleship;

import java.util.ArrayList;
import java.util.Objects;

public class GameInput{

    protected boolean isValidCoordinates(int startRow, int startCol,
                                         int  endRow, int endCol) {
        boolean result= false;
           if ( startCol > 0  && endCol > 0 && startCol <= 10 && endCol <= 10 ) {
               if (startRow == endRow && startCol != endCol){
                   result = true;
               }else if (startRow != endRow  && startCol == endCol){
                   result = true;
               }
           }
        return result;
    }

    protected boolean isValidCoordinates(int row, int column){

        return row < 11 && column < 11;
    }

    protected boolean isValidLength(int userLength, int length){
        return  userLength == length;

    }

    protected boolean isTooCloseToOtherShip(String[][] gameField,
                                            int startRow,int endRow,
                                            int startCol, int endCol ){
        int minRow = Math.min(startRow,endRow);
        int maxRow = Math.max(startRow,endRow);
        int minCol= Math.min(startCol,endCol);
        int maxCol = Math.max(startCol, endCol);
        for (int row = minRow - 1; row <= maxRow + 1; row++){
            for (int column = minCol -1; column <= maxCol + 1; column++){
                if( isValidCoordinates(row ,column)
                        && Objects.equals(gameField[row][column], "O")){
                    return  false;
                }
            }
        }
        return true;

    }
    public boolean isAllShipsSunk(ArrayList<Ship> fleet) {
        for (Ship ship : fleet) {
            if (ship.getShipRemainingLife() > 0) {
                return false;
            }
        }
        return true;
    }
    Ship identifyShipByLocation(ArrayList<Ship> fleet, int row, int column) {
        boolean isRow = false;
        boolean isColumn = false;
        for (Ship ship : fleet) {
            if(ship.getRowMinimum() == ship.getRowMaximum() && ship.getRowMinimum() == row ){
                isRow = row >= ship.getRowMinimum() && row <= ship.getRowMaximum();
                isColumn = column >= ship.getColMinimum() && column <= ship.getColMaximum();

            }else if(ship.getColMinimum()==  ship.getColMaximum()&& ship.getColMaximum()== column ){
                isRow = row >= ship.getRowMinimum() && row <= ship.getRowMaximum();
                isColumn = column >= ship.getColMinimum() && column <= ship.getColMaximum();

            }
            if (isRow && isColumn) {
                ship.setShipRemainingLife(ship.getShipRemainingLife() - 1);
                return ship;
            }
        }
        return null;
    }
}
