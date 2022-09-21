package org.pe_accounting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
public class Window extends JPanel implements Runnable {

	private final int squareSize;
	private final int updateFrequency;
	@Getter
	private Cell[][] boardState;
	private Thread gameThread;
	private Update update;

	public Window (int rows, int cols, int squareSize, int updateFrequency) {
		this.squareSize = squareSize;
		this.updateFrequency = updateFrequency;
		this.setPreferredSize(new Dimension(rows * squareSize, cols * squareSize));
		this.setDoubleBuffered(true);
		this.setBackground(Color.lightGray);
	}

	public void initialSetup(int rows, int cols, int initialLife) {
		this.boardState = setupRandomisedBoard(rows, cols, initialLife);
		this.update = new Update();
	}

	public Cell[][] setupStaticDeadBoard(int rows, int cols) {
		Cell[][] boardState = new Cell[rows][cols];
		for (int row = 0; row < boardState.length; row++) {
			for (int col = 0; col < boardState[row].length; col++) {
				boardState[row][col] = new Cell(State.DEAD);
			}
		}
		return boardState;	}

	public Cell[][] setupRandomisedBoard(int rows, int cols,int livingPercentage) {
		Cell[][] boardState = new Cell[rows][cols];
		Random random = new Random();
		for (int row = 0; row < boardState.length; row++) {
			for (int col = 0; col < boardState[row].length; col++) {
				boardState[row][col] =random.nextInt((100)+1)>=livingPercentage ? new Cell(State.DEAD) : new Cell(State.ALIVE);
			}
		}
		return boardState;
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {

		while(gameThread != null) {
			try {
				Thread.sleep(updateFrequency);
				update();
				repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void update() {
		boardState = update.updateBoardState(this);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		drawBoard(g2);
	}

	private void drawBoard(Graphics2D g2) {
		//Draw a graphic representation of the board state
		for (int row = 0; row < boardState.length; row++) {
			for (int col = 0; col < boardState[row].length; col++) {
				//Paint blue squares for living cells
				if(boardState[row][col].getState() == State.ALIVE) {
					g2.setColor(Color.blue);
					g2.fillRoundRect(calcDrawPosY(row), calcDrawPosX(col), squareSize, squareSize, 15, 15);
				}
				else {
					//Paint white squares for dead cells
					g2.setColor(Color.white); 
					g2.fillRoundRect(calcDrawPosY(row), calcDrawPosX(col), squareSize, squareSize, 15, 15);
				}
			}
		}
	}

	private int calcDrawPosY(int row) {
		return (row + 1) * squareSize - squareSize; 
	}

	private int calcDrawPosX(int col) {
		return (col + 1) * squareSize - squareSize;
	}

}
