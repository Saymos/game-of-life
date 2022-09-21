package org.pe_accounting;

import javax.swing.JFrame;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GameOfLife
{

	private static final int ROWS = 80; //Number of rows
	private static final int COLS = 80; //Number of cols
	private static final int SQUARE_SIZE = 15; //Size of squares
	private static final int UPDATE_FREQUENCY = 300; //In milliseconds
	private static final int INITIAL_LIFE = 15; //Approximate  percentage of living cells at the start

	public static void main(String args[]){ 
		Window window = new Window(ROWS, COLS, SQUARE_SIZE, UPDATE_FREQUENCY);
		window.initialSetup(ROWS, COLS, INITIAL_LIFE);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setTitle("Game of Life");

		frame.add(window);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		window.startGameThread();
	}
}
