package battleship;

public class Conventions{
    final  int SIZE = 11;
    final  String EMPTY_CELL = "~";

    protected String[][] createGrid(String[] alphabet){
        String[][] field= new String[SIZE][SIZE];
        for  (int row = 0; row < field.length; row++) {
            for (int column = 0; column  < field[0].length; column ++) {
                if (row == 0 && column == 0) {
                    field[row][column] =" ";
                }else if (row == 0) {
                    field[row][column] = String.valueOf(column);
                } else if (column == 0) {
                    field[row][column] = alphabet[row-1];
                } else{
                    field[row][column] = EMPTY_CELL;
                }
            }
        }
        return field;
    }

    protected void printGameField(String[][] gameField){

        System.out.println();
        for (String[] seat : gameField) {
            for (int column = 0; column < gameField[0].length; column++) {
                System.out.print(seat[column]);
                if (column != gameField[0].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
