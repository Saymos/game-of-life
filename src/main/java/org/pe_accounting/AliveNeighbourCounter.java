package org.pe_accounting;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AliveNeighbourCounter {

	private Cell[][] boardState;
	private int numberOfRows;
	private int numberOfCols;
	private int row;
	private int col;

	public void updateAnc(Cell[][] boardState) {
		this.boardState = boardState;
		numberOfRows = boardState.length -1;
		numberOfCols = boardState[0].length -1;
	}

	public void updateRowCol(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int countAliveNeighbours(Position pos) {
		int numberOfAliveNeighbours = 0;
		Cell[] cells;
		switch (pos) {
		case MIDDLE:
			cells = addNeigboursWhenMiddle();
			break;
		case TOP_ROW:
			cells = addNeighboursWhenTopRow();
			break;
		case BOTTOM_ROW:
			cells = addNeighboursWhenBottomRow();
			break;
		case LEFTMOST_COL:
			cells = AddNeighboursWhenLeftmostCol();
			break;
		case RIGHTMOST_COL:
			cells = AddNeighboursWhenRightmostCol();
			break;
		default:
			cells = AddNeighboursWhenCorner(pos);

		}

		for (Cell cell : cells) {
			if (State.ALIVE == cell.getState())
				numberOfAliveNeighbours +=1;
		}

		return numberOfAliveNeighbours;
	}

	private Cell[] addNeigboursWhenMiddle() {
		Cell[] cells =  {
				boardState[row-1][col-1],
				boardState[row-1][col],
				boardState[row-1][col+1],
				boardState[row][col-1],
				boardState[row][col+1],
				boardState[row+1][col-1],
				boardState[row+1][col],
				boardState[row+1][col+1]
		};
		return cells;
	}

	private Cell[] addNeighboursWhenTopRow() {
		Cell[] cells =  {
				boardState[numberOfRows][col-1],
				boardState[numberOfRows][col],
				boardState[numberOfRows][col+1],
				boardState[row][col-1],
				boardState[row][col+1],
				boardState[row+1][col-1],
				boardState[row+1][col],
				boardState[row+1][col+1]
		};
		return cells;
	}

	private Cell[] addNeighboursWhenBottomRow() {
		Cell[] cells =  {
				boardState[row-1][col-1],
				boardState[row-1][col],
				boardState[row-1][col+1],
				boardState[row][col-1],
				boardState[row][col+1],
				boardState[0][col-1],
				boardState[0][col],
				boardState[0][col+1]
		};
		return cells;
	}

	private Cell[] AddNeighboursWhenLeftmostCol() {
		Cell[] cells =  {
				boardState[row-1][numberOfCols],
				boardState[row-1][col],
				boardState[row-1][col+1],
				boardState[row][numberOfCols],
				boardState[row][col+1],
				boardState[row+1][numberOfCols],
				boardState[row+1][col],
				boardState[row+1][col+1]
		};
		return cells;
	}

	private Cell[] AddNeighboursWhenRightmostCol() {
		Cell[] cells =  {
				boardState[row-1][col-1],
				boardState[row-1][col],
				boardState[row-1][0],
				boardState[row][col-1],
				boardState[row][0],
				boardState[row+1][col-1],
				boardState[row+1][col],
				boardState[row+1][0]
		};
		return cells;
	}

	private Cell[] AddNeighboursWhenCorner(Position pos) {
		Cell[] cells =  new Cell[8];

		if (pos == Position.TOPLEFT_CORNER) {
			cells[0] = boardState[numberOfRows][numberOfCols];
			cells[1] = boardState[numberOfRows][0];
			cells[2] = boardState[numberOfRows][1];
			cells[3] = boardState[0][numberOfCols];
			cells[4] = boardState[0][1];
			cells[5] = boardState[1][numberOfCols];
			cells[6] = boardState[1][0];
			cells[7] = boardState[1][1];

		} else if (pos == Position.TOPRIGHT_CORNER) {
			cells[0] = boardState[numberOfRows][numberOfCols-1];
			cells[1] = boardState[numberOfRows][numberOfCols];
			cells[2] = boardState[numberOfRows][0];
			cells[3] = boardState[0][numberOfCols-1];
			cells[4] = boardState[0][0];
			cells[5] = boardState[1][numberOfCols-1];
			cells[6] = boardState[1][numberOfCols];
			cells[7] = boardState[1][0];

		} else if (pos == Position.BOTTOMLEFT_CORNER) {
			cells[0] = boardState[numberOfRows-1][numberOfCols];
			cells[1] = boardState[numberOfRows-1][0];
			cells[2] = boardState[numberOfRows-1][1];
			cells[3] = boardState[numberOfRows][numberOfCols];
			cells[4] = boardState[numberOfRows][1];
			cells[5] = boardState[0][numberOfCols];
			cells[6] = boardState[0][0];
			cells[7] = boardState[0][1];

		}else if (pos == Position.BOTTOMRIGHT_CORNER) {
			cells[0] = boardState[numberOfRows-1][numberOfCols-1];
			cells[1] = boardState[numberOfRows-1][numberOfCols];
			cells[2] = boardState[numberOfRows-1][0];
			cells[3] = boardState[numberOfRows][numberOfCols-1];
			cells[4] = boardState[numberOfRows][0];
			cells[5] = boardState[0][numberOfCols-1];
			cells[6] = boardState[0][numberOfCols];
			cells[7] = boardState[0][0];
		}
		return cells;
	}
}