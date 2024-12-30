package battleship;

public class Ship{
    private final int rowMinimum;
    private final int rowMaximum;
    private final int colMinimum;
    private final int colMaximum;
    private final String shipType;
    private int shipRemainingLife;

    public Ship(int row1, int col1, int row2, int col2, String shipType, int shipRemainingLife) {
        this.rowMinimum = Math.min(row1, row2);
        this.rowMaximum = Math.max(row1, row2);
        this.colMinimum = Math.min(col1, col2);
        this.colMaximum = Math.max(col1, col2);
        this.shipType = shipType;
        this.shipRemainingLife = shipRemainingLife;
    }
    public int getRowMinimum() {
        return this.rowMinimum;
    }

    public int getRowMaximum() {
        return this.rowMaximum;
    }

    public int getColMinimum() {
        return this.colMinimum;
    }

    public int getColMaximum() {
        return this.colMaximum;
    }

    public int getShipRemainingLife() {
        return this.shipRemainingLife;
    }

    public void setShipRemainingLife(int shipRemainingLife) {
        this.shipRemainingLife = shipRemainingLife;
    }


}

