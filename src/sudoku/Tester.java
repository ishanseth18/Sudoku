package sudoku;
import java.util.*;
import java.io.*;

public class Tester {
	public static void main(String[] args) {
		Board start = new Board ();
		start.initialize();
		try {
			Scanner scanner = new Scanner(new File("file.txt"));
			for(int row = 0; row < 9; row++) {
				for (int col = 0; col < 9; col++) {
					start.values[0][row][col] = scanner.nextInt();
					start.update(row, col);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		// Everything above this point just accepts the board as input
		
		start.printBoard();
		System.out.println();
		System.out.println();
		System.out.println();
		start = Solver.solve(start);
		System.out.println();
		System.out.println();
		System.out.println();
		//start.printOptions();
		start = FinishBoard.temp(start);
		System.out.println("--------------------");
	}
}
