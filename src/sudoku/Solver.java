package sudoku;

public class Solver {
	public static Board solve(Board start) {
		for(int i = 1; i <= 9; i++) {
			ChangedBoard updated;
			
			updated = rowCheck(i, start);
			start = updated.board;
			if(updated.progress) {
				solve(start);
			}
			
			updated = colCheck(i, start);
			start = updated.board;
			if(updated.progress) {
				solve(start);
			}
			
			updated = boxCheck(i, start);
			start = updated.board;
			if(updated.progress) {
				solve(start);
			}
			
			updated = oneOp(i, start);
			start = updated.board;
			if(updated.progress) {
				solve(start);
			}
			
			updated = optionCheck(i, start);
			start = updated.board;
			if(updated.progress) {
				solve(start);
			}
		}
		return start;
	}

	public static ChangedBoard rowCheck(int op, Board start) {
		boolean changed = false;
		for(int row = 0; row < 9; row++) {
			int ctr = 0;
			int mrow = 0;
			int mcol = 0;
			
			for(int col = 0; col < 9; col++) {
				if(start.values[op][row][col] != 0) {
					ctr++;
					mcol = col;
					mrow = row;
				}
			}
			
			if (ctr == 1) {
				start.values[0][mrow][mcol] = start.values[op][mrow][mcol];
				System.out.print("rowCheck: ");
				System.out.println("insert " + start.values[op][mrow][mcol] + " @ " + mrow + "," + mcol);
				start.update(mrow, mcol);
				changed = true;
			}
		}
		ChangedBoard updated = new ChangedBoard(changed, start);
		return updated;
	}

	public static ChangedBoard colCheck(int op, Board start) {
		boolean changed = false;
		
		for(int col = 0; col < 9; col++) {
			int ctr = 0;
			int mrow = 0;
			int mcol = 0;
			
			for(int row = 0; row < 9; row++) {
				if(start.values[op][row][col] != 0) {
					ctr++;
					mcol = col;
					mrow = row;
				}
			}
			
			if (ctr == 1) {
				start.values[0][mrow][mcol] = start.values[op][mrow][mcol];
				System.out.print("colCheck: ");
				System.out.println("insert " + start.values[op][mrow][mcol] + " @ " + mrow + "," + mcol);
				start.update(mrow, mcol);
				changed = true;
			}
		}
		ChangedBoard updated = new ChangedBoard(changed, start);
		return updated;
	}

	public static ChangedBoard boxCheck(int op, Board start) {
		boolean changed = false;
		// set to false so it can change to true if change is made
		
		int mrow = 0;
		int mcol = 0;
		// initialize the markers for where the change is made
		
		for(int boxr = 0; boxr < 3; boxr++){
			for(int boxc = 0; boxc < 3; boxc++){
				// above should cycle through each box
				
				int ctr = 0;
				// set ctr for how many non zeros are encountered
				
				for(int tiler = 0; tiler < 3; tiler++) {
					for(int tilec = 0; tilec < 3; tilec++){
						// within a box, this should iterate through each tile
						
						int row = 3 * boxr + tiler;
						int col = 3 * boxc + tilec;
						// above should calculate the row and col position
						// given the box ur in and the tile ur lookin at in the box
						
						if(start.values[op][row][col] != 0) {
							ctr++;
							mrow = row;
							mcol = col;
						}
					}
				}
				if (ctr == 1) {
					start.values[0][mrow][mcol] = start.values[op][mrow][mcol];
					System.out.print("boxCheck: ");
					System.out.println("insert " + start.values[op][mrow][mcol] + " @ " + mrow + "," + mcol);
					start.update(mrow, mcol);
					changed = true;
				}
			}
		}
		ChangedBoard updated = new ChangedBoard(changed, start);
		return updated;
	}

	public static ChangedBoard oneOp(int num, Board start) {
		int row = num - 1;
		boolean change = false;
		// set to false, if any change occurs change = true.
		
		for(int col = 0; col < 9; col++) {
			int ctr = 0;
			int mop = 0;
			int mcol = 0;
			// checks one box at a time in a given row. m -> marker.
			
			for(int op = 1; op <= 9; op++) {
				if(start.values[op][row][col] != 0) {
					ctr++;
					mop = op;
					mcol = col;
				}
			}
			// for each box, all options are iterated through
			// # of non zeros recorded, mop = num, mcol = location on board
			// only matters if ctr = 1. 
			
			if(ctr == 1) {
				start.values[0][row][mcol] = start.values[mop][row][mcol];
				// pulls the option to the actual board
				
				System.out.print("oneOp: ");
				System.out.println("insert " + start.values[mop][row][mcol] + " @ " + row + "," + mcol);
				
				start.update(row, mcol);
				// updates the board
				
				change =  true;
				// changes value to true for solver method
			}
		}
		ChangedBoard updated = new ChangedBoard(change, start);
		return updated;

	}

	public static ChangedBoard optionCheck(int op, Board start) {
		boolean changed = false; // change in the options
		boolean found = false; // found a non zero value in that row
		
		int mrow = 0; // marker for the row thats being edited
		int rowCtr = 0; // counter for the rows where non zeros occur
		int mboxc = 0; // marker for where not to change options
		
		int mcol = 0; // marker for the column thats being edited
		int colCtr = 0; // counter for the columns where non zeros occur
		int mboxr = 0; // marker for where not to change options
		
		for(int boxr = 0; boxr < 3; boxr++){
			for(int boxc = 0; boxc < 3; boxc++){
				// above should cycle through each box
				
				rowCtr = 0;
				colCtr = 0;
				// counts rows and columns PER BOX that non-zeros are found in
				
				
				// BELOW SOLVES FOR ROWS
				for(int tiler = 0; tiler < 3; tiler++) {
					
					found = false;
					// reset found for each row visited
					
					for(int tilec = 0; tilec < 3; tilec++){
						// within a box, this should iterate through each row 
						
						int row = 3 * boxr + tiler;
						int col = 3 * boxc + tilec;
						// should give exact location of tile in the board
						
						if(start.values[op][row][col] != 0) {
							found =  true;
							mrow = row;
							mboxc = boxc;
							break;
						}
					}
					if(found) {
						rowCtr++;
					}
				}
				
				
				// BELOW SOLVES FOR COLUMNS
				for(int tilec = 0; tilec < 3; tilec++) {
					
					found = false;
					// reset found to not double count
					
					for(int tiler = 0; tiler < 3; tiler++){
						// within a box, this should iterate through each row 
						
						int row = 3 * boxr + tiler;
						int col = 3 * boxc + tilec;
						// should give exact location of tile in the board
						
						if(start.values[op][row][col] != 0) {
							found =  true;
							mcol = col; // remember the column you are in
							mboxr = boxr; // remember the box you don't want to change
							break;
						}
					}
					if(found) {
						colCtr++;
					}
				}
				
				
				
				if(rowCtr == 1) { // means that option was only found in one row
					for(int i = 0; i < 9; i++) { // iterates through that row
						
						if(i >= mboxc * 3 && i < (mboxc * 3) + 3) {
							continue;
						}
						if(start.values[op][mrow][i] == 0) {
							continue;
						}
						System.out.println("option for " + op + " deleted @ " + mrow +", " + i);
						start.values[op][mrow][i] = 0;
						changed = true;
					}
				}
	
				if(colCtr == 1) {
					for(int i = 0; i < 9; i++) {
						if(i >= mboxr * 3 && i < (mboxr * 3) + 3) {
							continue;
						}
						if(start.values[op][i][mcol] == 0) {
							continue;
						}
						System.out.println("option for " + op + " deleted @ " + i +", " + mcol);
						start.values[op][i][mcol] = 0;
						changed = true;
					}
				}
			}
		}
		ChangedBoard updated = new ChangedBoard(changed, start);
		return updated;
	}
}
