package sudoku;

public class ChangedBoard {
	Board board;
	boolean progress = false;
	
	ChangedBoard(boolean progress, Board board) {
		this.board = board;
		this.progress = progress;
	}
}
