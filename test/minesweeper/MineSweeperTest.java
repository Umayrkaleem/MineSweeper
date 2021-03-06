package minesweeper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MineSweeperTest {
    private Minesweeper minesweeper;

    @BeforeEach
    void init() {
        minesweeper = new Minesweeper();
    }

    @Test
    void canary(){
        assert(true);
    }

    @Test
    void exposeUnexposedCell() {
        minesweeper.exposeCell(0, 1);
        assertEquals(CellState.EXPOSED, minesweeper.getCellState(0, 1));
    }

    @Test
    void exposeAnExposedCell() {
        minesweeper.exposeCell(0, 1);
        assertEquals(CellState.EXPOSED, minesweeper.getCellState(0, 1));
    }

    @Test
    void sealAnUnsealedCell() {
        minesweeper.sealCell(0, 1);
        assertEquals(CellState.SEALED, minesweeper.getCellState(0, 1));
    }

    @Test
    void unsealASealedCell() {
        minesweeper.unsealCell(0, 1);
        assertEquals(CellState.UNEXPOSED, minesweeper.getCellState(0, 1));
    }

    @Test
    void initialCellStateIsUnExposed() {
        assertEquals(CellState.UNEXPOSED, minesweeper.getCellState(2, 3));
    }

    @Test
    void checkCorrectNumberOfMinesAreCreated() {
        assertEquals(minesweeper.getMineTotal(), minesweeper.createMines());
    }

    @Test
    void outOfRangeWhenCheckingAdjCellForMines(){
        assertEquals(Boolean.FALSE, minesweeper.isValidBounds(-1, 7));
        assertEquals(Boolean.FALSE, minesweeper.isValidBounds(7, -1));
        assertEquals(Boolean.FALSE, minesweeper.isValidBounds(11, 7));
        assertEquals(Boolean.FALSE, minesweeper.isValidBounds(-11, -11));
        assertEquals(Boolean.FALSE, minesweeper.isValidBounds(7, -11));
        assertEquals(Boolean.FALSE, minesweeper.isValidBounds(-11, 7));
        assertEquals(Boolean.TRUE, minesweeper.isValidBounds(1, 3));
    }

    @Test
    void exposeCellOutOfRange() {
        assertAll(

                () -> assertThrows(IndexOutOfBoundsException.class,
                        () -> minesweeper.exposeCell(-1, 2)),
                () -> assertThrows(IndexOutOfBoundsException.class,
                        () -> minesweeper.exposeCell(10, 2)),
                () -> assertThrows(IndexOutOfBoundsException.class,
                        () -> minesweeper.exposeCell(1, -2)),
                () -> assertThrows(IndexOutOfBoundsException.class,
                        () -> minesweeper.exposeCell(2, 12))
        );
    }


}