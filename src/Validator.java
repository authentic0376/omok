

public class Validator {
	private Board board;
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public boolean stoneInRange(int x, int y) {
		return 0 <= x && x < board.getSize() && 0 <= y && y < board.getSize();
	}

	public boolean stoneDuplicated(int x, int y) {
		return board.getStoneAt(x, y) == '●' || board.getStoneAt(x, y) == '○';
	}
}
