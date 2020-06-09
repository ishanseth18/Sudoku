package sudoku;

public class BoardOptions {
	Board board;
	BoardOptions next;
	
	BoardOptions() {
		this.next = null;
	}
	
	public boolean isEmpty() {
		if (this.next == null) {
			return true;
		}
		return false;
	}
	
	public void push (Board op) {
		BoardOptions ptr = this;
		while(ptr.next != null) {
			ptr = ptr.next;
		}
		BoardOptions add = new BoardOptions();
		add.board = op;
		ptr.next = add;
		return;
	}
	
	public Board pop () {
		BoardOptions ptr = this;
		while(ptr.next != null) {
			ptr = ptr.next;
		}
		Board current = ptr.board;
		ptr = null;
		return current;
	}
	
	public void print () {
		int ctr = 0;
		BoardOptions ptr = this;
		while(ptr.next != null) {
			ptr = ptr.next;
			ctr ++;
			System.out.println("this is option #" + ctr + ":");
			System.out.println();
			ptr.board.printBoard();
		}
		return;
	}

}
