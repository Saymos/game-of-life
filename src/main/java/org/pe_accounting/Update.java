package org.pe_accounting;

public class Update {

	private  Cell[][] boardState;
	private final AliveNeighbourCounter anc;

	public Update() {
		this.anc = new AliveNeighbourCounter();
	}

	public Cell[][] updateBoardState(Window window) {
		this.boardState = window.getBoardState();
		int rows = boardState.length;
		int cols = boardState[0].length;
		//Setup temporary board to save new state
		anc.updateAnc(boardState);
		Cell[][] tempBoardState = window.setupStaticDeadBoard(rows, cols);
		//Loop through board
		for (int row = 0; row < boardState.length; row++) {
			for (int col = 0; col < boardState[row].length; col++) {
				anc.updateRowCol(row, col);
				//Count living neighbours of this cell
				Position position = findPosOnBoard(row, col);
				int aliveNeighbours = anc.countAliveNeighbours(position);

				//Update state of cell
				State state = updateState(boardState[row][col], aliveNeighbours);
				tempBoardState[row][col].setState(state);
			}

		}
		return tempBoardState;
	}

	private Position findPosOnBoard(int row, int col) {
		int rows = boardState.length -1;
		int cols = boardState[0].length -1;
		Position pos = null;

		//Top row
		if(row == 0) 
			//Topleft corner
			if(col == 0)
				pos = Position.TOPLEFT_CORNER;
		//Topright corner
			else if (col == cols)
				pos = Position.TOPRIGHT_CORNER;
			else
				pos = Position.TOP_ROW;
		//Leftmost col
		else if (col == 0)
			//Bottomleft corner
			if (row == rows)
				pos = Position.BOTTOMLEFT_CORNER;
			else
				//Leftmost col
				pos = Position.LEFTMOST_COL;
		//Rightmost col
		else if (col == cols)
			//Bottomright corner
			if (row == rows)
				pos = Position.BOTTOMRIGHT_CORNER;
			else 
				//Rightmost col
				pos = Position.RIGHTMOST_COL;
		else if (row == rows)
			pos = Position.BOTTOM_ROW;
		else 
			//The rest 
			pos = Position.MIDDLE;

		return pos;
	}

	private State updateState(Cell cell, int aliveNeighbours) {
		State state = State.DEAD;
		if (cell.getState() == State.ALIVE) {
			if(aliveNeighbours == 2 || aliveNeighbours == 3) {
				state = State.ALIVE;
			}
		}
		else if(aliveNeighbours == 3)
			state = State.ALIVE;
		return state;
	}
}