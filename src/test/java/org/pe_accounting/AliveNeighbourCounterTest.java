package org.pe_accounting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AliveNeighbourCounterTest {

	private AliveNeighbourCounter anc;
	
	@BeforeEach
	public void setup() {
		anc = new AliveNeighbourCounter();
	}

	@Test
	public void givenBoardState4x3_whenUpdateAnc_thenReturnCorrectNumberOfRowsAndCols() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Cell[][] boardState = new Cell[4][3];
		anc.updateAnc(boardState);
		Field rowField = anc.getClass().getDeclaredField("numberOfRows");
		Field colField = anc.getClass().getDeclaredField("numberOfCols");
		rowField.setAccessible(true);
		colField.setAccessible(true);
		
		int actualNumberOfRows = rowField.getInt(anc);
		int actualnumberOfCols = colField.getInt(anc);

		int expectedNumberOfRows = boardState.length -1;
		int expectedNumberOfCols = boardState[0].length -1;
		
		assertEquals(expectedNumberOfRows, actualNumberOfRows);
		assertEquals(expectedNumberOfCols, actualnumberOfCols);
	}

	@Test 
	public void givenRow3Col3_whenUpdateRowCol_thenReturnCorrectRowAndCol() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		anc.updateRowCol(4,3);
		Field rowField = anc.getClass().getDeclaredField("row");
		Field colField = anc.getClass().getDeclaredField("col");
		rowField.setAccessible(true);
		colField.setAccessible(true);
		
		int actualnumberOfRows = rowField.getInt(anc);
		int actualnumberOfCols = colField.getInt(anc);

		int expectedNumberOfRows = 4;
		int expectedNumberOfCols = 3;
		
		assertEquals(expectedNumberOfRows, actualnumberOfRows);
		assertEquals(expectedNumberOfCols, actualnumberOfCols);
	}

	@Test
	public void givenMidPosAnd1LivingNeighbour_whenCountAliveNeighBours_thenReturn1() {
		//Setup a dead board
		Cell[][] boardState = setupDeadBoard(3,3);

		//Add one living neighbour
		boardState[0][0].setState(State.ALIVE);
		int expectedCount = 1; 

		//Update with boardState
		anc.updateAnc(boardState);
		//Set current position to a mid position with 1 living neighbour
		anc.updateRowCol(1, 1);

		int actualCount = anc.countAliveNeighbours(Position.MIDDLE);

		assertEquals(expectedCount, actualCount);
	}

	@Test
	public void givenTopPosAnd1LivingNeighbour_whenCountingAliveNeighbours_thenReturn1() {
		//Setup a dead board
		Cell[][] boardState = setupDeadBoard(3,3);

		//Add one living neighbour
		boardState[0][0].setState(State.ALIVE);
		int expectedCount = 1; 

		//Update with boardState
		anc.updateAnc(boardState);
		//Set current position to a top row position with 1 living neighbour
		anc.updateRowCol(0, 1);

		int actualCount = anc.countAliveNeighbours(Position.TOP_ROW);

		assertEquals(expectedCount, actualCount);
	}

	@Test
	public void givenBottomPosAnd1LivingNeighbour_whenCountingAliveNeighbours_thenReturn1() {
		//Setup a dead board
		Cell[][] boardState = setupDeadBoard(3,3);

		//Add one living neighbour
		boardState[0][0].setState(State.ALIVE);
		int expectedCount = 1; 

		//Update with boardState
		anc.updateAnc(boardState);
		//Set current position to a bottom row position with 1 living neighbour
		anc.updateRowCol(2, 1);

		int actualCount = anc.countAliveNeighbours(Position.BOTTOM_ROW);

		assertEquals(expectedCount, actualCount);
	}

	@Test
	public void givenLeftmostPosAnd1LivingNeighbour_whenCountingAliveNeighbours_thenReturn1() {
		//Setup a dead board
		Cell[][] boardState =setupDeadBoard(3,3);

		//Add one living neighbour
		boardState[0][0].setState(State.ALIVE);
		int expectedCount = 1; 

		//Update with boardState
		anc.updateAnc(boardState);
		//Set current position to a leftmost col position with 1 living neighbour
		anc.updateRowCol(1, 0);

		int actualCount = anc.countAliveNeighbours(Position.LEFTMOST_COL);

		assertEquals(expectedCount, actualCount);
	}

	@Test
	public void givenRightmostPosAnd1LivingNeighbour_whenCountingAliveNeighbours_thenReturn1() {
		//Setup a dead board
		Cell[][] boardState = setupDeadBoard(3,3);

		//Add one living neighbour
		boardState[0][0].setState(State.ALIVE);
		int expectedCount = 1; 

		//Update with boardState
		anc.updateAnc(boardState);
		//Set current position to a rightmost col position with 1 living neighbour
		anc.updateRowCol(1, 2);

		int actualCount = anc.countAliveNeighbours(Position.RIGHTMOST_COL);

		assertEquals(expectedCount, actualCount);
	}

	@Test
	public void givenTopleftCornerPosAnd1LivingNeighbour_whenCountingAliveNeighbours_thenReturn1() {
		//Setup a dead board
		Cell[][] boardState = setupDeadBoard(3,3);

		//Add one living neighbour
		boardState[1][0].setState(State.ALIVE);
		int expectedCount = 1; 

		//Update with boardState
		anc.updateAnc(boardState);
		//Set current position to a top left corner position with 1 living neighbour
		anc.updateRowCol(0, 0);

		int actualCount = anc.countAliveNeighbours(Position.TOPLEFT_CORNER);

		assertEquals(expectedCount, actualCount);
	}

	@Test
	public void givenToprightCornerPosAnd1LivingNeighbour_whenCountingAliveNeighbours_thenReturn1() {
		//Setup a dead board
		Cell[][] boardState = setupDeadBoard(3,3);

		//Add one living neighbour
		boardState[0][1].setState(State.ALIVE);
		int expectedCount = 1; 

		//Update with boardState
		anc.updateAnc(boardState);
		//Set current position to a top right corner position with 1 living neighbour
		anc.updateRowCol(0, 2);

		int actualCount = anc.countAliveNeighbours(Position.TOPRIGHT_CORNER);

		assertEquals(expectedCount, actualCount);
	}

	@Test
	public void givenBottomrightCornerPosAnd1LivingNeighbour_whenCountingAliveNeighbours_thenReturn1() {
		//Setup a dead board
		Cell[][] boardState = setupDeadBoard(3,3);

		//Add one living neighbour
		boardState[1][2].setState(State.ALIVE);
		int expectedCount = 1; 

		//Update with boardState
		anc.updateAnc(boardState);
		//Set current position to a bottom right corner position with 1 living neighbour
		anc.updateRowCol(2, 2);

		int actualCount = anc.countAliveNeighbours(Position.BOTTOMRIGHT_CORNER);

		assertEquals(expectedCount, actualCount);
	}

	@Test
	public void givenBottomleftCornerPosAnd1LivingNeighbour_whenCountingAliveNeighbours_thenReturn1() {
		//Setup a dead board
		Cell[][] boardState = setupDeadBoard(3,3);

		//Add one living neighbour
		boardState[1][1].setState(State.ALIVE);
		int expectedCount = 1; 

		//Update with boardState
		anc.updateAnc(boardState);
		//Set current position to a bottom left corner position with 1 living neighbour
		anc.updateRowCol(0, 2);

		int actualCount = anc.countAliveNeighbours(Position.BOTTOMLEFT_CORNER);

		assertEquals(expectedCount, actualCount);
	}
	
	private Cell[][] setupDeadBoard(int numberOfRows, int numberOfCols) {
		Cell[][] boardState = new Cell[numberOfRows][numberOfCols];
		for (int row = 0; row < boardState.length; row++) {
			for (int col = 0; col < boardState[row].length; col++) {
				boardState[row][col] = new Cell(State.DEAD);
			}
		}
		return boardState;
	}
}
