package minesweeper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


}