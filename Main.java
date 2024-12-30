package battleship;

public class Main{

    public static void main(String[] args) {

        final String[] ALPHABET = {"A","B","C","D","E","F","G","H","I","J"};

        Conventions conventions= new Conventions();
        Game game = new Game();

        System.out.println("Player 1, place your ships on the game field\n");
        String[][] gameField1 = conventions.createGrid(ALPHABET );
        conventions.printGameField(gameField1);
        String[][] player1Field = game.arrangeTheShip(gameField1,1);

        System.out.println("Player 2, place your ships on the game field\n");
        String[][] gameField2 = conventions.createGrid(ALPHABET );
        conventions.printGameField(gameField2);
        String[][] player2Field = game.arrangeTheShip(gameField2,2);

        game.battleStart(player1Field, player2Field,ALPHABET);

    }

}

