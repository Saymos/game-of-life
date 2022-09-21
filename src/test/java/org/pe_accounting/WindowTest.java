package org.pe_accounting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.Test;

class WindowTest {

	private Window window;

	@Test
	void whenSetupStaticDeadBoard_thenReturnBoardOfCorrectSize() {
		window = new Window(10, 10, anyInt(), anyInt());
		Cell[][] expectedCellArraySize = new Cell[10][10];

		Cell[][] actualCellArraySize = window.setupStaticDeadBoard(10, 10);

		assertEquals(expectedCellArraySize[0].length, actualCellArraySize[0].length);
		assertEquals(expectedCellArraySize[1].length, actualCellArraySize[1].length);
	}

	@Test
	void whenSetupStaticDeadBoard_thenAssertAllCellsAreDead() {
		window = new Window(10, 10, anyInt(), anyInt());
		Cell[][] boardState = window.setupStaticDeadBoard(10, 10);
		int actualAliveCounter = 0;
		for (int row = 0; row < boardState[0].length; row++) {
			for (int col = 0; col < boardState[1].length; col++) {
				if (boardState[row][col].getState() == State.ALIVE)
					actualAliveCounter += 1;
			}
		}
		assertEquals(0, actualAliveCounter);
	}

	@Test
	void whenSetupRandomisedBoard_thenReturnBoardOfCorrectSize() {
		window = new Window(10, 10, anyInt(), anyInt());
		Cell[][] expectedCellArraySize = new Cell[10][10];

		Cell[][] actualCellArraySize = window.setupRandomisedBoard(10, 10, anyInt());

		assertEquals(expectedCellArraySize[0].length, actualCellArraySize[0].length);
		assertEquals(expectedCellArraySize[1].length, actualCellArraySize[1].length);
	}

	@Test
	void givenInitialLife0_whenSetupRandomisedBoard_thenAssertAtleast95CellsAreDead() {
		window = new Window(10, 10, anyInt(), 0);
		Cell[][] boardState = window.setupRandomisedBoard(10, 10, 0);
		int actualAliveCounter = 0;
		for (int row = 0; row < boardState[0].length; row++) {
			for (int col = 0; col < boardState[1].length; col++) {
				if (boardState[row][col].getState() == State.ALIVE)
					actualAliveCounter += 1;
			}
		}
		assertTrue(5 > actualAliveCounter);
	}

	@Test
	void givenInitialLife100_whenSetupRandomisedBoard_thenAssertAtleast95CellsAreAlive() {
		window = new Window(10, 10, anyInt(), anyInt());
		Cell[][] boardState = window.setupRandomisedBoard(10, 10, 100);
		int actualAliveCounter = 0;
		for (int row = 0; row < boardState[0].length; row++) {
			for (int col = 0; col < boardState[1].length; col++) {
				if (boardState[row][col].getState() == State.ALIVE)
					actualAliveCounter += 1;
			}
		}
		assertTrue(95 < actualAliveCounter);
	}

	@Test void givenInitialLife25On100SquareBoard_whenSetupRandomisedBoard_thenAssert15To35CellsAreAlive() {
		window = new Window(10, 10, anyInt(), anyInt());
		Cell[][] boardState = window.setupRandomisedBoard(10, 10, 25);
		int actualAliveCounter = 0;
		for (int row = 0; row < boardState[0].length; row++) {
			for (int col = 0; col < boardState[1].length; col++) {
				if (boardState[row][col].getState() == State.ALIVE)
					actualAliveCounter += 1;
			}
		}
		assertTrue(15 < actualAliveCounter && actualAliveCounter < 35);
	}
}
