package sudoku;

public class Board {
	static int[][][] values = new int[10][9][9];
	// [options][row#][col#]
	// [depth][down][right]
	
	public static void printOptions() { //will print the board and all the options
		for(int op = 0; op < 10; op++) {
			System.out.println();
			System.out.println();
			if (op == 0) {
				System.out.println("THIS IS THE ACTUAL BOARD");
			}
			for(int row = 0; row < 9; row++) {
				for(int col = 0; col < 9; col++) {
					if (values[op][row][col] == 0) {
						System.out.print("-");
					}
					else {
						System.out.print(values[op][row][col]);
					}
					if ((col + 1)%3 == 0) {
						System.out.print(" | ");
					}
					else{
						System.out.print("  ");
					}
				}
				System.out.println();
				if((row + 1)%3 == 0) {
					System.out.println("______________________________");
				}
			}
		}
	}
	
	public static void printBoard() {
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				if(values[0][row][col] == 0) {
					System.out.print("-");
				}
				else {
					System.out.print(values[0][row][col]);
				}
				if ((col + 1)%3 == 0) {
					System.out.print(" | ");
				}
				else{
					System.out.print("  ");
				}
			}
			System.out.println();
			if((row + 1)%3 == 0) {
				System.out.println("______________________________");
			}
		}
	}
	
	public static void update(int row, int col) {
		int num = values[0][row][col];
		// num is the value at this positon on the actual board
		
		if(num == 0) return;
		else {
			for(int op = 1; op < 10; op++) {
				values[op][row][col] = 0;
			}
		}
		// if num is 0 it will mess up actual board
		
		int boxr = (row/3) * 3;
		int boxc = (col/3) * 3;
		for(int i = boxr; i < boxr + 3; i++) {
			for(int j = boxc; j < boxc + 3; j++) {
				values[num][i][j] = 0;
			}
		}
		// updates the 3x3 box
		
		for(int i = 0; i < 9; i++) {
			values[num][i][col] = 0;
		}
		// updates values in a column
		
		for(int j = 0; j < 9; j++) {
			values[num][row][j] = 0;
		}
		// updates the values in a row
		
	}
	
	public static void initialize () {
		for(int op = 1; op < 10; op++) {
			for(int row = 0; row < 9; row++) {
				for(int col = 0; col < 9; col++) {
					values[op][row][col] = op;
				}
			}
		}
	}
}
