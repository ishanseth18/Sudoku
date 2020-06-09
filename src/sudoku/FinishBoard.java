package sudoku;

public class FinishBoard {
	public static Board temp(Board initial)  {
		BoardOptions stack = makeStack(initial);
		
		while(!stack.isEmpty()) {
			Board current = stack.pop();
			current = Solver.solve(current);
			
			if(!current.isComplete() && current.isValid()) {
				current = temp(current);
			}
			else if(current.isComplete()) {
				return current;
			}
			else if (!current.isValid()) {
				continue;
			}
			
		}

		return initial;
	}
	
	public static BoardOptions makeStack(Board initial)  {
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
				}
				// if the options are less than the current min, record the coordinates and update the min 
			}
		}
		
		
		BoardOptions stack = new BoardOptions();
		for(int i = 1; i < 10; i++) {
			if(initial.values[i][rowMarker][colMarker] != 0) {
				Board copy = new Board();
				copy = copy.copyBoard(initial);
				copy.values[0][rowMarker][colMarker] = initial.values[i][rowMarker][colMarker];
				copy.update(rowMarker, colMarker);
				stack.push(copy);
			}
		}
		return stack;
	}
}
