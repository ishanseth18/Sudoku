package sudoku;

public class BoardOptions {
	Board board;
	BoardOptions next;
	BoardOptions head = new BoardOptions(null); //first node with a board
	
	BoardOptions(Board board) {
		this.board = board;
		this.next = null;
	}
	
	public boolean isEmpty() {
		if (head.next == null) {
			return true;
		}
		return false;
	}
	
	public void enqueue (Board op) {
		BoardOptions ptr = this.head;
		while(ptr.next != null) {
			ptr = ptr.next;
		}
		BoardOptions add = new BoardOptions(op);
		ptr.next = add;
		return;
	}
	
	public Board dequeue () {
		BoardOptions ptr = this.head;
		while(ptr.next != null) {
			ptr = ptr.next;
		}
		Board current = ptr.board;
		ptr = null;
		return current;
	}
	
}
