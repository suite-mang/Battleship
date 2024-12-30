package battleship;

import java.util.ArrayList;
import java.util.Scanner;

class Game{
    final ArrayList<Ship> fleet1 = new ArrayList<>();
    final ArrayList<Ship> fleet2 = new ArrayList<>();

    GameInput gameInput = new GameInput();

    protected String[][] arrangeTheShip(String[][] gameField ,int player) {

        Scanner scanner = new Scanner(System.in);

        Conventions conventions = new Conventions();

        for (ShipType shiptype : ShipType.values()) {
            boolean isError = false;
            System.out.printf("\nEnter the coordinates of the %s (%d cells):%n%n", shiptype.name, shiptype.length);

            while (!isError) {
                String input = scanner.nextLine();
                String[] coordinates = input.split("\\s");
                int startCol = Integer.parseInt(coordinates[0].substring(1));
                int endCol = Integer.parseInt(coordinates[1].substring(1));
                int startRow = Character.getNumericValue(String.valueOf(coordinates[0].charAt(0)).charAt(0)) - 9;
                int endRow = Character.getNumericValue(String.valueOf(coordinates[1].charAt(0)).charAt(0)) - 9;

                CalculatingField calculatingField = new CalculatingField(startRow, startCol, endRow, endCol);
                int length = calculatingField.lengthOfTheShip();
                calculatingField.resetCalculatingField();

                if (!gameInput.isValidCoordinates(startRow, startCol, endRow, endCol)) {
                    System.out.println("\nError! Wrong ship location! Try again:\n");
                    continue;
                }

                if (!gameInput.isValidLength(length, shiptype.length)) {
                    System.out.printf("%nError! Wrong length of the %s! Try again:%n%n", shiptype.name);
                    continue;
                }

                if (!gameInput.isTooCloseToOtherShip(gameField, startRow, endRow, startCol, endCol)) {
                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                    continue;
                }

                gameField= placingShip(gameField, startRow, endRow, startCol, endCol);
                conventions.printGameField(gameField);
                isError = true;
                Ship  newShip = new Ship(startRow, startCol , endRow, endCol, shiptype.name, shiptype.length);

                switch(player){
                    case 1:
                        fleet1.add(newShip);
                        break;
                    case 2:
                        fleet2.add(newShip);
                        break;
                    default:
                        break;
                }
            }
        }
        playerSwitch();
        //scanner.close();
        return gameField;
    }
    protected void battleStart(String[][] player1Field, String[][] player2Field, String[] ALPHABET){

        int player1 = 1;
        int player2 = 2;
        int player = player1;

        Conventions conventions = new Conventions();
        String[][] player1FogWar = conventions.createGrid(ALPHABET);
        String[][] player2FogWar = conventions.createGrid(ALPHABET);

        while (!gameInput.isAllShipsSunk(fleet1) && !gameInput.isAllShipsSunk(fleet2)) {
            switch (player) {
                case 1:
                    conventions.printGameField(player1FogWar);
                    System.out.println("---------------------------------------");
                    conventions.printGameField(player1Field);
                    System.out.println("\nPlayer 1, it's your turn:\n");
                    checkHit(player1FogWar,player2Field, player1);
                    playerSwitch();
                    player = player2;
                    break;

                case 2:
                    conventions.printGameField(player2FogWar);
                    System.out.println("---------------------------------------");
                    conventions.printGameField(player2Field);
                    System.out.println("\nPlayer 2, it's your turn:\n");
                    checkHit(player2FogWar,player1Field, player2);
                    playerSwitch();
                    player = player1;
                    break;

                default:
                    break;
            }

        }


    }
    private void checkHit(String[][] fogWar, String[][] battleField, int player){
        Scanner scanner = new Scanner(System.in);
        String target = scanner.nextLine();
        Ship ship;
        int targetRow = Character.getNumericValue(String.valueOf(target.charAt(0)).charAt(0)) - 9;
        int targetColumn = Integer.parseInt(target.substring(1));
        if(!gameInput.isValidCoordinates(targetRow,targetColumn)){
            System.out.println("\nError! Wrong ship location! Try again:\n");
            

        }else{
            String shot= battleField[targetRow][targetColumn];
            switch (shot){
                case  "O":
                    fogWar[targetRow][targetColumn] = "X";
                    battleField[targetRow][targetColumn] =  "X";
                    if (player == 1){
                        ship = gameInput.identifyShipByLocation(fleet2, targetRow,targetColumn);
                    }else {
                        ship = gameInput.identifyShipByLocation(fleet1, targetRow,targetColumn);
                    }
                    if (ship != null && ship.getShipRemainingLife() != 0) {
                        System.out.println("\nYou hit a ship");
                        break;
                    }
                    if (gameInput.isAllShipsSunk(fleet1) || gameInput.isAllShipsSunk(fleet2)){
                        System.out.println("You sank the last ship. You won. Congratulations!\n");
                        break;
                    }
                    System.out.println("\nYou sank a ship!");
                    break;


                case "~":
                    battleField[targetRow][targetColumn] = "M";
                    fogWar[targetRow][targetColumn] = "M";
                    System.out.println("\nYou missed!");
                    break;
                case "X":
                    System.out.println("\nYou hit a ship");
                    break;

                default:
                    break;
            }
        }

    }
    

    protected String[][] placingShip(String[][] field,
                                     int startRow, int endRow, int startCol, int endCol){
        int minRow = Math.min(startRow,endRow);
        int maxRow = Math.max(startRow,endRow);
        int minCol= Math.min(startCol,endCol);
        int maxCol = Math.max(startCol, endCol);

        for (int row = minRow; row <= maxRow; row++){
            for (int column = minCol; column <= maxCol; column++){
                field[row][column] = "O";
            }
        }
        return field;
    }
    protected void playerSwitch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPress Enter and pass the move to another player");
        scanner.nextLine();

    }

}
