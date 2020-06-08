package sudoku;

public class FinishBoard {
	public static void temp(Board initial) throws CloneNotSupportedException {
		BoardOptions stack = makeStack(initial);
		//stack.print();
		return;
	}
	
	public static BoardOptions makeStack(Board initial) throws CloneNotSupportedException {
		int ctr = 0;
		int min = 10;
		int rowMarker = 0;
		int colMarker = 0;
	
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				// iterate through each coordinate
				
				ctr = 0; //reset ctr for each new coordinate
				if(initial.values[0][row][col] == 0) {
					for(int op = 1; op < 10; op++) {
						if(initial.values[op][row][col] != 0) {
							ctr++;
						}
					}
				}
				// if there is no value in a spot, count the options available for that spot
				
				if (ctr <= min && ctr != 0) {
					rowMarker = row;
					colMarker = col;
					min = ctr;
					System.out.println(min);
				}
				// if the options are less than the current min, record the coordinates and update the min 
			}
		}
		BoardOptions stack = new BoardOptions();
		for(int i = 1; i < 10; i++) {
			if(initial.values[i][rowMarker][colMarker] != 0) {
				Board copy = (Board) initial.clone();
				copy.values[0][rowMarker][colMarker] = initial.values[i][rowMarker][colMarker];
				copy.printBoard();
				System.out.println();
				System.out.println();
				initial.printBoard();
				copy.update(rowMarker, colMarker);
				stack.push(copy);
			}
		}
		return stack;
	}
}
