package minesweeper;

import java.util.Random;

public class Minesweeper {

    private static int BOUNDS = 10;
    private static int nMINES = 10;
    private boolean gameStatus = Boolean.TRUE;
    private boolean hasWon = Boolean.FALSE;

    private CellState[][]cellStates = new CellState[BOUNDS][BOUNDS];
    private int[][]cellValues = new int[BOUNDS][BOUNDS];

    public Minesweeper()
    {
        for (int i = 0; i < BOUNDS; i++)
            for (int j = 0; j < BOUNDS; j++)
                cellStates[i][j] = CellState.UNEXPOSED;
        createMines();
    }

    public void expose(int row, int column) {
        CellState currentState = cellStates[row][column];

        if (isMine(row, column)){
            gameStatus = Boolean.FALSE;
        }
        else if (currentState == CellState.UNEXPOSED) {
            cellStates[row][column] = CellState.EXPOSED;
            exposeNeighbors(row, column);
        }
    }

    public void exposeNeighbors(int row, int column){
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (isValidNeighbor(row, column))
                    expose(row + i, column + j);
    }

    public Boolean isMine(int row, int column) {
        return (cellValues[row][column] == -1);
    }

    public void unsealCell(int row, int column){
        cellStates[row][column] = CellState.UNEXPOSED;
    }

    public void exposeCell (int row, int column) {
        cellStates[row][column] = CellState.EXPOSED;

    }

    public void sealCell (int row, int column) {
        cellStates[row][column] = CellState.SEALED;
    }

    public int createMines() {
        Random rand = new Random();
        int tMines = 0;

        while (tMines != nMINES)
        {
            int randROW = rand.nextInt(BOUNDS);
            int randCOL = rand.nextInt(BOUNDS);

            if (cellValues[randROW][randCOL] == 0) {
                cellValues[randROW][randCOL] = -1;
                incrementAdjCells(randROW, randCOL);
                tMines++;
            }
        }
        return tMines;
    }

    public void incrementAdjCells(int row, int col) {
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (isValidBounds(row + i, col + j))
                    cellValues[row + i][col + j]++;
    }

    public Boolean isValidBounds(int row, int col) {
        return (-1 < row && BOUNDS > row) && (-1 < col && BOUNDS > col) && (cellValues[row][col] != -1);
    }
    public Boolean isValidNeighbor(int row, int col) {
        return isValidBounds(row, col) && cellValues[row][col] == 0;
    }

    public int getMineTotal(){
        return nMINES;
    }

    public CellState getCellState (int row, int column) {
        return cellStates[row][column];
    }
}
