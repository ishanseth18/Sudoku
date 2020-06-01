package sudoku;
import java.util.*;
import java.io.*;

public class Tester {
	public static void main(String[] args) {
		Board.initialize();
		try {
			Scanner scanner = new Scanner(new File("file.txt"));
			for(int row = 0; row < 9; row++) {
				for (int col = 0; col < 9; col++) {
					Board.values[0][row][col] = scanner.nextInt();
					Board.update(row, col);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		// Everything above this point just accepts the board as input
		
		Board.printOptions();
		System.out.println();
		System.out.println();
		System.out.println();
		Solver.solve();
		System.out.println();
		System.out.println();
		System.out.println();
		Board.printOptions();
	}
}
